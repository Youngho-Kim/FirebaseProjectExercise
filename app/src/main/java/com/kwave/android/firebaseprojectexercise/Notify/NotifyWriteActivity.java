package com.kwave.android.firebaseprojectexercise.Notify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

public class NotifyWriteActivity extends AppCompatActivity {
    // 데이터베이스
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    // 스토리지
    private StorageReference mStorageRef;

    EditText editNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 데이터베이스 레퍼런스
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("남일빌라/준수 사항/");


        setContentView(R.layout.activity_notify_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.notifyWriteToolbar);
        setSupportActionBar(toolbar);

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editNotify = (EditText) findViewById(R.id.editNotify);

    }


    public void afterUploadFile(Uri imageUri){
        // 데이터 받아 올 변수 만들기
        String masterNotify = editNotify.getText().toString();

//        Log.i("FBStorage","Upload check ========= 3");

        // 파이어베이스 데이터베이스에 데이터 넣기
        // 1. 데이터 객체 생성
        MyHomeData bbs = new MyHomeData(masterNotify);

        // 이미지가 있으면 이미지 올리기
//        if(imageUri != null){
//            bbs.fileUriString = imageUri.toString();
//        }

        // 2. 입력할 데이터의 키 생성
        String bbsKey = bbsRef.push().getKey(); // 자동생성된 키를 가져온다
        // 3. 생성된 키를 레퍼런스로 데이터를 입력
        //    insert 와 update, delete 는 동일하게 동작
//        bbsRef.child("bbsKey").setValue(bbs.masterNotify);
        bbsRef.child("임차인 준수사항 확인서").setValue(bbs.masterNotify);
        //    update : bbsRef.child(bbsKey).setValue(bbs);
        //    delete : bbsRef.child(bbsKey).setValue(null);
        // 데이터 입력후 창 닫기
        finish();
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
                afterUploadFile(null);
                Intent intent = new Intent(NotifyWriteActivity.this,NotifyReadActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------



}
