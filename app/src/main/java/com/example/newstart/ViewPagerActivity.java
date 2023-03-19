package com.example.newstart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newstart.adapters.MyFragmentPagerAdapter;
import com.example.newstart.adapters.MyPagerAdapter;
import com.example.newstart.fragments.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends BaseActivity {

    private ViewPager mViewPager;

    @Override
    protected int getResId() {
        return R.layout.activity_view_pager;
    }

    @Override
    public void initView() {
        super.initView();
        mViewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void initData() {
        super.initData();
//        List<View> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            TextView textView = new TextView(mContext);
//            textView.setTextSize(30);
//            textView.setGravity(Gravity.CENTER);
//            textView.setTextColor(Color.BLACK);
//            textView.setText("TextView"+i);
//            list.add(textView);
//        }

        List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TestFragment fragment = new TestFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title","TestFragment "+ i);
            fragment.setArguments(bundle);
            list.add(fragment);
        }

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,list);
        mViewPager.setAdapter(adapter);
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, ViewPagerActivity.class));
    }
}