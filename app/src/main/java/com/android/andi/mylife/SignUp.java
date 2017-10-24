package com.android.andi.mylife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    String account, nickname, password, check_password, gender;
    public static String URL_SignUp = "http://172.20.10.9/~wsh/php/signup.php";
    String tag_string_signup = "req_signup";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                account = ((EditText) findViewById(R.id.et_account)).getText().toString();
                password = ((EditText) findViewById(R.id.et_password)).getText().toString();
                check_password = ((EditText) findViewById(R.id.et_checkpassword)).getText().toString();
                nickname = ((EditText) findViewById(R.id.et_nickname)).getText().toString();
                gender = ((EditText) findViewById(R.id.et_gender)).getText().toString();

                if (password.equals(check_password)) {

                    signUp(account, password, nickname, gender, account);
                } else {


                    Toast.makeText(SignUp.this, "密码不一致", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    private void signUp(final String account, final String password, final String nickname, final String gender, final String pic) {

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_SignUp, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        sp = SignUp.this.getSharedPreferences("config", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("account", account);
                        editor.commit();
                        Intent it = new Intent(SignUp.this, MainActivity.class);
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
                params.put("nickname", nickname);
                params.put("gender", gender);
                params.put("pic", pic);

                return params;
            }


        };


        AppController.getInstance().addToRequestQueue(strReq, tag_string_signup);


    }
}
