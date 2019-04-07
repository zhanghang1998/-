package zyh.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zyh.frag.Fragment_01;
import zyh.frag.Fragment_02;
import zyh.frag.Fragment_03;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.radioButton01)
    RadioButton radioButton01;
    @BindView(R.id.radioButton02)
    RadioButton radioButton02;
    @BindView(R.id.radioButton03)
    RadioButton radioButton03;
    @BindView(R.id.radioGroup_main)
    RadioGroup radioGroupMain;
    @BindView(R.id.viewPager_main)
    ViewPager viewPagerMain;
    private List<Fragment> listFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listFrag = new ArrayList<>();
        listFrag.add(new Fragment_01());
        listFrag.add(new Fragment_02());
        listFrag.add(new Fragment_03());

        viewPagerMain.setAdapter(new MyViewpagerAdapter(getSupportFragmentManager(), listFrag));

        radioGroupMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton01:
                        viewPagerMain.setCurrentItem(0);
                        break;
                    case R.id.radioButton02:
                        viewPagerMain.setCurrentItem(1);
                        break;
                    case R.id.radioButton03:
                        viewPagerMain.setCurrentItem(2);
                        break;
                }
            }
        });

    }



    private class MyViewpagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;

        public MyViewpagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
