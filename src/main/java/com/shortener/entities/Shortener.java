package com.shortener.entities;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

/**
 * Created by isvaldo on 26/03/16.
 */
public class Shortener implements Serializable{
    private static final long serialVersionUID = 1L;
    private static long longId = 100;

    private String id;

    @URL
    @NotEmpty
    public String targeUrl;

    @NotEmpty
    public String codeName;

    @NotEmpty
    public Long views;

    public Shortener(String id, String targeUrl, String codeName) {
        this.id = id;
        this.targeUrl = targeUrl;
        this.codeName = codeName;
        this.views = 0L;
    }

    public static String generateNextId() {
        return String.valueOf(longId++);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargeUrl() {
        return targeUrl;
    }

    public void setTargeUrl(String targeUrl) {
        this.targeUrl = targeUrl;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
