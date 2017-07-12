package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-04.
 */

public class GroupTenantData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public int groupRoom;  // 호수
    public String groupName;     // 이름
    public int groupCountTenant; // 금액
    public int groupDay;   // 납부일

    public GroupTenantData(){

    }

    public GroupTenantData(String id, int groupRoom, String groupName, int groupCount, int groupDay) {
        this.id = id;
        this.groupRoom = groupRoom;
        this.groupName = groupName;
        this.groupCountTenant = groupCount;
        this.groupDay = groupDay;
    }
}

