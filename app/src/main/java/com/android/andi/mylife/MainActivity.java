package com.android.andi.mylife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.andi.Browse;
import com.android.andi.mylife.Beacon.BLECentral;
import com.android.andi.mylife.Beacon.CouponList;
import com.android.andi.mylife.Map.Map;
import com.android.andi.mylife.Map.ShopInfo1;
import com.android.andi.mylife.zxing.activity.CaptureActivity;

public class MainActivity extends AppCompatActivity {


    private int REQUEST_ENABLE_BT = 1;
    private BLECentral bleService;
    private ImageView map_main;
    private ImageView coupon_main;
    private ImageView shop_main;
    private ImageView friends_main;

    private Button left_bar;



    //new

    private String account;
    private SharedPreferences sp;

    private String gender;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        sp=getSharedPreferences("config",Context.MODE_PRIVATE);

        account=sp.getString("account", "");
        gender=sp.getString("gender", "");
        nickname=sp.getString("nickname","");

        if (account.equals("")){
            Intent intent=new Intent(this, Browse.class);
            startActivity(intent);
            finish();




        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        map_main = (ImageView) findViewById(R.id.map_main);
        map_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Map.class));
            }
        });

        shop_main = (ImageView) findViewById(R.id.shop_main);
        shop_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopInfo1.class));
            }
        });


        coupon_main = (ImageView) findViewById(R.id.coupon_main);
        coupon_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CouponList.class));
            }
        });


        friends_main = (ImageView) findViewById(R.id.friends_main);
        friends_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, nearby.class));
            }
        });

        left_bar = (Button) findViewById(R.id.left_bar);
        left_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hhhh", Toast.LENGTH_SHORT).show();
                showLeftMenu(MainActivity.this, v);


            }
        });


        map_main.getBackground().setAlpha(190);
        coupon_main = (ImageView) findViewById(R.id.coupon_main);
        coupon_main.getBackground().setAlpha(190);
        shop_main = (ImageView) findViewById(R.id.shop_main);
        shop_main.getBackground().setAlpha(190);
        friends_main = (ImageView) findViewById(R.id.friends_main);
        friends_main.getBackground().setAlpha(190);


        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "BLE Not Supported",
                    Toast.LENGTH_SHORT).show();
            finish();
        }


        bleService = BLECentral.getInstance(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showLeftMenu(Context context, View view) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View PopupWindow = inflater.inflate(R.layout.activity_left_bar, null, false);
        final android.widget.PopupWindow pw = new PopupWindow(PopupWindow, 1000, ViewGroup.LayoutParams.MATCH_PARENT, true);
        pw.setAnimationStyle(R.style.AnimationFade);
        pw.showAtLocation(view, Gravity.LEFT, 0, 0);

        ((Button) PopupWindow.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), CaptureActivity.class);
                startActivity(it);
            }
        });

        ((Button) PopupWindow.findViewById(R.id.btn_logout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();


                Intent it = new Intent(v.getContext(), Browse.class);
                startActivity(it);
                finish();
            }
        });
        ((TextView)PopupWindow.findViewById(R.id.tv_nickname)).setText("Hi," + nickname + "~");

        ((TextView)PopupWindow.findViewById(R.id.tv_account)).setText(account);
        ((TextView)PopupWindow.findViewById(R.id.tv_gender)).setText(gender);



        PopupWindow.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (pw != null && pw.isShowing()) {
                    pw.dismiss();
                }
                return false;
            }
        });

    }
}
