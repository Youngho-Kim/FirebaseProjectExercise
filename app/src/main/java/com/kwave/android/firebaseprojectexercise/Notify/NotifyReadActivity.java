package com.kwave.android.firebaseprojectexercise.Notify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;

public class NotifyReadActivity extends AppCompatActivity {
    TextView textNotify;
    List<MyHomeData> data = new ArrayList<>();
    MyHomeData bbs;
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_read);
        Toolbar toolbar = (Toolbar) findViewById(R.id.notifyReadToolbar);
        textNotify = (TextView) findViewById(R.id.textNotify);
        setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("남일빌라/준수사항/");

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadFireBase();

    }




    //-----------------------------액션바에서 WriteActivity로 가기 ----------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notify_read_menu, menu);

        return true;
    }

    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.notifyReadPen:
                Intent intent = new Intent(NotifyReadActivity.this,NotifyWriteActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------


    private void loadFireBase(){
//        Query query = bbsRef.orderByChild("연락처").equalTo(location);
        ValueEventListener postListener = new ValueEventListener()  {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               MyHomeData bbs= dataSnapshot.getValue(MyHomeData.class);
                textNotify.setText(bbs.masterNotify);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        bbsRef.addValueEventListener(postListener);
    }


}
