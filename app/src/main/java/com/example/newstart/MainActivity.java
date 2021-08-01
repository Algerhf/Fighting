package com.example.newstart;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_constraint_layout)
    AppCompatButton mBtn_constraint_layout;

    @BindView(R.id.btn_custom_drawable)
    AppCompatButton mBtnCustomDrawable;

    @BindView(R.id.btn_xm_clock)
    AppCompatButton mBtnClock;

    @BindView(R.id.card_3d)
    AppCompatButton mBtnCard3D;

    @BindView(R.id.btn_camera)
    AppCompatButton mBtnCamera;

    @BindView(R.id.btn_event_conflict)
    AppCompatButton mBtnEventConflict;

    @BindView(R.id.btn_multi_touch)
    AppCompatButton mBtnMultiTouch;

    @BindView(R.id.btn_view_drag)
    AppCompatButton mBtn_view_drag;

    @BindView(R.id.btn_recycler_view)
    AppCompatButton mBtn_Recycler_view;

    @BindView(R.id.btn_anim)
    AppCompatButton mBtn_Anim;

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mBtn_constraint_layout.setOnClickListener(this);
        mBtnCustomDrawable.setOnClickListener(this);
        mBtnCard3D.setOnClickListener(this);
        mBtnClock.setOnClickListener(this);
        mBtnCamera.setOnClickListener(this);
        mBtnEventConflict.setOnClickListener(this);
        mBtnMultiTouch.setOnClickListener(this);
        mBtn_view_drag.setOnClickListener(this);
        mBtn_Recycler_view.setOnClickListener(this);
        mBtn_Anim.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_constraint_layout:
                ConstrainLayoutActivity.actionStart(mContext);
                break;
            case R.id.btn_custom_drawable:
                startActivity(new Intent(MainActivity.this, CustomDrawableActivity.class));
                break;
            case R.id.card_3d:
                startActivity(new Intent(MainActivity.this, Rotate3dActivity.class));
                break;
            case R.id.btn_xm_clock:
                startActivity(new Intent(MainActivity.this, ClockViewActivity.class));
                break;
            case R.id.btn_camera:
                startActivity(new Intent(MainActivity.this, ThreeDirectionActivity.class));
                break;
            case R.id.btn_event_conflict:
                startActivity(new Intent(MainActivity.this, EventConflictActivity.class));
                break;
            case R.id.btn_multi_touch:
                startActivity(new Intent(MainActivity.this, MultyTouchActivity.class));
                break;
            case R.id.btn_view_drag:
                startActivity(new Intent(MainActivity.this, ViewDragActivity.class));
                break;
            case R.id.btn_recycler_view:
                RecyclerViewActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_anim:
                AnimActivity.actionStart(mContext);
                break;
            default:
                break;
        }
    }
}