package com.example.myapplication.models.recycler;

public class Meeting {

    private String title;
    private String ownerName;
    private String status;
    private String time;

    public Meeting(String title, String ownerName, String status, String time) {
        this.title = title;
        this.ownerName = ownerName;
        this.status = status;
        this.time = time;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
