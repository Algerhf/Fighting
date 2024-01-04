package com.example.msdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.palette.graphics.Palette;

import com.example.msdemo.databinding.ActivityMainBinding;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private static final String CHANNEL_ID = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolBar();
        initNotification();
    }

    private void initNotification() {
        mBinding.btnNotification.setOnClickListener(v -> {

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // Android 8.0 及以上需要使用 NotificationChannel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "消息提醒", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);

            RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.view_fold);

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            builder.setAutoCancel(true);
            builder.setContentTitle("普通通知");
            builder.setCustomBigContentView(remoteView);
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

            notificationManager.notify(1, builder.build());
        });

        bindService(new Intent(this, AIDLService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyInterface myInterface = MyInterface.Stub.asInterface(service);
                try {
                    int result = myInterface.add(1, 2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    private void initToolBar() {

        mBinding.toolbar.setTitle("主标题");
        mBinding.toolbar.setSubtitle("子标题");
        mBinding.toolbar.setLogo(android.R.mipmap.sym_def_app_icon);
        setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setNavigationIcon(android.R.drawable.ic_menu_view);

        mBinding.toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_share:
                    Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_settings:
                    Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return true;
        });

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        Palette.from(bitmap).generate(palette -> {
            assert palette != null;
            List<Palette.Swatch> swatches = palette.getSwatches();
            if (swatches.size() > 0) {
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(swatches.get(0).getRgb()));
            }
        });
    }

    private void changeSkip(){
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
        // mBinding.ivImage.setImageDrawable(resource.getDrawable(drawableId,null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}