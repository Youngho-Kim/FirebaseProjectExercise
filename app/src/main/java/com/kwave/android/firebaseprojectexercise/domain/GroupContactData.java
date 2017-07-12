package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-11.
 */

public class GroupContactData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public int GroupRoom;  // 호수
    public String GroupName;     // 이름
    public String GroupPhoneContact; // 핸드폰 번호

    public GroupContactData(){

    }

    public GroupContactData(String id, int groupRoom, String groupName, String groupPhone) {
        this.id = id;
        this.GroupRoom = groupRoom;
        this.GroupName = groupName;
        this.GroupPhoneContact = groupPhone;
    }
}

