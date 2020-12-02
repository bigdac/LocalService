package com.li.localservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.li.localservice.ScreenUtil.StatusBarUtil;
import com.li.localservice.ScreenUtil.UIUtils;
import com.li.localservice.ScreenUtil.ViewCalculateUtil;
import com.li.localservice.service.HelpService;
import com.li.localservice.service.LocalService;
import com.li.localservice.service.MyJobService;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIUtils.getInstance(getApplicationContext());
        TextView t = findViewById(R.id.tv);
        TextView t1 = findViewById(R.id.tv1);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        StatusBarUtil.setStateBar(this,toolbar);
        ViewCalculateUtil.setViewRelativeLayoutParam(t,360,1280,48,0,0,0,ViewCalculateUtil.TYPE_WIDTH);
        //ViewCalculateUtil.setViewRelativeLayoutParam(t1,540,950,10,0,0,0);
//        startService(new Intent(this, LocalService.class));
//        startService(new Intent(this, HelpService.class));
//        MyJobService.startJob(this);
    }

    //设置监听事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
