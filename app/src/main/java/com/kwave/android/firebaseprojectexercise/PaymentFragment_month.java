package com.kwave.android.firebaseprojectexercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        payMonthRecycler = (RecyclerView) view.findViewById(R.id.payMonthRecycler);
        adapter = new PaymentListAdapter_month(getContext());
        payMonthRecycler.setAdapter(adapter);
        payMonthRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

//        loadData();


        return view;
    }


}
