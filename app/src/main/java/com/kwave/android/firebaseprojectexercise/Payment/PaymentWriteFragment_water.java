package com.kwave.android.firebaseprojectexercise.Payment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;


public class PaymentWriteFragment_water extends Fragment {
    RecyclerView payWriteWaterRecycler;
    PaymentWriteListAdapter_water adapter;

    public PaymentWriteFragment_water() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_write_water, container, false);


        List<MyHomeData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        MyHomeData bbs = new MyHomeData();
        bbs.name = "kwave";
        bbs.countWater = 3000;
        bbs.day = 2;
        bbs.room = 301;
        bbs.use = 300;
        bbs.checkWater = true;
        data.add(bbs);

        // RecyclerView Setting
        payWriteWaterRecycler = (RecyclerView) view.findViewById(R.id.payWriteWaterRecycler);
        adapter = new PaymentWriteListAdapter_water(data, getContext());
        payWriteWaterRecycler.setAdapter(adapter);
        payWriteWaterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }


}
