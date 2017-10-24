package com.android.andi.mylife.ShopClass;

/**
 * Created by wsh on 16/10/25.
 */
public class Gym {
    private String gymname;
    private String gymprice;

    public Gym(String gymname, String gymprice){
        super();
        this.gymname=gymname;
        this.gymprice=gymprice;
    }

    public String getGymname() {
        return gymname;
    }
    public void setGymname(String gymname) {
        this.gymname = gymname;
    }
    public String getGymprice() {
        return gymprice;
    }
    public void setGymprice(String gymprice) {
        this.gymprice = gymprice;
    }
}

