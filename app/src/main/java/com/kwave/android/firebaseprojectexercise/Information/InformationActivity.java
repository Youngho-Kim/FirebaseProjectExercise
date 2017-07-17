package com.kwave.android.firebaseprojectexercise.Information;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * 나는 뭐하는 놈입니다~
 */
public class InformationActivity extends AppCompatActivity {
    static final int REQ_ADD_CONTACT = 1 ;
    TextView editAddressRead, editNameRead, editPhone, editAccountRead, editTrashRead;
    ImageView infoImage;
    List<MyHomeData> data = new ArrayList<>();
    MyHomeData bbs;
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.infoToolbar);
        setSupportActionBar(toolbar);

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.information);

        Drawable dr = getResources().getDrawable(R.drawable.pagepencilicon);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();

        Drawable drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));
        toolbar.setOverflowIcon(drawable);
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("남일빌라/집 정보/");
        loadFireBase();
    }

    private void setView(){
        editAddressRead = (TextView) findViewById(R.id.editAddressRead);
        editNameRead = (TextView) findViewById(R.id.editNameRead);
        editPhone = (TextView) findViewById(R.id.editPhone);
        editAccountRead = (TextView) findViewById(R.id.editAccountRead);
        editTrashRead = (TextView) findViewById(R.id.editTrashRead);
        infoImage = (ImageView) findViewById(R.id.infoImage);
    }



    //-----------------------------액션바에서 WriteActivity로 가기 ----------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.infomation_read_menu, menu);

        return true;
    }

    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.informationReadPen:
                Intent intent = new Intent(InformationActivity.this,InformationWriteActivity.class);
                startActivityForResult(intent, REQ_ADD_CONTACT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------

    /**
     * 설명
     */
    private void loadFireBase(){
//        Query query = bbsRef.orderByChild("연락처").equalTo(location);
        ValueEventListener postListener = new ValueEventListener()  {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() >= 1) {
                    MyHomeData bbs = dataSnapshot.getValue(MyHomeData.class);
                    setData(bbs);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        bbsRef.addValueEventListener(postListener);
    }

    private void setData(MyHomeData bbs){
        Glide.with(this)
                .load(bbs.fileUriString)
                .into(infoImage);

        editAddressRead.setText(bbs.masterAddr);
        editNameRead.setText(bbs.masterName);
        editPhone.setText(bbs.masterPhoneNumber);
        editAccountRead.setText(bbs.masterAccount);
        editTrashRead.setText(bbs.masterTrash);
    }

}
