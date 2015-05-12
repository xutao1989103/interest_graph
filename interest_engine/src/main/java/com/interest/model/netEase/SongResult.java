package com.interest.model.netEase;

/**
 * Created by 431 on 2015/5/11.
 */
public class SongResult {
    private SongInfo[] allData;
    private Integer code;
    public SongResult(){

    }

    public SongInfo[] getAllData() {
        return allData;
    }

    public void setAllData(SongInfo[] allData) {
        this.allData = allData;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
