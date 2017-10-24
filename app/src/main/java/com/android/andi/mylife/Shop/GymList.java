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
import com.android.andi.mylife.ShopClass.Gym;

import java.util.ArrayList;
import java.util.List;

public class GymList extends AppCompatActivity {

    List<Gym> GymList;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_list);

        ListView listView = (ListView)findViewById(R.id.listview_gym);
        GymList = new ArrayList<Gym>();
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
            return GymList.size();
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

            Gym gym = GymList.get(position);
            final String id=gym.getGymname();
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.gym_item, null);
                viewHolder.gymname = (TextView) convertView
                        .findViewById(R.id.name_gym_item);
                viewHolder.gymprice = (TextView) convertView
                        .findViewById(R.id.price_gym_item);
                viewHolder.gymbutton = (Button) convertView
                        .findViewById(R.id.gym_button);
                viewHolder.gympic = (ImageView) convertView
                      .findViewById(R.id.pic_gym_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            int gymID = convertView.getResources().getIdentifier("gym"+position, "drawable", "com.android.andi.mylife");
            viewHolder.gympic.setImageResource(gymID);
            viewHolder.gymname.setText(gym.getGymname());
            viewHolder.gymprice.setText(gym.getGymprice());
            viewHolder.gymbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id.equals("Inshape")) {
                        startActivity(new Intent(GymList.this, gym1.class));
                    }
                    if (id.equals("Impluse")) {
                        startActivity(new Intent(GymList.this, gym2.class));
                    }
                    if (id.equals("Powerhouse")) {
                        startActivity(new Intent(GymList.this, gym3.class));
                    }

                }
            });
            //viewHolder.respic.setImageDrawable(R.drawable.shop4);

            return convertView;
        }
    }

    class ViewHolder{
        private TextView gymname;
        private TextView gymprice;
        private Button gymbutton;
        private ImageView gympic;
    }

    public void Query() {
        Cursor cursor = sqLiteDatabase.query("gyminfo", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String gymname = cursor.getString(0);
            String gymprice = cursor.getString(1);
            Gym gym = new Gym(gymname, gymprice);
            GymList.add(gym);
        }
    }



}


