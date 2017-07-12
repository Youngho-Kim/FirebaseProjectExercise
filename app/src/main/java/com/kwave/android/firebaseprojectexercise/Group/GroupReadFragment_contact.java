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
import java.util.List;

/**
 * Created by kwave on 2017-07-11.
 */

public class GroupReadFragment_contact extends Fragment{

    RecyclerView groupReadContactRecycler;
    GroupReadListAdapter_contact adapter;

    public GroupReadFragment_contact() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_read_contact, container, false);


        List<MyHomeData> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        MyHomeData bbs = new MyHomeData();
        bbs.room = 301;
        bbs.name = "kwave";
        bbs.phoneNumber = "010_7894-5612";
        data.add(bbs);

        // RecyclerView Setting
        groupReadContactRecycler = (RecyclerView) view.findViewById(R.id.groupReadContactRecycler);
        adapter = new GroupReadListAdapter_contact(data, getContext());
        groupReadContactRecycler.setAdapter(adapter);
        groupReadContactRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();




        return view;
    }

}
