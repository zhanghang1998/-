package zyh.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import zyh.activity.R;

public class MyUtilView extends LinearLayout implements View.OnClickListener {

    private TextView mAddBtn,mSubBtn;
    private TextView mNumText;
    private AddSubListener addSubListener;

    public MyUtilView(Context context) {
        super(context);
        initView();
    }

    public MyUtilView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyUtilView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int width = r-l;//getWidth();
        int height = b-t;//getHeight();

    }

    private void initView(){
        //加载layout布局，第三个参数ViewGroup一定写成this
        View view = View.inflate(getContext(),R.layout.fragment_shopcar_button_view,this);

        mAddBtn = view.findViewById(R.id.frag03_shop_view_btn_add);
        mSubBtn = view.findViewById(R.id.frag03_shop_view_btn_sub);
        mNumText = view.findViewById(R.id.frag03_shop_view_text_number);
        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int number = Integer.parseInt(mNumText.getText().toString());

        switch (v.getId()){
            case R.id.frag03_shop_view_btn_add:
                number++;
                mNumText.setText(number+"");
                break;
            case R.id.frag03_shop_view_btn_sub:
                if (number==0){
                    Toast.makeText(getContext(),"商品数量不能小于0",Toast.LENGTH_LONG).show();
                    return;
                }
                number--;
                mNumText.setText(number+"");
                break;
        }
        if (addSubListener!=null){
            addSubListener.addSub(number);
        }
    }

    public void setCount(int count) {
        mNumText.setText(count+"");
    }

    public void setAddSubListener(AddSubListener addSubListener) {
        this.addSubListener = addSubListener;
    }

    public interface AddSubListener{
        void addSub(int count);
    }

}
