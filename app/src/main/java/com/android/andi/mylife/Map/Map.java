package com.android.andi.mylife.Map;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.android.andi.mylife.R;
import com.android.andi.mylife.Shop.Restaurant1;
import com.android.andi.mylife.Shop.Restaurant10;
import com.android.andi.mylife.Shop.Restaurant11;
import com.android.andi.mylife.Shop.Restaurant2;
import com.android.andi.mylife.Shop.Restaurant3;
import com.android.andi.mylife.Shop.Restaurant4;
import com.android.andi.mylife.Shop.Restaurant5;
import com.android.andi.mylife.Shop.Restaurant6;
import com.android.andi.mylife.Shop.Restaurant7;
import com.android.andi.mylife.Shop.Restaurant8;
import com.android.andi.mylife.Shop.Restaurant9;
import com.android.andi.mylife.Shop.gym1;
import com.android.andi.mylife.Shop.gym2;
import com.android.andi.mylife.Shop.gym3;
import com.android.andi.mylife.Shop.hotel1;
import com.android.andi.mylife.Shop.hotel2;
import com.android.andi.mylife.Shop.ktv1;
import com.android.andi.mylife.Shop.ktv2;
import com.android.andi.mylife.Shop.life1;
import com.android.andi.mylife.Shop.life2;
import com.android.andi.mylife.Shop.theatre1;
import com.android.andi.mylife.Shop.theatre2;
import com.android.andi.mylife.Shop.theatre3;

public class Map extends AppCompatActivity implements LocationSource, AMapLocationListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter, AMap.OnMarkerClickListener {

    MapView mapView;
    AMap aMap;
    private LocationManagerProxy mLocationManagerProxy;
    private LocationSource.OnLocationChangedListener mListener;
    Marker Gym1;
    Marker Gym2;
    Marker Gym3;
    Marker Res1;
    Marker Res2;
    Marker Res3;
    Marker Res4;
    Marker Res5;
    Marker Res6;
    Marker Res7;
    Marker Res8;
    Marker Res9;
    Marker Res10;
    Marker Res11;
    Marker Ktv1;
    Marker Ktv2;
    Marker Lif1;
    Marker Lif2;
    Marker Hot1;
    Marker Hot2;
    Marker The1;
    Marker The2;
    Marker The3;

