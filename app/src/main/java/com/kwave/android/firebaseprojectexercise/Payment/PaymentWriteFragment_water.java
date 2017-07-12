package com.kwave.android.firebaseprojectexercise.Payment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.PayWaterData;

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


        List<PayWaterData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        PayWaterData bbs = new PayWaterData();
        bbs.payName = "kwave";
        bbs.payCountWater = 3000;
        bbs.payDay = 2;
        bbs.payRoom = 301;
        bbs.payUse = 300;
        bbs.payCheckWater = true;
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
