package com.kwave.android.firebaseprojectexercise.Payment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.PayMonthData;

import java.util.ArrayList;
import java.util.List;


public class PaymentWriteFragment_month extends Fragment {
    RecyclerView payWriteMonthRecycler;
    PaymentWriteListAdapter_month adapter;

    public PaymentWriteFragment_month() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_write_month, container, false);


        List<PayMonthData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        PayMonthData bbs = new PayMonthData();
        bbs.payRoom = 301;
        bbs.payName = "kwave";
        bbs.payCountMonth = 3000;
        bbs.payDay = 4;
        bbs.payCheckMonth = true;
        data.add(bbs);

        // RecyclerView Setting
        payWriteMonthRecycler = (RecyclerView) view.findViewById(R.id.payWriteMonthRecycler);
        adapter = new PaymentWriteListAdapter_month(data, getContext());
        payWriteMonthRecycler.setAdapter(adapter);
        payWriteMonthRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }


}
