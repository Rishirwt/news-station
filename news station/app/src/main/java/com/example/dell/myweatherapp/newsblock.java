package com.example.dell.myweatherapp;

public class newsblock {
    String author;
    String title;
    String desc;
    String urlimage;
    String link;
    String published;

    public newsblock(String author, String title, String desc, String urlimage, String link, String published) {
        this.author = author;
        this.title = title;
        this.desc = desc;
        this.urlimage = urlimage;
        this.link = link;
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public String getLink() {
        return link;
    }

    public String getPublished() {
        return published;
    }
}
