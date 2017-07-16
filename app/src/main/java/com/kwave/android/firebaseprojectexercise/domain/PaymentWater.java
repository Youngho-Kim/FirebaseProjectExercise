package com.kwave.android.firebaseprojectexercise.domain;

/**
 * Created by kwave on 2017-07-12.
 */

public class PaymentWater {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public String room;  // 호수
    public String name;     // 이름
    public String countWater; // 수도세
    public String use; // 수도 사용량
    public String day;   // 납부일
    public boolean checkWater;  // 체크여부
    public int dataMonth;   // 현재 월 표시



    public PaymentWater() {
    }


    public PaymentWater(String room, String name, String countWater, String use, String day, boolean checkWater) {
        this.room = room;
        this.name = name;
        this.countWater = countWater;
        this.use = use;
        this.day = day;
        this.checkWater = checkWater;
    }
}
