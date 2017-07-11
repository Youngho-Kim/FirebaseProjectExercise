package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-11.
 */

public class PayWaterData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public int PayRoom;  // 호수
    public String PayName;     // 이름
    public int PayCountWater; // 금액
    public int PayUse; // 사용량
    public int PayDay;   // 납부일
    public boolean PayCheckWater;

    public PayWaterData(){

    }

    public PayWaterData(String id, int payRoom, String payName, int payCount, int payUse, int payDay, boolean payCheck) {
        this.id = id;
        this.PayRoom = payRoom;
        this.PayName = payName;
        this.PayCountWater = payCount;
        this.PayUse = payUse;
        this.PayDay = payDay;
        this.PayCheckWater = payCheck;
    }
}

