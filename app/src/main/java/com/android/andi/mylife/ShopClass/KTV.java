package com.android.andi.mylife.ShopClass;

/**
 * Created by wsh on 16/10/25.
 */
public class KTV {

    private String ktvname;
    private String ktvprice;

    public KTV(String ktvname, String ktvprice){
        super();
        this.ktvname=ktvname;
        this.ktvprice=ktvprice;
    }

    public String getKtvname() {
        return ktvname;
    }
    public void setKtvname(String ktvname) {
        this.ktvname = ktvname;
    }
    public String getKtvprice() {
        return ktvprice;
    }
    public void setKtvprice(String ktvprice) {
        this.ktvprice = ktvprice;
    }
}
