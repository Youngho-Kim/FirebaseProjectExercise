package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class GroupReadListAdapter_tenant extends RecyclerView.Adapter<GroupReadListAdapter_tenant.Holder>{
    private List<MyHomeData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public GroupReadListAdapter_tenant(List<MyHomeData> data, Context context) {
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
        View view = inflater.inflate(R.layout.item_group_read_tenant, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        MyHomeData bbs = data.get(position);
        holder.setGroupWriteNameTenant(bbs.name);
        holder.setGroupWriteCountTenant(bbs.countTenant);
        holder.setGroupWriteDayTenant(bbs.contract);
        holder.setGroupWriteRoomTenant(bbs.room);
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private TextView textGroupReadRoomTenant;
        private TextView textGroupReadNameTenant;
        private TextView textGroupReadCountTenant;
        private TextView textGroupReadDayTenant;
        public Holder(View v) {
            super(v);
            textGroupReadNameTenant = (TextView) v.findViewById(R.id.textGroupReadNameTenant);
            textGroupReadCountTenant = (TextView) v.findViewById(R.id.textGroupReadCountTenant);
            textGroupReadDayTenant = (TextView) v.findViewById(R.id.textGroupReadDayTenant);
            textGroupReadRoomTenant = (TextView) v.findViewById(R.id.textGroupReadRoomTenant);
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


        public void setGroupWriteNameTenant(String GroupWriteNameTenant) {
            textGroupReadNameTenant.setText(GroupWriteNameTenant);
        }

        public void setGroupWriteCountTenant(String GroupWriteCountTenant) {
            textGroupReadCountTenant.setText(GroupWriteCountTenant+"만원");
        }

        public void setGroupWriteDayTenant(String GroupWriteDayTenant) {
            textGroupReadDayTenant.setText(GroupWriteDayTenant);
        }
        public void setGroupWriteRoomTenant(String GroupWriteRoomTenant) {
            textGroupReadRoomTenant.setText(GroupWriteRoomTenant+"호");
        }

    }
}
