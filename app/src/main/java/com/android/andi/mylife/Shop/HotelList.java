package com.android.andi.mylife.Shop;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.andi.mylife.DBHelper;
import com.android.andi.mylife.R;
import com.android.andi.mylife.ShopClass.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelList extends AppCompatActivity {
    List<Hotel> HotelList;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        ListView listView = (ListView)findViewById(R.id.listview_hotel);
        HotelList = new ArrayList<Hotel>();
        dbHelper = new DBHelper(this);

        sqLiteDatabase = dbHelper.getWritableDatabase();
        //Insert();
        Query();
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return HotelList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Hotel hotel = HotelList.get(position);
            final String id=hotel.getHotname();
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.hotel_item, null);
                viewHolder.hotname = (TextView) convertView
                        .findViewById(R.id.name_hotel_item);
                viewHolder.hotprice = (TextView) convertView
                        .findViewById(R.id.price_hotel_item);
                viewHolder.hotbutton = (Button) convertView
                        .findViewById(R.id.hotel_button);
                viewHolder.hotpic = (ImageView) convertView
                      .findViewById(R.id.pic_hotel_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            int hotID = convertView.getResources().getIdentifier("hot"+position, "drawable", "com.android.andi.mylife");
            viewHolder.hotpic.setImageResource(hotID);
            viewHolder.hotname.setText(hotel.getHotname());
            viewHolder.hotprice.setText(hotel.getHotprice());
            viewHolder.hotbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id.equals("7 Days Inn")) {
                        startActivity(new Intent(HotelList.this, hotel1.class));
                    }
                    if (id.equals("Worldhotel Grand Dushulake")) {
                        startActivity(new Intent(HotelList.this, hotel2.class));
                    }

                }
            });
            //viewHolder.respic.setImageDrawable(R.drawable.shop4);

            return convertView;
        }
    }

    class ViewHolder{
        private TextView hotname;
        private TextView hotprice;
        private Button hotbutton;
        private ImageView hotpic;
    }

    public void Query() {
        Cursor cursor = sqLiteDatabase.query("hotinfo", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String hotname = cursor.getString(0);
            String hotprice = cursor.getString(1);
            Hotel hotel = new Hotel(hotname, hotprice);
            HotelList.add(hotel);
        }
    }



}


