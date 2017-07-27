package com.kwave.android.firebaseprojectexercise.Payment;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;


public class PaymentWriteFragment_water extends Fragment {
    RecyclerView payWriteWaterRecycler;
    PaymentWriteListAdapter_water adapter;

    List<MyHomeData> data = new ArrayList<>();
    MyHomeData bbs;
    EditText writeInfo[] = new EditText[10];

    FirebaseDatabase database;
    DatabaseReference bbsRef;
    PaymentWriteActivity paymentWriteActivity = new PaymentWriteActivity();
    TextView textPayWriteWriteCheck;
    public PaymentWriteFragment_water() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {                           // 액티비티의 oncreateView 보다 먼저 생김
                                                                                    // 그래서 fragment의 onViewCreate는 확실하게 액티비티의 onCreate보다 늦게 나온다.
        View view = inflater.inflate(R.layout.fragment_payment_write_water, container, false);
        textPayWriteWriteCheck = (TextView) view.findViewById(R.id.textPayWriteWriteCheck);


        makeData(6);

        // RecyclerView Setting
        payWriteWaterRecycler = (RecyclerView) view.findViewById(R.id.payWriteWaterRecycler);
        adapter = new PaymentWriteListAdapter_water(data, getContext());
        payWriteWaterRecycler.setAdapter(adapter);
        payWriteWaterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter.setData(data);
//        adapter.notifyDataSetChanged();



        //
        textPayWriteWriteCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goFirebase();
            }
        });

        int currentMonths = paymentWriteActivity.getMonthValue();
        Log.d("currentMonths","----------------------------------currentMonths : " + currentMonths);

        return view;
    }

    public void makeData(int num) {
        // 리스트를 띄우기 위한 임시데이터
        for (int i = 0; i < num; i++) {
            MyHomeData bbs = new MyHomeData();
            bbs.room = "";
            bbs.name = "";
            bbs.countTenant = "";
            bbs.day = "";
            bbs.checkWater = true;
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
//            bbs = new MyHomeData();
        // 2. 입력할 데이터의 키 생성
//        String bbsKey = bbsRef.push().getKey(); // 자동생성된 키를 가져온다
        // 3. 생성된 키를 레퍼런스로 데이터를 입력
        //    insert 와 update, delete 는 동일하게 동작
//        bbsRef.child("bbsKey").setValue(bbs.masterNotify);        // 자동생성키로 키를 받아서 입력된다.
        for(int i=0; i<adapter.getItemCount(); i++) {
            MyHomeData bbs = adapter.getItem(i);
            database = FirebaseDatabase.getInstance();
            bbsRef = database.getReference("남일빌라/납부내역/2017/7/수도세/" + bbs.room + "/");
//        bbsRef.setValue(bbs);        // 내가 원하는 부분으로 입력된다.
            bbsRef.child("room").setValue(bbs.room);        // 내가 원하는 부분으로 입력된다.
            Log.d("bbs.room", "Room 입력사항 " + bbs.room);
            bbsRef.child("name").setValue(bbs.name);
            Log.d("bbs.name", "name 입력사항 " + bbs.name);
            bbsRef.child("countWater").setValue(bbs.countWater);
            Log.d("bbs.countWater", "countWater 입력사항 " + bbs.countWater);
            bbsRef.child("use").setValue(bbs.use);
            Log.d("bbs.use", "use 입력사항 " + bbs.use);
            bbsRef.child("day").setValue(bbs.day);
            Log.d("bbs.day", "day 입력사항 " + bbs.day);
            bbsRef.child("checkWater").setValue(bbs.checkWater);
            //    update : bbsRef.child(bbsKey).setValue(bbs);
            //    delete : bbsRef.child(bbsKey).setValue(null);
            // 데이터 입력후 창 닫기
        }
    }












//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 여기서 부터는 RecyclerView의 어댑터 부분입니다.
     */




    public class PaymentWriteListAdapter_water extends RecyclerView.Adapter<PaymentWriteListAdapter_water.Holder>{
        private List<MyHomeData> data = new ArrayList<>();
        private LayoutInflater inflater;


        public PaymentWriteListAdapter_water(List<MyHomeData> data, Context context) {
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
            View view = inflater.inflate(R.layout.item_payment_write_water, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            MyHomeData bbs = data.get(position);
            holder.setPayName(bbs.name);
            holder.setPayCount(bbs.countWater);
            holder.setPayDay(bbs.day);
            holder.setPayRoom(bbs.room);
            holder.setPayUse(bbs.use);
            holder.setTextcheckPay(bbs.checkWater);
            holder.setPosition(position);
        }

        class Holder extends RecyclerView.ViewHolder {
            private int position;
            private EditText editPayWriteNameWater;
            private EditText editPayWriteCountWater;
            private EditText editPayWriteDayWater;
            private EditText editPayWriteRoomWater;
            private EditText editPayWriteUseWater;
            private CheckBox checkPayWriteWater;
            public Holder(View v) {
                super(v);
                editPayWriteNameWater = (EditText) v.findViewById(R.id.editPayWriteNameWater);
                editPayWriteCountWater = (EditText) v.findViewById(R.id.editPayWriteCountWater);
                editPayWriteDayWater = (EditText) v.findViewById(R.id.editPayWriteDayWater);
                editPayWriteRoomWater = (EditText) v.findViewById(R.id.editPayWriteRoomWater);
                editPayWriteUseWater = (EditText) v.findViewById(R.id.editPayWriteUseWater);
                checkPayWriteWater = (CheckBox) v.findViewById(R.id.checkPayWriteWater);

                editPayWriteRoomWater.addTextChangedListener(roomTenantWatcher);
                editPayWriteNameWater.addTextChangedListener(nameTenantWatcher);
                editPayWriteCountWater.addTextChangedListener(countWaterWatcher);
                editPayWriteUseWater.addTextChangedListener(useTenantWatcher);
                editPayWriteDayWater.addTextChangedListener(dayTenantWatcher);
            }




//-------------------------------------EditText의 TextWatcher 실행 끝   ----------------------------------------
            // TextWatcher는 EditText를 작성하는 것을 실시간으로 어딘가에 적용하거나 저장하고 싶을때 주로 사용한다.

            TextWatcher roomTenantWatcher = new TextWatcher() {
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
            TextWatcher nameTenantWatcher = new TextWatcher() {
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
            TextWatcher countWaterWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    data.get(position).countWater = edit.toString();
                    Log.d("countWater", "countWater 변경사항 "+bbs.countWater);
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

            TextWatcher useTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    data.get(position).use = edit.toString();
                    Log.d("use", "use 변경사항 "+bbs.use);
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

            TextWatcher dayTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    data.get(position).day = edit.toString();
                    Log.d("day", "day 변경사항 "+bbs.day);
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

            public void setPayName(String payName) {
                editPayWriteNameWater.setText(payName);
            }

            public void setPayCount(String payCount) {
                editPayWriteCountWater.setText(payCount);
            }

            public void setPayDay(String payDay) {
                editPayWriteDayWater.setText(payDay);
            }

            public void setPayRoom(String payRoom) {
                editPayWriteRoomWater.setText(payRoom);
            }

            public void setPayUse(String payUse) {
                editPayWriteUseWater.setText(payUse);
            }

            public void setTextcheckPay(boolean payWriteWater) {
                checkPayWriteWater.setChecked(payWriteWater);
            }
        }
    }

}
