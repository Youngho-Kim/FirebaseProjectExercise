package com.kwave.android.firebaseprojectexercise;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.drawable.ic_menu_edit;

public class PaymentActivity extends AppCompatActivity {
    TextView textPayMonth,  textPayRoom, textPayName, textPayCount, textPayUse, textPayCheck ;
    TabLayout payTab;
    TabItem tabMonth, tabWater;
    LocationManager manager;
    ViewPager payViewPaser;
    Fragment monthFee,waterFee;
    PagerAdapter adapter;

//    FirebaseDatabase database;
//    DatabaseReference bbsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        database = FirebaseDatabase.getInstance();
//        bbsRef = database.getReference("bbs");


        setContentView(R.layout.activity_payment);

        textPayMonth = (TextView) findViewById(R.id.textPayMonth);
        textPayRoom = (TextView) findViewById(R.id.textPayRoom);
        textPayName = (TextView) findViewById(R.id.textPayName);
        textPayCount = (TextView) findViewById(R.id.textPayCount);
        textPayUse = (TextView) findViewById(R.id.textPayUse);
        textPayCheck = (TextView) findViewById(R.id.textPayCheck);
        payTab = (TabLayout) findViewById(R.id.payTab);
        tabMonth = (TabItem) findViewById(R.id.tabMonth);
        tabWater = (TabItem) findViewById(R.id.tabWater);
        payViewPaser = (ViewPager) findViewById(R.id.payViewPaser);


        Toolbar toolbar = (Toolbar) findViewById(R.id.payToolbar);
        setSupportActionBar(toolbar);

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        toolbar.inflateMenu(R.menu.information);
        getSupportActionBar().setIcon(ic_menu_edit);


        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


//        // 1. ViewPager 위젯 연결           // 탭을 생성
//        payTab.addTab(payTab.newTab().setText("One"));
//        payTab.addTab(payTab.newTab().setText("Two"));
//        payTab.addTab(payTab.newTab().setText("Three"));
//        payTab.addTab(payTab.newTab().setText("Four"));

        // 2. Fragment 생성
        monthFee = new PaymentFragment_month();
        waterFee = new PaymentFragment_water();

        // 3. Fragment를 datas 저장소에 담은 후
        List<Fragment> datas = new ArrayList<>();
        datas.add(monthFee);
        datas.add(waterFee);

        // 4. Fragment Manager와 함께 adapter에 전달
        adapter = new PagerAdapter(getSupportFragmentManager(), datas);


        // 5. adapter를 pager 위젯에 연결
        payViewPaser.setAdapter(adapter);

        // 6. 페이저가 변경 되었을 때 탭을 변경해주는 리스너
        payViewPaser.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(payTab));


        // 7. 탭이 변경되었을 때 탭을 변경해주는 리스너
        payTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(payViewPaser));

        // 마시멜로 버전 이상에서 권한설정을 한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission();
        }




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





    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case android.R.id.custom:
                goInformation();
                return true;
            case ic_menu_edit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------


    public void goInformation(){
        Intent intent = new Intent(PaymentActivity.this,InfomationWriteActivity.class);
        startActivity(intent);
    }


//
//
//    public void loadData(){
//        bbsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot data) {
////                List<Bbs> list = new ArrayList<Bbs>();
//                Data.list.clear();
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















