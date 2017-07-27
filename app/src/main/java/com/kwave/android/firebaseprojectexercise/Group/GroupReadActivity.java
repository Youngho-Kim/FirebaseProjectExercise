package com.kwave.android.firebaseprojectexercise.Group;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;
import com.kwave.android.firebaseprojectexercise.domain.PermissionControl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.kwave.android.firebaseprojectexercise.R.id.textPayReadMonth;


public class GroupReadActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textGroupReadMonth;
    ImageButton groupReadPreMonth, groupReadNextMonth;
    TabLayout groupReadTab;
    TabItem groupReadTabTenant, groupReadTabContact;
    Menu menu;
    LocationManager manager;
    ViewPager groupReadViewPager;
    Fragment tenantFee,contactFee;
    PagerAdapter adapter;
    MyHomeData myHomeData = new MyHomeData();
    // Date 대신에 사용
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
        setContentView(R.layout.activity_group_read);
        // view Setting
        initView();
        initToolbar();
        setViewPager();
        // 월 초기화
        setCurrentMonth();
    //        database = FirebaseDatabase.getInstance();
    //        bbsRef = database.getReference("남일빌라/납부내역/2017/7/");

    }


    private void setCurrentMonth(){
        myHomeData.dataMonth = calendar.get(Calendar.MONTH);
        int currentMonth = myHomeData.dataMonth;
        Log.d("onCreat","currentMonth : " +currentMonth +"/  myHomeData.dataMonth"+myHomeData.dataMonth);
        textGroupReadMonth.setText(currentMonth+"월");
    }


    private void initView(){
        textGroupReadMonth = (TextView) findViewById(R.id.textGroupReadMonth);
        groupReadTab = (TabLayout) findViewById(R.id.groupReadTab);
        groupReadTabTenant = (TabItem) findViewById(R.id.groupReadTabTenant);
        groupReadTabContact = (TabItem) findViewById(R.id.groupReadTabContact);
        groupReadViewPager = (ViewPager) findViewById(R.id.groupReadViewPager);
        groupReadPreMonth = (ImageButton) findViewById(R.id.groupReadPreMonth);
        groupReadNextMonth = (ImageButton) findViewById(R.id.groupReadNextMonth);
        groupReadPreMonth.setOnClickListener(this);
        groupReadNextMonth.setOnClickListener(this);
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    private void initToolbar(){
        menu = (Menu) findViewById(R.id.groupReadPen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.groupReadToolbar);
        setSupportActionBar(toolbar);
        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    //-------------------------------  해당 월을 앞 뒤로 넘기기---------------------------------------------------------
    public void SetDataPreMonth(){
        int currentMonth = myHomeData.dataMonth;
        if(currentMonth > 12){
            currentMonth = (calendar.get(Calendar.MONTH))+1;
        }
        else if(currentMonth <= 1)
        {
            currentMonth = 12;
        }
        else{
            currentMonth = currentMonth-1;
        }
        textGroupReadMonth.setText(currentMonth+"월");
        myHomeData.dataMonth = currentMonth;
    }
    public void SetDataNextMonth(){
        int currentMonth = myHomeData.dataMonth;
        if(currentMonth < 1){
            currentMonth = (calendar.get(Calendar.MONTH))+1;
        }
        else if(myHomeData.dataMonth >=12)
        {
            currentMonth = 1;
        }
        else{
            currentMonth = currentMonth+1;
        }
        textGroupReadMonth.setText(currentMonth+"월");
        myHomeData.dataMonth = currentMonth;
    }


    //-------------------------------  해당 월을 앞 뒤로 넘기기 끝---------------------------------------------------------

    /**
     * viewPager 및 tabLayout 연결
     */
    private void setViewPager(){
        //        // 1. ViewPager 위젯 연결           // 탭을 생성
        //        payTab.addTab(payTab.newTab().setText("One"));
        //        payTab.addTab(payTab.newTab().setText("Two"));
        //        payTab.addTab(payTab.newTab().setText("Three"));
        //        payTab.addTab(payTab.newTab().setText("Four"));

        // 2. Fragment 생성
        tenantFee = new GroupReadFragment_tenant();
        contactFee = new GroupReadFragment_contact();

        // 3. Fragment를 datas 저장소에 담은 후
        List<Fragment> datas = new ArrayList<>();
        datas.add(tenantFee);
        datas.add(contactFee);

        // 4. Fragment Manager와 함께 adapter에 전달
        adapter = new PagerAdapter(getSupportFragmentManager(), datas);

        // 5. adapter를 pager 위젯에 연결
        groupReadViewPager.setAdapter(adapter);

        // 6. 페이저가 변경 되었을 때 탭을 변경해주는 리스너
        groupReadViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(groupReadTab));

        // 7. 탭이 변경되었을 때 탭을 변경해주는 리스너
        groupReadTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(groupReadViewPager));

//        // 마시멜로 버전 이상에서 권한설정을 한다.
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            PermissionControl.checkPermission(GroupReadActivity.this);
//        }
    }

    /**
     * 액션바 메뉴 inflate 시키기
     * @param menu  설정되는 메뉴
     * @return  생성 유무
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.group_read_menu, menu);
        return true;
    }


    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 실행----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.groupReadPen:
                Intent intent = new Intent(GroupReadActivity.this,GroupWriteActivity.class);
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

            case R.id.groupReadPreMonth :
    //                textGroupReadMonth.setText((myHomeData.dataMonth-1)+"월");
                SetDataPreMonth();
                break;
            case R.id.groupReadNextMonth :
    //                textGroupReadMonth.setText((myHomeData.dataMonth+1)+"월");
                SetDataNextMonth();
                break;
//            case R.menu.group_read_menu:
//                Intent intent = new Intent(GroupReadActivity.this,GroupWriteActivity.class);
//                startActivity(intent);
//                break;
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
}



