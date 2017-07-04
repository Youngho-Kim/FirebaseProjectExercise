package com.kwave.android.firebaseprojectexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class InfomationWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.infomationToolbar);
        setSupportActionBar(toolbar);

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goInformation();
                return true;
            case android.R.id.button1:
                goInformation();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------

    public void goInformation(){
        Intent intent = new Intent(InfomationWriteActivity.this,InformationActivity.class);
        startActivity(intent);
    }
}

