package me.aheadlcx.health.test;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/12/5 下午10:31
 */

public class Page extends RealmObject {
    @PrimaryKey
    private String query;
    @SerializedName("pageid")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("extract")
    private String content;

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtra() {

        return extra;
    }

    private String extra;

    public Page() {
    }

    public Page(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

