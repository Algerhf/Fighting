package com.example.newstart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.newstart.beans.PieBean;
import com.example.newstart.views.PieView;

import java.util.ArrayList;
import java.util.List;

public class PieActivity extends BaseActivity {

    private PieView mPieView;

    @Override
    protected int getResId() {
        return R.layout.activity_pie;
    }

    @Override
    public void initView() {
        super.initView();
        mPieView = findViewById(R.id.pie_view);
    }

    @Override
    public void initData() {
        super.initData();
        List<PieBean> list = new ArrayList<>();
        list.add(new PieBean(Color.RED,30));
        list.add(new PieBean(Color.GREEN,60));
        list.add(new PieBean(Color.BLUE,50));
        list.add(new PieBean(Color.BLACK,120));
        list.add(new PieBean(Color.YELLOW,100));
        mPieView.setData(list);
        mPieView.performAnimation(1000);
    }

    public static void actionStart(Context context){
        context.startActivity(new Intent(context,PieActivity.class));
    }
}