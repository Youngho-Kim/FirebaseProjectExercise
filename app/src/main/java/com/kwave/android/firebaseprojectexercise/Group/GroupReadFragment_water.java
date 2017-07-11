package com.kwave.android.firebaseprojectexercise.Group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.GroupWaterData;
import com.kwave.android.firebaseprojectexercise.domain.PayWaterData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-11.
 */

public class GroupReadFragment_water extends Fragment{

    RecyclerView groupReadWaterRecycler;
    GroupReadListAdapter_water adapter;

    public GroupReadFragment_water() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_read_water, container, false);


        List<GroupWaterData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        GroupWaterData bbs = new GroupWaterData();
        bbs.GroupName = "kwave";
        bbs.GroupCountWater = 3000;
        bbs.GroupDay = 8;
        bbs.GroupRoom = 301;
        bbs.GroupUse = 300;
        bbs.GroupCheckWater = true;
        data.add(bbs);

        // RecyclerView Setting
        groupReadWaterRecycler = (RecyclerView) view.findViewById(R.id.groupReadWaterRecycler);
        adapter = new GroupReadListAdapter_water(data, getContext());
        groupReadWaterRecycler.setAdapter(adapter);
        groupReadWaterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }

}
