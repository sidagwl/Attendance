package com.example.siddhartha.attendance.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.siddhartha.attendance.R;


public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        TextView ProfileTeacherID = (TextView) findViewById(R.id.TecherID);
        TextView ProfileTeacherName = (TextView) findViewById(R.id.TeacherName);
        TextView ProfileTeacherSubject = (TextView) findViewById(R.id.Subject);


        Button MarkAttendance = (Button) findViewById(R.id.UserProfilePUCButton);
        Button ViewRecords = (Button) findViewById(R.id.UserProfileInsuranceButton);

        SharedPreferences settings = getSharedPreferences("profile",
                Context.MODE_PRIVATE);
        String ptid = settings.getString( "tid" , "");
        String pname = settings.getString( "username" , "");
        String psubject = settings.getString( "Subject" , "");


        ProfileTeacherID.setText(ptid);
        ProfileTeacherName.setText(pname);
        ProfileTeacherSubject.setText(psubject);



        MarkAttendance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, Mark_Attendance.class);
                startActivity(intent);
                finish();
            }
        });
        ViewRecords.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, View_Records.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Log out ")
                .setMessage("Are you sure you want to Log out")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ProfileActivity.this,Login_activity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
