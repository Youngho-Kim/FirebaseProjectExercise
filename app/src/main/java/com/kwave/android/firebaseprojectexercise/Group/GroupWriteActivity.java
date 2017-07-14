package com.kwave.android.firebaseprojectexercise.Group;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GroupWriteActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int REQ_GROUP_WRITE_TENANT = 100;
    TextView textGroupWriteMonth;
    TabLayout groupWriteTab;
    TabItem groupWriteTabTenant, groupWriteTabContact;
    LocationManager manager;
    ViewPager groupWriteViewPaser;
    Fragment tenantFee,contactFee;
    GroupWriteFragment_tenant tenantF = new GroupWriteFragment_tenant();
    PagerAdapter adapter;
    ImageButton groupWritePreMonth, groupWriteNextMonth;
    int current_month;
    GroupWriteFragment_tenant tenant1;

//
//    int GroupWriteRoomTenant;
//    String GroupWriteNameTenant;
//    int GroupWriteCountTenant;
//    String GroupWriteDayTenant;


    MyHomeData myHomeData = new MyHomeData();
    Date date = new Date();
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    List<MyHomeData> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_write);

        textGroupWriteMonth = (TextView) findViewById(R.id.textGroupWriteMonth);
        groupWriteTab = (TabLayout) findViewById(R.id.groupWriteTab);
        groupWriteTabTenant = (TabItem) findViewById(R.id.groupWriteTabTenant);
        groupWriteTabContact = (TabItem) findViewById(R.id.groupWriteTabContact);
        groupWriteViewPaser = (ViewPager) findViewById(R.id.groupWriteViewPager);
        groupWritePreMonth = (ImageButton) findViewById(R.id.groupWritePreMonth);
        groupWriteNextMonth = (ImageButton) findViewById(R.id.groupWriteNextMonth);
        groupWritePreMonth.setOnClickListener(this);
        groupWriteNextMonth.setOnClickListener(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.groupWriteToolbar);
        setSupportActionBar(toolbar);

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        toolbar.inflateMenu(R.group_read_menu.information);
//        getSupportActionBar().setIcon(ic_menu_edit);

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

//        // 1. ViewPager 위젯 연결           // 탭을 생성
//        payTab.addTab(payTab.newTab().setText("One"));
//        payTab.addTab(payTab.newTab().setText("Two"));
//        payTab.addTab(payTab.newTab().setText("Three"));
//        payTab.addTab(payTab.newTab().setText("Four"));

        // 2. Fragment 생성
        tenantFee = new GroupWriteFragment_tenant();
//        ((GroupWriteFragment_tenant) tenantFee).setParentActivity(this);

        contactFee = new GroupWriteFragment_contact();

        // 3. Fragment를 datas 저장소에 담은 후
        List<Fragment> datas = new ArrayList<>();
        datas.add(tenantFee);
        datas.add(contactFee);

        // 4. Fragment Manager와 함께 adapter에 전달
        adapter = new PagerAdapter(getSupportFragmentManager(), datas);


        // 5. adapter를 pager 위젯에 연결
        groupWriteViewPaser.setAdapter(adapter);

        // 6. 페이저가 변경 되었을 때 탭을 변경해주는 리스너
        groupWriteViewPaser.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(groupWriteTab));


        // 7. 탭이 변경되었을 때 탭을 변경해주는 리스너
        groupWriteTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(groupWriteViewPaser));

//        startFragment(tenant1);
//        goFragment(tenant1);


        myHomeData.dataMonth = date.getMonth()+1;
        int currentMonth = myHomeData.dataMonth;
        Log.d("onCreat","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        textGroupWriteMonth.setText(currentMonth+"월");
        current_month = currentMonth;
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("남일빌라/세입자 관리/2017/"+current_month+"/세입자 정보/101/");


//        loadData();

        // 마시멜로 버전 이상에서 권한설정을 한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission();
        }


    }


    public void startFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.tenantFee3, fragment);
        transaction.commit();
    }
//
//    public void goFragment(Fragment fragment) {
//        FragmentManager manager = this.getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.tenantFee3, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
//
//    public void goPrevFragment(){
//        FragmentManager manager = this.getSupportFragmentManager();
//        manager.popBackStack();
//    }
//





//    public int getQueryIndex() {
////        int temp = myHomeData.dataMonth;
//        int temp = 7;
//        Log.d("TAG","=====================================================================================");
//        return temp;
//    }

