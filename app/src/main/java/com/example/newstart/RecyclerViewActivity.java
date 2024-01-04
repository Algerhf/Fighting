package com.example.newstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearSnapHelper;

import com.example.newstart.databinding.ActivityRecyclerViewBinding;
import com.example.newstart.recycler.CoverFlowAdapter;
import com.example.newstart.recycler.CoverFlowLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {
    private ActivityRecyclerViewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initData();
    }

    private void initData() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("i = " + i);
        }

        CoverFlowLayoutManager coverFlowLayoutManager = new CoverFlowLayoutManager();
        mBinding.recyclerView.setLayoutManager(coverFlowLayoutManager);
        CoverFlowAdapter mAdapter = new CoverFlowAdapter(mContext, mList);
        mBinding.recyclerView.setAdapter(mAdapter);
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(mBinding.recyclerView);
    }

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, RecyclerViewActivity.class));
    }
}