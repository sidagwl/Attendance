package com.example.siddhartha.attendance.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.siddhartha.attendance.R;
import com.example.siddhartha.attendance.app.AppConfig;
import com.example.siddhartha.attendance.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * Created by Siddhartha on 10/9/2017.
 */

public class Login_activity extends AppCompatActivity{

    private TextView ToRegisterscreen;
private EditText LoginUsername;
    private EditText LoginPassword;
    private ProgressDialog pDialog;
    private String Lusername;
    private String Lpassword;
    private Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        ToRegisterscreen=(TextView)findViewById(R.id.toregisterscreen);
LoginUsername=(EditText)findViewById(R.id.editText3);
        LoginPassword=(EditText)findViewById(R.id.editText4);
LoginButton=(Button)findViewById(R.id.button);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Lusername = LoginUsername.getText().toString().trim();
               Lpassword  = LoginPassword.getText().toString().trim();

                // Tag used to cancel the request
                String tag_string_req = "req_login";

                pDialog.setMessage("Logging In ...");
                showDialog();

                StringRequest strReq = new StringRequest(Request.Method.GET,
                        AppConfig.USER_URL_REGISTER+"demo2"+"/teacher?transform=1&columns=tid,username,Subject,userpassword&filter=username,eq,"+Lusername+"&filter=userpassword,eq,"+Lpassword, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Login Response: " + response);
                        hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);

                            JSONArray users = jObj.getJSONArray("teacher");
                            JSONObject user = users.getJSONObject(0);
                            String tid = user.getString("tid");
                            String username = user.getString("username");
                            String subject = user.getString("Subject");



                            SharedPreferences settings = getSharedPreferences("profile", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("tid", tid);
                            editor.putString("username", username);
                            editor.putString("Subject", subject);
                          ;

                            editor.commit();

                            Intent intent = new Intent(Login_activity.this, ProfileActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();

                             if (Lpassword.length() < 8)
                                Toast.makeText(getApplicationContext(), "Password is less than 8 characters.", Toast.LENGTH_LONG).show();
                             else if (Lpassword.isEmpty()==true)
                                Toast.makeText(getApplicationContext(), "Password can't be empty. Please enter Password.", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Registration Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        hideDialog();
                    }
                }) {



                };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
});
        ToRegisterscreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Login_activity.this, Register_activity.class);
                startActivity(intent);
                finish();
            }



        });
    }
}