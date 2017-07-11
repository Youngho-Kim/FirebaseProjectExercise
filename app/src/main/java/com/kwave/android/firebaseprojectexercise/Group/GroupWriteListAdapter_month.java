package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.GroupMonthData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class GroupWriteListAdapter_month extends RecyclerView.Adapter<GroupWriteListAdapter_month.Holder>{
    private List<GroupMonthData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public GroupWriteListAdapter_month(List<GroupMonthData> data, Context context) {
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
        View view = inflater.inflate(R.layout.item_group_write_month, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        GroupMonthData bbs = data.get(position);
        holder.setEditGroupWriteNameMonth(bbs.GroupName);
        holder.setEditGroupWriteCountMonth(bbs.GroupCountMonth);
        holder.setEditGroupWriteDayMonth(bbs.GroupDay);
        holder.setEditGroupWriteRoomMonth(bbs.GroupRoom);
        holder.setCheckGroupWriteMonth();
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private EditText editGroupWriteNameMonth;
        private EditText editGroupWriteCountMonth;
        private EditText editGroupWriteDayMonth;
        private EditText editGroupWriteRoomMonth;
        private CheckBox checkGroupWriteMonth;
        public Holder(View v) {
            super(v);
            editGroupWriteNameMonth = (EditText) v.findViewById(R.id.editGroupWriteNameMonth);
            editGroupWriteCountMonth = (EditText) v.findViewById(R.id.editGroupWriteCountMonth);
            editGroupWriteDayMonth = (EditText) v.findViewById(R.id.editGroupWriteDayMonth);
            editGroupWriteRoomMonth = (EditText) v.findViewById(R.id.editGroupWriteRoomMonth);
            checkGroupWriteMonth = (CheckBox) v.findViewById(R.id.checkGroupWriteMonth);
            v.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), GroupWriteActivity.class);
                    intent.putExtra("LIST_POSITION", position);
                    v.getContext().startActivity(intent);
                 }
            });
        }
        public void setPosition(int position){
            this.position = position;
        }


        public String getEditGroupName() {
            return editGroupWriteNameMonth.getText().toString();
        }

        public String getEditGroupCount() {
            return editGroupWriteCountMonth.getText().toString();
        }

        public String getEditGroupDay() {
            return editGroupWriteDayMonth.getText().toString();
        }

        public String getEditGroupRoom() {
            return editGroupWriteRoomMonth.getText().toString();
        }

        public boolean getCheckGroup() {
            return checkGroupWriteMonth.isChecked();
        }


        public void setEditGroupWriteNameMonth(String GroupWriteNameMonth) {
            editGroupWriteNameMonth.setText(GroupWriteNameMonth);
        }

        public void setEditGroupWriteCountMonth(int GroupWriteCountMonth) {
            editGroupWriteCountMonth.setText(GroupWriteCountMonth+"");
        }

        public void setEditGroupWriteDayMonth(int GroupWriteDayMonth) {
            editGroupWriteDayMonth.setText(GroupWriteDayMonth+"일");
        }
        public void setEditGroupWriteRoomMonth(int GroupWriteRoomMonth) {
            editGroupWriteRoomMonth.setText(GroupWriteRoomMonth+"호");
        }

        public void setCheckGroupWriteMonth() {
            checkGroupWriteMonth.isChecked();
        }
    }
}
