package com.example.siddhartha.attendance.activities;

/**
 * Created by Siddhartha on 10/10/2017.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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
import org.w3c.dom.Text;

import static android.R.attr.filter;
import static android.content.ContentValues.TAG;

public class View_Records extends AppCompatActivity {
    private ProgressDialog pDialog;

    EditText View_Date;
    Button View_Records;
    TextView status1;
    TextView status2;
    TextView status3;
    TextView status4;
    TextView status5;
    TextView PUCCertificatePriceText;
    String vdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_records);


        View_Records = (Button) findViewById(R.id.PUCProfileEditButton);
        View_Date = (EditText) findViewById(R.id.dated);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        // Tag used to cancel the request


        View_Records.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String tag_string_req = "req_login";

                pDialog.setMessage("Loading...");
                showDialog();

                vdate = View_Date.getText().toString().trim();


                StringRequest strReq = new StringRequest(Request.Method.GET,
                        AppConfig.USER_URL_REGISTER + "demo2/attendance" + "?transform=1&filter=Date,eq," + vdate, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Login Response: " + response);
                        hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);

                            JSONArray users = jObj.getJSONArray("attendance");
                            JSONObject user = users.getJSONObject(0);
                            String st1 = user.getString("Abhay");
                            String st2 = user.getString("Siddhartha");
                            String st3 = user.getString("Arun");
                            String st4 = user.getString("Jatin");
                            String st5 = user.getString("Rishi");

                            status1 = (TextView) findViewById(R.id.s1);
                            status2 = (TextView) findViewById(R.id.s2);
                            status3 = (TextView) findViewById(R.id.s3);
                            status4 = (TextView) findViewById(R.id.s4);
                            status5 = (TextView) findViewById(R.id.s5);

                            status1.setText(st1);
                            status2.setText(st2);
                            status3.setText(st3);
                            status4.setText(st4);
                            status5.setText(st5);

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
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
    }


    public void onBackPressed() {


                        Intent intent = new Intent(View_Records.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();

                    }


    }




