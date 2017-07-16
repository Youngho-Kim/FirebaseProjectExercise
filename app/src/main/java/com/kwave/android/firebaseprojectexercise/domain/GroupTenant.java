package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-12.
 */

public class GroupTenant {
    public int id;    // 파이어베이스의 push 로 자동생성된다.
    public String room;  // 호수
    public String name;     // 이름
    public String contract;   // 계약기간
    public int dataMonth;   // 현재 월 표시
    public String countTenant; // 월세

    public GroupTenant(){

    }
    public GroupTenant(String room, String name, String countTenant, String contract) {
        this.room = room;
        this.name = name;
        this.countTenant = countTenant;
        this.contract = contract;
    }



}