//    public void insertUsingRemote(MyHomeData myHomeData){
//        // 파이어베이스 데이터베이스에 데이터 넣기
//        if(myHomeData == null){
//            return;
//        }
//
//        // 이미지가 있으면 이미지 올리기
////        if(imageUri != null){
////            myHomeData.fileUriString = imageUri.toString();
////        }
//
//        // 2. 입력할 데이터의 키 생성
//        String bbsKey = bbsRef.push().getKey(); // 자동생성된 키를 가져온다
//        // 3. 생성된 키를 레퍼런스로 데이터를 입력
//        //    insert 와 update, delete 는 동일하게 동작
////        bbsRef.child("bbsKey").setValue(myHomeData.masterNotify);        // 자동생성키로 키를 받아서 입력된다.
//        bbsRef.child("집주인 이름").setValue(this.myHomeData.masterName);        // 내가 원하는 부분으로 입력된다.
//        bbsRef.child("집주인 주소").setValue(myHomeData.masterAddr);
//        bbsRef.child("집주인 계좌번호").setValue(myHomeData.masterAccount);
//        bbsRef.child("집주인 전화번호").setValue(myHomeData.masterPhoneNumber);
//        bbsRef.child("분리수거 안내").setValue(myHomeData.masterTrash);
//        //    update : bbsRef.child(bbsKey).setValue(myHomeData);
//        //    delete : bbsRef.child(bbsKey).setValue(null);
//        // 데이터 입력후 창 닫기
//        finish();
//    }





    public void afterUploadFile(Uri imageUri){
        // 데이터 받아 올 변수 만들기

//        Log.i("FBStorage","Upload check ========= 3");
//        int groupWriteRoom = tenantF.data.get(data.indexOf(tenantF)).room
//
//        String groupWriteName = tenantF.data.get(data.indexOf(tenantF)).name;
//        int groupWriteCountTenant = tenantF.data.get(data.indexOf(tenantF)).countTenant;

        // 파이어베이스 데이터베이스에 데이터 넣기
        // 1. 데이터 객체 생성
//        MyHomeData bbs = new MyHomeData(groupWriteRoom,groupWriteName,groupWriteCountTenant,groupWriteContract);
        MyHomeData bbs = new MyHomeData();

            Log.d("getTextWatcher.size", "몇이 나올까?? : "+tenantF.getTextWatcher().size());
            bbs.room = tenantF.getTextWatcher().get(0).room;
            bbs.name = tenantF.getTextWatcher().get(0).name;
            bbs.countTenant = tenantF.getTextWatcher().get(0).countTenant;
            bbs.contract = tenantF.getTextWatcher().get(0).contract;

        // 이미지가 있으면 이미지 올리기
//        if(imageUri != null){
//            bbs.fileUriString = imageUri.toString();
//        }

        // 2. 입력할 데이터의 키 생성
        String bbsKey = bbsRef.push().getKey(); // 자동생성된 키를 가져온다
        // 3. 생성된 키를 레퍼런스로 데이터를 입력
        //    insert 와 update, delete 는 동일하게 동작
//        bbsRef.child("bbsKey").setValue(bbs.masterNotify);        // 자동생성키로 키를 받아서 입력된다.
        bbsRef.child("호실").setValue(bbs.room);        // 내가 원하는 부분으로 입력된다.
        Log.d("bbs.room", "Room 입력사항 "+bbs.room);;
        bbsRef.child("이름").setValue(bbs.name);
        Log.d("bbs.name", "name 입력사항 "+bbs.name);;
        bbsRef.child("금액(/달)").setValue(bbs.countTenant);
        Log.d("bbs.countTenant", "countTenant 입력사항 "+bbs.countTenant);;
        bbsRef.child("계약일").setValue(bbs.contract);
        Log.d("bbs.contract", "contract 입력사항 "+bbs.contract);;
        //    update : bbsRef.child(bbsKey).setValue(bbs);
        //    delete : bbsRef.child(bbsKey).setValue(null);
        // 데이터 입력후 창 닫기
        finish();
    }
