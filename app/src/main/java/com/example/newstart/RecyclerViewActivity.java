package com.example.newstart;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstart.recycler.CoverFlowAdapter;
import com.example.newstart.recycler.CoverFlowLayoutManager;
import com.example.newstart.recycler.RecyclerCoverFlowView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {

    private RecyclerCoverFlowView mRecyclerView;
    private List<String>          mList;
    private CoverFlowAdapter mAdapter;

    @Override
    protected int getResId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        super.initData();
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("i = "+i);
        }

        CoverFlowLayoutManager coverFlowLayoutManager = new CoverFlowLayoutManager();
        mRecyclerView.setLayoutManager(coverFlowLayoutManager);

        mAdapter = new CoverFlowAdapter(mContext,mList);
        mRecyclerView.setAdapter(mAdapter);

        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, RecyclerViewActivity.class));
    }
}