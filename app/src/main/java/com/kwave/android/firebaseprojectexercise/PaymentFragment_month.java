package com.kwave.android.firebaseprojectexercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwave.android.firebaseprojectexercise.domain.PayMonthData;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PaymentFragment_month extends Fragment {
    RecyclerView payMonthRecycler;
    PaymentListAdapter_month adapter;

    public PaymentFragment_month() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_month, container, false);


        List<PayMonthData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        PayMonthData bbs = new PayMonthData();
        bbs.PayRoom = "301호";
        bbs.PayName = "kwave";
        bbs.PayCount = "3000";
        bbs.PayDay = "2/4";
        bbs.PayCheck = true;
        data.add(bbs);

        // RecyclerView Setting
        payMonthRecycler = (RecyclerView) view.findViewById(R.id.payMonthRecycler);
        adapter = new PaymentListAdapter_month(data, getContext());
        payMonthRecycler.setAdapter(adapter);
        payMonthRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }


}
