package com.example.newstart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.newstart.views.RoundDrawable;

public class CustomDrawableActivity extends AppCompatActivity {

    private static final String TAG = CustomDrawableActivity.class.getSimpleName();

    private AppCompatImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawable);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.photo1);
        RoundDrawable drawable = new RoundDrawable(bitmap);

        iv = findViewById(R.id.image);
        iv.setImageDrawable(drawable);
    }
}