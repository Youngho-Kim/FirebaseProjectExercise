package com.kwave.android.firebaseprojectexercise.Notify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kwave.android.firebaseprojectexercise.R;

public class NotifyWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.notifyWriteToolbar);
        setSupportActionBar(toolbar);

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    //-----------------------------액션바에서 ReadActivity로 가기 ----------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notify_write_menu, menu);

        return true;
    }

    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.notifyWritePen:
                Intent intent = new Intent(NotifyWriteActivity.this,NotifyReadActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------



}
