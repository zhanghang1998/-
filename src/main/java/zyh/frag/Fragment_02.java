package zyh.frag;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zyh.activity.R;
import zyh.adapter.SearchGoodsAdapter;
import zyh.adapter.ShopCarAdapter;
import zyh.base.BaseContract;
import zyh.base.BaseFragment;
import zyh.bean.ShopCarBean;
import zyh.presenter.SearchQueryPresenter;
import zyh.util.SpacingItemDecoration;

public class Fragment_02 extends BaseFragment {

    private RecyclerView shopCarRecyclerView;
    private CheckBox shopCarBoxAll;
    private TextView shopCarTextAllprice;
    private TextView shopCarTextGoBuy;
    private SearchQueryPresenter searchQueryPresenter;
    private ShopCarAdapter shopCarAdapter;

    @Override
    protected boolean setIsRealTimeRefresh() {
        return false;
    }

    @Override
    protected void initData() {
        //调用p层
        searchQueryPresenter = new SearchQueryPresenter(new initData());
        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        shopCarRecyclerView.setLayoutManager(layoutManager);
        //recyclerview条目应对屏幕适配
        int space = getResources().getDimensionPixelSize(R.dimen.dp_10);
        shopCarRecyclerView.addItemDecoration(new SpacingItemDecoration(space));
        //适配器
        shopCarAdapter = new ShopCarAdapter(getContext());
        shopCarRecyclerView.setAdapter(shopCarAdapter);
        //请求数据
        searchQueryPresenter.requestShopCar();

        //点击商家
        shopCarAdapter.setOnIitmClick(new ShopCarAdapter.OnIitmClick() {
            @Override
            public void initComeID(List<ShopCarBean.DataBean> listes) {



            }
        });

        //点击商品
        shopCarAdapter.setOnIitmClickes(new ShopCarAdapter.OnIitmClickes() {
            @Override
            public void initComeID(List<ShopCarBean.DataBean.ListBean> listes) {

                //在这里重新遍历已经改变状态后的数据
                //这里不能break跳出，因为还有需要计算后面点击商品的价格和数量，所以必须跑完整个循环
                double totalPrice = 0;
                //勾选商品的数量，不是该商品购买的数量
                int num = 0;
                //商家勾选的数量
                int numes = 0;
                //所有商品总数，和上面的数量做比对，如果两者相等，则说明全选
                int totalNum = 0;
                for (int i = 0; i < listes.size(); i++) {
                    totalNum = totalNum + listes.get(i).getNum();
                    if (listes.get(i).isFlagItem()) {
                        totalPrice = totalPrice + listes.get(i).getPrice() * listes.get(i).getNum();
                        num = num + listes.get(i).getNum();
                    }
                }
                if (num < totalNum) {
                    shopCarBoxAll.setChecked(false);
                } else {
                    shopCarBoxAll.setChecked(true);
                }
                shopCarTextAllprice.setText("" + totalPrice);//总的计算价格
                shopCarTextGoBuy.setText("去结算(" + num + ")");//去结算按钮

            }
        });

        //全选 , 全不选
        shopCarBoxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean allChecked = shopCarBoxAll.isChecked();

                double totalPrice=0;
                int num=0;

                for (int i=0;i<dataBeanList.size();i++){
                    //遍历商品，改变状态
                    dataBeanList.get(i).setFlags(allChecked);
                    List<ShopCarBean.DataBean.ListBean> listBeanList = dataBeanList.get(i).getList();
                    for (int j = 0; j < listBeanList.size(); j++) {

                        listBeanList.get(j).setFlagItem(allChecked);

                        totalPrice = totalPrice + (listBeanList.get(j).getPrice() * listBeanList.get(j).getNum());
                        num=num+listBeanList.get(j).getNum();
                    }
                }

                if (allChecked){
                    shopCarTextAllprice.setText(""+totalPrice);
                    shopCarTextGoBuy.setText("去结算("+num+")");
                }else{
                    shopCarTextAllprice.setText("0");
                    shopCarTextGoBuy.setText("去结算");
                }
                shopCarAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void initView() {
        //对View中控件的操作方法
        shopCarRecyclerView = mRootView.findViewById(R.id.shopCar_recyclerView);
        shopCarTextGoBuy = mRootView.findViewById(R.id.shopCar_text_goBuy);
        shopCarBoxAll = mRootView.findViewById(R.id.shopCar_box_all);
        shopCarTextAllprice = mRootView.findViewById(R.id.shopCar_text_allprice);

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.frag02_shopcar;
    }

    private List<ShopCarBean.DataBean> dataBeanList;

    private class initData implements BaseContract.BaseView<ShopCarBean> {

        @Override
        public void onSuccess(ShopCarBean data) {

            if (data.getData().size() != 0) {
                dataBeanList = data.getData();
                shopCarAdapter.addAll(dataBeanList);
                shopCarAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(Throwable throwable) {

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        searchQueryPresenter.onBind();
    }
}
