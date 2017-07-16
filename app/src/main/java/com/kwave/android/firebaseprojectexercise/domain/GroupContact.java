package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-12.
 */

public class GroupContact {
    public int id;    // 파이어베이스의 push 로 자동생성된다.
    public String room;  // 호수
    public String name;     // 이름
    public String phoneNumber; // 핸드폰 번호
    public int dataMonth;   // 현재 월 표시

    public GroupContact() {
    }

    public GroupContact(String room, String name, String phoneNumber) {
        this.room = room;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }



}
