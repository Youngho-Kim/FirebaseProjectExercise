package com.kwave.android.firebaseprojectexercise.Payment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.PayWaterData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class PaymentReadListAdapter_water extends RecyclerView.Adapter<PaymentReadListAdapter_water.Holder>{
    private List<PayWaterData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public PaymentReadListAdapter_water(List<PayWaterData> data, Context context) {
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<PayWaterData> data){
        this.data = data;
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_payment_read_water, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PayWaterData bbs = data.get(position);
        holder.setPayName(bbs.payName);
        holder.setPayCount(bbs.payCountWater);
        holder.setPayDay(bbs.payDay);
        holder.setPayRoom(bbs.payRoom);
        holder.setPayUse(bbs.payUse);
        holder.setTextcheckPay(bbs.payCheckWater);
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private TextView textPayReadNameWater;
        private TextView textPayReadCountWater;
        private TextView textPayReadDayWater;
        private TextView textPayReadRoomWater;
        private TextView textPayReadUseWater;
        private CheckBox checkPayReadWater;
        public Holder(View v) {
            super(v);
            textPayReadNameWater = (TextView) v.findViewById(R.id.textPayReadNameWater);
            textPayReadCountWater = (TextView) v.findViewById(R.id.textPayReadCountWater);
            textPayReadDayWater = (TextView) v.findViewById(R.id.textPayReadDayWater);
            textPayReadRoomWater = (TextView) v.findViewById(R.id.textPayReadRoomWater);
            textPayReadUseWater = (TextView) v.findViewById(R.id.textPayReadUseWater);
            checkPayReadWater = (CheckBox) v.findViewById(R.id.checkPayReadWater);
            v.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PaymentWriteActivity.class);
                    intent.putExtra("LIST_POSITION", position);
                    v.getContext().startActivity(intent);
                 }
            });
        }
        public void setPosition(int position){
            this.position = position;
        }

        public void setPayName(String payName) {
            textPayReadNameWater.setText(payName);
        }

        public void setPayCount(int payCount) {
            textPayReadCountWater.setText(payCount+"만원");
        }

        public void setPayDay(int payDay) {
            textPayReadDayWater.setText(payDay+"일");
        }

        public void setPayRoom(int payRoom) {
            textPayReadRoomWater.setText(payRoom+"호");
        }

        public void setPayUse(int payUse) {
            textPayReadUseWater.setText(payUse+"");
        }

        public void setTextcheckPay(boolean payReadWater) {
            checkPayReadWater.setChecked(payReadWater);
        }
    }
}
