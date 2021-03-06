package com.kwave.android.firebaseprojectexercise.Group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class GroupReadListAdapter_contact extends RecyclerView.Adapter<GroupReadListAdapter_contact.Holder>{
    private List<MyHomeData> data = new ArrayList<>();
    private LayoutInflater inflater;
    Context context = null;

    public GroupReadListAdapter_contact(List<MyHomeData> data, Context context) {
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
        if(context == null){
            this.context = parent.getContext();
        }
        View view = inflater.inflate(R.layout.item_group_read_contact, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        MyHomeData bbs = data.get(position);
        holder.setGroupName(bbs.name);
        Log.d("bbs.name", "bbs.name"+bbs.name);
        holder.setGroupPhone(bbs.phoneNumber);
        holder.setGroupRoom(bbs.room);
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private TextView textGroupReadNameContact;
        private TextView textGroupReadPhoneContact;
        private TextView textGroupReadRoomContact;
        public Holder(View v) {
            super(v);
            textGroupReadNameContact = (TextView) v.findViewById(R.id.textGroupReadNameContact);
            textGroupReadPhoneContact = (TextView) v.findViewById(R.id.textGroupReadPhoneContact);
            textGroupReadRoomContact = (TextView) v.findViewById(R.id.textGroupReadRoomContact);
        }
        public void setPosition(int position){
            this.position = position;
        }

        public void setGroupName(String GroupName) {
            textGroupReadNameContact.setText(GroupName);
        }

        public void setGroupPhone(String GroupPhone) {
            textGroupReadPhoneContact.setText(GroupPhone);
        }


        public void setGroupRoom(String GroupRoom) {
            textGroupReadRoomContact.setText(GroupRoom);
        }

    }
}
