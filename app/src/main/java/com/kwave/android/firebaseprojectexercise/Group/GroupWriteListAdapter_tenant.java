package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.GroupTenantData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class GroupWriteListAdapter_tenant extends RecyclerView.Adapter<GroupWriteListAdapter_tenant.Holder>{
    private List<GroupTenantData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public GroupWriteListAdapter_tenant(List<GroupTenantData> data, Context context) {
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<GroupTenantData> data){
        this.data = data;
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group_write_tenant, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        GroupTenantData bbs = data.get(position);
        holder.setEditGroupWriteNameTenant(bbs.groupName);
        holder.setEditGroupWriteCountTenant(bbs.groupCountTenant);
        holder.setEditGroupWriteDayTenant(bbs.groupDay);
        holder.setEditGroupWriteRoomTenant(bbs.groupRoom);
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private EditText editGroupWriteNameTenant;
        private EditText editGroupWriteCountTenant;
        private EditText editGroupWriteDayTenant;
        private EditText editGroupWriteRoomTenant;
        public Holder(View v) {
            super(v);
            editGroupWriteNameTenant = (EditText) v.findViewById(R.id.editGroupWriteNameTenant);
            editGroupWriteCountTenant = (EditText) v.findViewById(R.id.editGroupWriteCountTenant);
            editGroupWriteDayTenant = (EditText) v.findViewById(R.id.editGroupWriteDayTenant);
            editGroupWriteRoomTenant = (EditText) v.findViewById(R.id.editGroupWriteRoomTenant);
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
            return editGroupWriteNameTenant.getText().toString();
        }

        public String getEditGroupCount() {
            return editGroupWriteCountTenant.getText().toString();
        }

        public String getEditGroupDay() {
            return editGroupWriteDayTenant.getText().toString();
        }

        public String getEditGroupRoom() {
            return editGroupWriteRoomTenant.getText().toString();
        }



        public void setEditGroupWriteNameTenant(String GroupWriteNameTenant) {
            editGroupWriteNameTenant.setText(GroupWriteNameTenant);
        }

        public void setEditGroupWriteCountTenant(int GroupWriteCountTenant) {
            editGroupWriteCountTenant.setText(GroupWriteCountTenant+"만원");
        }

        public void setEditGroupWriteDayTenant(int GroupWriteDayTenant) {
            editGroupWriteDayTenant.setText(GroupWriteDayTenant+"일");
        }
        public void setEditGroupWriteRoomTenant(int GroupWriteRoomTenant) {
            editGroupWriteRoomTenant.setText(GroupWriteRoomTenant+"호");
        }

    }
}
