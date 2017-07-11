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
import com.kwave.android.firebaseprojectexercise.domain.GroupWaterData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class GroupWriteListAdapter_water extends RecyclerView.Adapter<GroupWriteListAdapter_water.Holder>{
    private List<GroupWaterData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public GroupWriteListAdapter_water(List<GroupWaterData> data, Context context) {
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<GroupWaterData> data){
        this.data = data;
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group_write_water, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        GroupWaterData bbs = data.get(position);
        holder.setGroupName(bbs.GroupName);
        holder.setGroupCount(bbs.GroupCountWater);
        holder.setGroupDay(bbs.GroupDay);
        holder.setGroupRoom(bbs.GroupRoom);
        holder.setGroupUse(bbs.GroupUse);
        holder.setTextcheckGroup();
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private EditText editGroupWriteNameWater;
        private EditText editGroupWriteCountWater;
        private EditText editGroupWriteDayWater;
        private EditText editGroupWriteRoomWater;
        private EditText editGroupWriteUseWater;
        private CheckBox checkGroupWriteWater;
        public Holder(View v) {
            super(v);
            editGroupWriteNameWater = (EditText) v.findViewById(editGroupWriteNameWater);
            editGroupWriteCountWater = (EditText) v.findViewById(editGroupWriteCountWater);
            editGroupWriteDayWater = (EditText) v.findViewById(editGroupWriteDayWater);
            editGroupWriteRoomWater = (EditText) v.findViewById(editGroupWriteRoomWater);
            editGroupWriteUseWater = (EditText) v.findViewById(R.id.editGroupWriteUseWater);
            checkGroupWriteWater = (CheckBox) v.findViewById(R.id.checkGroupWriteWater);
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

        public void setGroupName(String groupName) {
            editGroupWriteNameWater.setText(groupName);
        }

        public void setGroupCount(int groupCount) {
            editGroupWriteCountWater.setText(groupCount+"");
        }

        public void setGroupDay(int groupDay) {
            editGroupWriteDayWater.setText(groupDay+"일");
        }

        public void setGroupRoom(int groupRoom) {
            editGroupWriteRoomWater.setText(groupRoom+"호");
        }

        public void setGroupUse(int groupUse) {
            editGroupWriteUseWater.setText(groupUse+"");
        }

        public void setTextcheckGroup() {
            checkGroupWriteWater.isChecked();
        }
    }
}
