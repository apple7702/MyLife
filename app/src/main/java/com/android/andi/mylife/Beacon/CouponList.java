package com.android.andi.mylife.Beacon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.andi.mylife.MainActivity;
import com.android.andi.mylife.Map.ShopInfo1;
import com.android.andi.mylife.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponList extends AppCompatActivity {
    ListView couponList;

    private List<Map<String, Object>> promotionList;
    List CouponList = BLECentral.getPromotionList();
    ArrayList<String> promotion_information_list;

    MyAdapter myAdapter;
    static String gym_name;
    static int coupon_int;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        couponList = (ListView) findViewById(R.id.listView_couponlist);
        (findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CouponList.this, MainActivity.class));
                finish();
            }
        });


        promotion_information_list = new ArrayList<>();


        for (int i = 0; i < CouponList.size(); i++) {
            String gym_name = ((Promotion) CouponList.get(i)).getStoreName();
            String coupon_string = String.valueOf(((Promotion) CouponList.get(i)).getCoupon());
            promotion_information_list.add(gym_name);
            promotion_information_list.add(coupon_string);

        }


        promotionList = getData();
        myAdapter=new MyAdapter(CouponList.this);
        couponList.setAdapter(myAdapter);

    }

    private List<Map<String, Object>>getData(){
        List<Map<String, Object>>list = new ArrayList<>();

        for(int i = 0;i<promotion_information_list.size();i=i+2){
            Map<String, Object> map = new HashMap<>();

            map.put("Gym", promotion_information_list.get(i));

            map.put("Coupon", promotion_information_list.get(i+1));
            list.add(map);
        }
        return list;
    }

    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private int itemPositon;
        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return promotionList.size();
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
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.promotion_item, null);
                holder.gym= (TextView) convertView.findViewById(R.id.gym_item);
                holder.coupon= (TextView) convertView.findViewById(R.id.coupon_item);
                //holder.duration=(TextView)convertView.findViewById(R.id.duration);
                holder.bt = (Button) convertView.findViewById(R.id.delete_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.gym.setText("You got a coupon from :"+(String) promotionList.get(position).get("Gym"));
            holder.coupon.setText("Coupon value: "+(String)promotionList.get(position).get("Coupon"));

            holder.bt.setTag(position);
            holder.bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemPositon=position;


                    showDialog();

                }
            });

            return convertView;
        }

        public final class ViewHolder {
            public TextView gym;
            public TextView coupon;
            public Button bt;
            //public TextView duration;
        }

        private void showDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(CouponList.this);
            builder.setTitle("Options");
            builder.setMessage("what do you wanna do?");

            builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() { //
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            builder.setNegativeButton("go to this gym", new DialogInterface.OnClickListener() { // 锟斤拷锟斤拷取锟斤拷锟斤拷钮
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String gym_name= (String) promotionList.get(itemPositon).get("Gym");
                    if(gym_name.equals("INSHAPE")){
                        startActivity(new Intent(CouponList.this,ShopInfo1.class));
                    }else if(gym_name.equals("POWERHOUSE")){
                        startActivity(new Intent(CouponList.this,ShopInfo1.class));
                    }else if(gym_name.equals("IMPLUSE")){
                        startActivity(new Intent(CouponList.this,ShopInfo1.class));
                    }



                }
            });

            builder.setNeutralButton("delete ", new DialogInterface.OnClickListener() {// 锟斤拷锟矫猴拷锟皆帮拷钮
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    promotionList.remove(itemPositon);

                    promotionList = getData();
                    myAdapter = new MyAdapter(CouponList.this);
                    couponList.setAdapter(myAdapter);

                    myAdapter.notifyDataSetChanged();

                }
            });

            builder.create().show();
        }

    }

}
