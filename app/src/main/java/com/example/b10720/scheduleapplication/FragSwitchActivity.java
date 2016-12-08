package com.example.b10720.scheduleapplication;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class FragSwitchActivity extends AppCompatActivity {

    final M_Fragment m_Fragment = new M_Fragment();
    final W_Fragment w_Fragment = new W_Fragment();
    final D_Fragment d_Fragment = new D_Fragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_switch);

        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        /*값을 받아와서 액션바 이름이랑 플레그먼트를 바꿔줌*/
        Intent intent = getIntent();
        String MorWorD = intent.getExtras().getString("key");
        ab.setTitle(MorWorD);

        if (MorWorD.contains("Month")) {
            fragmentTransaction.replace(R.id.fragment, m_Fragment);
        }
        else if(MorWorD.contains("Week")) {
            fragmentTransaction.replace(R.id.fragment, w_Fragment);
        }
        else if(MorWorD.contains("Day")) {
            fragmentTransaction.replace(R.id.fragment, d_Fragment);
        }
        fragmentTransaction.commit();


        Button button = null;
        button = (Button)findViewById(R.id.M_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(0);
            }
        });
        button = (Button)findViewById(R.id.W_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(1);
            }
        });
        button = (Button)findViewById(R.id.D_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(2);
            }
        });
    }

    protected void switchFragment(int id) {
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        if (id == 0) {
            fragmentTransaction.replace(R.id.fragment, m_Fragment);
            ab.setTitle("Month");
        }
            else if(id == 1) {
            fragmentTransaction.replace(R.id.fragment, w_Fragment);
            ab.setTitle("Week");
        }
            else if (id == 2) {
            fragmentTransaction.replace(R.id.fragment, d_Fragment);
            ab.setTitle("Day");
        }
        fragmentTransaction.commit();
    }
}
