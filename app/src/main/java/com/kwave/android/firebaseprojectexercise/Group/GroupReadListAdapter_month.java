package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.GroupMonthData;
import com.kwave.android.firebaseprojectexercise.domain.PayMonthData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class GroupReadListAdapter_month extends RecyclerView.Adapter<GroupReadListAdapter_month.Holder>{
    private List<GroupMonthData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public GroupReadListAdapter_month(List<GroupMonthData> data, Context context) {
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<GroupMonthData> data){
        this.data = data;
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group_read_month, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        GroupMonthData bbs = data.get(position);
        holder.setGroupWriteNameMonth(bbs.GroupName);
        holder.setGroupWriteCountMonth(bbs.GroupCountMonth);
        holder.setGroupWriteDayMonth(bbs.GroupDay);
        holder.setGroupWriteRoomMonth(bbs.GroupRoom);
        holder.setCheckGroupWriteMonth();
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private TextView textGroupReadRoomMonth;
        private TextView textGroupReadNameMonth;
        private TextView textGroupReadCountMonth;
        private TextView textGroupReadDayMonth;
        private CheckBox checkGroupReadMonth;
        public Holder(View v) {
            super(v);
            textGroupReadNameMonth = (TextView) v.findViewById(R.id.textGroupReadNameMonth);
            textGroupReadCountMonth = (TextView) v.findViewById(R.id.textGroupReadCountMonth);
            textGroupReadDayMonth = (TextView) v.findViewById(R.id.textGroupReadDayMonth);
            textGroupReadRoomMonth = (TextView) v.findViewById(R.id.textGroupReadRoomMonth);
            checkGroupReadMonth = (CheckBox) v.findViewById(R.id.checkGroupWriteMonth);
            v.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), GroupReadActivity.class);
                    intent.putExtra("LIST_POSITION", position);
                    v.getContext().startActivity(intent);
                 }
            });
        }
        public void setPosition(int position){
            this.position = position;
        }


        public void setGroupWriteNameMonth(String GroupWriteNameMonth) {
            textGroupReadNameMonth.setText(GroupWriteNameMonth);
        }

        public void setGroupWriteCountMonth(int GroupWriteCountMonth) {
            textGroupReadCountMonth.setText(GroupWriteCountMonth);
        }

        public void setGroupWriteDayMonth(int GroupWriteDayMonth) {
            textGroupReadDayMonth.setText(GroupWriteDayMonth+"일");
        }
        public void setGroupWriteRoomMonth(int GroupWriteRoomMonth) {
            textGroupReadRoomMonth.setText(GroupWriteRoomMonth+"호");
        }

        public void setCheckGroupWriteMonth() {
            checkGroupReadMonth.isChecked();
        }
    }
}
