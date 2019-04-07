package zyh.frag;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import zyh.activity.CommitActivity;
import zyh.activity.R;
import zyh.adapter.SearchGoodsAdapter;
import zyh.base.BaseContract;
import zyh.base.BaseFragment;
import zyh.bean.Result;
import zyh.bean.SearchGoodsBean;
import zyh.presenter.SearchQueryPresenter;
import zyh.util.SpacingItemDecoration;

public class Fragment_01 extends BaseFragment {

    EditText searcheGoodsDitText;
    TextView searchGoodsText;
    XRecyclerView searchGoodsXRecyclerView;
    private SearchQueryPresenter searchQueryPresenter;
    private List<SearchGoodsBean> searchGoodsBeans;
    private SearchGoodsAdapter searchGoodsAdapter;
    private LinearLayout searcheGoodsMeiyou;

    @Override
    protected boolean setIsRealTimeRefresh() {
        return false;
    }

    @Override
    protected void initData() {
        //调用p层
        searchQueryPresenter = new SearchQueryPresenter(new initData());
        //布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        searchGoodsXRecyclerView.setLayoutManager(gridLayoutManager);
        //recyclerview条目应对屏幕适配
        int space = getResources().getDimensionPixelSize(R.dimen.dp_10);
        searchGoodsXRecyclerView.addItemDecoration(new SpacingItemDecoration(space));
        //允许上下拉
        searchGoodsXRecyclerView.setPullRefreshEnabled(true);
        searchGoodsXRecyclerView.setLoadingMoreEnabled(true);
        //适配器
        searchGoodsAdapter = new SearchGoodsAdapter(getContext());
        searchGoodsXRecyclerView.setAdapter(searchGoodsAdapter);
        //点击搜索
        searchGoodsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框信息
                String keyNmae = searcheGoodsDitText.getText().toString().trim();
                Log.d("ZYH", keyNmae + "");
                searchQueryPresenter.requestData(keyNmae, true);
            }
        });//点击搜索
        //上下拉刷新加载监听
        searchGoodsXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (searchQueryPresenter.isRunning()) {
                    searchGoodsXRecyclerView.refreshComplete();
                }
                //获取输入框信息
                String keyNmae = searcheGoodsDitText.getText().toString().trim();
                searchGoodsAdapter.clearAll();
                searchQueryPresenter.requestData(keyNmae, true);
            }

            @Override
            public void onLoadMore() {
                if (searchQueryPresenter.isRunning()) {
                    searchGoodsXRecyclerView.loadMoreComplete();
                }
                //获取输入框信息
                String keyNmae = searcheGoodsDitText.getText().toString().trim();
                searchQueryPresenter.requestData(keyNmae, false);
            }
        });//上下拉刷新加载监听

        //条目点击展示详情
        searchGoodsAdapter.setOnIitmClick(new SearchGoodsAdapter.OnIitmClick() {
            @Override
            public void initComeID(int cid) {
                Intent intent = new Intent(getActivity(), CommitActivity.class);
                Toast.makeText(getActivity(), cid + "", Toast.LENGTH_SHORT).show();
                intent.putExtra("comID", cid);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        //对View中控件的操作方法
        searcheGoodsDitText = mRootView.findViewById(R.id.searche_goods_ditText);
        searcheGoodsMeiyou = mRootView.findViewById(R.id.searche_goods_meiyou);

        searchGoodsText = mRootView.findViewById(R.id.search_goods_text);
        searchGoodsXRecyclerView = mRootView.findViewById(R.id.search_goods_xRecyclerView);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.frag01_search;
    }

    private class initData implements BaseContract.BaseView<Result<List<SearchGoodsBean>>> {

        @Override
        public void onSuccess(Result<List<SearchGoodsBean>> data) {

            if (data.getResult().equals("")) {

                Toast.makeText(getActivity(), "没有搜索到数据!",Toast.LENGTH_SHORT).show();
                searcheGoodsMeiyou.setVisibility(View.VISIBLE);//INVISIBLE
                searchGoodsXRecyclerView.setVisibility(View.GONE);


            } else {
                searchGoodsXRecyclerView.setVisibility(View.VISIBLE);
                searcheGoodsMeiyou.setVisibility(View.GONE);
                searchGoodsXRecyclerView.refreshComplete();
                searchGoodsXRecyclerView.loadMoreComplete();
                if (data.getStatus().equals("0000")) {
                    searchGoodsBeans = data.getResult();
                    searchGoodsAdapter.addAll(searchGoodsBeans);
                    searchGoodsAdapter.notifyDataSetChanged();
                }
            }


        }

        @Override
        public void onError(Throwable throwable) {
            Log.d("ZYH", throwable.toString() + "");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchQueryPresenter.onBind();
    }
}
