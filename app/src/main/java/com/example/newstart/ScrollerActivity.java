package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.newstart.adapters.MyAdapter;
import com.example.newstart.databinding.ActivityScrollerBinding;

import java.util.ArrayList;
import java.util.List;

public class ScrollerActivity extends BaseActivity implements MyAdapter.OnDeleteListener {
    private ActivityScrollerBinding mBinding;
    private List<String> mList;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityScrollerBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initData();
    }

    @Override
    public void onDeleteClicked(int pos) {
        mList.remove(pos);
        mAdapter.notifyDataSetChanged();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("第" + i + "项");
        }
        mAdapter = new MyAdapter(mList, this);
        mBinding.listView.setAdapter(mAdapter);
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, ScrollerActivity.class));
    }
}