package com.android.andi.mylife.Shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import com.android.andi.mylife.ShopClass.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends Activity {

    List<Restaurant> restaurantList;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        ListView listView = (ListView)findViewById(R.id.listview_restaurant);
        restaurantList = new ArrayList<Restaurant>();
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
            return restaurantList.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            final Restaurant restaurant = restaurantList.get(position);
            final String id=restaurant.getResname();
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.restaurant_item, null);
                viewHolder.resname = (TextView) convertView
                        .findViewById(R.id.name_restaurant_item);
                viewHolder.resprice = (TextView) convertView
                        .findViewById(R.id.price_restaurant_item);
                viewHolder.restag = (TextView) convertView
                        .findViewById(R.id.tag_restaurant_item);
                viewHolder.resbutton = (Button) convertView
                        .findViewById(R.id.restaurant_button);
                viewHolder.respic = (ImageView) convertView
                        .findViewById(R.id.pic_restaurant_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            int resID = convertView.getResources().getIdentifier("res"+position, "drawable", "com.android.andi.mylife");
            viewHolder.respic.setImageResource(resID);
            viewHolder.resname.setText(restaurant.getResname());
            viewHolder.resprice.setText(restaurant.getResprice());
            viewHolder.restag.setText(restaurant.getRestag());
            viewHolder.resbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id.equals("Red Connection Barbecue Buffet")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant1.class));
                    }
                    if (id.equals("Korea Crazy Taste Buffet Barbecue Chaffy Dish")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant2.class));
                    }
                    if (id.equals("Thai Delicious Cheese Cakes Hot Pot")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant3.class));
                    }
                    if (id.equals("Collection of Small Lamb Shop")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant4.class));
                    }
                    if (id.equals("Jiangnan Taste Chinese Restaurant")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant5.class));
                    }
                    if (id.equals("The Food in Hunan")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant6.class));
                    }
                    if (id.equals("Couples Fried Chicken")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant7.class));
                    }
                    if (id.equals("Villain Grilled Fish - Academician Edge Shop")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant8.class));
                    }
                    if (id.equals("The Clouds in the Sky")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant9.class));
                    }
                    if (id.equals("Boot Camp")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant10.class));
                    }
                    if (id.equals("Kam Lepidoptera Hin")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant11.class));
                    }
                    if (id.equals("McDonald")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant12.class));
                    }
                    if (id.equals("Shop of Sophie")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant13.class));
                    }
                    if (id.equals("LaoShanJie Hunan Cuisine")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant14.class));
                    }
                    if (id.equals("On the Upper DIY Baking")) {
                        startActivity(new Intent(RestaurantList.this, Restaurant15.class));
                    }


                }
            });
            //viewHolder.respic.setImageDrawable(R.drawable.shop4);

            return convertView;
        }
    }

    class ViewHolder{
        private TextView resname;
        private TextView resprice;
        private TextView restag;
        private Button resbutton;
        private ImageView respic;
    }



    public void Query() {
        Cursor cursor = sqLiteDatabase.query("resinfo", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String resname = cursor.getString(0);
            String resprice = cursor.getString(1);
            String restag = cursor.getString(2);
            Restaurant restaurant = new Restaurant(resname, resprice, restag);
            restaurantList.add(restaurant);
        }
    }



}

