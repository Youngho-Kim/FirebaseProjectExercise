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

        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("남일빌라/세입자 관리/2017/7/세입자 정보/");

        makeData(6);



        // RecyclerView Setting
        groupWriteTenantRecycler = (RecyclerView) view.findViewById(R.id.groupWriteTenantRecycler);
        textGroupWriteDayTenant = (TextView) view.findViewById(R.id.textGroupWriteDayTenant);


        adapter = new GroupWriteListAdapter_tenant(data, getContext());
        groupWriteTenantRecycler.setAdapter(adapter);
        groupWriteTenantRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();

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
            bbs = new GroupTenant();
            bbs.room = "";
            bbs.name = "";
            bbs.countTenant = "";
            bbs.contract = "";
            data.add(bbs);
        }

    }



    public void goFirebase(){
//
//            int groupWriteRoom = Integer.parseInt(getEditGroupRoom());
//            String groupWriteName = getEditGroupName();
//            int groupWriteCountTenant = Integer.parseInt(getEditGroupCount());
//            String groupWriteContract = getEditGroupDay();
//
//            bbs = new GroupTenant(groupWriteRoom,groupWriteName,groupWriteCountTenant,groupWriteContract);
        // 2. 입력할 데이터의 키 생성
//        String bbsKey = bbsRef.push().getKey(); // 자동생성된 키를 가져온다
        // 3. 생성된 키를 레퍼런스로 데이터를 입력
        //    insert 와 update, delete 는 동일하게 동작
//        bbsRef.child("bbsKey").setValue(bbs.masterNotify);        // 자동생성키로 키를 받아서 입력된다.
        bbsRef.child("호실").setValue(bbs.room);        // 내가 원하는 부분으로 입력된다.
        Log.d("bbs.room", "Room 입력사항 "+bbs.room);;
        bbsRef.child("이름").setValue(bbs.name);
        Log.d("bbs.name", "name 입력사항 "+bbs.name);;
        bbsRef.child("금액(달)").setValue(bbs.countTenant);
        Log.d("bbs.countTenant", "countTenant 입력사항 "+bbs.countTenant);;
        bbsRef.child("계약일").setValue(bbs.contract);
        Log.d("bbs.contract", "contract 입력사항 "+bbs.contract);;
        //    update : bbsRef.child(bbsKey).setValue(bbs);
        //    delete : bbsRef.child(bbsKey).setValue(null);
        // 데이터 입력후 창 닫기
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 여기서 부터는 RecyclerView의 어댑터 부분입니다.
     */



    public class GroupWriteListAdapter_tenant extends RecyclerView.Adapter<GroupWriteListAdapter_tenant.Holder>{
        List<GroupTenant> writeDataTenants = new ArrayList<>();
        private LayoutInflater inflater;



        public GroupWriteListAdapter_tenant(List<GroupTenant> data, Context context) {
            writeDataTenants = data;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<GroupTenant> data){
            writeDataTenants = data;
        }

        @Override
        public int getItemCount() {
            Log.d("writeDataTenants.size()", "writeDataTenants.size는 몇개인가??"+writeDataTenants.size());
            return writeDataTenants.size();
        }



        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_group_write_tenant, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {

            bbs = writeDataTenants.get(position);
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
//                    bbs = writeDataTenants.get(position);
                        bbs.room = edit.toString();
//                    writeDataTenants.add(bbs);
                        Log.d("room", "Room 변경사항 "+bbs.room);
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
//                    bbs = writeDataTenants.get(position);
                    bbs.name = edit.toString();
//                    writeDataTenants.add(bbs);
                    Log.d("name", "name 변경사항 "+bbs.name);
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
//                    bbs = writeDataTenants.get(position);
                    bbs.countTenant = edit.toString();
//                    writeDataTenants.add(bbs);
                    Log.d("countTenant", "countTenant 변경사항 "+bbs.countTenant);
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
//                    bbs = writeDataTenants.get(position);
                    bbs.contract = edit.toString();
//                    writeDataTenants.add(bbs);
                    Log.d("contract", "contract 변경사항 "+bbs.contract);
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
