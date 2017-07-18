package com.kwave.android.firebaseprojectexercise.Payment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * Created by kwave on 2017-07-11.
 */

public class PaymentReadFragment_water extends Fragment{

    RecyclerView payReadWaterRecycler;
    PaymentReadListAdapter_water adapter;
    List<MyHomeData> data = new ArrayList<>();
    MyHomeData bbs;
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    public PaymentReadFragment_water() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_read_water, container, false);
        setFirebaseReference("남일빌라/납부내역/2017/7/수도세/");
        // RecyclerView Setting
        payReadWaterRecycler = (RecyclerView) view.findViewById(R.id.payReadWaterRecycler);
        setRecyclerView();
        loadFireBase();
        return view;
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
     * RecyclerView Setting
     */
    private void setRecyclerView(){
        adapter = new PaymentReadListAdapter_water(data, getContext());
        payReadWaterRecycler.setAdapter(adapter);
        payReadWaterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    /**
     * Firebase에서 데이터 가져오기
     */
    private void loadFireBase(){
//        Query query = bbsRef.orderByChild("연락처").equalTo(location);
        bbsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
//                DataMyHomeData.list.clear();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    try {
                        bbs = item.getValue(MyHomeData.class);
                        data.add(bbs);
                        Log.i("MyHomeData" , "======================"+bbs);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
//                dialogInterface.dismiss();
                refreshList(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     *  들어온 데이터로 데이터 변경
     * @param data 새로 변경될 데이터
     */
    private void refreshList(List<MyHomeData> data){
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

}

