package com.kwave.android.firebaseprojectexercise.Payment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class PaymentWriteListAdapter_month extends RecyclerView.Adapter<PaymentWriteListAdapter_month.Holder>{
    private List<MyHomeData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public PaymentWriteListAdapter_month(List<MyHomeData> data, Context context) {
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
        View view = inflater.inflate(R.layout.item_payment_write_month, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        MyHomeData bbs = data.get(position);
        holder.setEditPayWriteNameMonth(bbs.name);
        holder.setEditPayWriteCountMonth(bbs.countTenant);
        holder.setEditPayWriteDayMonth(bbs.day);
        holder.setEditPayWriteRoomMonth(bbs.room);
        holder.setCheckPayWriteMonth(bbs.checkTenant);
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
            editPayWriteNameMonth = (EditText) v.findViewById(R.id.editPayWriteNameMonth);
            editPayWriteCountMonth = (EditText) v.findViewById(R.id.editPayWriteCountMonth);
            editPayWriteDayMonth = (EditText) v.findViewById(R.id.editPayWriteDayMonth);
            editPayWriteRoomMonth = (EditText) v.findViewById(R.id.editPayWriteRoomMonth);
            checkPayWriteMonth = (CheckBox) v.findViewById(R.id.checkPayWriteMonth);
            v.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PaymentReadActivity.class);
                    intent.putExtra("LIST_POSITION", position);
                    v.getContext().startActivity(intent);
                 }
            });
        }
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

        public void setEditPayWriteCountMonth(int payWriteCountMonth) {
            editPayWriteCountMonth.setText(payWriteCountMonth+"");
        }

        public void setEditPayWriteDayMonth(int payWriteDayMonth) {
            editPayWriteDayMonth.setText(payWriteDayMonth+"일");
        }
        public void setEditPayWriteRoomMonth(int payWriteRoomMonth) {
            editPayWriteRoomMonth.setText(payWriteRoomMonth+"호");
        }

        public void setCheckPayWriteMonth(boolean payWriteMonth) {
            checkPayWriteMonth.setChecked(payWriteMonth);
        }
    }
}
