package com.interest.enums;

/**
 * Created by 431 on 2015/4/10.
 */
public enum InterestType {
    MUSIC(1),NEWS(2),BOOKS(3),SPORT(4);

    private int vaule = 0;
    InterestType(int vaule){
        this.vaule = vaule;
    }

    public static InterestType valueOf(int value){
        switch (value){
            case 1: return MUSIC;
            case 2: return NEWS;
            case 3: return BOOKS;
            case 4: return SPORT;
            default: return null;
        }
    }

    public int getVaule(){
        return this.vaule;
    }
}
