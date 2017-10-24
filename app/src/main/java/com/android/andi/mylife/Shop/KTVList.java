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
import com.android.andi.mylife.ShopClass.KTV;

import java.util.ArrayList;
import java.util.List;

public class KTVList extends AppCompatActivity {

    List<KTV> KTVList;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ktvlist);

        ListView listView = (ListView)findViewById(R.id.listview_ktv);
        KTVList = new ArrayList<KTV>();
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
            return KTVList.size();
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

            KTV ktv = KTVList.get(position);
            final String id=ktv.getKtvname();
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.ktv_item, null);
                viewHolder.ktvname = (TextView) convertView
                        .findViewById(R.id.name_ktv_item);
                viewHolder.ktvprice = (TextView) convertView
                        .findViewById(R.id.price_ktv_item);
                viewHolder.ktvbutton = (Button) convertView
                        .findViewById(R.id.ktv_button);
                viewHolder.ktvpic = (ImageView) convertView
                     .findViewById(R.id.pic_ktv_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            int ktvID = convertView.getResources().getIdentifier("ktv"+position, "drawable", "com.android.andi.mylife");
            viewHolder.ktvpic.setImageResource(ktvID);
            viewHolder.ktvname.setText(ktv.getKtvname());
            viewHolder.ktvprice.setText(ktv.getKtvprice());
            viewHolder.ktvbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id.equals("The Star KTV")) {
                        startActivity(new Intent(KTVList.this, ktv1.class));
                    }
                    if (id.equals("Eight Songs KTV")) {
                        startActivity(new Intent(KTVList.this, ktv2.class));
                    }
                }
            });

            //viewHolder.respic.setImageDrawable(R.drawable.shop4);

            return convertView;
        }
    }

    class ViewHolder{
        private TextView ktvname;
        private TextView ktvprice;
        private Button ktvbutton;
        private ImageView ktvpic;
    }

    public void Query() {
        Cursor cursor = sqLiteDatabase.query("ktvinfo", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String ktvname = cursor.getString(0);
            String ktvprice = cursor.getString(1);
            KTV ktv= new KTV(ktvname, ktvprice);
            KTVList.add(ktv);
        }
    }



}


