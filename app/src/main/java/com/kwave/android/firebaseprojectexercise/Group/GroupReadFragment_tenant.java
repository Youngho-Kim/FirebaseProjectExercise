package com.kwave.android.firebaseprojectexercise.Group;

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
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class GroupReadFragment_tenant extends Fragment {
    RecyclerView groupReadTenantRecycler;
    GroupReadListAdapter_tenant adapter;
    Date date;
    public GroupReadFragment_tenant() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_read_tenant, container, false);


        List<MyHomeData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        MyHomeData bbs = new MyHomeData();
        bbs.room = "301";
        bbs.name = "kwave";
        bbs.countTenant= "50";
        bbs.contract = "2017.7.2 ~ 2018.7.1";
        data.add(bbs);

        // RecyclerView Setting
        groupReadTenantRecycler = (RecyclerView) view.findViewById(R.id.groupReadTenantRecycler);
        adapter = new GroupReadListAdapter_tenant(data, getContext());
        groupReadTenantRecycler.setAdapter(adapter);
        groupReadTenantRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }
}
