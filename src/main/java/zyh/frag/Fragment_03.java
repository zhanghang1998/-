package zyh.frag;

import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import zyh.activity.R;
import zyh.base.BaseFragment;

public class Fragment_03 extends BaseFragment {

    @Override
    protected boolean setIsRealTimeRefresh() {
        return false;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //对View中控件的操作方法

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.frag03_my;
    }
}
