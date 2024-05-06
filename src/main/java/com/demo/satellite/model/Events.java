package com.demo.satellite.model;

import java.util.List;

public class Events
{
    public List<Event> events;

    public Status status;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
