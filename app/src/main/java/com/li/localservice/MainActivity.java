package com.li.localservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.li.localservice.service.HelpService;
import com.li.localservice.service.LocalService;
import com.li.localservice.service.MyJobService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, HelpService.class));
        MyJobService.startJob(this);
    }
}
