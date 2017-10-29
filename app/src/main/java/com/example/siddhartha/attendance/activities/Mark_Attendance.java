package com.example.siddhartha.attendance.activities;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.siddhartha.attendance.activities.Students;
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

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


/**
 * Created by Siddhartha on 10/10/2017.
 */

public class Mark_Attendance extends AppCompatActivity {

    private ProgressDialog pDialog;
    private CheckBox s1;
    private CheckBox s2;
    private CheckBox s3;
    private CheckBox s4;
    private CheckBox s5;
    private Button B1;
    private String student1;
    private String student2;
    private String student3;
    private String student4;
    private String student5;
    private EditText DATE;
    private String MDAte;
     String mtid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_attendance);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);





        B1=(Button)findViewById(R.id.btton);
        DATE = (EditText) findViewById(R.id.editText);
        s1 = (CheckBox) findViewById(R.id.checkBox);
        s2 = (CheckBox) findViewById(R.id.checkBox2);
        s3 = (CheckBox) findViewById(R.id.checkBox3);
        s4 = (CheckBox) findViewById(R.id.checkBox4);
        s5 = (CheckBox) findViewById(R.id.checkBox5);

        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String tag_string_req = "req_login";
                pDialog.setMessage("Loading...");
                showDialog();

                MDAte = DATE.getText().toString().trim();
                if (s1.isChecked())
                    student1 = "Present";
                else
                    student1 = "Absent";

                if (s2.isChecked())
                    student2 = "Present";
                else
                    student2 = "Absent";

                if (s3.isChecked())
                    student3 = "Present";
                else
                    student3 = "Absent";

                if (s4.isChecked())
                    student4 = "Present";
                else
                    student4 = "Absent";

                if (s5.isChecked())
                    student5 = "Present";
                else
                    student5 = "Absent";

                SharedPreferences settings = getSharedPreferences("profile",
                        Context.MODE_PRIVATE);
                mtid = settings.getString("tid" , "");


                StringRequest strReq = new StringRequest(Request.Method.POST,
                        AppConfig.USER_URL_REGISTER + "demo2/attendance", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response: " + response.toString());
                        hideDialog();

                        try {
                            if (response.equals("0")) {

                                Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(Mark_Attendance.this, ProfileActivity.class);
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
                        params.put("Tid", mtid);
                        params.put("Date", MDAte);
                        params.put("Abhay", student1);
                        params.put("Siddhartha", student2);
                        params.put("Arun", student3);
                        params.put("Jatin", student4);
                        params.put("Rishi", student5);
                        return params;
                    }

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

                        Intent intent = new Intent(Mark_Attendance.this,ProfileActivity.class);
                        startActivity(intent);
                        finish();

                    }


}




