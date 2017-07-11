package com.kwave.android.firebaseprojectexercise.Group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.GroupMonthData;

import java.util.ArrayList;
import java.util.List;


public class GroupWriteFragment_month extends Fragment {
    RecyclerView groupWriteMonthRecycler;
    GroupWriteListAdapter_month adapter;

    public GroupWriteFragment_month() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_write_month, container, false);


        List<GroupMonthData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        GroupMonthData bbs = new GroupMonthData();
        bbs.GroupRoom = 301;
        bbs.GroupName = "kwave";
        bbs.GroupCountMonth = 3000;
        bbs.GroupDay = 4;
        bbs.GroupCheckMonth = true;
        data.add(bbs);

        // RecyclerView Setting
        groupWriteMonthRecycler = (RecyclerView) view.findViewById(R.id.groupWriteMonthRecycler);
        adapter = new GroupWriteListAdapter_month(data, getContext());
        groupWriteMonthRecycler.setAdapter(adapter);
        groupWriteMonthRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }


}
