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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;


public class GroupWriteFragment_tenant extends Fragment {
    List<MyHomeData> data = new ArrayList<>();
    RecyclerView groupWriteTenantRecycler;
    GroupWriteListAdapter_tenant adapter;
    FirebaseDatabase database;
    DatabaseReference bbsRef;


    public GroupWriteFragment_tenant() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("남일빌라/세입자 관리/2017/7/세입자 정보/101/");
        View view = inflater.inflate(R.layout.fragment_group_write_tenant, container, false);

        // 리스트를 띄우기 위한 임시데이터
        MyHomeData bbs = new MyHomeData();
        bbs.room = 301;
        bbs.name = "kwave";
        bbs.countTenant = 100;
        bbs.contract = "2017.7.2 ~ 2018.7.1";
        data.add(bbs);


        // RecyclerView Setting
        groupWriteTenantRecycler = (RecyclerView) view.findViewById(R.id.groupWriteTenantRecycler);
        adapter = new GroupWriteListAdapter_tenant(data, getContext());
        groupWriteTenantRecycler.setAdapter(adapter);
        groupWriteTenantRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();


        adapter.

        return view;

    }


        public List<MyHomeData> getTextWatcher( ){
            return data;
        }




//
//
//    public void afterUploadFile(Uri imageUri){
//        // 데이터 받아 올 변수 만들기
//        String masterAddr = data.;
//        String masterName = editNameWrite.getText().toString();
//        String masterPhoneNumber = editPhoneWrite.getText().toString();
//        String masterAccount = editAccountWrite.getText().toString();
//        String masterTrash = editTrashWrite.getText().toString();
//
////        Log.i("FBStorage","Upload check ========= 3");
//
//        // 파이어베이스 데이터베이스에 데이터 넣기
//        // 1. 데이터 객체 생성
//        MyHomeData bbs = new MyHomeData(masterName,masterAddr,masterAccount,masterPhoneNumber,masterTrash);
//
//        // 이미지가 있으면 이미지 올리기
////        if(imageUri != null){
////            bbs.fileUriString = imageUri.toString();
////        }
//
//        // 2. 입력할 데이터의 키 생성
//        String bbsKey = bbsRef.push().getKey(); // 자동생성된 키를 가져온다
//        // 3. 생성된 키를 레퍼런스로 데이터를 입력
//        //    insert 와 update, delete 는 동일하게 동작
////        bbsRef.child("bbsKey").setValue(bbs.masterNotify);        // 자동생성키로 키를 받아서 입력된다.
//        bbsRef.child("집주인 이름").setValue(bbs.masterName);        // 내가 원하는 부분으로 입력된다.
//        bbsRef.child("집주인 주소").setValue(bbs.masterAddr);
//        bbsRef.child("집주인 계좌번호").setValue(bbs.masterAccount);
//        bbsRef.child("집주인 전화번호").setValue(bbs.masterPhoneNumber);
//        bbsRef.child("분리수거 안내").setValue(bbs.masterTrash);
//        //    update : bbsRef.child(bbsKey).setValue(bbs);
//        //    delete : bbsRef.child(bbsKey).setValue(null);
//        // 데이터 입력후 창 닫기
//        finish();
//    }



    class GroupWriteListAdapter_tenant extends RecyclerView.Adapter<GroupWriteListAdapter_tenant.Holder>{
        private List<MyHomeData> datas = new ArrayList<>();
        private LayoutInflater inflater;



        public GroupWriteListAdapter_tenant(List<MyHomeData> data1, Context context) {
            data = data1;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<MyHomeData> data1){
            data = data1;
        }

        @Override
        public int getItemCount() {

            return data.size();
        }



        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_group_write_tenant, parent, false);

            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {

            MyHomeData bbs = data.get(position);
            holder.setEditGroupWriteRoomTenant(bbs.room);
            holder.setEditGroupWriteNameTenant(bbs.name);
            holder.setEditGroupWriteCountTenant(bbs.countTenant);
            holder.setEditGroupWriteDayTenant(bbs.contract);
            holder.setPosition(position);
        }

        class Holder extends RecyclerView.ViewHolder{

            private int position;
            private EditText editGroupWriteNameTenant;
            private EditText editGroupWriteCountTenant;
            private EditText editGroupWriteDayTenant;
            private EditText editGroupWriteRoomTenant;



            TextWatcher roomTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    MyHomeData homeData = data.get(position);
                    try {
                        homeData.room = Integer.parseInt(edit.toString());
                        data.add(homeData);
                        Log.d("room", "Room 변경사항 "+homeData.room);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                }
            };
            TextWatcher nameTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
//                    MyHomeData homeData = data.get(position);
                    MyHomeData homeData = data.get(position);
                    try {
                        homeData.name = edit.toString();
                        data.add(homeData);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                }
            };
            TextWatcher countTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    MyHomeData homeData = data.get(position);
                    try {
                        homeData.countTenant = Integer.parseInt(edit.toString());
                        data.add(homeData);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
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
                    MyHomeData homeData = data.get(position);
                    try {
                        homeData.contract = edit.toString();
                        data.add(homeData);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                }
            };

//-------------------------------------EditText의 TextWatcher 실행


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

//            v.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), GroupWriteActivity.class);
//
//
//
//                    v.getContext().startActivity(intent);
//                 }
//            });

            }

            public List<MyHomeData> getData() {
                return data;
            }



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



            public void setEditGroupWriteNameTenant(String GroupWriteNameTenant) {
                editGroupWriteNameTenant.setText(GroupWriteNameTenant);
            }

            public void setEditGroupWriteCountTenant(int GroupWriteCountTenant) {
                editGroupWriteCountTenant.setText(GroupWriteCountTenant+"");
            }

            public void setEditGroupWriteDayTenant(String GroupWriteDayTenant) {
                editGroupWriteDayTenant.setText(GroupWriteDayTenant);
            }
            public void setEditGroupWriteRoomTenant(int GroupWriteRoomTenant) {
                editGroupWriteRoomTenant.setText(GroupWriteRoomTenant+"");
            }

//
//            public void setTest() {
//                // 데이터 받아 올 변수 만들기
//                int GroupWriteRoomTenant = Integer.parseInt(editGroupWriteRoomTenant.getText().toString());
//                String GroupWriteNameTenant = editGroupWriteNameTenant.getText().toString();
//                int GroupWriteCountTenant = Integer.parseInt(editGroupWriteCountTenant.getText().toString());
//                String GroupWriteDayTenant = editGroupWriteDayTenant.getText().toString();
//
////        Log.i("FBStorage","Upload check ========= 3");
//
//                // 파이어베이스 데이터베이스에 데이터 넣기
//                // 1. 데이터 객체 생성
//                MyHomeData bbs = new MyHomeData(GroupWriteRoomTenant, GroupWriteNameTenant,GroupWriteCountTenant, GroupWriteDayTenant);
//                data.add(bbs);
//                Log.d("data","data : "+data);
//            }
        }
    }

}
