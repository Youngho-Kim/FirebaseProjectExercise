package com.kwave.android.firebaseprojectexercise.Information;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.io.File;

/**
 * 집 정보 WritePage
 */

public class InformationWriteActivity extends AppCompatActivity {
    EditText editAddressWrite, editNameWrite, editPhoneWrite, editAccountWrite, editTrashWrite;
    ImageView infoImageWrite;
    // 데이터베이스
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    // 스토리지
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFirebaseReference("남일빌라/집 정보/");
        setFirebaseStorageReference("image");
        setContentView(R.layout.activity_infomation_write);

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setView();
        infoImageWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery(v);
            }
        });
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
     * 스토리지 레퍼런스 설정
     * @param reference 파이어베이스 스토리지 저장경로
     */
    private void setFirebaseStorageReference(String reference){
        // 스토리지 레퍼런스
        mStorageRef = FirebaseStorage.getInstance().getReference(reference);
    }

    private void setView(){
        editAddressWrite = (EditText) findViewById(R.id.editAddressWrite);
        editNameWrite = (EditText) findViewById(R.id.editNameWrite);
        editPhoneWrite = (EditText) findViewById(R.id.editPhoneWrite);
        editAccountWrite = (EditText) findViewById(R.id.editAccountWrite);
        editTrashWrite = (EditText) findViewById(R.id.editTrashWrite);
        infoImageWrite = (ImageView) findViewById(R.id.infoImageWrite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.infomationToolbar);
        setSupportActionBar(toolbar);
    }


    /**
     * 데이터 전송
     */
    public void postData(){
        String imagePath = getPackageName();
        // 이미지가 있으면 이미지 경로를 받아서 저장해야 되기 때문에
        // 이미지를 먼저 업로드 한다.
        if(imagePath != null && !"".equals(imagePath)){
            uploadFile(imagePath);
        }else{
            afterUploadFile(null);
        }
    }

    /**
     *  이미지 파일을 올리거나 적용시키기 위해 경로 설정
     * @param filePath  이미지 파일의 경로
     */
    public void uploadFile(String filePath){
        // 스마트폰에 있는 파일의 경로
        File file = new File(filePath);
        Uri uri = Uri.fromFile(file);
        // 파이어베이스에 있는 파일 경로
        String fileName = file.getName(); // + 시간값 or UUID 추가해서 만듬
        // 데이터베이스의 키 = 값과 동일한 구조
        StorageReference fileRef = mStorageRef.child(fileName);

        Log.i("FBStorage","Upload check ========= 1");

        fileRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // 파이어베이스 스토리지에 방금 업로드한 파일의 경로
                        @SuppressWarnings("VisibleForTests")
                        Uri uploadedUri = taskSnapshot.getDownloadUrl();
                        afterUploadFile(uploadedUri);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Log.e("FBStorage","Upload Fail:"+exception.getMessage());
                    }
                });
        Log.i("FBStorage","Upload check ========= 2");
    }


    /**
     *  작성된 데이터를 파이어 베이스로 보내기
     * @param imageUri  이미지 파일 URI
     */
    public void afterUploadFile(Uri imageUri){
        // 데이터 받아 올 변수 만들기
        String masterAddr = editAddressWrite.getText().toString();
        String masterName = editNameWrite.getText().toString();
        String masterPhoneNumber = editPhoneWrite.getText().toString();
        String masterAccount = editAccountWrite.getText().toString();
        String masterTrash = editTrashWrite.getText().toString();

        // 파이어베이스 데이터베이스에 데이터 넣기
        // 1. 데이터 객체 생성
        MyHomeData bbs = new MyHomeData(masterName,masterAddr,masterAccount,masterPhoneNumber,masterTrash);

        // 이미지가 있으면 이미지 올리기
        if(imageUri != null){
            bbs.fileUriString = imageUri.toString();
        }

        // 2. 입력할 데이터의 키 생성
        String bbsKey = bbsRef.push().getKey(); // 자동생성된 키를 가져온다
        // 3. 생성된 키를 레퍼런스로 데이터를 입력
        //    insert 와 update, delete 는 동일하게 동작
//        bbsRef.child("bbsKey").setValue(bbs.masterNotify);        // 자동생성키로 키를 받아서 입력된다.
        bbsRef.child("masterName").setValue(bbs.masterName);        // 내가 원하는 부분으로 입력된다.
        bbsRef.child("masterAddr").setValue(bbs.masterAddr);
        bbsRef.child("masterAccount").setValue(bbs.masterAccount);
        bbsRef.child("masterPhoneNumber").setValue(bbs.masterPhoneNumber);
        bbsRef.child("masterPhoneNumber").setValue(bbs.masterTrash);
        //    update : bbsRef.child(bbsKey).setValue(bbs);
        //    delete : bbsRef.child(bbsKey).setValue(null);
        // 데이터 입력후 창 닫기
        finish();
    }


    // 화면의 Gallery 버튼에서 자동 링크
    public void OpenGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // 가. 이미지 선택창 호출
        startActivityForResult( Intent.createChooser(intent, "앱을 선택하세요") , 100);
    }


/**
 *  Uri 에서 실제 경로 꺼내는 함수
 */
    public static String getPathFromUri(Context context, Uri uri){
        String realPath = "";
        Cursor cursor = context.getContentResolver().query(uri,null,null,null,null);
        if(cursor.moveToNext()){
            realPath = cursor.getString(cursor.getColumnIndex("_data"));
        }
        cursor.close();
        return realPath;
    }


    /**
     * 액션바 메뉴 inflate 시키기
     * @param menu  설정되는 메뉴
     * @return  생성 유무
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.infomation_write_menu, menu);

        return true;
    }


    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.informationWritePen:
                // 파이어베이스에 데이터 보내기
                postData();
                // 액티비티 이동하기
                Intent intent = new Intent(InformationWriteActivity.this, InformationActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------


}