//package com.kwave.android.firebaseprojectexercise.Group;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.kwave.android.firebaseprojectexercise.R;
//import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GroupWriteFragment_tenant extends Fragment {
//
//    public static interface upLoadFirebase{
//        public void upLoad();
//    }
//    public upLoadFirebase callBack;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if(context instanceof upLoadFirebase){
//            callBack = (upLoadFirebase) context;
//        }
//    }
//
//    List<MyHomeData> data = new ArrayList<>();
//    MyHomeData bbs;
//    RecyclerView groupWriteTenantRecycler;
//    GroupWriteListAdapter_tenant adapter;
//    FirebaseDatabase database;
//    DatabaseReference bbsRef;
//    TextView textGroupWriteDayTenant;
//
//    public GroupWriteFragment_tenant() {
//    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_group_write_tenant, container, false);
//        textGroupWriteDayTenant = (TextView) view.findViewById(R.id.textGroupWriteDayTenant);
//        makeData(6);
//        setRecycler(view);
//        bbs = new MyHomeData();
//        setFirebaseReference("남일빌라/세입자 관리/2017/7/세입자 정보/"+bbs.room+"/");
//        loadFireBase();
//        textGroupWriteDayTenant.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goFirebase();
//            }
//        });
//        return view;
//    }
//
//
//
//    public void upLoad1(){
//        if(callBack != null){
//            callBack.upLoad();
//        }
//    }
//
//
//    /**
//     *  데이터베이스 레퍼런스 설정
//     * @param reference 파이어베이스에 데이터 저장경로
//     */
//    private void setFirebaseReference(String reference){
//        database = FirebaseDatabase.getInstance();
//        bbsRef = database.getReference(reference);
//    }
//
//
//    /**
//     * RecyclerView Setting
//     */
//    private void setRecycler(View view){
//        groupWriteTenantRecycler = (RecyclerView) view.findViewById(R.id.groupWriteTenantRecycler);
//        adapter = new GroupWriteListAdapter_tenant(data,getContext());
//        groupWriteTenantRecycler.setAdapter(adapter);
//        groupWriteTenantRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//
//
//    public void makeData(int num){
//        // 리스트를 띄우기 위한 임시데이터
//        if(num <=0){
//            num = 1;
//        }
//        for(int i =1; i<=num;i++){
//            MyHomeData bbs = new MyHomeData();
//            bbs.room = "";
//            bbs.name = "";
//            bbs.countTenant = "";
//            bbs.contract = "";
//            data.add(bbs);
//        }
//    }
//
//
//    public void goFirebase(){
//        for(int i=1; i<=adapter.getItemCount(); i++) {
//            MyHomeData bbs = adapter.getItem(i);
//            bbsRef.child("room").setValue(bbs.room);        // 내가 원하는 부분으로 입력된다.
//            bbsRef.child("name").setValue(bbs.name);
//            bbsRef.child("countTenant").setValue(bbs.countTenant);
//            bbsRef.child("contract").setValue(bbs.contract);
//        }
//    }
//
//    /**
//     * Firebase에서 데이터 가져오기
//     */
//    private void loadFireBase(){
//        bbsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                data = new ArrayList<>();
//                for(DataSnapshot item : dataSnapshot.getChildren()){
//                    try {
//                        bbs = item.getValue(MyHomeData.class);
//                        data.add(bbs);
//                        Log.i("MyHomeData" , "======================"+bbs);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//                refreshList(data);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void refreshList(List<MyHomeData> data){
//        adapter.setData(data);
//        adapter.notifyDataSetChanged();
//    }
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /**
//     * 여기서 부터는 RecyclerView의 어댑터 부분입니다.
//     */
//    public class GroupWriteListAdapter_tenant extends RecyclerView.Adapter<GroupWriteListAdapter_tenant.Holder>{
//        List<MyHomeData> data = new ArrayList<>();;
//        private LayoutInflater inflater;
//
//        public GroupWriteListAdapter_tenant(List<MyHomeData> data,Context context) {
//            this.data = data;
//            Log.d("writeDataTenants.size()", "writeDataTenants.size : "+data.size());
//            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        public void setData(List<MyHomeData> data){
//            this.data = data;
//        }
//
//        @Override
//        public int getItemCount() {
//            return data.size();
//        }
//
//        public MyHomeData getItem(int position) {
//            return data.get(position);
//        }
//
//        @Override
//        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = inflater.inflate(R.layout.item_group_write_tenant, parent, false);
//            return new Holder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(Holder holder, int position) {
//            MyHomeData bbs = data.get(position);
//            Log.d("onBindViewHolder", "data.get(position)  : "+position);
//            holder.setEditGroupWriteRoomTenant(bbs.room);
//            holder.setEditGroupWriteNameTenant(bbs.name);
//            holder.setEditGroupWriteCountTenant(bbs.countTenant);
//            holder.setEditGroupWriteDayTenant(bbs.contract);
//            holder.setPosition(position);
//        }
//
//        class Holder extends RecyclerView.ViewHolder{
//            private int position;
//            private EditText editGroupWriteNameTenant;
//            private EditText editGroupWriteCountTenant;
//            private EditText editGroupWriteDayTenant;
//            private EditText editGroupWriteRoomTenant;
//
//            public Holder(View v) {
//                super(v);
//                editGroupWriteNameTenant = (EditText) v.findViewById(R.id.editGroupWriteNameTenant);
//                editGroupWriteCountTenant = (EditText) v.findViewById(R.id.editGroupWriteCountTenant);
//                editGroupWriteDayTenant = (EditText) v.findViewById(R.id.editGroupWriteDayTenant);
//                editGroupWriteRoomTenant = (EditText) v.findViewById(R.id.editGroupWriteRoomTenant);
//
//                editGroupWriteRoomTenant.addTextChangedListener(roomTenantWatcher);
//                editGroupWriteNameTenant.addTextChangedListener(nameTenantWatcher);
//                editGroupWriteCountTenant.addTextChangedListener(countTenantWatcher);
//                editGroupWriteDayTenant.addTextChangedListener(contractTenantWatcher);
//            }
//
//
//            /**
//             * EditText를 실시간으로 적용하기 위한 TextWatcher
//             */
//            // TextWatcher는 EditText를 작성하는 것을 실시간으로 어딘가에 적용하거나 저장하고 싶을때 주로 사용한다.
//            TextWatcher roomTenantWatcher = new TextWatcher() {
//                @Override
//                public void afterTextChanged(Editable edit) {
//                    bbs = data.get(position);
//                    data.get(position).room = edit.toString();
//                }
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count,
//                                              int after) {
//                }
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before,
//                                          int count) {
//                    if(s.equals(null) ||  s.equals("")){
//                        s = "0";
//                    }
//                }
//            };
//            TextWatcher nameTenantWatcher = new TextWatcher() {
//                @Override
//                public void afterTextChanged(Editable edit) {
//                    bbs = data.get(position);
//                    data.get(position).name = edit.toString();
//                }
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count,
//                                              int after) {
//                }
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before,
//                                          int count) {
//                    if(s.equals(null) ||  s.equals("")){
//                        s = "0";
//                    }
//                }
//            };
//            TextWatcher countTenantWatcher = new TextWatcher() {
//                @Override
//                public void afterTextChanged(Editable edit) {
//                    bbs = data.get(position);
//                    data.get(position).countTenant = edit.toString();
//                }
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count,
//                                              int after) {
//                }
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before,
//                                          int count) {
//                    if(s.equals(null) ||  s.equals("")){
//                        s = "0";
//                    }
//                }
//            };
//            TextWatcher contractTenantWatcher = new TextWatcher() {
//                @Override
//                public void afterTextChanged(Editable edit) {
//                    bbs = data.get(position);
//                    data.get(position).contract = edit.toString();
//                }
//
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count,
//                                              int after) {
//                }
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before,
//                                          int count) {
//                    if(s.equals(null) ||  s.equals("")){
//                        s = "0";
//                    }
//                }
//            };
//
//
//            // --------------------Getter----------------------------------------------------------------
//            public String getEditGroupName() {
//                return editGroupWriteNameTenant.getText().toString();
//            }
//            public String getEditGroupCount() {
//                return editGroupWriteCountTenant.getText().toString();
//            }
//            public String getEditGroupDay() {
//                return editGroupWriteDayTenant.getText().toString();
//            }
//            public String getEditGroupRoom() {
//                return editGroupWriteRoomTenant.getText().toString();
//            }
//            // --------------------Setter----------------------------------------------------------------
//            public void setPosition(int position){
//                this.position = position;
//            }
//            public void setEditGroupWriteNameTenant(String GroupWriteNameTenant) {
//                editGroupWriteNameTenant.setText(GroupWriteNameTenant);
//            }
//            public void setEditGroupWriteCountTenant(String GroupWriteCountTenant) {
//                editGroupWriteCountTenant.setText(GroupWriteCountTenant+"");
//            }
//            public void setEditGroupWriteDayTenant(String GroupWriteDayTenant) {
//                editGroupWriteDayTenant.setText(GroupWriteDayTenant);
//            }
//            public void setEditGroupWriteRoomTenant(String GroupWriteRoomTenant) {
//                editGroupWriteRoomTenant.setText(GroupWriteRoomTenant+"");
//            }
//        }
//    }
//}
