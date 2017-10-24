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
import com.android.andi.mylife.ShopClass.Theatre;

import java.util.ArrayList;
import java.util.List;

public class TheatreList extends AppCompatActivity {

    List<Theatre> TheatreList;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_list);

        ListView listView = (ListView)findViewById(R.id.listview_gym);
        TheatreList = new ArrayList<Theatre>();
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
            return TheatreList.size();
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

            Theatre theatre = TheatreList.get(position);
            final String id=theatre.getThename();
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.theatre_item, null);
                viewHolder.thename = (TextView) convertView
                        .findViewById(R.id.name_theatre_item);
                viewHolder.theprice = (TextView) convertView
                        .findViewById(R.id.price_theatre_item);
                viewHolder.thebutton = (Button) convertView
                        .findViewById(R.id.theatre_button);
                viewHolder.thepic = (ImageView) convertView
                      .findViewById(R.id.pic_theatre_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            int theID = convertView.getResources().getIdentifier("the"+position, "drawable", "com.android.andi.mylife");
            viewHolder.thepic.setImageResource(theID);
            viewHolder.thename.setText(theatre.getThename());
            viewHolder.theprice.setText(theatre.getTheprice());
            viewHolder.thebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id.equals("Dushu Lake Theatre")) {
                        startActivity(new Intent(TheatreList.this, theatre1.class));
                    }
                    if (id.equals("Stellar International Cineplex")) {
                        startActivity(new Intent(TheatreList.this, theatre2.class));
                    }
                    if (id.equals("China International Film Studios")) {
                        startActivity(new Intent(TheatreList.this, theatre3.class));
                    }

                }
            });
            //viewHolder.respic.setImageDrawable(R.drawable.shop4);

            return convertView;
        }
    }

    class ViewHolder{
        private TextView thename;
        private TextView theprice;
        private Button thebutton;
        private ImageView thepic;
    }

    public void Query() {
        Cursor cursor = sqLiteDatabase.query("theinfo", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String thename = cursor.getString(0);
            String theprice = cursor.getString(1);
            Theatre theatre = new Theatre(thename, theprice);
            TheatreList.add(theatre);
        }
    }



}
