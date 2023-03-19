package com.example.newstart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newstart.views.SlideMenuGroup;

public class ViewDragActivity extends BaseActivity {

    private SlideMenuGroup mSlideMenuGroup;
    private   AppCompatTextView mTv;

    @Override
    protected int getResId() {
        return R.layout.activity_view_drag;
    }

    @Override
    public void initView() {
        super.initView();
        mSlideMenuGroup = findViewById(R.id.root);

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_content,null,false);
        ViewGroup.LayoutParams mainParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        View menuView = LayoutInflater.from(mContext).inflate(R.layout.layout_menu,null,false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(500, ViewGroup.LayoutParams.WRAP_CONTENT);

        mSlideMenuGroup.setView(view,mainParams,menuView,params);

        mTv= view.findViewById(R.id.name);

        menuView.findViewById(R.id.tv_apple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMainText("苹果");
            }
        });

        menuView.findViewById(R.id.tv_banana).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMainText("香蕉");
            }
        });

        menuView.findViewById(R.id.tv_orange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMainText("橘子");
            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    private void changeMainText(String name) {
        mTv.setText(name);
        mSlideMenuGroup.closeMenu();
    }
}