package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-12.
 */

public class PaymentRent {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public String room;  // 호수
    public String name;     // 이름
    public String countTenant; // 월세
    public String day;   // 납부일
    public boolean checkTenant;  // 체크여부
    public int dataMonth;   // 현재 월 표시


    public PaymentRent() {
    }

    public PaymentRent(String room, String name, String countTenant, String day, boolean checkTenant) {
        this.room = room;
        this.name = name;
        this.countTenant = countTenant;
        this.day = day;
        this.checkTenant = checkTenant;
    }
}
