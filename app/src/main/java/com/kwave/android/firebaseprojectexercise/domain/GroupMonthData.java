package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-04.
 */

public class GroupMonthData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public int GroupRoom;  // 호수
    public String GroupName;     // 이름
    public int GroupCountMonth; // 금액
    public int GroupDay;   // 납부일
    public boolean GroupCheckMonth;

    public GroupMonthData(){

    }

    public GroupMonthData(String id, int groupRoom, String groupName, int groupCount, int groupDay, boolean groupCheck) {
        this.id = id;
        this.GroupRoom = groupRoom;
        this.GroupName = groupName;
        this.GroupCountMonth = groupCount;
        this.GroupDay = groupDay;
        this.GroupCheckMonth = groupCheck;
    }
}

