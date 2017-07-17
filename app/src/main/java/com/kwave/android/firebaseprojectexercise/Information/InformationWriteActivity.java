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
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("남일빌라/집 정보/");
        // 스토리지 레퍼런스
        mStorageRef = FirebaseStorage.getInstance().getReference("images");


        setContentView(R.layout.activity_infomation_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.infomationToolbar);
        setSupportActionBar(toolbar);

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


    private void setView(){
        editAddressWrite = (EditText) findViewById(R.id.editAddressWrite);
        editNameWrite = (EditText) findViewById(R.id.editNameWrite);
        editPhoneWrite = (EditText) findViewById(R.id.editPhoneWrite);
        editAccountWrite = (EditText) findViewById(R.id.editAccountWrite);
        editTrashWrite = (EditText) findViewById(R.id.editTrashWrite);
        infoImageWrite = (ImageView) findViewById(R.id.infoImageWrite);
    }
    // 데이터 전송
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
    public void afterUploadFile(Uri imageUri){
        // 데이터 받아 올 변수 만들기
        String masterAddr = editAddressWrite.getText().toString();
        String masterName = editNameWrite.getText().toString();
        String masterPhoneNumber = editPhoneWrite.getText().toString();
        String masterAccount = editAccountWrite.getText().toString();
        String masterTrash = editTrashWrite.getText().toString();

//        Log.i("FBStorage","Upload check ========= 3");

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode) {
                // 나. 이미지 선택창에서 선택된 이미지의 경로 추출
                case 100:
                    Uri imageUri = data.getData();
                    String filePath = getPathFromUri(this, imageUri);
                    infoImageWrite.setImageURI(imageUri);
                    break;
            }
        }
    }

    // Uri 에서 실제 경로 꺼내는 함수
    public static String getPathFromUri(Context context, Uri uri){
        String realPath = "";
        Cursor cursor = context.getContentResolver().query(uri,null,null,null,null);
        if(cursor.moveToNext()){
            realPath = cursor.getString(cursor.getColumnIndex("_data"));
        }
        cursor.close();
        return realPath;
    }

    //-----------------------------액션바에서 ReadActivity로 가기 ----------------------------------------
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

                // 인텐트를 통해서 작성한 글 Read Activity로 보내기
//                Intent intent = new Intent();
//                intent.putExtra("addrWrite",editAddressWrite.getText().toString());
//                intent.putExtra("nameWrite", editNameWrite.getText().toString());
//                intent.putExtra("phoneWrite", editPhoneWrite.getText().toString());
//                intent.putExtra("accountWrite", editAccountWrite.getText().toString());
//                intent.putExtra("trashWrite", editTrashWrite.getText().toString());
//                setResult(RESULT_OK, intent) ;
//                finish();


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


