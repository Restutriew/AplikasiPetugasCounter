package com.retutriew.aplikasipetugascounter.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.zxing.Result;
import com.retutriew.aplikasipetugascounter.R;
import com.retutriew.aplikasipetugascounter.inputdatalaporan.inputdatalaporan;
import com.retutriew.aplikasipetugascounter.sessionManager.SessionManager;

import java.util.HashMap;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class mainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    public static final String EXTRA_MESSAGE = "com.restutriew.aplikasipetugascounter.MESSAGE";
    private ZXingScannerView mScannerView;


    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());

        Intent intent = new Intent(this, inputdatalaporan.class);
        String message = rawResult.getText();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

}