package com.example.msdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class AIDLService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    MyInterface.Stub iBinder = new MyInterface.Stub() {
        @Override
        public int add(int v1, int v2) throws RemoteException {
            return v1 + v2;
        }
    };
}
