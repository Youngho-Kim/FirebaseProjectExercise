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
import com.kwave.android.firebaseprojectexercise.domain.GroupWaterData;
import com.kwave.android.firebaseprojectexercise.domain.PayWaterData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class GroupReadListAdapter_water extends RecyclerView.Adapter<GroupReadListAdapter_water.Holder>{
    private List<GroupWaterData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public GroupReadListAdapter_water(List<GroupWaterData> data, Context context) {
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
        View view = inflater.inflate(R.layout.item_group_read_water, parent, false);
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
        private TextView textGroupReadNameWater;
        private TextView textGroupReadCountWater;
        private TextView textGroupReadDayWater;
        private TextView textGroupReadRoomWater;
        private TextView textGroupReadUseWater;
        private CheckBox checkGroupReadWater;
        public Holder(View v) {
            super(v);
            textGroupReadNameWater = (TextView) v.findViewById(R.id.textGroupReadNameWater);
            textGroupReadCountWater = (TextView) v.findViewById(R.id.textGroupReadCountWater);
            textGroupReadDayWater = (TextView) v.findViewById(R.id.textGroupReadDayWater);
            textGroupReadRoomWater = (TextView) v.findViewById(R.id.textGroupReadRoomWater);
            textGroupReadUseWater = (TextView) v.findViewById(R.id.textGroupReadUseWater);
            checkGroupReadWater = (CheckBox) v.findViewById(R.id.checkGroupReadWater);
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

        public void setGroupName(String GroupName) {
            textGroupReadNameWater.setText(GroupName);
        }

        public void setGroupCount(int GroupCount) {
            textGroupReadCountWater.setText(GroupCount);
        }

        public void setGroupDay(int GroupDay) {
            textGroupReadDayWater.setText(GroupDay);
        }

        public void setGroupRoom(int GroupRoom) {
            textGroupReadRoomWater.setText(GroupRoom);
        }

        public void setGroupUse(int GroupUse) {
            textGroupReadUseWater.setText(GroupUse);
        }

        public void setTextcheckGroup() {
            checkGroupReadWater.isChecked();
        }
    }
}
