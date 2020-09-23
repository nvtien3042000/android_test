package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailContact extends AppCompatActivity {

    private TextView tvName;
    private TextView tvPhoneNumber;
    private TextView tvSms;
    private TextView tvInf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        tvName = findViewById(R.id.tv_name);
        tvPhoneNumber = findViewById(R.id.tv_phone);
        tvSms = findViewById(R.id.tv_sms);
        tvInf = findViewById(R.id.tv_inf);

        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvPhoneNumber.setText(intent.getStringExtra("phoneNumber"));
        tvSms.setText(intent.getStringExtra("sms"));
        tvInf.setText(intent.getStringExtra("inf"));
    }
}