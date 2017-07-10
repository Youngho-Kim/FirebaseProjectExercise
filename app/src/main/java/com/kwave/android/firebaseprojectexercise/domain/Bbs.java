package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-04.
 */

public class Bbs {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public String Name;
    public String PayCount;
    public String PayDay;
    public String PayRoom;
    public String PayUse;
    public boolean PayCheck;
    public long date;
    public long count; // 조회수

    public Bbs(){

    }

    public Bbs(String id, String name, String payCount, String payDay, String payRoom, String payUse, boolean payCheck) {
        this.id = id;
        this.Name = name;
        this.PayCount = payCount;
        this.PayDay = payDay;
        this.PayRoom = payRoom;
        this.PayUse = payUse;
        this.PayCheck = payCheck;
    }
}
