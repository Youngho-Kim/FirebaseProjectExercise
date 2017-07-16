package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.GroupTenant;

import java.util.ArrayList;
import java.util.List;


public class GroupWriteFragment_tenant extends Fragment {
    List<GroupTenant> data = new ArrayList<>();
    GroupTenant bbs;
    RecyclerView groupWriteTenantRecycler;
    GroupWriteListAdapter_tenant adapter;
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    TextView textGroupWriteDayTenant;


    public GroupWriteFragment_tenant() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_write_tenant, container, false);




        textGroupWriteDayTenant = (TextView) view.findViewById(R.id.textGroupWriteDayTenant);

        makeData(6);

        // RecyclerView Setting
        groupWriteTenantRecycler = (RecyclerView) view.findViewById(R.id.groupWriteTenantRecycler);
        adapter = new GroupWriteListAdapter_tenant(data,getContext());
        groupWriteTenantRecycler.setAdapter(adapter);
        groupWriteTenantRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter.setData(data);
//        adapter.notifyDataSetChanged();

        textGroupWriteDayTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goFirebase();
            }
        });


        return view;

    }


    public void makeData(int num){
        // 리스트를 띄우기 위한 임시데이터
        if(num <=0){
            num = 1;
        }
        for(int i =1; i<=num;i++){
            GroupTenant bbs = new GroupTenant();
            bbs.room = "";
            bbs.name = "";
            bbs.countTenant = "";
            bbs.contract = "";
            data.add(bbs);
//            Log.i("DATA","====================bbs.room : " + bbs.room);
//            Log.i("DATA", "=================data.add(bbs) : " + data.add(bbs));
//            Log.i("DATA", "=================data.size() : " + data.size());
//            Log.i("DATA", "=================data.indexOf(bbs) : " + data.indexOf(bbs));

        }

    }



    public void goFirebase(){

        for(int i=0; i<adapter.getItemCount(); i++) {
            GroupTenant bbs = adapter.getItem(i);
            database = FirebaseDatabase.getInstance();
            bbsRef = database.getReference("남일빌라/세입자 관리/2017/7/세입자 정보/"+bbs.room+"/");

            bbsRef.child("호실").setValue(bbs.room);        // 내가 원하는 부분으로 입력된다.
            bbsRef.child("이름").setValue(bbs.name);
            bbsRef.child("금액(달)").setValue(bbs.countTenant);
            bbsRef.child("계약일").setValue(bbs.contract);
        }
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 여기서 부터는 RecyclerView의 어댑터 부분입니다.
     */



    public class GroupWriteListAdapter_tenant extends RecyclerView.Adapter<GroupWriteListAdapter_tenant.Holder>{
        List<GroupTenant> data = new ArrayList<>();;
        private LayoutInflater inflater;



        public GroupWriteListAdapter_tenant(List<GroupTenant> data,Context context) {
            this.data = data;
            Log.d("writeDataTenants.size()", "writeDataTenants.size : "+data.size());
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<GroupTenant> data){
            this.data = data;
        }

        @Override
        public int getItemCount() {
//            Log.d("writeDataTenants.size()", "writeDataTenants.size는 몇개인가??"+writeDataTenants.size());
            return data.size();
        }

        public GroupTenant getItem(int position) {
            return data.get(position);
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_group_write_tenant, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {

            GroupTenant bbs = data.get(position);
            Log.d("onBindViewHolder", "data.get(position)  : "+position);
            holder.setEditGroupWriteRoomTenant(bbs.room);
            holder.setEditGroupWriteNameTenant(bbs.name);
            holder.setEditGroupWriteCountTenant(bbs.countTenant);
            holder.setEditGroupWriteDayTenant(bbs.contract);
            holder.setPosition(position);
//            writeDataTenants.add(bbs);
        }

        class Holder extends RecyclerView.ViewHolder{

            private int position;
            private EditText editGroupWriteNameTenant;
            private EditText editGroupWriteCountTenant;
            private EditText editGroupWriteDayTenant;
            private EditText editGroupWriteRoomTenant;


            public Holder(View v) {
                super(v);
                editGroupWriteNameTenant = (EditText) v.findViewById(R.id.editGroupWriteNameTenant);
                editGroupWriteCountTenant = (EditText) v.findViewById(R.id.editGroupWriteCountTenant);
                editGroupWriteDayTenant = (EditText) v.findViewById(R.id.editGroupWriteDayTenant);
                editGroupWriteRoomTenant = (EditText) v.findViewById(R.id.editGroupWriteRoomTenant);

                editGroupWriteRoomTenant.addTextChangedListener(roomTenantWatcher);
                editGroupWriteNameTenant.addTextChangedListener(nameTenantWatcher);
                editGroupWriteCountTenant.addTextChangedListener(countTenantWatcher);
                editGroupWriteDayTenant.addTextChangedListener(contractTenantWatcher);

            }



//-------------------------------------EditText의 TextWatcher 실행 끝   ----------------------------------------
            // TextWatcher는 EditText를 작성하는 것을 실시간으로 어딘가에 적용하거나 저장하고 싶을때 주로 사용한다.

            TextWatcher roomTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
//                    GroupTenant bbs = data.get(position);
                    bbs = data.get(position);
                    data.get(position).room = edit.toString();
//                    writeDataTenants.add(bbs);
//                        Log.d("room", data.indexOf(bbs)+"Room 변경사항 "+bbs.room);
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if(s.equals(null) ||  s.equals("")){
                        s = "0";
                    }
                }
            };
            TextWatcher nameTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
//                    GroupTenant bbs = data.get(position);
                    bbs = data.get(position);
                    data.get(position).name = edit.toString();
//                    writeDataTenants.add(bbs);
//                    Log.d("name", data.indexOf(bbs)+"name 변경사항 "+bbs.name);
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if(s.equals(null) ||  s.equals("")){
                        s = "0";
                    }
                }
            };
            TextWatcher countTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
