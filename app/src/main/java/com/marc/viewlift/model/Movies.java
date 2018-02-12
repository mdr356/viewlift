package com.marc.viewlift.model;

/**
 * Created by disciplemarc on 2/10/18.
 */

public class Movies {
    private String title, tag, permaLink, image;

    public Movies(String title, String tag, String permaLink, String image) {
        //this.id = id;
        this.title = title;
        this.tag = tag;
        this.permaLink = permaLink;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPermaLink() {
        return permaLink;
    }

    public void setPermaLink(String permaLink) {
        this.permaLink = permaLink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
