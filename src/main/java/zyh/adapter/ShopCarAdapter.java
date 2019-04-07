package zyh.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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

//购物车列表数据适配器adapter
public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.MyHoader> {

    List<ShopCarBean.DataBean> list = new ArrayList<>();
    private Context context;

    public ShopCarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ShopCarAdapter.MyHoader onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.fragment_shopcar_item, null);
        return new MyHoader(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCarAdapter.MyHoader myHoader, final int i) {

        final ShopCarBean.DataBean comListBean = list.get(i);

        myHoader.text_name.setText(comListBean.getSellerName()+ "");
        myHoader.checked.setChecked(comListBean.isFlags());
        //改变复选框按钮
        myHoader.checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                comListBean.setFlags(isChecked);
                if (onIitmClick!=null) {
                    onIitmClick.initComeID(list);
                }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        myHoader.recyclerviews.setLayoutManager(layoutManager);
        ShopCarItemAdapter shopCarItemAdapter = new ShopCarItemAdapter(context);
        myHoader.recyclerviews.setAdapter(shopCarItemAdapter);
        shopCarItemAdapter.addAll(comListBean.getList());
        shopCarItemAdapter.setOnIitmClicks(new ShopCarItemAdapter.OnIitmClicks() {
            @Override
            public void initComeID(List<ShopCarBean.DataBean.ListBean> listes) {
                if (onIitmClickes!=null) {
                    onIitmClickes.initComeID(listes);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<ShopCarBean.DataBean> comListBeans) {
        if (comListBeans != null) {
            list.addAll(comListBeans);
        }
    }

    public void clearAll() {
        list.clear();
    }

    public class MyHoader extends RecyclerView.ViewHolder {

        private final TextView text_name;
        private final CheckBox checked;
        private final RecyclerView recyclerviews;

        public MyHoader(@NonNull View itemView) {
            super(itemView);

            text_name = itemView.findViewById(R.id.frag03_shop_shopcar_name);
            checked = itemView.findViewById(R.id.frag03_shop_shopcar_checkBox);
            recyclerviews = itemView.findViewById(R.id.recyclerView_shopcar);
        }
    }

    private OnIitmClick onIitmClick;
    public interface OnIitmClick {
        void initComeID(List<ShopCarBean.DataBean> listes);
    }
    public void setOnIitmClick(OnIitmClick onIitmClick) {
        this.onIitmClick = onIitmClick;
    }

    private OnIitmClickes onIitmClickes;
    public interface OnIitmClickes {
        void initComeID(List<ShopCarBean.DataBean.ListBean> listes);
    }
    public void setOnIitmClickes(OnIitmClickes onIitmClickes) {
        this.onIitmClickes = onIitmClickes;
    }

}
