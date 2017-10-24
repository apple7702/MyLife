package com.android.andi.mylife.ShopClass;

/**
 * Created by apple on 10/25/16.
 */
public class Restaurant {
    private String resname;
    private String resprice;
    private String restag;

    public Restaurant(String resname, String resprice, String restag){
        super();
        this.resname=resname;
        this.resprice=resprice;
        this.restag=restag;
    }

    public String getResname() {
        return resname;
    }
    public void setResname(String resname) {
        this.resname = resname;
    }
    public String getResprice() {
        return resprice;
    }
    public void setResprice(String resprice) {
        this.resprice = resprice;
    }
    public String getRestag() {
        return restag;
    }
    public void setRestag(String restag) {
        this.restag = restag;
    }

}
