package com.example.b10720.scheduleapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            /*인텐트 값 전달하는 코드*/
            case R.id.Month:
                intent = new Intent(this, FragSwitchActivity.class);
                intent.putExtra("key", "Month");
                startActivity(intent);
                break;
            case R.id.Week:
                intent = new Intent(this, FragSwitchActivity.class);
                intent.putExtra("key", "Week");
                startActivity(intent);
                break;
            case R.id.Day:
                intent = new Intent(this, FragSwitchActivity.class);
                intent.putExtra("key", "Day");
                startActivity(intent);
                break;
            case R.id.Add_date:
                DialogSelectOption();
                break;
        }
        return true;
    }

    private void DialogSelectOption() {
        final String showitems[] = { "월별 일정", "주별 일정", "일별 일정" };
        final String keyitems[] = { "Month", "Week", "Day" };
        final Intent[] intent = {null};
        //원인은 모르겠으나 배열로 사용해야 아래 리스너들이 사용하는 것이 가능함
        intent[0] = new Intent(MainActivity.this, Add_Date_Activity.class);
        intent[0].putExtra("key", keyitems[0]);
        //인텐트 초기화 월별로 고정 예외처리
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("일정 추가");
        ab.setSingleChoiceItems(showitems, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent[0] = new Intent(MainActivity.this, Add_Date_Activity.class);
                intent[0].putExtra("key", keyitems[which]);
            }}).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent[0]);
            }
        });
        ab.show();
    }
}
