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
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class PaymentReadListAdapter_month extends RecyclerView.Adapter<PaymentReadListAdapter_month.Holder>{
    private List<MyHomeData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public PaymentReadListAdapter_month(List<MyHomeData> data, Context context) {
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
        View view = inflater.inflate(R.layout.item_payment_read_month, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        MyHomeData bbs = data.get(position);
        holder.setPayWriteNameMonth(bbs.name);
        holder.setPayWriteCountMonth(bbs.countTenant);
        holder.setPayWriteDayMonth(bbs.day);
        holder.setPayWriteRoomMonth(bbs.room);
        holder.setCheckPayWriteMonth(bbs.checkTenant);
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private TextView textPayReadRoomMonth;
        private TextView textPayReadNameMonth;
        private TextView textPayReadCountMonth;
        private TextView textPayReadDayMonth;
        private CheckBox checkPayReadMonth;
        public Holder(View v) {
            super(v);
            textPayReadNameMonth = (TextView) v.findViewById(R.id.textPayReadNameMonth);
            textPayReadCountMonth = (TextView) v.findViewById(R.id.textPayReadCountMonth);
            textPayReadDayMonth = (TextView) v.findViewById(R.id.textPayReadDayMonth);
            textPayReadRoomMonth = (TextView) v.findViewById(R.id.textPayReadRoomMonth);
            checkPayReadMonth = (CheckBox) v.findViewById(R.id.checkPayReadMonth);
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


        public void setPayWriteNameMonth(String payWriteNameMonth) {
            textPayReadNameMonth.setText(payWriteNameMonth);
        }

        public void setPayWriteCountMonth(String payWriteCountMonth) {
            textPayReadCountMonth.setText(payWriteCountMonth+"원");
        }

        public void setPayWriteDayMonth(String payWriteDayMonth) {
            textPayReadDayMonth.setText(payWriteDayMonth+"일");
        }
        public void setPayWriteRoomMonth(String payWriteRoomMonth) {
            textPayReadRoomMonth.setText(payWriteRoomMonth+"호");
        }

        public void setCheckPayWriteMonth(boolean payReadMonth) {
            checkPayReadMonth.setChecked(payReadMonth);
        }
    }
}
