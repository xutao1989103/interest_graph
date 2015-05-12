package com.interest.model.netEase;

/**
 * Created by 431 on 2015/5/11.
 */
public class Song {
    private Integer id;
    private String name;
    private Artist[] ar;
    private Album al;

    public Artist[] getAr() {
        return ar;
    }

    public void setAr(Artist[] ar) {
        this.ar = ar;
    }

    public Album getAl() {
        return al;
    }

    public void setAl(Album al) {
        this.al = al;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Song(){

    }

}
