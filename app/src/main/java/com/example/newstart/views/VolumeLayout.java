package com.example.newstart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.newstart.R;

public class VolumeLayout extends ConstraintLayout {
    private VolumeView mVolumeView;

    public VolumeLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public VolumeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VolumeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.volume_layout, this, true);
        mVolumeView = view.findViewById(R.id.volume_view);
    }

    public void volumeUp() {
        mVolumeView.volumeUp();
    }


    public void volumeDown() {
        mVolumeView.volumeDown();
    }
}
