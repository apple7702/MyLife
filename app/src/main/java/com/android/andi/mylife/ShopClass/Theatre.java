package com.android.andi.mylife.ShopClass;

/**
 * Created by wsh on 16/10/25.
 */
public class Theatre {

    private String thename;
    private String theprice;

    public Theatre(String thename, String theprice){
        super();
        this.thename=thename;
        this.theprice=theprice;
    }

    public String getThename() {
        return thename;
    }
    public void setThename(String thename) {
        this.thename = thename;
    }
    public String getTheprice() {
        return theprice;
    }
    public void setTheprice(String theprice) {
        this.theprice = theprice;
    }
}
