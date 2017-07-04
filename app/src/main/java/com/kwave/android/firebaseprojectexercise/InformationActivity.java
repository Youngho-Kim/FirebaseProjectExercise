package com.kwave.android.firebaseprojectexercise;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.infoToolbar);
        setSupportActionBar(toolbar);

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.information);

        Drawable dr = getResources().getDrawable(R.drawable.pagepencilicon);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();

        Drawable drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));
        toolbar.setOverflowIcon(drawable);


    }
    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goSelect();
                return true;
            case android.R.id.custom:
                goInformation();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------


    public void goInformation(){
        Intent intent = new Intent(InformationActivity.this,InfomationWriteActivity.class);
        startActivity(intent);
    }
    public void goSelect(){
        Intent intent = new Intent(InformationActivity.this,SelectActivity.class);
        startActivity(intent);
    }
}
