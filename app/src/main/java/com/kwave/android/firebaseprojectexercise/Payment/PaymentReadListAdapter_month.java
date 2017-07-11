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
import com.kwave.android.firebaseprojectexercise.domain.PayMonthData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class PaymentReadListAdapter_month extends RecyclerView.Adapter<PaymentReadListAdapter_month.Holder>{
    private List<PayMonthData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public PaymentReadListAdapter_month(List<PayMonthData> data, Context context) {
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<PayMonthData> data){
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
        PayMonthData bbs = data.get(position);
        holder.setPayWriteNameMonth(bbs.PayName);
        holder.setPayWriteCountMonth(bbs.PayCountMonth);
        holder.setPayWriteDayMonth(bbs.PayDay);
        holder.setPayWriteRoomMonth(bbs.PayRoom);
        holder.setCheckPayWriteMonth();
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
            checkPayReadMonth = (CheckBox) v.findViewById(R.id.checkPayWriteMonth);
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


        public void setPayWriteNameMonth(String PayWriteNameMonth) {
            textPayReadNameMonth.setText(PayWriteNameMonth);
        }

        public void setPayWriteCountMonth(int PayWriteCountMonth) {
            textPayReadCountMonth.setText(PayWriteCountMonth);
        }

        public void setPayWriteDayMonth(int PayWriteDayMonth) {
            textPayReadDayMonth.setText(PayWriteDayMonth+"일");
        }
        public void setPayWriteRoomMonth(int PayWriteRoomMonth) {
            textPayReadRoomMonth.setText(PayWriteRoomMonth+"호");
        }

        public void setCheckPayWriteMonth() {
            checkPayReadMonth.isChecked();
        }
    }
}
