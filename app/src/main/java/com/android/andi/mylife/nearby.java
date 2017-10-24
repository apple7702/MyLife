package com.android.andi.mylife;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class nearby extends AppCompatActivity {


    private TextView test;
    public static String URL_GETNEARBY = "http://172.20.10.9/~wsh/php/getnearby.php";

    String tag_string_nearby = "req_nearby";

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        dbHelper = new DBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();


        test = (TextView)findViewById(R.id.test_nearby);
        String phone="13685221607";
        String location="";
        String created_at="";

        getNearby(phone,location,created_at);

        ArrayList<String> list=dbHelper.readAllUser(db);

        String s = "";
        //s= list.get(0);
        // test.setText(list.size());
        //for (String a:list){
        //Toast.makeText(this,a,Toast.LENGTH_SHORT).show();


        // }


    }

    public void getNearby(final String phone, final String location, final String created_at) {


        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_GETNEARBY, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite

                        JSONObject perObject = null;
                        JSONArray jsonArray = jObj.getJSONArray("users");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            perObject = jsonArray.getJSONObject(i);
                            String phone = perObject.getString("phone");
                            String gender = perObject.getString("gender");
                            String password = perObject.getString("password");
                            String nickname = perObject.getString("nickname");
                            String location = perObject.getString("location");
                            String created_at = perObject.getString("created_at");
                            String pic = perObject.getString("pic");

                            dbHelper.insertUser(db, phone, nickname, password, gender, location, created_at, pic);
                        }


                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "aaaa", Toast.LENGTH_LONG).show();
                    // handler.sendEmptyMessage(-1);

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url

                Map<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("created_at", created_at);
                params.put("location", location);

                return params;
            }


        };


        AppController.getInstance().addToRequestQueue(strReq, tag_string_nearby);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        db.close();
        //do something
    }


}

