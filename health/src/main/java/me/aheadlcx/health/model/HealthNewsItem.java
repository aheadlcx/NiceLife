package me.aheadlcx.health.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午9:03
 */
public class HealthNewsItem extends RealmObject implements Serializable{
//    private long keyId;
    @PrimaryKey
    private int id;
    @SerializedName("count")
    private int count;
    @SerializedName("description")
    private String description;
    @SerializedName("fcount")
    private int fcount;
    @SerializedName("img")
    private String img;
    @SerializedName("infoclass")
    private int infoclass;//2 就是健康咨询
    @SerializedName("keywords")
    private String keywords;
    @SerializedName("rcount")
    private int rcount;
    @SerializedName("time")
    private long time;
    @SerializedName("title")
    private String title;

    private int loreclass;//3 就是健康知识
    private int askclass;//2 就是健康问答列表

    public HealthNewsItem setLoreclass(int loreclass) {
        this.loreclass = loreclass;
        return this;
    }

    public HealthNewsItem setAskclass(int askclass) {

        this.askclass = askclass;
        return this;
    }

    public int getLoreclass() {

        return loreclass;
    }

    public int getAskclass() {

        return askclass;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getInfoclass() {
        return infoclass;
    }

    public void setInfoclass(int infoclass) {
        this.infoclass = infoclass;
    }

//    public void setKeyId(long keyId) {
//        this.keyId = keyId;
//    }
//
//    public long getKeyId() {
//
//        return keyId;
//    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTest(String test){
        this.description = test;
    }
}
