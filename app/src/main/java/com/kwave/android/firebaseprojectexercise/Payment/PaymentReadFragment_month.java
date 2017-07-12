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

/**
 * A placeholder fragment containing a simple view.
 */
public class PaymentReadFragment_month extends Fragment {
    RecyclerView payReadMonthRecycler;
    PaymentReadListAdapter_month adapter;
    public PaymentReadFragment_month() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_read_month, container, false);


        List<MyHomeData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        MyHomeData bbs = new MyHomeData();
        bbs.room = 301;
        bbs.name = "kwave";
        bbs.countTenant = 3000;
        bbs.day = 4;
        bbs.checkTenant = true;
        data.add(bbs);

        // RecyclerView Setting
        payReadMonthRecycler = (RecyclerView) view.findViewById(R.id.payReadMonthRecycler);
        adapter = new PaymentReadListAdapter_month(data, getContext());
        payReadMonthRecycler.setAdapter(adapter);
        payReadMonthRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }
}
