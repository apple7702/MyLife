package com.android.andi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.andi.mylife.AppController;
import com.android.andi.mylife.MainActivity;
import com.android.andi.mylife.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {
    private String account;
    private String password;
    public static String URL_LogIn = "http://172.20.10.9/~wsh/php/login.php";
    String tag_string_login = "req_login";

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        ((Button) findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                account = ((EditText) findViewById(R.id.et_account)).getText().toString();
                password = ((EditText) findViewById(R.id.et_password)).getText().toString();

                login(account, password);
            }
        });


    }


    private void login(final String account, final String password) {

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_LogIn, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        String gender = jObj.getString("gender");
                        String nickname = jObj.getString("nickname");

                        Toast.makeText(LogIn.this, nickname + gender, Toast.LENGTH_SHORT).show();
                        sp = LogIn.this.getSharedPreferences("config", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("account", account);
                        editor.putString("gender", gender);
                        editor.putString("nickname", nickname);
                        editor.commit();
                        Intent it = new Intent(LogIn.this, MainActivity.class);
                        startActivity(it);
                        finish();


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
                params.put("phone", account);
                params.put("password", password);

                return params;
            }


        };


        AppController.getInstance().addToRequestQueue(strReq, tag_string_login);


    }


}
