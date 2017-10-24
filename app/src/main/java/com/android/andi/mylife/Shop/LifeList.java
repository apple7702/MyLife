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
import com.android.andi.mylife.ShopClass.Life;

import java.util.ArrayList;
import java.util.List;

public class LifeList extends AppCompatActivity {
    List<Life> LifeList;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_list);

        ListView listView = (ListView)findViewById(R.id.listview_life);
        LifeList = new ArrayList<Life>();
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
            return LifeList.size();
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

            Life life = LifeList.get(position);
            final String id=life.getLifname();
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.life_item, null);
                viewHolder.lifname = (TextView) convertView
                        .findViewById(R.id.name_life_item);
                viewHolder.lifprice = (TextView) convertView
                        .findViewById(R.id.price_life_item);
                viewHolder.lifbutton = (Button) convertView
                        .findViewById(R.id.life_button);
                viewHolder.lifpic = (ImageView) convertView
                      .findViewById(R.id.pic_life_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            int lifID = convertView.getResources().getIdentifier("lif"+position, "drawable", "com.android.andi.mylife");
            viewHolder.lifpic.setImageResource(lifID);
            viewHolder.lifname.setText(life.getLifname());
            viewHolder.lifprice.setText(life.getLifprice());
            viewHolder.lifbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id.equals("Danyang Glasses")) {
                        startActivity(new Intent(LifeList.this, life1.class));
                    }
                    if (id.equals("Alice Flower Shop")) {
                        startActivity(new Intent(LifeList.this, life2.class));
                    }

                }
            });
            //viewHolder.respic.setImageDrawable(R.drawable.shop4);

            return convertView;
        }
    }

    class ViewHolder{
        private TextView lifname;
        private TextView lifprice;
        private Button lifbutton;
        private ImageView lifpic;
    }

    public void Query() {
        Cursor cursor = sqLiteDatabase.query("lifinfo", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String lifname = cursor.getString(0);
            String lifprice = cursor.getString(1);
            Life life = new Life(lifname, lifprice);
            LifeList.add(life);
        }
    }



}


