package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import static android.R.attr.data;

public class MyPagerAdapter extends PagerAdapter {
    private static final int NUMB_OF_PAGE = 2;
    RecyclerView recyclerView;
    List<MyHomeData> data = new ArrayList<>();
    MyHomeData bbs;

    FirebaseDatabase database;
    DatabaseReference bbsRef;

    GroupWriteListAdapter_contact groupWriteListAdapter_contact;
    GroupWriteListAdapter_tenant groupWriteListAdapter_tenant;

    int select;
    @Override
    public int getCount() {
        return NUMB_OF_PAGE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int select) {
        View view;

        makeData(1);
        if(select == 1){
            view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_group_write_contact,container,false);
            recyclerView = (RecyclerView) view.findViewById(R.id.groupWriteContactRecycler);
            groupWriteListAdapter_contact = new GroupWriteListAdapter_contact(data, container.getContext());
            recyclerView.setAdapter(groupWriteListAdapter_contact);
            recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
            loadFireBase(1);
        } else {
            view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_group_write_tenant,container,false);
            recyclerView = (RecyclerView) view.findViewById(R.id.groupWriteTenantRecycler);
            groupWriteListAdapter_tenant = new GroupWriteListAdapter_tenant(data, container.getContext());
            recyclerView.setAdapter(groupWriteListAdapter_tenant);
            recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
            loadFireBase(0);
        }

        container.addView(view);
        return view;
    }
    public void makeData(int num){
        // 리스트를 띄우기 위한 임시데이터
        if(num <=0){
            num = 1;
        }
        for(int i =1; i<=num;i++){
            MyHomeData bbs = new MyHomeData();
            bbs.room = "";
            bbs.name = "";
            bbs.phoneNumber = "";
            data.add(bbs);
        }
    }

    private void loadFireBase(final int select){
        if(select == 1){
            setFirebaseReference("남일빌라/세입자 관리/2017/7/연락처/");
        } else{
            setFirebaseReference("남일빌라/세입자 관리/2017/7/세입자 정보/");
        }
        bbsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    try {
                        bbs = item.getValue(MyHomeData.class);
                        data.add(bbs);
                        Log.i("MyHomeData" , "======================"+bbs);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                refreshList(data,select);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

    private void refreshList(List<MyHomeData> data, int select){
        if(select == 1){
            groupWriteListAdapter_contact.setData(data);
            groupWriteListAdapter_contact.notifyDataSetChanged();
        }else{
            groupWriteListAdapter_tenant.setData(data);
            groupWriteListAdapter_tenant.notifyDataSetChanged();
        }

    }

    public int getSelect(){
        return select;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public static int getNumbOfPage() {
        return NUMB_OF_PAGE;
    }



}
