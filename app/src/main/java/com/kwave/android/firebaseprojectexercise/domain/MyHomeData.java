package com.kwave.android.firebaseprojectexercise.domain;

import java.util.Date;

/**
 * Created by kwave on 2017-07-12.
 */

public class MyHomeData {
    public String id;    // 파이어베이스의 push 로 자동생성된다.
    public int room;  // 호수
    public String name;     // 이름
    public String phoneNumber; // 핸드폰 번호
    public String contract;   // 계약기간
    public int day;   // 납부일
    public int dataMonth;   // 현재 월 표시
    public Date currentDay;   // 현재일자
    public int countTenant; // 월세
    public int countWater; // 수도세
    public int use; // 수도 사용량
    public boolean checkWater;  // 체크여부
    public boolean checkTenant;  // 체크여부

    // master Info

    public String masterHome;   // 집 이름
    public String masterName;   // 주인 이름
    public String masterAddr;   // 집 주소
    public String masterAccount;   // 주인 계좌번호
    public String masterPhoneNumber;   // 주인 전화번호
    public String masterTrash;   // 분리수거 사항
    public String masterNotify;   // 분리수거 사항

}
