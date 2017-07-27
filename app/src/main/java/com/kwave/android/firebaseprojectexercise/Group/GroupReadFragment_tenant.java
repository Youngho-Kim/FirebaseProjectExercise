package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class GroupReadFragment_tenant extends Fragment {
    List<MyHomeData> data = new ArrayList<>();
    MyHomeData bbs;
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    RecyclerView groupReadTenantRecycler;
    GroupReadListAdapter_tenant adapter;

    public GroupReadFragment_tenant() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_read_tenant, container, false);
        groupReadTenantRecycler = (RecyclerView) view.findViewById(R.id.groupReadTenantRecycler);
        setFirebaseReference("남일빌라/세입자 관리/2017/7/세입자 정보/");
        setRecycler();
        loadFireBase();
        return view;
    }


    /**
     *  데이터베이스 레퍼런스 설정
     * @param reference 파이어베이스에 데이터 저장경로
     */
    private void setFirebaseReference(String reference){
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference(reference);
    }


    /**
     * RecyclerView Setting
     */
    private void setRecycler(){
        adapter = new GroupReadListAdapter_tenant(data, getContext());
        groupReadTenantRecycler.setAdapter(adapter);
        groupReadTenantRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter.setData(data);
//        adapter.notifyDataSetChanged();
    }


    private void loadFireBase(){
        bbsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    try {
                        bbs = item.getValue(MyHomeData.class);
                        data.add(bbs);
                        Log.i("MyHomeData" , "======================"+bbs);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                refreshList(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void refreshList(List<MyHomeData> data){
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 여기서 부터는 RecyclerView의 어댑터 부분입니다.
     */

    public class GroupReadListAdapter_tenant extends RecyclerView.Adapter<GroupReadListAdapter_tenant.Holder>{
        private List<MyHomeData> data = new ArrayList<>();
        private LayoutInflater inflater;
        Context context = null;

        public GroupReadListAdapter_tenant(List<MyHomeData> data, Context context) {
            this.data = data;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<MyHomeData> data){
            this.data = data;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(context == null){
                this.context = parent.getContext();
            }
            View view = inflater.inflate(R.layout.item_group_read_tenant, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            MyHomeData bbs = data.get(position);
            holder.setGroupWriteNameTenant(bbs.name);
            holder.setGroupWriteCountTenant(bbs.countTenant);
            holder.setGroupWriteDayTenant(bbs.contract);
            holder.setGroupWriteRoomTenant(bbs.room);
            holder.setPosition(position);
        }

        class Holder extends RecyclerView.ViewHolder {
            private int position;
            private TextView textGroupReadRoomTenant;
            private TextView textGroupReadNameTenant;
            private TextView textGroupReadCountTenant;
            private TextView textGroupReadDayTenant;

            public Holder(View v) {
                super(v);
                textGroupReadNameTenant = (TextView) v.findViewById(R.id.textGroupReadNameTenant);
                textGroupReadCountTenant = (TextView) v.findViewById(R.id.textGroupReadCountTenant);
                textGroupReadDayTenant = (TextView) v.findViewById(R.id.textGroupReadDayTenant);
                textGroupReadRoomTenant = (TextView) v.findViewById(R.id.textGroupReadRoomTenant);
            }
            public void setPosition(int position){
                this.position = position;
            }


            public void setGroupWriteNameTenant(String GroupWriteNameTenant) {
                textGroupReadNameTenant.setText(GroupWriteNameTenant);
            }

            public void setGroupWriteCountTenant(String GroupWriteCountTenant) {
                textGroupReadCountTenant.setText(GroupWriteCountTenant+"만원");
            }

            public void setGroupWriteDayTenant(String GroupWriteDayTenant) {
                textGroupReadDayTenant.setText(GroupWriteDayTenant);
            }
            public void setGroupWriteRoomTenant(String GroupWriteRoomTenant) {
                textGroupReadRoomTenant.setText(GroupWriteRoomTenant+"호");
            }

        }
    }
}