//
//
//
//
//    // 권한 설정을 위한 플래그
//    final int REQ_PEMISSON = 100;
//
//
//    // activity_main에 띄워놓은 위젯들을 세팅해준다.
//    ImageView image;
//    Button btnGallery, btnCamera, btnUtil;
//
//
//
//        // 위젯을 View에 연결시켜준다.
//        image = (ImageView) findViewById(R.id.image);
//        btnGallery = (Button) findViewById(R.id.btnGallery);
//        btnCamera = (Button) findViewById(R.id.btnCamera);
//        btnUtil = (Button) findViewById(R.id.btnUtil);
//
//
//        // 각 버튼에 리스너를 달아준다.
//        btnGallery.setOnClickListener(this);
//        btnCamera.setOnClickListener(this);
//        btnUtil.setOnClickListener(this);
//
//
//        // 권한 설정 전에는 버튼을 사용할 수 없게 한다.
//        btnGallery.setEnabled(false);
//        btnCamera.setEnabled(false);
//
//        // 카메라와 갤러리의 경우 마시멜로 이상에서는 권한 설정이 없으면 사용할 수 없다.
//        // 그러므로 사용을 설정해주어야 한다.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            checkPermission();
//        }
//        else{
//            init();
//        }
//
//
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void checkPermission() {
//        // 시스템에 권한 설정을 허락할 수 있는지 알아본다.
//
//        // 카메라와 갤러리가 사용하는 것은 외부 저장소와 카메라이다. 그러므로 각 항목이 권한 동의을 물어봐야한다.
//        if((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
//                (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)){
//            init();
//        }
//        else{
//            // 권한 동의를 하지 않았을 경우 권한을 요청하기 위해서 준비해야한다.
//            // 권한을 요청할 리스트를 만든다.
//            String permission[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
//            // 권한을 설정하기 위한 요청을 위해 요청할 리스트와 플래그 값을 보낸다.
//            requestPermissions(permission,REQ_PEMISSON);
//        }
//
//    }
//
//    // requestPermissions 요청에 의해서 아래 항목이 실행된다.
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        // 권한에 대한 결과가 무엇인지 알아본다.
//        // 권한 설정 요청이 받아들여지면 아래를 실행한다.
//        if(requestCode == REQ_PEMISSON){
//            // 리스트로 요청한 두가지가 사용자로부터 모두 권한 설정이 되었을 경우.
//            if((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)){
//                init();
//            }
//            else{
//                cancel();
//            }
//        }
//
//    }
//
//    private void cancel() {
//        Toast.makeText(this,"권한에 동의해야 기능을 사용할 수 있습니다.",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onClick(View v) {
//        Intent intent = null;
//        switch (v.getId()){
//            case R.id.btnGallery :
//                // 인텐트를 통해서 다른 액티비티를 호출한다. 여기서는 갤러리로 가야하므로
//                // 외부 저장소에 저장되어있는 사진을 골라야한다.
//                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                // 갤러리를 볼 수 있는 앱이 2개 이상인 경우 사용할 앱을 선택해줘야한다.
//                startActivityForResult(intent.createChooser(intent,"사용할 앱을 선택하세요"),REQ_PEMISSON);
//
//                break;
//
//            case R.id.btnCamera :
//                // 카메라를 사용하기 위해 카메라 액티비티로 이동한다.
//                takePhoto();
////                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                // 갤러리를 볼 수 있는 앱이 2개 이상인 경우 사용할 앱을 선택해줘야한다.
////                startActivityForResult(intent.createChooser(intent,"사용할 앱을 선택하세요"),REQ_CAMERA);
//                break;
//            case R.id.btnUtil :
//                // 다른 기능들을 사용하기 위해 유틸 액티비티로 이동한다.
//                intent = new Intent(this,UtilActivity.class);
//                startActivity(intent);
//                break;
//        }
//    }
//
//
//    Uri fileUri = null;
//    private void takePhoto() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    // 인텐터를 호출하면 사진을 찍을 수 있게 세팅해준다.
//        // 롤리팝 이상 버전은 권한을 획득해야함
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            File photoFile = null;
//            try {
//                photoFile = createFile();
//                if (photoFile != null) {
//                    // 마시멜로 이상 버전은 파일 프로바이더를 통해 권한을 획득
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        // 파일프로바이더에게 파일에 대한 URI를 받아온다.
//                        fileUri = FileProvider.getUriForFile(getBaseContext(), BuildConfig.APPLICATION_ID+".provider", photoFile);
//
//                    } else {   // 마시멜로우 이하 버전은 권한 없어도 됨
//                        fileUri = Uri.fromFile(photoFile);  // 마시멜로우 이하 버전에서는 파일에서 바로 가져올 수 있다.
//                    }
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//                    startActivityForResult(intent, REQ_CAMERA);
//                }
//            }
//            catch (Exception e) {
//                Toast.makeText(getBaseContext(), "사진파일 저장을 위한 임시파일을 생성할 수 없습니다.", Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
//                return;
//            }
//        }else{      // 롤리팝 미만 버전에서만 바로 실행
//            startActivityForResult(intent, REQ_CAMERA);
//        }
//    }
//
//    private File createFile() throws IOException {
//        // 임시 파일명 생성
//        String tempFilename = "TEMP" + System.currentTimeMillis();
//        // 임시 파일 저장용 디렉토리 생성
//        File tempDir = new File(Environment.getExternalStorageDirectory()+"/CameraN/");
//        if(!tempDir.exists()){
//            tempDir.mkdir();
//        }
//        // 실제 임시파일을 생성
//        File tempFile = File.createTempFile(tempFilename, ".JPG" , tempDir);
//        return tempFile;
//    }
//
//}
