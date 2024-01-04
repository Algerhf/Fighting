package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.newstart.beans.PieBean;
import com.example.newstart.databinding.ActivityPieBinding;

import java.util.ArrayList;
import java.util.List;

public class PieActivity extends BaseActivity {
    private ActivityPieBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityPieBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initData();
    }

    private void initData() {
        List<PieBean> list = new ArrayList<>();
        list.add(new PieBean(Color.RED,30));
        list.add(new PieBean(Color.GREEN,60));
        list.add(new PieBean(Color.BLUE,50));
        list.add(new PieBean(Color.BLACK,120));
        list.add(new PieBean(Color.YELLOW,100));
        mBinding.pieView.setData(list);
        mBinding.pieView.performAnimation(1000);
    }

    public static void actionStart(Context context){
        context.startActivity(new Intent(context,PieActivity.class));
    }
}