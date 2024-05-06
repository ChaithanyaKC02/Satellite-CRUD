package com.demo.satellite.adapter;

import com.demo.satellite.common.DateUtil;
import com.demo.satellite.rest.model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.annotation.RequestScope;

import java.util.*;

@Component
@RequestScope
public class EventAdapter
{

    public com.demo.satellite.model.Event prepareRequest(com.demo.satellite.rest.model.Event event)
    {
        com.demo.satellite.model.Event eventData = new com.demo.satellite.model.Event();
        eventData.setSatelliteName(event.getSatelliteName());
        eventData.setDate(event.getDate().toLocalDateTime());
        eventData.setDescription(event.getDescription());
        eventData.setPriority(event.getPriority());
        return eventData;
    }

    public ResponseEntity<List<com.demo.satellite.rest.model.Event>> retrieveResponse(List<com.demo.satellite.model.Event> events)
    {
        if (Objects.nonNull(events) && !CollectionUtils.isEmpty(events))
        {
            List<com.demo.satellite.rest.model.Event> eventList = new ArrayList<>();
            for (com.demo.satellite.model.Event e : events)
            {
                Event event = new Event();
                event.setSatelliteName(e.getSatelliteName());
                event.setDate(DateUtil.convertToOffsetDateTime(e.getDate()));
                event.setDescription(e.getDescription());
                event.setPriority(e.getPriority());
                eventList.add(event);
            }
            return ResponseEntity.ok(eventList);
        }
        return null;
    }
}
