package com.example.b10720.scheduleapplication;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Add_Date_Activity extends AppCompatActivity {
    final static String TAG="SQLITEDB";
    private MyDBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);
        helper = new MyDBHelper(this);//SQLLiteDB 객체 생성

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        Intent intent = getIntent();
        String MorWorD = intent.getExtras().getString("key");
        ab.setTitle(MorWorD);/*값을 받아와서 액션바 이름을 바꿔줌*/


        TextView registerDate = (TextView) findViewById(R.id.Insert_Date);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        registerDate.setText(sdf.format(date).toString());

        Button insertButton = (Button) findViewById(R.id.Insert);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView registerDate = (TextView) findViewById(R.id.Insert_Date);//내부에서 사용하기 위해 지역변수로 지정함
                EditText title = (EditText)findViewById(R.id.Insert_Title);

                EditText place = (EditText)findViewById(R.id.Insert_Place);
                EditText starttime = (EditText)findViewById(R.id.Insert_StartTime);
                EditText endtime = (EditText)findViewById(R.id.Insert_EndTime);
                EditText memo = (EditText)findViewById(R.id.Insert_Memo);

                final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                android.support.v7.app.ActionBar ab = getSupportActionBar();
                String MorWorD = (String) ab.getTitle();



                if(MorWorD.contains("Month")){
                    String sql = String.format (
                            "INSERT INTO mschedule (_id, title, datetime, place, starttime, endtime, memo)\n"+
                                    "VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s')",//Insert SQL문 작성
                            title.getText(), registerDate.getText(), place.getText(), starttime.getText(), endtime.getText(), memo.getText());
                    helper.getWritableDatabase().execSQL(sql);
                }
                else if(MorWorD.contains("Week")){
                    String sql1 = String.format (
                            "INSERT INTO wschedule (_id, title, datetime, place, starttime, endtime, memo)\n"+
                                    "VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s')",//Insert SQL문 작성
                            title.getText(), registerDate.getText(), place.getText(), starttime.getText(), endtime.getText(), memo.getText());
                    helper.getWritableDatabase().execSQL(sql1);
                }
                else if(MorWorD.contains("Day")){
                    String sql2 = String.format (
                            "INSERT INTO dschedule (_id, title, datetime, place, starttime, endtime, memo)\n"+
                                    "VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s')",//Insert SQL문 작성
                            title.getText(), registerDate.getText(), place.getText(), starttime.getText(), endtime.getText(), memo.getText());
                    helper.getWritableDatabase().execSQL(sql2);
                }
                finish();
            }
        });

    }
}
