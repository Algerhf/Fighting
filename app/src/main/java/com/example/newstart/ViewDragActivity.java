package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.newstart.databinding.ActivityViewDragBinding;

public class ViewDragActivity extends BaseActivity {
    private ActivityViewDragBinding mBinding;
    private TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityViewDragBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_content, null, false);
        ViewGroup.LayoutParams mainParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        View menuView = LayoutInflater.from(mContext).inflate(R.layout.layout_menu, null, false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(500, ViewGroup.LayoutParams.WRAP_CONTENT);

        mBinding.vgSlide.setView(view, mainParams, menuView, params);

        mTv = view.findViewById(R.id.name);

        menuView.findViewById(R.id.tv_apple).setOnClickListener(v -> changeMainText("苹果"));
        menuView.findViewById(R.id.tv_banana).setOnClickListener(v -> changeMainText("香蕉"));
        menuView.findViewById(R.id.tv_orange).setOnClickListener(v -> changeMainText("橘子"));
    }

    private void changeMainText(String name) {
        if (null != mTv) {
            mTv.setText(name);
        }
        mBinding.vgSlide.closeMenu();
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, ViewDragActivity.class));
    }
}