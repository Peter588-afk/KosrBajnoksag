package com.example.kosrbajnoksag;

import java.util.List;

public class Calender {
    private String name;
    private List<Event> events;

    public Calender(String name, List<Event> events) {
        this.name = name;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }
}