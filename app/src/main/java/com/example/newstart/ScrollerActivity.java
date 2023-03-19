package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.example.newstart.adapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScrollerActivity extends BaseActivity implements MyAdapter.OnDeleteListener {

    private ListView     mListView;
    private List<String> mList;
    private MyAdapter    mAdapter;

    @Override
    protected int getResId() {
        return R.layout.activity_scroller;
    }

    @Override
    public void initView() {
        super.initView();
        mListView = findViewById(R.id.list_view);
    }

    @Override
    public void initData() {
        super.initData();
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("第" + i + "项");
        }
        mAdapter = new MyAdapter(mList, this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, ScrollerActivity.class));
    }

    @Override
    public void onDeleteClicked(int pos) {
        mList.remove(pos);
        mAdapter.notifyDataSetChanged();
    }
}