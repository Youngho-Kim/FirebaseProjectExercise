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
import android.widget.TextView;

import com.kwave.android.firebaseprojectexercise.R;


public class InformationActivity extends AppCompatActivity {
    static final int REQ_ADD_CONTACT = 1 ;
    TextView editAddressRead, editNameRead, editPhone, editAccountRead, editTrashRead;


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


    }

    private void setView(){
        editAddressRead = (TextView) findViewById(R.id.editAddressRead);
        editNameRead = (TextView) findViewById(R.id.editNameRead);
        editPhone = (TextView) findViewById(R.id.editPhone);
        editAccountRead = (TextView) findViewById(R.id.editAccountRead);
        editTrashRead = (TextView) findViewById(R.id.editTrashRead);
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
                Intent intent = new Intent(InformationActivity.this,InfomationWriteActivity.class);
                startActivityForResult(intent, REQ_ADD_CONTACT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQ_ADD_CONTACT) {
            if (resultCode == RESULT_OK) {
                // Information Write Activity에서 보낸 값을 세팅
                String addrWrite = intent.getStringExtra("addrWrite");
                // 주소에 해당하는 값이 들어오면 주소를 표시해줌
                if(addrWrite != null){
                    editAddressRead.setText(addrWrite);
                }


                String nameWrite = intent.getStringExtra("nameWrite");
                // 주소에 해당하는 값이 들어오면 주소를 표시해줌
                if(nameWrite != null){
                    editNameRead.setText(nameWrite);
                }


                String phoneWrite = intent.getStringExtra("phoneWrite");
                // 주소에 해당하는 값이 들어오면 주소를 표시해줌
                if(phoneWrite != null){
                    editPhone.setText(phoneWrite);
                }


                String accountWrite = intent.getStringExtra("accountWrite");
                // 주소에 해당하는 값이 들어오면 주소를 표시해줌
                if(accountWrite != null){
                    editAccountRead.setText(accountWrite);
                }


                String trashWrite = intent.getStringExtra("trashWrite");
                // 주소에 해당하는 값이 들어오면 주소를 표시해줌
                if(trashWrite != null){
                    editTrashRead.setText(trashWrite);
                }
            }
        }
    }



}
