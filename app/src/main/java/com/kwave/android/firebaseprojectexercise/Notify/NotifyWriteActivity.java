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

/**
 *  준수사항 WritePage
 */
public class NotifyWriteActivity extends AppCompatActivity {
    // 데이터베이스
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    // 스토리지
    private StorageReference mStorageRef;
    String reference[] = new String[6];
    EditText editNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFirebaseReference("남일빌라/준수사항/");
        setContentView(R.layout.activity_notify_write);

        InitView();
        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     *  데이터베이스 레퍼런스 설정
     * @param reference 파이어베이스에 데이터 저장경로
     */
    private void setFirebaseReference(String reference){
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference(reference);
    }


    /**
     * View 관련 사항 init
     */
    private void InitView(){
        editNotify = (EditText) findViewById(R.id.editNotify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.notifyWriteToolbar);
        setSupportActionBar(toolbar);
    }


    /**
     *  작성된 데이터를 파이어 베이스로 보내기
     * @param imageUri  이미지 파일 URI
     */
    public void afterUploadFile(Uri imageUri){
        // 데이터 받아 올 변수 만들기
        String masterNotify = editNotify.getText().toString();

        // 파이어베이스 데이터베이스에 데이터 넣기
        // 1. 데이터 객체 생성
        MyHomeData bbs = new MyHomeData(masterNotify);

        // 2. 입력할 데이터의 키 생성
        String bbsKey = bbsRef.push().getKey(); // 자동생성된 키를 가져온다
        // 3. 생성된 키를 레퍼런스로 데이터를 입력
        //    insert 와 update, delete 는 동일하게 동작
//        bbsRef.child("bbsKey").setValue(bbs.masterNotify);
        bbsRef.child("masterNotify").setValue(bbs.masterNotify);
        //    update : bbsRef.child(bbsKey).setValue(bbs);
        //    delete : bbsRef.child(bbsKey).setValue(null);
        // 데이터 입력후 창 닫기
        finish();
    }


    /**
     * 액션바 메뉴 inflate 시키기
     * @param menu  설정되는 메뉴
     * @return  생성 유무
     */
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
