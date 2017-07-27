package com.kwave.android.firebaseprojectexercise.Payment;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PaymentWriteActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textPayWriteMonth;
    TabLayout payWriteTab;
    TabItem payWriteTabMonth, payWriteTabWater;
    LocationManager manager;
    ViewPager payWriteViewPaser;
    Fragment monthFee,waterFee;
    PaymentWriteFragment_month writeFragment_month;
    PaymentWriteFragment_month writeFragment_water;
    PagerAdapter adapter;

    ImageButton payWritePreMonth, payWriteNextMonth;
    FirebaseDatabase database;

    DatabaseReference bbsRef;
    MyHomeData myHomeData = new MyHomeData();
    List<MyHomeData> datas = new ArrayList<>();
    Calendar calendar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
        setFirebaseReference("남일빌라/납부내역/2017/");

        setContentView(R.layout.activity_payment_write);

        initView();
        setCurrentMonth();
        setViewPager();

        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    private void initView() {
        textPayWriteMonth = (TextView) findViewById(R.id.textPayWriteMonth);
        payWriteTab = (TabLayout) findViewById(R.id.payWriteTab);
        payWriteTabMonth = (TabItem) findViewById(R.id.payWriteTabMonth);
        payWriteTabWater = (TabItem) findViewById(R.id.payWriteTabWater);
        payWriteViewPaser = (ViewPager) findViewById(R.id.payWriteViewPager);
        payWritePreMonth = (ImageButton) findViewById(R.id.payWritePreMonth);
        payWriteNextMonth = (ImageButton) findViewById(R.id.payWriteNextMonth);
        payWritePreMonth.setOnClickListener(this);
        payWriteNextMonth.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.payWriteToolbar);
        setSupportActionBar(toolbar);

    }

    private void setViewPager() {

//        // 1. ViewPager 위젯 연결           // 탭을 생성
//        payTab.addTab(payTab.newTab().setText("One"));
//        payTab.addTab(payTab.newTab().setText("Two"));
//        payTab.addTab(payTab.newTab().setText("Three"));
//        payTab.addTab(payTab.newTab().setText("Four"));

        // 2. Fragment 생성
        monthFee = new PaymentWriteFragment_month();
        waterFee = new PaymentWriteFragment_water();

        // 3. Fragment를 datas 저장소에 담은 후
        List<Fragment> datas = new ArrayList<>();
        datas.add(monthFee);
        datas.add(waterFee);

        // 4. Fragment Manager와 함께 adapter에 전달
        adapter = new PagerAdapter(getSupportFragmentManager(), datas);

        // 5. adapter를 pager 위젯에 연결
        payWriteViewPaser.setAdapter(adapter);

        // 6. 페이저가 변경 되었을 때 탭을 변경해주는 리스너
        payWriteViewPaser.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(payWriteTab));

        // 7. 탭이 변경되었을 때 탭을 변경해주는 리스너
        payWriteTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(payWriteViewPaser));

        // 마시멜로 버전 이상에서 권한설정을 한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission();
        }
    }


    private void setFirebaseReference(String reference){
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference(reference);
    }

    private void loadFireBase(){
//        Query query = bbsRef.orderByChild("연락처").equalTo(location);
        ValueEventListener postListener = new ValueEventListener()  {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MyHomeData bbs= dataSnapshot.getValue(MyHomeData.class);
                textPayWriteMonth.setText(bbs.dataMonth);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        bbsRef.addValueEventListener(postListener);
    }


    private void setCurrentMonth(){
        myHomeData.dataMonth = calendar.get(Calendar.MONTH);
        Log.d("setCurrentMonth", "calendar.get(Calendar.MONTH) : " + calendar.get(Calendar.MONTH) + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
        int currentMonth = myHomeData.dataMonth;
        textPayWriteMonth.setText(currentMonth + "월");
    }

    //------------------------------------------리스트의 월 바꾸기  -------------------------------------------------------
    /**
     * textPayReadMonth의 달을 이전 달로 변경
     */
    public void SetDataPreMonth() {
        int currentMonth = myHomeData.dataMonth;
        Log.d("first", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
        if (currentMonth > 12) {
            currentMonth = calendar.get(Calendar.MONTH);   //deprecated       date.getMonth();
            Log.d("if", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
        } else if (currentMonth <= 1) {
            currentMonth = 12;
//            myHomeData.dataMonth = currentMonth;
            Log.d("elseif", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
        } else {
            currentMonth = currentMonth - 1;
//            myHomeData.dataMonth = currentMonth;
            Log.d("else", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
        }
        textPayWriteMonth.setText(currentMonth + "월");
        myHomeData.dataMonth = currentMonth;
        Log.d("last", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
    }


    /**
     * textPayReadMonth의 달을 다음 달로 변경
     */
    public void SetDataNextMonth() {
        int currentMonth = myHomeData.dataMonth;
        Log.d("first", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
        if (currentMonth < 1) {
            currentMonth = calendar.get(Calendar.MONTH);
            ;
//            myHomeData.dataMonth = currentMonth;
            Log.d("if", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
        } else if (myHomeData.dataMonth >= 12) {
            currentMonth = 1;
//            myHomeData.dataMonth = currentMonth;
            Log.d("elseif", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
        } else {
            currentMonth = currentMonth + 1;
//            myHomeData.dataMonth = currentMonth;
            Log.d("else", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
        }
        textPayWriteMonth.setText(currentMonth + "월");
        myHomeData.dataMonth = currentMonth;
        Log.d("last", "currentMonth : " + currentMonth + "/  myHomeData.dataMonth" + myHomeData.dataMonth);
    }

    public int getMonthValue(){
        int currentMonth = myHomeData.dataMonth;
        return currentMonth;
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



    //-----------------------------액션바에서 ReadActivity로 가기 ----------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.payment_write_menu, menu);
        return true;
    }

    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.paymentWritePen:
                Intent intent = new Intent(PaymentWriteActivity.this,PaymentReadActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------

    //------------------해당 월이 바뀌면서 데이터 갱신하기 ----------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.payWritePreMonth :
                SetDataPreMonth();
                break;
            case R.id.payWriteNextMonth :
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















