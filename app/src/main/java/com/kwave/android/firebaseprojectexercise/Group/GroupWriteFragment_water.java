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

import java.util.ArrayList;
import java.util.List;


public class GroupWriteFragment_water extends Fragment {
    RecyclerView groupWriteWaterRecycler;
    GroupWriteListAdapter_water adapter;

    public GroupWriteFragment_water() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_write_water, container, false);


        List<GroupWaterData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        GroupWaterData bbs = new GroupWaterData();
        bbs.GroupName = "kwave";
        bbs.GroupCountWater = 3000;
        bbs.GroupDay = 2;
        bbs.GroupRoom = 301;
        bbs.GroupUse = 300;
        bbs.GroupCheckWater = true;
        data.add(bbs);

        // RecyclerView Setting
        groupWriteWaterRecycler = (RecyclerView) view.findViewById(R.id.groupWriteWaterRecycler);
        adapter = new GroupWriteListAdapter_water(data, getContext());
        groupWriteWaterRecycler.setAdapter(adapter);
        groupWriteWaterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }


}
