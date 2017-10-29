package com.example.siddhartha.attendance.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import static android.R.id.list;
import static android.content.ContentValues.TAG;

import com.android.volley.toolbox.Volley;
import com.example.siddhartha.attendance.R;
import com.example.siddhartha.attendance.app.AppConfig;
import com.example.siddhartha.attendance.app.AppController;

import static android.content.ContentValues.TAG;

/**
 * Created by Siddhartha on 10/9/2017.
 */

public class Register_activity extends Activity {
    private EditText Registertid;
    private EditText RegisterUsername;
    private EditText RegisterSubject;
    private EditText RegisterPassword;
    private EditText RegisterConfirmPassword;
    private Button RegisterButton;
    private ProgressDialog pDialog;
    private TextView Signin;

    private String Rtid;
    private String Rusername;
    private String RSubject;
    private String Rpassword;
    private String Cnfpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Signin=(TextView)findViewById(R.id.toregisterscreen);
        RegisterButton = (Button) findViewById(R.id.button1);
        Registertid = (EditText) findViewById(R.id.editText2);
        RegisterUsername = (EditText) findViewById(R.id.editText3);
        RegisterPassword = (EditText) findViewById(R.id.editText4);
        RegisterSubject = (EditText) findViewById(R.id.editText5);
        RegisterConfirmPassword = (EditText) findViewById(R.id.editText6);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Rtid = Registertid.getText().toString().trim();
                Rusername = RegisterUsername.getText().toString().trim();
                RSubject = RegisterSubject.getText().toString().trim();
                Rpassword = RegisterPassword.getText().toString().trim();
                Cnfpassword = RegisterConfirmPassword.toString().trim();
                // Tag used to cancel the request
                String tag_string_req = "req_register";

                pDialog.setMessage("Registering ...");
                showDialog();


                StringRequest strReq = new StringRequest(Request.Method.POST,
                        AppConfig.USER_URL_REGISTER + "demo2" +"/teacher", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response: " + response.toString());
                        hideDialog();

                        try {
                            if (response.equals("0")) {

                                Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(Register_activity.this, Login_activity.class);
                                startActivity(intent);
                                finish();
                            } else {

                                // Error occurred in registration. Get the error
                                // message
                                Toast.makeText(getApplicationContext(),
                                        "Error occured in Registration!", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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

                    @Override
                    protected Map<String, String> getParams() {
                        // Posting params to register url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("tid", Rtid);
                        params.put("username", Rusername);
                        params.put("Subject", RSubject);
                        params.put("userpassword", Rpassword);
                        return params;
                    }

                };

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(strReq,tag_string_req);

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
        Signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Register_activity.this, Login_activity.class);
                startActivity(intent);
                finish();
            }
        });




    }



}


