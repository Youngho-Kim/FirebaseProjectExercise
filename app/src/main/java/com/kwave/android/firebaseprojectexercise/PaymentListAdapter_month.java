package com.kwave.android.firebaseprojectexercise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.kwave.android.firebaseprojectexercise.domain.Bbs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class PaymentListAdapter_month extends RecyclerView.Adapter<PaymentListAdapter_month.Holder>{
    private List<Bbs> data = new ArrayList<>();
    private LayoutInflater inflater;

    public PaymentListAdapter_month(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Bbs> data){
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_payment_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Bbs bbs = data.get(position);
        holder.getEditPayName();
        holder.getEditPayCount();
        holder.getEditPayDay();
        holder.getEditPayRoom();
        holder.getEditPayUse();
        holder.getCheckPay();
        holder.getPosition();
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private EditText editPayName;
        private EditText editPayCount;
        private EditText editPayDay;
        private EditText editPayRoom;
        private EditText editPayUse;
        private CheckBox checkPay;
        public Holder(View v) {
            super(v);
            editPayName = (EditText) v.findViewById(R.id.editPayName);
            editPayCount = (EditText) v.findViewById(R.id.editPayCount);
            editPayDay = (EditText) v.findViewById(R.id.editPayDay);
            editPayRoom = (EditText) v.findViewById(R.id.editPayRoom);
            editPayUse = (EditText) v.findViewById(R.id.editPayUse);
            checkPay = (CheckBox) v.findViewById(R.id.checkPay);
            v.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PaymentActivity.class);
                    intent.putExtra("LIST_POSITION", position);
                    v.getContext().startActivity(intent);
                 }
            });
        }
        public void setPosition(int position){
            this.position = position;
        }


        public String getEditPayName() {
            return editPayName.getText().toString();
        }

        public String getEditPayCount() {
            return editPayCount.getText().toString();
        }

        public String getEditPayDay() {
            return editPayDay.getText().toString();
        }

        public String getEditPayRoom() {
            return editPayRoom.getText().toString();
        }

        public String getEditPayUse() {
            return editPayUse.getText().toString();
        }

        public boolean getCheckPay() {
            return checkPay.isChecked();
        }
    }
}