//    @Override protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (requestCode == REQ_GROUP_WRITE_TENANT) {
//            if (resultCode == RESULT_OK) {
//                // Information Write Activity에서 보낸 값을 세팅
//                GroupWriteRoomTenant = Integer.parseInt(intent.getStringExtra("GroupWriteRoomTenant"));
//
//                GroupWriteNameTenant = intent.getStringExtra("GroupWriteNameTenant");
//
//                GroupWriteCountTenant = Integer.parseInt(intent.getStringExtra("GroupWriteCountTenant"));
//
//                GroupWriteDayTenant = intent.getStringExtra("GroupWriteDayTenant");
//
//
//            }
//        }
//    }





    public void SetDataPreMonth(){
        int currentMonth = myHomeData.dataMonth;
        Log.d("first","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        if(currentMonth > 12){
            currentMonth = date.getMonth();
            Log.d("if","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        }
        else if(currentMonth <= 1)
        {
            currentMonth = 12;
//            myHomeData.dataMonth = currentMonth;
            Log.d("elseif","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        }
        else{
            currentMonth = currentMonth-1;
//            myHomeData.dataMonth = currentMonth;
            Log.d("else","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        }
        textGroupWriteMonth.setText(currentMonth+"월");
        myHomeData.dataMonth = currentMonth;
        current_month = myHomeData.dataMonth;
        Log.d("last","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
    }
    public void SetDataNextMonth(){
        int currentMonth = myHomeData.dataMonth;
        Log.d("first","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        if(currentMonth < 1){
            currentMonth = date.getMonth()+1;
//            myHomeData.dataMonth = currentMonth;
            Log.d("if","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        }
        else if(myHomeData.dataMonth >=12)
        {
            currentMonth = 1;
//            myHomeData.dataMonth = currentMonth;
            Log.d("elseif","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        }
        else{
            currentMonth = currentMonth+1;
//            myHomeData.dataMonth = currentMonth;
            Log.d("else","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        }
        textGroupWriteMonth.setText(currentMonth+"월");
        myHomeData.dataMonth = currentMonth;
        current_month = myHomeData.dataMonth;
        Log.d("last","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
    }


    // 어댑터의 데이터가 달라지면서 리스트  갱신
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }




    //-------------------------------------------권한처리---------------------------------------------------------------------------------
    private final int REQ_PERMISSION = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        // Manifest에 있는 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />가 권한 허락을 맡았는지 물어보기
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

        }
        else{   // 권한을 허락받지 않았다면 권한을 허락 받기 위해 권한요청을 한다.
            String permssion[] = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permssion,REQ_PERMISSION);
        }
    }

    // 위에서 권한요청한 requestPermissions 함수에 따라 아래 함수가 실행된다.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 권한을 요청한 코드가 해당 코드인지 확인한 후 조건이 맞으면
        if(requestCode == REQ_PERMISSION){
            // 해당하는 조건의 결과가 동의가 되었는지 확인 한 후 동의됐으면 아래를 실행한다.
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
            // 해당하는 조건의 결과가 동의가 되었는지 확인 한 후 동의되지 않았으면 아래를 실행한다.
            else{
                cancel();
            }
        }
    }

    private void cancel() {
        Toast.makeText(this,"권한을 설정하셔야 사용이 가능합니다",Toast.LENGTH_SHORT).show();
        finish();
    }

    //------------------------------------------------------------------------------------------------------------------------------------



    //-----------------------------액션바에서 WriteActivity로 가기 ----------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.group_write_menu, menu);

        return true;
    }

    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.groupWritePen:
//                adapter.notifyDataSetChanged();
                afterUploadFile(null);
//                adapter.notifyDataSetChanged();
//                tenantF.test();
                Intent intent = new Intent(GroupWriteActivity.this,GroupReadActivity.class);
                startActivity(intent);
//                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------
//------------------해당 월이 바뀌면서 데이터 갱신하기 ----------------------------------------
@Override
public void onClick(View v) {
    switch (v.getId()) {

        case R.id.groupWritePreMonth :
//            textGroupWriteMonth.setText((myHomeData.dataMonth-1)+"월");
            SetDataPreMonth();
            break;
        case R.id.groupWriteNextMonth :
//            textGroupWriteMonth.setText((myHomeData.dataMonth+1)+"월");
            SetDataNextMonth();
            break;

    }
}

    //------------------해당 월이 바뀌면서 데이터 갱신하기 끝 ----------------------------------------







    class PagerAdapter extends FragmentStatePagerAdapter {
        List<Fragment> datas;
        public PagerAdapter(FragmentManager fm, List<Fragment> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
//
////
//    public void loadData(){
//        bbsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot data) {
//                List<Bbs> list = new ArrayList<Bbs>();
//                list.clear();
//                for(DataSnapshot item : data.getChildren()){
//                    // json 데이터를 Bbs 인스턴스로 변환오류 발생 가능성 있음
//                    // 그래서 예외처리 필요
//                    try {
//                        Bbs bbs = item.getValue(Bbs.class);
//                        list.add(bbs);
//                    }catch(Exception e){
//                        Log.e("Firebase",e.getMessage());
//                    }
//                }
//                refreshList(list);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void refreshList(List<Bbs> data){
//        adapter.setData(data);
//        adapter.notifyDataSetChanged();
//    }
//
//    // 데이터 전송처리
//    public void postData(View view){
//        Intent intent = new Intent(this, WriteActivity.class);
//        startActivity(intent);
//    }
}















