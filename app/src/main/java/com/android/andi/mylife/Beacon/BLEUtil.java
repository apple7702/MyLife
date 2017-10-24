package com.android.andi.mylife.Beacon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;

/**
 * Created by apple on 10/19/16.
 */
public class BLEUtil {
    private static final String TAG = BLEUtil.class.getSimpleName();
    private static final String COUPON_UUID = "40943DFA-C0EF-4AB6-8685-BB52D44861A2";


    public static BluetoothManager getBluetoothManager(Context context) {
        return (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
    }

    public static boolean isBluetoothOn(BluetoothAdapter bluetoothAdapter) {
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public static boolean isBluetoothOn(Context context) {
        BluetoothManager manager = getBluetoothManager(context);
        return isBluetoothOn(manager.getAdapter());
    }

    public static Promotion extractCoupon(final byte[] scanRecord) {

        int startByte = 2;
        boolean patternFound = false;
        while (startByte <= 5) {
            if (((int) scanRecord[startByte + 2] & 0xff) == 0x02 && //Identifies an iBeacon
                    ((int) scanRecord[startByte + 3] & 0xff) == 0x15) { //Identifies correct data length
                patternFound = true;
                break;
            }
            startByte++;
        }

        if (patternFound) {
            //Convert to hex String
            byte[] uuidBytes = new byte[16];
            System.arraycopy(scanRecord, startByte + 4, uuidBytes, 0, 16);
            String hexString = bytesToHex(uuidBytes);

            //Here is your UUID
            String uuid = hexString.substring(0, 8) + "-" +
                    hexString.substring(8, 12) + "-" +
                    hexString.substring(12, 16) + "-" +
                    hexString.substring(16, 20) + "-" +
                    hexString.substring(20, 32);

            if (!COUPON_UUID.equalsIgnoreCase(uuid)) {
                return null;
            }

            //Here is your Major value
            int major = (scanRecord[startByte + 20] & 0xff) * 0x100 + (scanRecord[startByte + 21] & 0xff);

            //Here is your Minor value
            int minor = (scanRecord[startByte + 22] & 0xff) * 0x100 + (scanRecord[startByte + 23] & 0xff);
            return new Promotion(major, minor);
        } else {
            return null;
        }
    }

    static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
