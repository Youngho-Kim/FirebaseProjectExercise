package com.kwave.android.firebaseprojectexercise.Group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.GroupTenantData;

import java.util.ArrayList;
import java.util.List;


public class GroupWriteFragment_tenant extends Fragment {
    RecyclerView groupWriteTenantRecycler;
    GroupWriteListAdapter_tenant adapter;

    public GroupWriteFragment_tenant() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_write_tenant, container, false);


        List<GroupTenantData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        GroupTenantData bbs = new GroupTenantData();
        bbs.groupRoom = 301;
        bbs.groupName = "kwave";
        bbs.groupCountTenant = 100;
        bbs.groupDay = 4;
        data.add(bbs);

        // RecyclerView Setting
        groupWriteTenantRecycler = (RecyclerView) view.findViewById(R.id.groupWriteTenantRecycler);
        adapter = new GroupWriteListAdapter_tenant(data, getContext());
        groupWriteTenantRecycler.setAdapter(adapter);
        groupWriteTenantRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }


}
