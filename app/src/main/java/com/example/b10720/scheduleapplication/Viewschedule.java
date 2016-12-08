package com.example.b10720.scheduleapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Viewschedule extends AppCompatActivity
{
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewschedule);
        MyDBHelper helper;
        helper = new MyDBHelper(this);//SQLLiteDB 객체 생성

        Intent intent = getIntent();
        String MorWorD = intent.getExtras().getString("key");
        id = intent.getExtras().getString("id");

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(MorWorD);/*값을 받아와서 액션바 이름을 바꿔줌*/

        TextView registerDate = (TextView) findViewById(R.id.view_Date);//내부에서 사용하기 위해 지역변수로 지정함
        TextView title = (TextView)findViewById(R.id.view_Title);
        TextView place = (TextView)findViewById(R.id.view_Place);
        TextView starttime = (TextView)findViewById(R.id.view_StartTime);
        TextView endtime = (TextView)findViewById(R.id.view_EndTime);
        TextView memo = (TextView)findViewById(R.id.view_Memo);

        String sql = "";
        Cursor cursor = null;
        if(MorWorD.contains("Month")){
         sql = "SELECT * FROM mschedule WHERE _id="+ id;
         cursor = helper.getReadableDatabase().rawQuery(sql,null);
        if(cursor.moveToFirst()) {
            registerDate.setText(cursor.getString(2));
            title.setText(cursor.getString(1));
            place.setText(cursor.getString(3));
            starttime.setText(cursor.getString(4));
            endtime.setText(cursor.getString(5));
            memo.setText(cursor.getString(6));
        }}
        else if(MorWorD.contains("Week")) {
            sql = "SELECT * FROM wschedule WHERE _id=" + id;
            cursor = helper.getReadableDatabase().rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                registerDate.setText(cursor.getString(2));
                title.setText(cursor.getString(1));
                place.setText(cursor.getString(3));
                starttime.setText(cursor.getString(4));
                endtime.setText(cursor.getString(5));
                memo.setText(cursor.getString(6));

            }} else if (MorWorD.contains("Day")) {
                sql = "SELECT * FROM dschedule WHERE _id=" + id;
                cursor = helper.getReadableDatabase().rawQuery(sql, null);
                if (cursor.moveToFirst()) {
                    registerDate.setText(cursor.getString(2));
                    title.setText(cursor.getString(1));
                    place.setText(cursor.getString(3));
                    starttime.setText(cursor.getString(4));
                    endtime.setText(cursor.getString(5));
                    memo.setText(cursor.getString(6));
                }

            }
        Button edit = (Button) findViewById(R.id.edit) ;
        edit.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // TODO : click event
                android.support.v7.app.ActionBar ab = getSupportActionBar();
                String MorWorD = (String) ab.getTitle();
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("key", MorWorD);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MyDBHelper helper;
                helper = new MyDBHelper(getApplication());//SQLLiteDB 객체 생성
                String sql = "";

                android.support.v7.app.ActionBar ab = getSupportActionBar();
                String MorWorD = (String) ab.getTitle();

                if(MorWorD.contains("Month"))
                {
                    Toast.makeText(getApplication(), id, Toast.LENGTH_SHORT).show();
                    sql = "DELETE FROM mschedule WHERE _id=" + id;
                     helper.getWritableDatabase().execSQL(sql);
                }
                else if(MorWorD.contains("Week"))
                {
                    Toast.makeText(getApplication(), id, Toast.LENGTH_SHORT).show();
                    sql = "DELETE FROM wschedule WHERE _id=" + id;
                    helper.getWritableDatabase().execSQL(sql);
                }
                else if(MorWorD.contains("Day"))
                {
                    Toast.makeText(getApplication(), id, Toast.LENGTH_SHORT).show();
                    sql = "DELETE FROM dschedule WHERE _id=" + id;
                    helper.getWritableDatabase().execSQL(sql);
                }
                finish();
            }

        });



        }





}
