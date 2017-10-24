package com.android.andi.mylife.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.andi.mylife.R;
import com.android.andi.mylife.Shop.GymList;
import com.android.andi.mylife.Shop.HotelList;
import com.android.andi.mylife.Shop.KTVList;
import com.android.andi.mylife.Shop.LifeList;
import com.android.andi.mylife.Shop.RestaurantList;
import com.android.andi.mylife.Shop.TheatreList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ShopInfo1 extends AppCompatActivity {

    private ImageView restaurant_shop;
    private ImageView gym_shop;
    private ImageView theatre_shop;
    private ImageView KTV_shop;
    private ImageView life_shop;
    private ImageView hotel_shop;

    static int select;

    private ViewPager viewPager; // android-support-v4
    private List<ImageView> imageViews; // all image

    private String[] titles; // image tittle
    private int[] imageResId; // image ID
    private List<View> dots; // dots

    private TextView tv_title;
    private int currentItem = 0;

    // An ExecutorService that can schedule commands to run after a given delay,
    // or to execute periodically.
    private ScheduledExecutorService scheduledExecutorService;


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);// change image
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info1);

        restaurant_shop = (ImageView) findViewById(R.id.restaurant_shop);
        restaurant_shop.getBackground().setAlpha(230);
        restaurant_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopInfo1.this, RestaurantList.class));
            }
        });
        gym_shop = (ImageView) findViewById(R.id.gym_shop);
        gym_shop.getBackground().setAlpha(230);
        gym_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopInfo1.this, GymList.class));

            }
        });
        theatre_shop = (ImageView) findViewById(R.id.theatre_shop);
        theatre_shop.getBackground().setAlpha(230);
        theatre_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopInfo1.this, TheatreList.class));
            }
        });
        KTV_shop = (ImageView) findViewById(R.id.ktv_shop);
        KTV_shop.getBackground().setAlpha(230);
        KTV_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopInfo1.this, KTVList.class));
            }
        });
        life_shop = (ImageView) findViewById(R.id.life_shop);
        life_shop.getBackground().setAlpha(230);
        life_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopInfo1.this, LifeList.class));
            }
        });
        hotel_shop = (ImageView) findViewById(R.id.hotel_shop);
        hotel_shop.getBackground().setAlpha(230);
        hotel_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopInfo1.this, HotelList.class));
            }
        });

        imageResId = new int[] { R.drawable.shoppager1, R.drawable.shoppager2, R.drawable.shoppaper3, };
        titles = new String[imageResId.length];
        titles[0] = "Library in XJTLU";
        titles[1] = "DuShu Lake Church";
        titles[2] = "Dushu Lake Tunnul";

        imageViews = new ArrayList<ImageView>();

        // initialize image
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }


        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.v_dot_a));
        dots.add(findViewById(R.id.v_dot_b));
        dots.add(findViewById(R.id.v_dot_c));

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(titles[0]);//

        viewPager = (ViewPager) findViewById(R.id.fitnesstips_vp);
        viewPager.setAdapter(new MyAdapter());// set adapter
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        /**(findViewById(R.id.btn_back)).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        startActivity(new Intent(ShopInfo1.this, TabController.class));
        finish();

        }
        });**/

    }

    @Override
    protected void onStart() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // change image every 8 second
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 7, 8, TimeUnit.SECONDS);
        super.onStart();
    }

    @Override
    protected void onStop() {
        // stop changing when image can't be saw
        scheduledExecutorService.shutdown();
        super.onStop();
    }

    /**
     * 换行切换任务
     *
     * @author Administrator
     *
     */
    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
                System.out.println("currentItem: " + currentItem);
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget(); // change image by hand
            }
        }

    }

    /**
     *
     * @author Administrator
     *
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
            tv_title.setText(titles[position]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    /**
     *
     * @author Administrator
     *
     */
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageResId.length;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(imageViews.get(arg1));
            return imageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }
}


