package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kwave.android.firebaseprojectexercise.R;
import com.kwave.android.firebaseprojectexercise.domain.MyHomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 */

public class GroupWriteListAdapter_contact extends RecyclerView.Adapter<GroupWriteListAdapter_contact.Holder>{
    private List<MyHomeData> data = new ArrayList<>();
    private LayoutInflater inflater;

    public GroupWriteListAdapter_contact(List<MyHomeData> data, Context context) {
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
        View view = inflater.inflate(R.layout.item_group_write_contact, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        MyHomeData bbs = data.get(position);
        holder.setGroupName(bbs.name);
        holder.setGroupPhone(bbs.phoneNumber);
        holder.setGroupRoom(bbs.room);
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private EditText editGroupWriteNameContact;
        private EditText editGroupWritePhoneContact;
        private EditText editGroupWriteRoomContact;
        public Holder(View v) {
            super(v);
            editGroupWriteNameContact = (EditText) v.findViewById(R.id.editGroupWriteNameContact);
            editGroupWritePhoneContact = (EditText) v.findViewById(R.id.editGroupWritePhoneContact);
            editGroupWriteRoomContact = (EditText) v.findViewById(R.id.editGroupWriteRoomContact);
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
            editGroupWriteNameContact.setText(groupName);
        }

        public void setGroupPhone(String groupPhone) {
            editGroupWritePhoneContact.setText(groupPhone);
        }

        public void setGroupRoom(int groupRoom) {
            editGroupWriteRoomContact.setText(groupRoom+"호");
        }

    }
}
