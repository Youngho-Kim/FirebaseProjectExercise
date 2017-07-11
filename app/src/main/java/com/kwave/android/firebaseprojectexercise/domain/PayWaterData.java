package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-11.
 */

public class PayWaterData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public String PayRoom;  // 호수
    public String PayName;     // 이름
    public String PayCount; // 금액
    public String PayUse; // 사용량
    public String PayDay;   // 납부일
    public boolean PayCheck;

    public PayWaterData(){

    }

    public PayWaterData(String id, String payRoom, String payName, String payCount, String payUse, String payDay, boolean payCheck) {
        this.id = id;
        this.PayRoom = payRoom;
        this.PayName = payName;
        this.PayCount = payCount;
        this.PayUse = payUse;
        this.PayDay = payDay;
        this.PayCheck = payCheck;
    }
}