    public static double latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        showMap();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


    }

    /**
     * show map
     */
    private void showMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();


        }
    }

    /**
     * location
     */
    private void setUpMap(){
        MyLocationStyle locationStyle = new MyLocationStyle();
        locationStyle.strokeColor(Color.WHITE);
        locationStyle.strokeWidth(0);
        locationStyle.radiusFillColor(0x8333);
        locationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.my));
        aMap.setMyLocationStyle(locationStyle);
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);



        /**
         * insert markers
         */
        LatLng ll_gym1 = new LatLng(31.2734826252,120.7399154083);//XJTLU
        LatLng ll_gym2 = new LatLng(31.2968021849,120.7187859378);//link
        LatLng ll_gym3 = new LatLng(31.2627391399,120.7465801138);//blocl c

        LatLng ll_res1 = new LatLng(31.2775990000,120.7442320000);//
        LatLng ll_res2 = new LatLng(31.2648830000,120.7473070000);//
        LatLng ll_res3 = new LatLng(31.2762240000,120.7414790000);//
        LatLng ll_res4 = new LatLng(31.2769550000,120.7466130000);
        LatLng ll_res5 = new LatLng(31.2742670000,120.7400900000);//
        LatLng ll_res6 = new LatLng(31.2758110000,120.7456900000);//
        LatLng ll_res7 = new LatLng(31.2558270000,120.7405400000);//
        LatLng ll_res8 = new LatLng(31.2772830000,120.7451860000);
        LatLng ll_res9 = new LatLng(31.2757230000,120.7455900000);//
        LatLng ll_res10 = new LatLng(31.2617600000,120.7446110000);//
        LatLng ll_res11 = new LatLng(31.2684690000,120.7469180000);

        LatLng ll_the1 = new LatLng(31.2663200000,120.7260280000);
        LatLng ll_the2 = new LatLng(31.2973480000,120.7189830000);
        LatLng ll_the3 = new LatLng(31.3181510000,120.7149430000);

        LatLng ll_ktv1 = new LatLng(31.2768690000,120.7423910000);
        LatLng ll_ktv2 = new LatLng(31.2757170000,120.7455060000);

        LatLng ll_lif1 = new LatLng(31.2751180000,120.7401890000);
        LatLng ll_lif2 = new LatLng(31.2676240000,120.7246530000);

        LatLng ll_hot1 = new LatLng(31.2761990000,120.7415860000);
        LatLng ll_hot2 = new LatLng(31.2661380000,120.7214490000);



        Gym1 = aMap.addMarker(new MarkerOptions()
                .position(ll_gym1)
                .title("Inshape")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Gym2 = aMap.addMarker(new MarkerOptions()
                .position(ll_gym2)
                .title("Impulse")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Gym3 = aMap.addMarker(new MarkerOptions()
                .position(ll_gym3)
                .title("Powerhouse")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res1 = aMap.addMarker(new MarkerOptions()
                .position(ll_res1)
                .title("Red Connection Barbecue Buffet")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res2 = aMap.addMarker(new MarkerOptions()
                .position(ll_res2)
                .title("Korea Crazy Taste Buffet Barbecue Chaffy Dish")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res3 = aMap.addMarker(new MarkerOptions()
                .position(ll_res3)
                .title("Thai Delicious Cheese Cakes Hot Pot")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res4 = aMap.addMarker(new MarkerOptions()
                .position(ll_res4)
                .title("Collection of Small Lamb Shop")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res5 = aMap.addMarker(new MarkerOptions()
                .position(ll_res5)
                .title("Jiangnan Taste Chinese Restaurant")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res6 = aMap.addMarker(new MarkerOptions()
                .position(ll_res6)
                .title("The Food in Hunan")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res7 = aMap.addMarker(new MarkerOptions()
                .position(ll_res7)
                .title("Couples Fried Chicken")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res8 = aMap.addMarker(new MarkerOptions()
                .position(ll_res8)
                .title("Villain Grilled Fish - Academician Edge Shop")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res9 = aMap.addMarker(new MarkerOptions()
                .position(ll_res9)
                .title("The Clouds in the Sky")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res10 = aMap.addMarker(new MarkerOptions()
                .position(ll_res10)
                .title("The Boot Camp")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Res11 = aMap.addMarker(new MarkerOptions()
                .position(ll_res11)
                .title("Kam Lepidoptera Hin")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        The1 = aMap.addMarker(new MarkerOptions()
                .position(ll_the1)
                .title("Dushu Lake Theater")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        The2 = aMap.addMarker(new MarkerOptions()
                .position(ll_the2)
                .title("Stellar International Cineplex")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        The3 = aMap.addMarker(new MarkerOptions()
                .position(ll_the3)
                .title("China International Film Studios")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Ktv1 = aMap.addMarker(new MarkerOptions()
                .position(ll_ktv1)
                .title("The Star KTV")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Ktv2 = aMap.addMarker(new MarkerOptions()
                .position(ll_ktv2)
                .title("Eight Songs KTV")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Lif1 = aMap.addMarker(new MarkerOptions()
                .position(ll_lif1)
                .title("Danyang Glasses")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Lif2 = aMap.addMarker(new MarkerOptions()
                .position(ll_lif2)
                .title("Danyang Glasses")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Hot1 = aMap.addMarker(new MarkerOptions()
                .position(ll_hot1)
                .title("7 Days Inn")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));

        Hot2 = aMap.addMarker(new MarkerOptions()
                .position(ll_hot2)
                .title("DanWorldhotel Grand Dushulake")
                .icon((BitmapDescriptorFactory.fromResource(R.drawable.marker)))
                .draggable(true));



        aMap.setOnMarkerClickListener(this);
        aMap.setOnInfoWindowClickListener(this);
        aMap.setInfoWindowAdapter(this);
        //Gym1.showInfoWindow();

        // aMap.setOnMapClickListener(this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * start location
     */
    @Override
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        mListener=onLocationChangedListener;
        if (mLocationManagerProxy==null) {
            mLocationManagerProxy=LocationManagerProxy.getInstance(this);

            mLocationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, 2000, 10, this);
        }
    }

    /**
     * stop location
     */
    @Override
    public void deactivate() {
        mListener = null;
        if(mLocationManagerProxy != null) {
            mLocationManagerProxy.removeUpdates(this);
            mLocationManagerProxy.destroy();
            mLocationManagerProxy = null;
        }
    }

    /**
     * listen when location changed
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener!=null && aMapLocation!=null) {
            mListener.onLocationChanged(aMapLocation);
        }
        latitude = aMapLocation.getLatitude();
        longitude = aMapLocation.getLongitude();
    }

    /**
     * Not used yet
     */
    @Override
    public void onLocationChanged(Location location) {

    }


    /**
     * Not used yet
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    /**
     * Not used yet
     */
    @Override
    public void onProviderEnabled(String provider) {

    }

    /**
     * Not used yet
     */
    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.marker_info_window, null);
        TextView title = ((TextView) view.findViewById(R.id.infowindow_title));
        title.setText(marker.getTitle());

        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        if(marker.getTitle().equals("Inshape"))
            startActivity(new Intent(Map.this,gym1.class));
        if(marker.getTitle().equals("Impulse"))
            startActivity(new Intent(Map.this,gym2.class));
        if(marker.getTitle().equals("Powerhouse"))
            startActivity(new Intent(Map.this,gym3.class));

        if(marker.getTitle().equals("Red Connection Barbecue Buffet"))
            startActivity(new Intent(Map.this,Restaurant1.class));
        if(marker.getTitle().equals("Korea Crazy Taste Buffet Barbecue Chaffy Dish"))
            startActivity(new Intent(Map.this,Restaurant2.class));
        if(marker.getTitle().equals("Thai Delicious Cheese Cakes Hot Pot"))
            startActivity(new Intent(Map.this,Restaurant3.class));
        if(marker.getTitle().equals("Collection of Small Lamb Shop"))
            startActivity(new Intent(Map.this,Restaurant4.class));
        if(marker.getTitle().equals("Jiangnan Taste Chinese Restaurant"))
            startActivity(new Intent(Map.this,Restaurant5.class));
        if(marker.getTitle().equals("The Food in Hunan"))
            startActivity(new Intent(Map.this,Restaurant6.class));
        if(marker.getTitle().equals("Couples Fried Chicken"))
            startActivity(new Intent(Map.this,Restaurant7.class));
        if(marker.getTitle().equals("Villain Grilled Fish - Academician Edge Shop"))
            startActivity(new Intent(Map.this,Restaurant8.class));
        if(marker.getTitle().equals("The Clouds in the Sky"))
            startActivity(new Intent(Map.this,Restaurant9.class));
        if(marker.getTitle().equals("The Boot Camp"))
            startActivity(new Intent(Map.this,Restaurant10.class));
        if(marker.getTitle().equals("Kam Lepidoptera Hin"))
            startActivity(new Intent(Map.this,Restaurant11.class));

        if(marker.getTitle().equals("Dushu Lake Theater"))
            startActivity(new Intent(Map.this,theatre1.class));
        if(marker.getTitle().equals("Stellar International Cineplex"))
            startActivity(new Intent(Map.this,theatre2.class));
        if(marker.getTitle().equals("China International Film Studios"))
            startActivity(new Intent(Map.this,theatre3.class));

        if(marker.getTitle().equals("The Star KTV"))
            startActivity(new Intent(Map.this,ktv1.class));
        if(marker.getTitle().equals("Eight Songs KTV"))
            startActivity(new Intent(Map.this,ktv2.class));

        if(marker.getTitle().equals("Danyang Glasses"))
            startActivity(new Intent(Map.this,life1.class));
        if(marker.getTitle().equals("Alice Flower Shop"))
            startActivity(new Intent(Map.this,life2.class));

        if(marker.getTitle().equals("7 Days Inn"))
            startActivity(new Intent(Map.this,hotel1.class));
        if(marker.getTitle().equals("Worldhotel Grand Dushulake"))
            startActivity(new Intent(Map.this,hotel2.class));

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }





    /*@Override
    public void onMapClick(LatLng latLng) {
        if (currentMarker != null) {
            currentMarker.hideInfoWindow();
        }
    }*/

}