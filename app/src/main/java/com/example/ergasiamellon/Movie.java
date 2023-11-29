package com.example.ergasiamellon;

public class Movie {
    private int id;
    private String title;
    private String img_url;
    private int duration;
    private int rating;
    private double ticket_price;

    public Movie(int id, String title, String img_url, int duration, int rating, double ticket_price) {
        this.id = id;
        this.title = title;
        this.img_url = img_url;
        this.duration = duration;
        this.rating = rating;
        this.ticket_price = ticket_price;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }
}
