package com.kwave.android.firebaseprojectexercise.Group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.GroupContactData;

import java.util.ArrayList;
import java.util.List;


public class GroupWriteFragment_contact extends Fragment {
    RecyclerView groupWriteContactRecycler;
    GroupWriteListAdapter_contact adapter;

    public GroupWriteFragment_contact() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_write_contact, container, false);


        List<GroupContactData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        GroupContactData bbs = new GroupContactData();
        bbs.GroupName = "kwave";
        bbs.GroupPhoneContact = "010-1234-5678";
        bbs.GroupRoom = 301;
        data.add(bbs);

        // RecyclerView Setting
        groupWriteContactRecycler = (RecyclerView) view.findViewById(R.id.groupWriteContactRecycler);
        adapter = new GroupWriteListAdapter_contact(data, getContext());
        groupWriteContactRecycler.setAdapter(adapter);
        groupWriteContactRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }


}
