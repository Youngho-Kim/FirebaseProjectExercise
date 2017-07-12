package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-11.
 */

public class PayWaterData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public int payRoom;  // 호수
    public String payName;     // 이름
    public int payCountWater; // 금액
    public int payUse; // 사용량
    public int payDay;   // 납부일
    public boolean payCheckWater;

    public PayWaterData(){

    }

    public PayWaterData(String id, int payRoom, String payName, int payCount, int payUse, int payDay, boolean payCheck) {
        this.id = id;
        this.payRoom = payRoom;
        this.payName = payName;
        this.payCountWater = payCount;
        this.payUse = payUse;
        this.payDay = payDay;
        this.payCheckWater = payCheck;
    }
}

