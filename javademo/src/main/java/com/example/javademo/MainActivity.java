package com.example.javademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatImageView  iv = findViewById(R.id.iv_image);
        AppCompatTextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_change).setOnClickListener(v -> {
            File file = getExternalFilesDir(Environment.DIRECTORY_DCIM);
            if(!file.exists()){
                file.mkdirs();
            }
            String path = file.getAbsolutePath()+ File.separator + "demo.apk";
            File apkFile = new File(path);
            if(!apkFile.exists()){
                return;
            }
            Resources resource = SkinManager.getInstance().getResource(MainActivity.this,apkFile.getAbsolutePath());
            int drawableId = resource.getIdentifier("battery","drawable","com.example.jetpack");
            iv.setImageDrawable(resource.getDrawable(drawableId,null));
        });

        findViewById(R.id.btn_recover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(R.drawable.battery);
            }
        });
    }
}