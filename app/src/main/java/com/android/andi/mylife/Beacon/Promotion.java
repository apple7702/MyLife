package com.android.andi.mylife.Beacon;

/**
 * Created by apple on 10/19/16.
 */

public class Promotion {
    String storeName;
    int coupon;
    //Date exptime;



    public Promotion(int storeId, int couponValue) {
        switch (storeId) {
            case 2:
                storeName = "IMPLUSE";
                break;
            case 3:
                storeName = "POWERHOUSE";
                break;

            case 4:
                storeName = "BIGCHEF";
                break;
            case 5:
                storeName = "PARFAIT";
                break;

            default:
                storeName = "INSHAPE";
                break;
        }
        coupon = couponValue;
    }

    @Override
    public String toString() {
        return "You got a coupon from : " + storeName ;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Promotion)){
            return false;
        }

        Promotion that = (Promotion) o;

        return storeName.equals(that.storeName) && coupon == that.coupon;

    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public int getCoupon() {
        return coupon;
    }
}

