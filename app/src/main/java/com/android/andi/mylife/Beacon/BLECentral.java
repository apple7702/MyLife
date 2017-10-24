package com.android.andi.mylife.Beacon;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.android.andi.mylife.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by apple on 10/19/16.
 */

public class BLECentral {
    private static final String TAG = BLECentral.class.getName();
    protected static BLECentral instance;
    protected static final long SCAN_PERIOD = 10000;

    protected Handler mHandler;
    protected BluetoothAdapter mBluetoothAdapter;
    static List<Promotion> promotionList = new ArrayList<Promotion>();
    int count=0;
    int help;

    Context ctx;
    protected Timer scanTimer;

    private boolean isScanning = false;

    public static List<Promotion> getPromotionList(){

        return promotionList;
    }

    public static BLECentral getInstance(Context context) {
        if (instance == null) {
            instance = (Build.VERSION.SDK_INT < 21) ? new BLECentral(context) : new BLECentral(context);
        }
        return instance;
    }

    protected BLECentral(){

    }

    protected BLECentral(Context context) {

        BluetoothManager bluetoothManager = BLEUtil.getBluetoothManager(context);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        mHandler = new Handler();
        ctx = context;
        startScan();
    }

    public void startScan() {
        if (isScanning) {
            return;
        }

        isScanning = true;
        if (scanTimer != null) {
            scanTimer.cancel();
        }

        scanTimer = new Timer();
        scanTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (!BLEUtil.isBluetoothOn(mBluetoothAdapter)) {
                    return;
                }

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopLeScan();
                    }
                }, SCAN_PERIOD);

                startLeScan();
            }
        }, 0, SCAN_PERIOD + 10000);
    }

    void stopScan() {
        if (scanTimer != null) {
            scanTimer.cancel();
            scanTimer = null;
        }

        if (!BLEUtil.isBluetoothOn(mBluetoothAdapter)) {
            return;
        }

        stopLeScan();
        isScanning = false;
    }

    protected void startLeScan () {
        Log.d(TAG, "startLeScan");

        boolean success = mBluetoothAdapter.startLeScan(mLeScanCallback);
        if (!success) {
            Log.e(TAG, "unalbe to start BLE scan");
        }
    }

    protected void stopLeScan() {
        Log.d(TAG, "stopLeScan");
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }


    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi,
                                     byte[] scanRecord) {

                    Promotion coupon = BLEUtil.extractCoupon(scanRecord);
                    if (coupon != null) {


                        if (promotionList.contains(coupon)) {
                            return;
                        }

                        //Log.e(TAG, "Got coupon of " + coupon);



                        promotionList.add(coupon);

                        Log.e(TAG, "Got coupon of " + coupon);
                        int notifyID =0;

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);
                        builder.setSmallIcon(R.drawable.arrow_blue);
                        builder.setContentTitle("MyLife");
                        builder.setContentText(coupon.toString());
                        builder.setAutoCancel(true);
                        Intent resultIntent = new Intent(ctx, CouponList.class);

                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
                        stackBuilder.addParentStack(CouponList.class);
                        stackBuilder.addNextIntent(resultIntent);

                        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                        builder.setContentIntent(pendingIntent);

                        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

                        notificationManager.notify(0,builder.build());

                    }

                }
            };
}