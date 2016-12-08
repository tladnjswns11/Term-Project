package com.example.b10720.scheduleapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.id;

public class EditActivity extends AppCompatActivity {

    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        Intent intent = getIntent();
        String MorWorD = intent.getExtras().getString("key");
        id = intent.getExtras().getString("id");

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(MorWorD);/*값을 받아와서 액션바 이름을 바꿔줌*/


        Button editbtn = (Button)findViewById(R.id.edit_complete);


        editbtn.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // TODO : click event
                android.support.v7.app.ActionBar ab = getSupportActionBar();
                String MorWorD = (String) ab.getTitle();

                MyDBHelper helper;
                helper = new MyDBHelper(getApplication());//SQLLiteDB 객체 생성

                TextView registerDate = (TextView) findViewById(R.id.edit_Date);//내부에서 사용하기 위해 지역변수로 지정함
                EditText title = (EditText)findViewById(R.id.edit_Title);
                EditText place = (EditText)findViewById(R.id.edit_Place);
                EditText starttime = (EditText)findViewById(R.id.edit_StartTime);
                EditText endtime = (EditText)findViewById(R.id.edit_EndTime);
                EditText memo = (EditText)findViewById(R.id.edit_Memo);

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                registerDate.setText(sdf.format(date).toString());

                String sql = "";

                if (MorWorD.contains("Month")) {
                    sql = String.format(
                            "UPDATE  mschedule\n" +
                                    "SET title = '%s',\n" +
                            "place = '%s',"+
                            "starttime = '%s',"+
                            "endtime = '%s',"+
                            "memo = '%s'"+
                                    "WHERE _id='%s'",
                            title.getText(), place.getText(), starttime.getText(), endtime.getText(), memo.getText(), id);
                    helper.getWritableDatabase().execSQL(sql);

                }
                if (MorWorD.contains("Week"))
                {
                    sql = String.format(
                            "UPDATE  wschedule\n" +
                                    "SET title = '%s',\n" +
                                    "place = '%s',"+
                                    "starttime = '%s',"+
                                    "endtime = '%s',"+
                                    "memo = '%s'"+
                                    "WHERE _id='%s'",
                            title.getText(), place.getText(), starttime.getText(), endtime.getText(), memo.getText(), id);
                    helper.getWritableDatabase().execSQL(sql);
                }
                if (MorWorD.contains("Day"))
                {

                    sql = String.format(
                            "UPDATE  dschedule\n" +
                                    "SET title = '%s',\n" +
                                    "place = '%s',"+
                                    "starttime = '%s',"+
                                    "endtime = '%s',"+
                                    "memo = '%s'"+
                                    "WHERE _id='%s'",
                            title.getText(), place.getText(), starttime.getText(), endtime.getText(), memo.getText(), id);
                    helper.getWritableDatabase().execSQL(sql);

                }

                finish();
            }
        });



    }


}
