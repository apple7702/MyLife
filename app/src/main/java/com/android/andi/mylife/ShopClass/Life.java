package com.android.andi.mylife.ShopClass;

/**
 * Created by wsh on 16/10/25.
 */
public class Life {

    private String lifname;
    private String lifprice;

    public Life(String lifname, String lifprice){
        super();
        this.lifname=lifname;
        this.lifprice=lifprice;
    }

    public String getLifname() {
        return lifname;
    }
    public void setLifname(String lifname) {
        this.lifname = lifname;
    }
    public String getLifprice() {
        return lifprice;
    }
    public void setLifprice(String lifprice) {
        this.lifprice = lifprice;
    }
}
