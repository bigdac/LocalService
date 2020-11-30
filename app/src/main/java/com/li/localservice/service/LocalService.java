package com.li.localservice.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.li.localservice.IMyAidlInterface;

/**
 * @author 版本：1.0
 * 创建日期：2020-11-30 09
 * 描述：
 */
public class LocalService extends Service {
    private MyBinder myBinder;
    class MyBinder extends IMyAidlInterface.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //8.0 前台service
            NotificationChannel channel = new NotificationChannel("deamon", "deamon",
                    NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            if (manager == null)
                return;
            manager.createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this,
                    "deamon").setAutoCancel(true).setCategory(
                    Notification.CATEGORY_SERVICE).setOngoing(true).setPriority(
                    NotificationManager.IMPORTANCE_LOW).build();
            startForeground(10, notification);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            //如果 18 以上的设备 启动一个Service startForeground给相同的id
            //然后结束那个Service
            startForeground(10, new Notification());
            startService(new Intent(this, InnerService.class));
        } else {
            startForeground(10, new Notification());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this,HelpService.class),new MyServiceConnection(),BIND_AUTO_CREATE);
        return super.onStartCommand(intent, flags, startId);
    }

    public static class InnerService extends Service {

        @Override
        public void onCreate() {
            super.onCreate();
            startForeground(10, new Notification());
            stopSelf();
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(LocalService.this, HelpService.class));
            bindService(new Intent(LocalService.this, HelpService.class), new MyServiceConnection(),
                    BIND_AUTO_CREATE);
        }
    }
}
