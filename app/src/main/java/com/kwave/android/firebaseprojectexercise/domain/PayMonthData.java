package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-04.
 */

public class PayMonthData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public String PayRoom;  // 호수
    public String PayName;     // 이름
    public String PayCount; // 금액
    public String PayDay;   // 납부일
    public boolean PayCheck;

    public PayMonthData(){

    }

    public PayMonthData(String id, String payRoom, String payName, String payCount, String payDay, boolean payCheck) {
        this.id = id;
        this.PayRoom = payRoom;
        this.PayName = payName;
        this.PayCount = payCount;
        this.PayDay = payDay;
        this.PayCheck = payCheck;
    }
}

