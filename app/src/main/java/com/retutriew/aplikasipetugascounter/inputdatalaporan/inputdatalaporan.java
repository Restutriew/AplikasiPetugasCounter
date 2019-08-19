package com.retutriew.aplikasipetugascounter.inputdatalaporan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.retutriew.aplikasipetugascounter.R;
import com.retutriew.aplikasipetugascounter.main.mainActivity;

public class inputdatalaporan extends AppCompatActivity {
    private EditText et_ID, et_nama_counter, et_pemilik,et_alamat,et_nomor_hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputdatalaporan_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String message = intent.getStringExtra(mainActivity.EXTRA_MESSAGE);
        TextView textView = (TextView)findViewById(R.id.hasil);
        et_ID = (EditText)findViewById(R.id.id);
        et_ID.setText(message);
        textView.setText(message);

    }

}
