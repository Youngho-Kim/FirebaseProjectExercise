package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-04.
 */

public class PayMonthData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public int payRoom;  // 호수
    public String payName;     // 이름
    public int payCountMonth; // 금액
    public int payDay;   // 납부일
    public boolean payCheckMonth;

    public PayMonthData(){

    }

    public PayMonthData(String id, int payRoom, String payName, int payCount, int payDay, boolean payCheck) {
        this.id = id;
        this.payRoom = payRoom;
        this.payName = payName;
        this.payCountMonth = payCount;
        this.payDay = payDay;
        this.payCheckMonth = payCheck;
    }
}

