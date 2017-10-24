package com.android.andi.mylife;

/**
 * Created by apple on 10/25/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "MyLife";
    private static final int DATABASE_VERSION = 1;

    static String create_user = "create table user(phone varchar, nickname varchar, password varchar, gender varchar,"
            + "location varchar, created_at varchar, pic varchar)";
    static String create_resinfo="create table resinfo(resname varchar(200), resprice varchar(100), restag varchar(100))";
    static String create_gyminfo="create table gyminfo(gymname varchar(200), gymprice varchar(100))";
    static String create_theinfo="create table theinfo(thename varchar(200), theprice varchar(100))";
    static String create_ktvinfo="create table ktvinfo(ktvname varchar(50), ktvprice varchar(50))";
    static String create_lifinfo="create table lifinfo(lifname varchar(50), lifprice varchar(50))";
    static String create_hotinfo="create table hotinfo(hotname varchar(50), hotprice varchar(50))";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_user);
        db.execSQL(create_resinfo);
        db.execSQL(create_gyminfo);
        db.execSQL(create_theinfo);
        db.execSQL(create_ktvinfo);
        db.execSQL(create_lifinfo);
        db.execSQL(create_hotinfo);
        db.execSQL("insert into resinfo(resname, resprice, restag)values('Red Connection Barbecue Buffet','Per Capita Consumption: ¥45','Barbecue Grill')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('Korea Crazy Taste Buffet Barbecue Chaffy Dish','Per Capita Consumption: ¥50','Barbecue Grill')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('Thai Delicious Cheese Cakes Hot Pot','Per Capita Consumption: ¥46','Japanese & South Korean Cuisine')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('Collection of Small Lamb Shop','Per Capita Consumption: ¥51','Jiangsu & Zhejiang Cuisine')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('Jiangnan Taste Chinese Restaurant','Per Capita Consumption: ¥70','Chinese Food')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('The Food in Hunan','Per Capita Consumption: ¥42','Sichuan & Hunan Cuisine')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('Couples Fried Chicken','Per Capita Consumption: ¥35','Japanese & South Korean Cuisine')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('Villain Grilled Fish - Academician Edge Shop','Per Capita Consumption: ¥60','Sichuan & Hunan Cuisine')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('The Clouds in the Sky','Per Capita Consumption: ¥47','Western Food')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('Boot Camp','Per Capita Consumption: ¥39','Coffee Shop')");
        db.execSQL("insert into resinfo(resname, resprice, restag)values('Kam Lepidoptera Hin','Per Capita Consumption: ¥70','Jiangsu & Zhejiang Cuisine')");
        //db.execSQL("insert into resinfo(resname, resprice, restag)values('McDonald','Per Capita Consumption: ¥29','Western Food')");
        //db.execSQL("insert into resinfo(resname, resprice, restag)values('Shop of Sophie','Per Capita Consumption: ¥37','Coffee Shop')");
        //db.execSQL("insert into resinfo(resname, resprice, restag)values('LaoShanJie Hunan Cuisine','Per Capita Consumption: ¥50','Sichuan & Hunan Cuisine')");
        //db.execSQL("insert into resinfo(resname, resprice, restag)values('On the Upper DIY Baking','Per Capita Consumption: ¥49','DIY Baking')");

        db.execSQL("insert into gyminfo(gymname, gymprice)values('Inshape','Price: ¥2200/Year')");
        db.execSQL("insert into gyminfo(gymname, gymprice)values('Impluse','Price: ¥2000/Year')");
        db.execSQL("insert into gyminfo(gymname, gymprice)values('Powerhouse','Price: ¥2200/Year')");

        db.execSQL("insert into theinfo(thename, theprice)values('Dushu Lake Theatre','Price: ¥30/Ticket')");
        db.execSQL("insert into theinfo(thename, theprice)values('Stellar International Cineplex','Price: ¥35/Ticket')");
        db.execSQL("insert into theinfo(thename, theprice)values('China International Film Studios','Price: ¥38/Ticket')");

        db.execSQL("insert into ktvinfo(ktvname, ktvprice)values('The Star KTV','Per Capita Consumption: ¥32')");
        db.execSQL("insert into ktvinfo(ktvname, ktvprice)values('Eight Songs KTV','Per Capita Consumption: ¥40')");

        db.execSQL("insert into lifinfo(lifname, lifprice)values('Danyang Glasses','Per Capita Consumption: ¥200')");
        db.execSQL("insert into lifinfo(lifname, lifprice)values('Alice Flower Shop','Per Capita Consumption: ¥80')");

        db.execSQL("insert into hotinfo(hotname, hotprice)values('7 Days Inn','Per Capita Consumption: ¥250')");
        db.execSQL("insert into hotinfo(hotname, hotprice)values('Worldhotel Grand Dushulake','Per Capita Consumption: ¥700')");

}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUser(SQLiteDatabase db, String phone, String nickname, String password, String gender,
                           String location, String created_at, String pic) {


        Cursor cursor = db.rawQuery("select * from user where phone=?", new String[]{phone});

        ContentValues values = new ContentValues();
        values.put("phone", phone);
        values.put("nickname", nickname);
        values.put("password", password);
        values.put("gender", gender);
        values.put("location", location);
        values.put("created_at", created_at);
        values.put("pic", pic);
        if (cursor.moveToFirst()) {
            db.update("user", values, "phone=?", new String[]{phone});


        } else {

            db.insert("user", null, values);
        }

    }


    public ArrayList<String> readAllUser(SQLiteDatabase db){

        ArrayList list=new ArrayList();
        Cursor cursor=db.rawQuery("select * from user",new String[]{});
        while (cursor.moveToNext()){
            String phone=cursor.getString(cursor.getColumnIndex("phone"));
            String nickname=cursor.getString(cursor.getColumnIndex("nickname"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            String gender=cursor.getString(cursor.getColumnIndex("gender"));
            String location=cursor.getString(cursor.getColumnIndex("location"));
            String pic=cursor.getString(cursor.getColumnIndex("pic"));
            String created_at=cursor.getString(cursor.getColumnIndex("created_at"));

            String per=phone+","+nickname+","+password+","+gender+","+location+","+created_at+","+pic;
            list.add(per);

        }

        cursor.close();

        return list;
    }


}

