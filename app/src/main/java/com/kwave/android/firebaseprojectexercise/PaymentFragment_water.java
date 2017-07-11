package com.kwave.android.firebaseprojectexercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwave.android.firebaseprojectexercise.domain.PayWaterData;

import java.util.ArrayList;
import java.util.List;


public class PaymentFragment_water extends Fragment {
    RecyclerView payWaterRecycler;
    PaymentListAdapter_water adapter;

    public PaymentFragment_water() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_water, container, false);


        List<PayWaterData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        PayWaterData bbs = new PayWaterData();
        bbs.PayName = "kwave";
        bbs.PayCount = "3000";
        bbs.PayDay = "2/4";
        bbs.PayRoom = "301호";
        bbs.PayUse = "300";
        bbs.PayCheck = true;
        data.add(bbs);

        // RecyclerView Setting
        payWaterRecycler = (RecyclerView) view.findViewById(R.id.payWaterRecycler);
        adapter = new PaymentListAdapter_water(data, getContext());
        payWaterRecycler.setAdapter(adapter);
        payWaterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }


}
