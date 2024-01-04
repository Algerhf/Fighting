package com.example.newstart;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.newstart.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        addListener();
    }

    private void addListener() {
        mBinding.btnConstraintLayout.setOnClickListener(v -> ConstrainLayoutActivity.actionStart(mContext));
        mBinding.btnCustomDrawable.setOnClickListener(v -> CustomDrawableActivity.actionStart(mContext));
        mBinding.btnCard3d.setOnClickListener(v -> Rotate3dActivity.actionStart(mContext));
        mBinding.btnXmClock.setOnClickListener(v -> XmViewActivity.actionStart(mContext));
        mBinding.btnCamera.setOnClickListener(v -> ThreeDirectionActivity.actionStart(mContext));
        mBinding.btnEventConflict.setOnClickListener(v -> EventConflictActivity.actionStart(mContext));
        mBinding.btnMultiTouch.setOnClickListener(v -> MultiTouchActivity.actionStart(mContext));
        mBinding.btnScroller.setOnClickListener(v -> ScrollerActivity.actionStart(mContext));
        mBinding.btnViewDrag.setOnClickListener(v -> ViewDragActivity.actionStart(mContext));
        mBinding.btnRecyclerView.setOnClickListener(v -> RecyclerViewActivity.actionStart(mContext));
        mBinding.btnAnim.setOnClickListener(v -> AnimActivity.actionStart(mContext));
        mBinding.btnHwClock.setOnClickListener(v -> HwClockActivity.actionStart(mContext));
        mBinding.btnPie.setOnClickListener(v -> PieActivity.actionStart(mContext));
        mBinding.btnVolume.setOnClickListener(v -> VolumeActivity.actionStart(mContext));
    }
}