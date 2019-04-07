package zyh.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import zyh.activity.R;
import zyh.bean.ShopCarBean;
import zyh.util.MyUtilView;

//购物车列表数据适配器adapter
public class ShopCarItemAdapter extends RecyclerView.Adapter<ShopCarItemAdapter.MyHoader> {

    List<ShopCarBean.DataBean.ListBean> list = new ArrayList<>();
    private Context context;

    public ShopCarItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ShopCarItemAdapter.MyHoader onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.fragment_shopcar_xrecyclerview, null);
        return new MyHoader(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCarItemAdapter.MyHoader myHoader, final int i) {

        final ShopCarBean.DataBean.ListBean comListBean = list.get(i);

        if (!(comListBean.getTitle().equals(""))) {
            myHoader.text_title.setText(comListBean.getSubhead()+"");
            myHoader.text_price.setText("￥" + comListBean.getPrice());
            //图片加载
            myHoader.sDV_image.setImageURI(Uri.parse(comListBean.getImages()));
            myHoader.checked.setChecked(comListBean.isFlagItem());
            //改变复选框按钮
            myHoader.checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    comListBean.setFlagItem(isChecked);
                    if (onIitmClicks!=null) {
                        onIitmClicks.initComeID(list);
                    }
                }
            });
            //加减器赋值
            myHoader.addSub.setCount(comListBean.getNum());
            //加减器设置接口回调
            myHoader.addSub.setAddSubListener(new MyUtilView.AddSubListener() {
                @Override
                public void addSub(int count) {
                    if (onIitmClicks!=null) {
                        onIitmClicks.initComeID(list);
                    }
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<ShopCarBean.DataBean.ListBean> comListBeans) {
        if (comListBeans != null) {
            list.addAll(comListBeans);
            notifyDataSetChanged();
        }
    }

    public void clearAll() {
        list.clear();
    }

    public class MyHoader extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sDV_image;
        private final TextView text_price;
        private final TextView text_title;
        private final MyUtilView addSub;
        private final CheckBox checked;

        public MyHoader(@NonNull View itemView) {
            super(itemView);

            sDV_image = itemView.findViewById(R.id.shopCar_item_img);
            text_price = itemView.findViewById(R.id.shopCar_item_price);
            text_title = itemView.findViewById(R.id.shopCar_item_title);
            addSub = itemView.findViewById(R.id.shopCar_item_add_sub_layout);
            checked = itemView.findViewById(R.id.shopCar_item_check_single);

        }
    }

    private OnIitmClicks onIitmClicks;
    public interface OnIitmClicks {
        void initComeID(List<ShopCarBean.DataBean.ListBean> listes);
    }
    public void setOnIitmClicks(OnIitmClicks onIitmClicks) {
        this.onIitmClicks = onIitmClicks;
    }

}
