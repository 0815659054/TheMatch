package com.example.administrator.thematch;

public class Card {
    private String imgURL;
    private String title;
    private String date;

    public Card(String imgURL, String title,String date) {
        this.imgURL = imgURL;
        this.title = title;
        this.date = date;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
