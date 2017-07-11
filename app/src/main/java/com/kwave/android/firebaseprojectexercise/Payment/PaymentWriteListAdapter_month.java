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
import com.kwave.android.firebaseprojectexercise.domain.PayMonthData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class PaymentWriteListAdapter_month extends RecyclerView.Adapter<PaymentWriteListAdapter_month.Holder>{
    private List<PayMonthData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public PaymentWriteListAdapter_month(List<PayMonthData> data, Context context) {
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
        View view = inflater.inflate(R.layout.item_payment_write_month, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PayMonthData bbs = data.get(position);
        holder.setEditPayWriteNameMonth(bbs.PayName);
        holder.setEditPayWriteCountMonth(bbs.PayCountMonth);
        holder.setEditPayWriteDayMonth(bbs.PayDay);
        holder.setEditPayWriteRoomMonth(bbs.PayRoom);
        holder.setCheckPayWriteMonth();
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
                    Intent intent = new Intent(v.getContext(), PaymentWriteActivity.class);
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


        public void setEditPayWriteNameMonth(String PayWriteNameMonth) {
            editPayWriteNameMonth.setText(PayWriteNameMonth);
        }

        public void setEditPayWriteCountMonth(int PayWriteCountMonth) {
            editPayWriteCountMonth.setText(PayWriteCountMonth+"");
        }

        public void setEditPayWriteDayMonth(int PayWriteDayMonth) {
            editPayWriteDayMonth.setText(PayWriteDayMonth+"일");
        }
        public void setEditPayWriteRoomMonth(int PayWriteRoomMonth) {
            editPayWriteRoomMonth.setText(PayWriteRoomMonth+"호");
        }

        public void setCheckPayWriteMonth() {
            checkPayWriteMonth.isChecked();
        }
    }
}
