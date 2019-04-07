package zyh.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import zyh.activity.R;
import zyh.bean.SearchGoodsBean;

//首页商品数据适配器adapter
public class SearchGoodsAdapter extends RecyclerView.Adapter<SearchGoodsAdapter.MyHoader> {

    List<SearchGoodsBean> list = new ArrayList<>();
    private Context context;

    public SearchGoodsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHoader onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.activity_search_goods_xrecyclerview, null);
        return new MyHoader(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHoader myHoader, int i) {

        final SearchGoodsBean comListBean = list.get(i);
        myHoader.text_count.setText(comListBean.getCommodityName()+"");
        myHoader.text_price.setText("￥"+comListBean.getPrice());
        myHoader.text_num.setText(""+comListBean.getSaleNum());
        //图片加载
        myHoader.sDV_image.setImageURI(Uri.parse(comListBean.getMasterPic()));
        //设置条目点击
        myHoader.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIitmClick.initComeID(comListBean.getCommodityId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<SearchGoodsBean> comListBeans) {
        if (comListBeans != null) {
            list.addAll(comListBeans);
        }
    }

    public void clearAll() {
        list.clear();
    }

    public class MyHoader extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sDV_image;
        private final TextView text_price;
        private final TextView text_count;
        private final TextView text_num;

        public MyHoader(@NonNull View itemView) {
            super(itemView);

            sDV_image = itemView.findViewById(R.id.search_goods_image);
            text_price = itemView.findViewById(R.id.search_goods_price);
            text_count = itemView.findViewById(R.id.search_goods_name);
            text_num = itemView.findViewById(R.id.search_goods_num);

        }
    }

    private OnIitmClick onIitmClick;

    public interface OnIitmClick{
        void initComeID(int cid);
    }

    public void setOnIitmClick(OnIitmClick onIitmClick) {
        this.onIitmClick = onIitmClick;
    }
}
