package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;


public class GroupWriteActivity extends AppCompatActivity {

    private AppBarLayout groupWriteAppBarLayout;
    private Toolbar groupWriteToolbar;
    private LinearLayout groupWriteLinearLayout;
    private ImageButton groupWritePreMonth;
    private TextView textGroupWriteMonth;
    private ImageButton groupWriteNextMonth;
    private TabLayout groupWriteTab;
    private TabItem groupWriteTabTenant;
    private TabItem groupWriteTabContact;
    private ViewPager groupWriteViewPager;
    MyPagerAdapter adapter;
    List<MyHomeData> data = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference bbsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_write);

        initView();
        initToolbar();
        setViewPager();

        if(adapter.getSelect() == 1){
            setFirebaseReference("남일빌라/세입자 관리/2017/7/연락처/");
        } else{
            setFirebaseReference("남일빌라/세입자 관리/2017/7/세입자 정보/");
        }
    }


    private void initView() {
        groupWriteAppBarLayout = (AppBarLayout) findViewById(R.id.groupWriteAppBarLayout);
        groupWriteToolbar = (Toolbar) findViewById(R.id.groupWriteToolbar);
        groupWriteLinearLayout = (LinearLayout) findViewById(R.id.groupWriteLinearLayout);
        groupWritePreMonth = (ImageButton) findViewById(R.id.groupWritePreMonth);
        textGroupWriteMonth = (TextView) findViewById(R.id.textGroupWriteMonth);
        groupWriteNextMonth = (ImageButton) findViewById(R.id.groupWriteNextMonth);
        groupWriteTab = (TabLayout) findViewById(R.id.groupWriteTab);
        groupWriteTabTenant = (TabItem) findViewById(R.id.groupWriteTabTenant);
        groupWriteTabContact = (TabItem) findViewById(R.id.groupWriteTabContact);
        groupWriteViewPager = (ViewPager) findViewById(R.id.groupWriteViewPager);
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.groupWriteToolbar);
        setSupportActionBar(toolbar);
        // 툴바에 뒤로가기 버튼 보이게 하기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setViewPager(){
        adapter = new MyPagerAdapter();
        groupWriteViewPager.setAdapter(adapter);
        groupWriteViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(groupWriteTab));
        groupWriteTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(groupWriteViewPager));
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
     * 액션바 메뉴 inflate 시키기
     * @param menu  설정되는 메뉴
     * @return  생성 유무
     */
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
                goFirebase();
//                tenantC.uploadFirebase();   // attach & transaction을 해야함        - fragment가 화면에 붙어야 new가 됨
                Intent intent = new Intent(GroupWriteActivity.this,GroupReadActivity.class);
                startActivity(intent);
//                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //------------------툴바에서 뒤로가기 버튼 추가 및 뒤로가기 끝----------------------------------------
    public void goFirebase(){
        if(adapter.getSelect() == 1){
//            setFirebaseReference("남일빌라/세입자 관리/2017/7/연락처/");
            for(int i=0; i<adapter.groupWriteListAdapter_tenant.getItemCount(); i++) {
                MyHomeData bbs = adapter.groupWriteListAdapter_tenant.getItem(i);
                setFirebaseReference("남일빌라/세입자 관리/2017/7/연락처/"+bbs.room+"/");
                bbsRef.child("room").setValue(bbs.room);        // 내가 원하는 부분으로 입력된다.
                bbsRef.child("name").setValue(bbs.name);
                bbsRef.child("phoneNumber").setValue(bbs.phoneNumber);
            }
        } else {
//            setFirebaseReference("남일빌라/세입자 관리/2017/7/세입자 정보/");
            for (int i = 0; i < adapter.groupWriteListAdapter_tenant.getItemCount(); i++) {
                MyHomeData bbs = adapter.groupWriteListAdapter_tenant.getItem(i);
                bbsRef.child("room").setValue(bbs.room);        // 내가 원하는 부분으로 입력된다.
                bbsRef.child("name").setValue(bbs.name);
                bbsRef.child("countTenant").setValue(bbs.countTenant);
                bbsRef.child("contract").setValue(bbs.contract);

            }
        }
    }
}
















