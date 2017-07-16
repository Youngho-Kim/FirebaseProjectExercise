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


public class PaymentWriteFragment_month extends Fragment {

    RecyclerView payWriteMonthRecycler;
    PaymentWriteListAdapter_month adapter;


    List<MyHomeData> data = new ArrayList<>();
    MyHomeData bbs;


    FirebaseDatabase database;
    DatabaseReference bbsRef;


    TextView textPayWriteCheckMonth;
    public PaymentWriteFragment_month() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_write_month, container, false);

        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("남일빌라/납부내역/2017/7/월세/");
        makeData(5);

        // RecyclerView Setting
        payWriteMonthRecycler = (RecyclerView) view.findViewById(R.id.payWriteMonthRecycler);
        adapter = new PaymentWriteListAdapter_month(data, getContext());
        payWriteMonthRecycler.setAdapter(adapter);
        payWriteMonthRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(data);
        adapter.notifyDataSetChanged();



        textPayWriteCheckMonth = (TextView) view.findViewById(R.id.textPayWriteCheckMonth);
        textPayWriteCheckMonth.setOnClickListener(new View.OnClickListener() {
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
        for(int i=0; i<num; i++){
            bbs = new MyHomeData();
            bbs.room = "";
            bbs.name = "";
            bbs.countTenant = "";
            bbs.day = "";
            bbs.checkTenant = true;
            data.add(bbs);
        }

    }

    public void goFirebase(){
        for(MyHomeData bbs : data) {
            Log.i("TAG", "bbs : " + bbs.room);
            bbsRef.child("호실").setValue(bbs.room);        // 내가 원하는 부분으로 입력된다.
            Log.d("bbs.room", "Room 입력사항 "+bbs.room);
            bbsRef.child("이름").setValue(bbs.name);
            Log.d("bbs.name", "name 입력사항 "+bbs.name);
            bbsRef.child("금액(달)").setValue(bbs.countTenant);
            Log.d("bbs.countTenant", "countTenant 입력사항 "+bbs.countTenant);
            bbsRef.child("납부일").setValue(bbs.day);
            Log.d("bbs.day", "day 입력사항 "+bbs.day);
        }
    }












//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 여기서 부터는 RecyclerView의 어댑터 부분입니다.
     */

    class PaymentWriteListAdapter_month extends RecyclerView.Adapter<PaymentWriteListAdapter_month.Holder> {
        private List<MyHomeData> data = new ArrayList<>();
        private LayoutInflater inflater;

        public PaymentWriteListAdapter_month(List<MyHomeData> data, Context context) {
            this.data = data;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<MyHomeData> data) {
            this.data = data;
        }

        @Override
        public int getItemCount() {

            return data.size();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_payment_write_month, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            MyHomeData bbs = data.get(position);
            holder.editPayWriteRoomMonth.setText(bbs.room);
            holder.editPayWriteNameMonth.setText(bbs.name);
            holder.editPayWriteCountMonth.setText(bbs.countTenant);
            holder.editPayWriteDayMonth.setText(bbs.day);
            holder.checkPayWriteMonth.setChecked(bbs.checkTenant);
            holder.setPosition(position);
        }

        class Holder extends RecyclerView.ViewHolder {
            private int position;
            private EditText editPayWriteNameMonth;
            private EditText editPayWriteCountMonth;
            private EditText editPayWriteDayMonth;
            private EditText editPayWriteRoomMonth;
            private CheckBox checkPayWriteMonth;

            public Holder(View v) {
                super(v);
                editPayWriteRoomMonth = (EditText) v.findViewById(R.id.editPayWriteRoomMonth);
                editPayWriteNameMonth = (EditText) v.findViewById(R.id.editPayWriteNameMonth);
                editPayWriteCountMonth = (EditText) v.findViewById(R.id.editPayWriteCountMonth);
                editPayWriteDayMonth = (EditText) v.findViewById(R.id.editPayWriteDayMonth);
                checkPayWriteMonth = (CheckBox) v.findViewById(R.id.checkPayWriteMonth);


                editPayWriteRoomMonth.addTextChangedListener(roomTenantWatcher);
                editPayWriteNameMonth.addTextChangedListener(nameTenantWatcher);
                editPayWriteCountMonth.addTextChangedListener(countTenantWatcher);
                editPayWriteDayMonth.addTextChangedListener(dayTenantWatcher);
            }


//-------------------------------------EditText의 TextWatcher 실행 끝   ----------------------------------------
            // TextWatcher는 EditText를 작성하는 것을 실시간으로 어딘가에 적용하거나 저장하고 싶을때 주로 사용한다.

            TextWatcher roomTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    bbs.room = edit.toString();
                    Log.d("room", "Room 변경사항 " + bbs.room);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (s.equals(null) || s.equals("")) {
                        s = "0";
                    }
                }
            };
            TextWatcher nameTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    bbs.name = edit.toString();
                    Log.d("name", "name 변경사항 " + bbs.name);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (s.equals(null) || s.equals("")) {
                        s = "0";
                    }
                }
            };
            TextWatcher countTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    bbs.countTenant = edit.toString();
                    Log.d("countTenant", "countTenant 변경사항 " + bbs.countTenant);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (s.equals(null) || s.equals("")) {
                        s = "0";
                    }
                }
            };

            TextWatcher dayTenantWatcher = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edit) {
                    bbs = data.get(position);
                    bbs.day = edit.toString();
                    Log.d("day", "day 변경사항 " + bbs.day);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (s.equals(null) || s.equals("")) {
                        s = "0";
                    }
                }
            };
//-------------------------------------EditText의 TextWatcher 실행 끝   ----------------------------------------

            public void setPosition(int position){
                this.position = position;
            }

        }

    }






    public class PayWaterModel{
        private int position;
        private EditText editPayWriteNameMonth;
        private EditText editPayWriteCountMonth;
        private EditText editPayWriteDayMonth;
        private EditText editPayWriteRoomMonth;
        private CheckBox checkPayWriteMonth;

            public void setPosition(int position){
                this.position = position;
            }


            public String getEditPayName() {
                return editPayWriteNameMonth.getText().toString();
            }

            public String getEditPayCount() {
                return editPayWriteCountMonth.getText().toString();
            }

            public String getEditPayDay() {
                return editPayWriteDayMonth.getText().toString();
            }

            public String getEditPayRoom() {
                return editPayWriteRoomMonth.getText().toString();
            }

            public boolean getCheckPay() {
                return checkPayWriteMonth.isChecked();
            }


            public void setEditPayWriteNameMonth(String payWriteNameMonth) {
                editPayWriteNameMonth.setText(payWriteNameMonth);
            }

            public void setEditPayWriteCountMonth(String payWriteCountMonth) {
                editPayWriteCountMonth.setText(payWriteCountMonth);
            }

            public void setEditPayWriteDayMonth(String payWriteDayMonth) {
                editPayWriteDayMonth.setText(payWriteDayMonth+"일");
            }
            public void setEditPayWriteRoomMonth(String payWriteRoomMonth) {
                editPayWriteRoomMonth.setText(payWriteRoomMonth+"호");
            }

            public void setCheckPayWriteMonth(boolean payWriteMonth) {
                checkPayWriteMonth.setChecked(payWriteMonth);
            }

    }





}
