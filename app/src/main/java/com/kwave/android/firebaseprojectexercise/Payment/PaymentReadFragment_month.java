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


        List<PayMonthData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        PayMonthData bbs = new PayMonthData();
        bbs.PayRoom = 301;
        bbs.PayName = "kwave";
        bbs.PayCountMonth = 3000;
        bbs.PayDay = 4;
        bbs.PayCheckMonth = true;
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
