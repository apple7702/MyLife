package com.android.andi.mylife.ShopClass;

/**
 * Created by wsh on 16/10/25.
 */
public class Hotel {

    private String hotname;
    private String hotprice;

    public Hotel(String hotname, String hotprice){
        super();
        this.hotname=hotname;
        this.hotprice=hotprice;
    }

    public String getHotname() {
        return hotname;
    }
    public void setHotname(String hotname) {
        this.hotname = hotname;
    }
    public String getHotprice() {
        return hotprice;
    }
    public void setHotprice(String hotprice) {
        this.hotprice = hotprice;
    }
}
