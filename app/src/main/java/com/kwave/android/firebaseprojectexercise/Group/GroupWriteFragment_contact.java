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
import com.kwave.android.firebaseprojectexercise.domain.GroupContact;

import java.util.ArrayList;
import java.util.List;


public class GroupWriteFragment_contact extends Fragment {
    GroupWriteListAdapter_contact adapter;
    List<GroupContact> data = new ArrayList<>();
    GroupContact bbs;
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
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("남일빌라/세입자 관리/2017/7/연락처/");
        textGroupPhoneContact = (TextView) view.findViewById(R.id.textGroupPhoneContact);

//        List<GroupContact> data = new ArrayList<>();
        // 리스트를 띄우기 위한 임시데이터
        GroupContact bbs = new GroupContact();
        bbs.name = "";
        bbs.phoneNumber = "";
        bbs.room = "";
        data.add(bbs);

        // RecyclerView Setting
        groupWriteContactRecycler = (RecyclerView) view.findViewById(R.id.groupWriteContactRecycler);
        adapter = new GroupWriteListAdapter_contact(data, getContext());
        groupWriteContactRecycler.setAdapter(adapter);
        groupWriteContactRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();


        textGroupPhoneContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goFirebase();
            }
        });
        return view;
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
        bbsRef.child("전화번호").setValue(bbs.phoneNumber);
        Log.d("bbs.phoneNumber", "phoneNumber 입력사항 "+bbs.phoneNumber);;

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






    public class GroupWriteListAdapter_contact extends RecyclerView.Adapter<GroupWriteListAdapter_contact.Holder>{
        private List<GroupContact> data = new ArrayList<>();
        private LayoutInflater inflater;

        public GroupWriteListAdapter_contact(List<GroupContact> data, Context context) {
            this.data = data;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<GroupContact> data){
            this.data = data;
        }

        @Override
        public int getItemCount() {

            return data.size();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_group_write_contact, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            GroupContact bbs = data.get(position);
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

                editGroupWriteNameContact.addTextChangedListener(nameTenantWatcher);
                editGroupWritePhoneContact.addTextChangedListener(phoneNumberTenantWatcher);
                editGroupWriteRoomContact.addTextChangedListener(roomTenantWatcher);
            }





//-------------------------------------EditText의 TextWatcher 실행 끝   ----------------------------------------
            // TextWatcher는 EditText를 작성하는 것을 실시간으로 어딘가에 적용하거나 저장하고 싶을때 주로 사용한다.

            TextWatcher roomTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    bbs.room = edit.toString();
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
                    bbs = data.get(position);
                    bbs.name = edit.toString();
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
            TextWatcher phoneNumberTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    bbs.phoneNumber = edit.toString();
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
