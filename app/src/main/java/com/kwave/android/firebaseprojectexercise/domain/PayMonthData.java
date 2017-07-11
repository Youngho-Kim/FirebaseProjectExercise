package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-04.
 */

public class PayMonthData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public int PayRoom;  // 호수
    public String PayName;     // 이름
    public int PayCountMonth; // 금액
    public int PayDay;   // 납부일
    public boolean PayCheckMonth;

    public PayMonthData(){

    }

    public PayMonthData(String id, int payRoom, String payName, int payCount, int payDay, boolean payCheck) {
        this.id = id;
        this.PayRoom = payRoom;
        this.PayName = payName;
        this.PayCountMonth = payCount;
        this.PayDay = payDay;
        this.PayCheckMonth = payCheck;
    }
}

