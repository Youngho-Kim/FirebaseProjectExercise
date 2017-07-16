//package com.kwave.android.firebaseprojectexercise.Group;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//
//import com.kwave.android.firebaseprojectexercise.R;
//import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by kwave on 2017-07-04.
// */
//
//public class GroupWriteListAdapter_contact extends RecyclerView.Adapter<GroupWriteListAdapter_contact.Holder>{
//    private List<MyHomeData> data = new ArrayList<>();
//    private LayoutInflater inflater;
//
//    public GroupWriteListAdapter_contact(List<MyHomeData> data, Context context) {
//        this.data = data;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    public void setData(List<MyHomeData> data){
//        this.data = data;
//    }
//
//    @Override
//    public int getItemCount() {
//
//        return data.size();
//    }
//
//    @Override
//    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.item_group_write_contact, parent, false);
//        return new Holder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(Holder holder, int position) {
//        MyHomeData bbs = data.get(position);
//        holder.setGroupName(bbs.name);
//        holder.setGroupPhone(bbs.phoneNumber);
//        holder.setGroupRoom(bbs.room);
//        holder.setPosition(position);
//    }
//
//    class Holder extends RecyclerView.ViewHolder {
//        private int position;
//        private EditText editGroupWriteNameContact;
//        private EditText editGroupWritePhoneContact;
//        private EditText editGroupWriteRoomContact;
//        public Holder(View v) {
//            super(v);
//            editGroupWriteNameContact = (EditText) v.findViewById(R.id.editGroupWriteNameContact);
//            editGroupWritePhoneContact = (EditText) v.findViewById(R.id.editGroupWritePhoneContact);
//            editGroupWriteRoomContact = (EditText) v.findViewById(R.id.editGroupWriteRoomContact);
//
//            editGroupWriteNameContact.addTextChangedListener(nameTenantWatcher);
//            editGroupWritePhoneContact.addTextChangedListener(phoneNumberTenantWatcher);
//            editGroupWriteRoomContact.addTextChangedListener(roomTenantWatcher);
//        }
//
//
//
//
//
////-------------------------------------EditText의 TextWatcher 실행 끝   ----------------------------------------
//        // TextWatcher는 EditText를 작성하는 것을 실시간으로 어딘가에 적용하거나 저장하고 싶을때 주로 사용한다.
//
//        TextWatcher roomTenantWatcher = new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable edit) {
//                bbs = data.get(position);
//                bbs.room = Integer.parseInt(edit.toString());
//                Log.d("room", "Room 변경사항 "+bbs.room);
//            }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//                if(s.equals(null) ||  s.equals("")){
//                    s = "0";
//                }
//            }
//        };
//        TextWatcher nameTenantWatcher = new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable edit) {
//                bbs = data.get(position);
//                bbs.name = edit.toString();
//                Log.d("name", "name 변경사항 "+bbs.name);
//            }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//                if(s.equals(null) ||  s.equals("")){
//                    s = "0";
//                }
//            }
//        };
//        TextWatcher phoneNumberTenantWatcher = new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable edit) {
//                bbs = data.get(position);
//                bbs.phoneNumber = edit.toString();
//                Log.d("countTenant", "countTenant 변경사항 "+bbs.phoneNumber);
//            }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//                if(s.equals(null) ||  s.equals("")){
//                    s = "0";
//                }
//            }
//        };
//
//
////-------------------------------------EditText의 TextWatcher 실행 끝   ----------------------------------------
//
//
//
//
//
//
//
//
//        public void setPosition(int position){
//            this.position = position;
//        }
//
//        public void setGroupName(String groupName) {
//            editGroupWriteNameContact.setText(groupName);
//        }
//
//        public void setGroupPhone(String groupPhone) {
//            editGroupWritePhoneContact.setText(groupPhone);
//        }
//
//        public void setGroupRoom(int groupRoom) {
//            editGroupWriteRoomContact.setText(groupRoom+"");
//        }
//
//    }
//}
