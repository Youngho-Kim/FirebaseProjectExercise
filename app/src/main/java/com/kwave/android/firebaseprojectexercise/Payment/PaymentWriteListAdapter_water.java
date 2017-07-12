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

        public void setPayName(String payName) {
            editPayWriteNameWater.setText(payName);
        }

        public void setPayCount(int payCount) {
            editPayWriteCountWater.setText(payCount+"");
        }

        public void setPayDay(int payDay) {
            editPayWriteDayWater.setText(payDay+"일");
        }

        public void setPayRoom(int payRoom) {
            editPayWriteRoomWater.setText(payRoom+"호");
        }

        public void setPayUse(int payUse) {
            editPayWriteUseWater.setText(payUse+"");
        }

        public void setTextcheckPay(boolean payWriteWater) {
            checkPayWriteWater.setChecked(payWriteWater);
        }
    }
}
