package com.kwave.android.firebaseprojectexercise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kwave.android.firebaseprojectexercise.domain.PayWaterData;

import java.util.ArrayList;
import java.util.List;

import static com.kwave.android.firebaseprojectexercise.R.id.tPayCount;
import static com.kwave.android.firebaseprojectexercise.R.id.tPayDay;
import static com.kwave.android.firebaseprojectexercise.R.id.tPayName;
import static com.kwave.android.firebaseprojectexercise.R.id.tPayRoom;
import static com.kwave.android.firebaseprojectexercise.R.id.tPayUse;

/**
 * Created by kwave on 2017-07-04.
 */

public class PaymentListAdapter_water extends RecyclerView.Adapter<PaymentListAdapter_water.Holder>{
    private List<PayWaterData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public PaymentListAdapter_water(List<PayWaterData> data, Context context) {
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
        View view = inflater.inflate(R.layout.item_payment_water, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PayWaterData bbs = data.get(position);
        holder.setPayName(bbs.PayName);
        holder.setPayCount(bbs.PayCount);
        holder.setPayDay(bbs.PayDay);
        holder.setPayRoom(bbs.PayRoom);
        holder.setPayUse(bbs.PayUse);
        holder.setTextcheckPay(bbs.PayCheck);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private TextView PayName;
        private TextView PayCount;
        private TextView PayDay;
        private TextView PayRoom;
        private TextView PayUse;
        private CheckBox textcheckPay;
        public Holder(View v) {
            super(v);
            PayName = (TextView) v.findViewById(tPayName);
            PayCount = (TextView) v.findViewById(tPayCount);
            PayDay = (TextView) v.findViewById(tPayDay);
            PayRoom = (TextView) v.findViewById(tPayRoom);
            PayUse = (TextView) v.findViewById(tPayUse);
            textcheckPay = (CheckBox) v.findViewById(R.id.tcheckPay);
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

        public void setPayName(String payName) {
            PayName.setText(payName);
        }

        public void setPayCount(String payCount) {
            PayCount.setText(payCount);
        }

        public void setPayDay(String payDay) {
            PayDay.setText(payDay);
        }

        public void setPayRoom(String payRoom) {
            PayRoom.setText(payRoom);
        }

        public void setPayUse(String payUse) {
            PayUse.setText(payUse);
        }

        public void setTextcheckPay(boolean tcheckPay) {
            textcheckPay.isChecked();
        }
    }
}
