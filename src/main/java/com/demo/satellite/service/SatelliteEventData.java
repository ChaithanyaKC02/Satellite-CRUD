package com.demo.satellite.service;

import com.demo.satellite.model.Event;
import com.demo.satellite.model.Status;

import java.util.List;

public interface SatelliteEventData
{

    Status saveEventInformation(Event events);

    Status deleteEvent(String eventId);

    List<Event> getEventInformation(String satelliteId);

    List<Event> getEventsInformation();

}
