package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-11.
 */

public class GroupWaterData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public int GroupRoom;  // 호수
    public String GroupName;     // 이름
    public int GroupCountWater; // 금액
    public int GroupUse; // 사용량
    public int GroupDay;   // 납부일
    public boolean GroupCheckWater;

    public GroupWaterData(){

    }

    public GroupWaterData(String id, int groupRoom, String groupName, int groupCount, int groupUse, int groupDay, boolean groupCheck) {
        this.id = id;
        this.GroupRoom = groupRoom;
        this.GroupName = groupName;
        this.GroupCountWater = groupCount;
        this.GroupUse = groupUse;
        this.GroupDay = groupDay;
        this.GroupCheckWater = groupCheck;
    }
}

