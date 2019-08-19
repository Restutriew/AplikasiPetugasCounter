package com.retutriew.aplikasipetugascounter.pilihan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.retutriew.aplikasipetugascounter.R;
import com.retutriew.aplikasipetugascounter.inputdatacounterbaru.inputdatacounterbaru;
import com.retutriew.aplikasipetugascounter.main.mainActivity;
import com.retutriew.aplikasipetugascounter.sessionManager.SessionManager;

import java.util.HashMap;

public class pilihan extends AppCompatActivity {
    Button buttonqr, button_input_counter;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilihan_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonqr=findViewById(R.id.scanqr);
        button_input_counter=findViewById(R.id.inputcounter);


        buttonqr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pilihan.this, mainActivity.class);
                startActivity(i);
//                finish();
            }
        });
//
        button_input_counter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pilihan.this, inputdatacounterbaru.class);
                startActivity(i);
                finish();
            }
        });

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String > user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mUsername = user.get(sessionManager.USERNAME);

//        name.setText(mName);
//        username.setText(mUsername);
//
//        btnlogout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                sessionManager.logout();
//            }
//        });

    }

}
