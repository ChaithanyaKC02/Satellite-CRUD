package com.demo.satellite.controller;

import com.demo.satellite.adapter.EventAdapter;
import com.demo.satellite.rest.api.EventsApi;
import com.demo.satellite.rest.model.Event;
import com.demo.satellite.rest.model.Status;
import com.demo.satellite.service.SatelliteEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/restfulservice")
public class SatelliteController implements EventsApi
{

    @Autowired
    EventAdapter eventAdapter;

    @Autowired
    SatelliteEventData satelliteEvent;


    /**
     * DELETE /events : Deletes the events infomation.
     *
     * @param eventId (optional)
     * @return Success (status code 200)
     * or Error (status code 200)
     */
    @Override
    public ResponseEntity<Status> delete(String eventId)
    {
        com.demo.satellite.model.Status status = satelliteEvent.deleteEvent(eventId);
        return prepareResponse(status);
    }

    /**
     * GET /events : retuns events infomation.
     *
     * @return Success (status code 200)
     * or Error (status code 200)
     */
    @Override
    public ResponseEntity<List<com.demo.satellite.rest.model.Event>> getAllEventInformation()
    {
        List<com.demo.satellite.model.Event> events = satelliteEvent.getEventsInformation();
        return eventAdapter.retrieveResponse(events);
    }

    /**
     * GET /events/satellite_id : retuns events infomation.
     *
     * @param satelliteId (optional)
     * @return Success (status code 200)
     * or Error (status code 200)
     */
    @Override
    public ResponseEntity<List<com.demo.satellite.rest.model.Event>> getEventInformation(String satelliteId)
    {
            List<com.demo.satellite.model.Event> event = satelliteEvent.getEventInformation(satelliteId);
            return eventAdapter.retrieveResponse(event);
    }

    /**
     * @param events (required)
     * @return
     */
    @Override
    public ResponseEntity<Status> post(Event events)
    {
        com.demo.satellite.model.Event eventData = eventAdapter.prepareRequest(events);
        com.demo.satellite.model.Status status = satelliteEvent.saveEventInformation(eventData);
        return prepareResponse(status);
    }

    private ResponseEntity<Status> prepareResponse(com.demo.satellite.model.Status status)
    {
        Status statusData = new Status();
        statusData.setCode(status.getCode());
        statusData.setMessage(status.getMessage());
        statusData.setType(status.getType());
        if (statusData.getType().equals("ERROR"))
        {
            return ResponseEntity.internalServerError().body(statusData);
        }
        return ResponseEntity.ok(statusData);
    }
}
