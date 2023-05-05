package com.example.kosrbajnoksag;

public class Event {
    private String name;
    private String day;
    private String startTime;

    public Event(String name, String startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

}