//                    GroupTenant bbs = data.get(position);
                    bbs = data.get(position);
                    data.get(position).countTenant = edit.toString();
//                    writeDataTenants.add(bbs);
//                    Log.d("countTenant", data.indexOf(bbs)+"countTenant 변경사항 "+bbs.countTenant);
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if(s.equals(null) ||  s.equals("")){
                        s = "0";
                    }
                }
            };
            TextWatcher contractTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
//                    GroupTenant bbs = data.get(position);
                    bbs = data.get(position);
                    data.get(position).contract = edit.toString();
//                    writeDataTenants.add(bbs);
//                    Log.d("contract", data.indexOf(bbs)+"contract 변경사항 "+bbs.contract);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if(s.equals(null) ||  s.equals("")){
                        s = "0";
                    }
                }
            };

//-------------------------------------EditText의 TextWatcher 실행 끝   ----------------------------------------




//
//            public List<GroupTenant> getData() {
//
//                return writeDataTenants;
//            }


            // --------------------Getter----------------------------------------------------------------
            public void setPosition(int position){
                this.position = position;
            }


            public String getEditGroupName() {
                return editGroupWriteNameTenant.getText().toString();
            }

            public String getEditGroupCount() {
                return editGroupWriteCountTenant.getText().toString();
            }

            public String getEditGroupDay() {
                return editGroupWriteDayTenant.getText().toString();
            }

            public String getEditGroupRoom() {
                return editGroupWriteRoomTenant.getText().toString();
            }

            // --------------------Setter----------------------------------------------------------------

            public void setEditGroupWriteNameTenant(String GroupWriteNameTenant) {
                editGroupWriteNameTenant.setText(GroupWriteNameTenant);
            }

            public void setEditGroupWriteCountTenant(String GroupWriteCountTenant) {
                editGroupWriteCountTenant.setText(GroupWriteCountTenant+"");
            }

            public void setEditGroupWriteDayTenant(String GroupWriteDayTenant) {
                editGroupWriteDayTenant.setText(GroupWriteDayTenant);
            }
            public void setEditGroupWriteRoomTenant(String GroupWriteRoomTenant) {
                editGroupWriteRoomTenant.setText(GroupWriteRoomTenant+"");

            }

        }
    }

}
