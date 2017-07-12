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
 * Created by kwave on 2017-07-11.
 */

public class PaymentReadFragment_water extends Fragment{

    RecyclerView payReadWaterRecycler;
    PaymentReadListAdapter_water adapter;

    public PaymentReadFragment_water() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_read_water, container, false);


        List<MyHomeData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        MyHomeData bbs = new MyHomeData();
        bbs.name = "kwave";
        bbs.countWater = 3000;
        bbs.day = 8;
        bbs.room = 301;
        bbs.use = 300;
        bbs.checkWater = true;
        data.add(bbs);

        // RecyclerView Setting
        payReadWaterRecycler = (RecyclerView) view.findViewById(R.id.payReadWaterRecycler);
        adapter = new PaymentReadListAdapter_water(data, getContext());
        payReadWaterRecycler.setAdapter(adapter);
        payReadWaterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }

}
