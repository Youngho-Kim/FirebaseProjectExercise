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
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;


public class GroupWriteFragment_contact extends Fragment {
    GroupWriteListAdapter_contact adapter;
    List<MyHomeData> data = new ArrayList<>();
    MyHomeData bbs;
    FirebaseDatabase database;
    DatabaseReference bbsRef;
    RecyclerView groupWriteContactRecycler;
    TextView textGroupPhoneContact;
    public GroupWriteFragment_contact() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_write_contact, container, false);
//        database = FirebaseDatabase.getInstance();
//        bbsRef = database.getReference("남일빌라/세입자 관리/2017/7/연락처/");
        textGroupPhoneContact = (TextView) view.findViewById(R.id.textGroupPhoneContact);
//
////        List<MyHomeData> data = new ArrayList<>();
//        // 리스트를 띄우기 위한 임시데이터
//        MyHomeData bbs = new MyHomeData();
//        bbs.name = "";
//        bbs.phoneNumber = "";
//        bbs.room = "";
//        data.add(bbs);

        makeData(6);

        // RecyclerView Setting
        groupWriteContactRecycler = (RecyclerView) view.findViewById(R.id.groupWriteContactRecycler);
        adapter = new GroupWriteListAdapter_contact(data, getContext());
        groupWriteContactRecycler.setAdapter(adapter);
        groupWriteContactRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter.setData(data);
//        adapter.notifyDataSetChanged();


        textGroupPhoneContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            MyHomeData bbs = new MyHomeData();
            bbs.room = "";
            bbs.name = "";
            bbs.phoneNumber = "";
            data.add(bbs);
//            Log.i("DATA","====================bbs.room : " + bbs.room);
//            Log.i("DATA", "=================data.add(bbs) : " + data.add(bbs));
//            Log.i("DATA", "=================data.size() : " + data.size());
//            Log.i("DATA", "=================data.indexOf(bbs) : " + data.indexOf(bbs));

        }

    }

        public void uploadFirebase(){
            goFirebase();
        }


    public void goFirebase(){

        for(int i=0; i<adapter.getItemCount(); i++) {
            MyHomeData bbs = adapter.getItem(i);
            database = FirebaseDatabase.getInstance();
            bbsRef = database.getReference("남일빌라/세입자 관리/2017/7/연락처/"+bbs.room+"/");

            bbsRef.child("room").setValue(bbs.room);        // 내가 원하는 부분으로 입력된다.
            bbsRef.child("name").setValue(bbs.name);
            bbsRef.child("phoneNumber").setValue(bbs.phoneNumber);
        }
    }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 여기서 부터는 RecyclerView의 어댑터 부분입니다.
     */






    public class GroupWriteListAdapter_contact extends RecyclerView.Adapter<GroupWriteListAdapter_contact.Holder>{
        private List<MyHomeData> data = new ArrayList<>();
        private LayoutInflater inflater;

        public GroupWriteListAdapter_contact(List<MyHomeData> data, Context context) {
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
        public MyHomeData getItem(int position) {
            return data.get(position);
        }
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_group_write_contact, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            MyHomeData bbs = data.get(position);
            holder.setGroupName(bbs.name);
            holder.setGroupPhone(bbs.phoneNumber);
            holder.setGroupRoom(bbs.room);
            holder.setPosition(position);
        }

        class Holder extends RecyclerView.ViewHolder {
            private int position;
            private EditText editGroupWriteNameContact;
            private EditText editGroupWritePhoneContact;
            private EditText editGroupWriteRoomContact;
            public Holder(View v) {
                super(v);
                editGroupWriteNameContact = (EditText) v.findViewById(R.id.editGroupWriteNameContact);
                editGroupWritePhoneContact = (EditText) v.findViewById(R.id.editGroupWritePhoneContact);
                editGroupWriteRoomContact = (EditText) v.findViewById(R.id.editGroupWriteRoomContact);

                editGroupWriteNameContact.addTextChangedListener(nameContactWatcher);
                editGroupWritePhoneContact.addTextChangedListener(phoneNumberContactWatcher);
                editGroupWriteRoomContact.addTextChangedListener(roomContactWatcher);
            }





//-------------------------------------EditText의 TextWatcher 실행 끝   ----------------------------------------
            // TextWatcher는 EditText를 작성하는 것을 실시간으로 어딘가에 적용하거나 저장하고 싶을때 주로 사용한다.

            TextWatcher roomContactWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    data.get(position).room = edit.toString();
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
            TextWatcher nameContactWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    data.get(position).name = edit.toString();
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
            TextWatcher phoneNumberContactWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    data.get(position).phoneNumber = edit.toString();
                    Log.d("phoneNumber", "phoneNumber 변경사항 "+bbs.phoneNumber);
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






            public void setPosition(int position){
                this.position = position;
            }

            public void setGroupName(String groupName) {
                editGroupWriteNameContact.setText(groupName);
            }

            public void setGroupPhone(String groupPhone) {
                editGroupWritePhoneContact.setText(groupPhone);
            }

            public void setGroupRoom(String groupRoom) {
                editGroupWriteRoomContact.setText(groupRoom+"");
            }

        }
    }




}
