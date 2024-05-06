package com.demo.satellite.service;

import com.demo.satellite.common.StatusMapper;
import com.demo.satellite.model.Event;
import com.demo.satellite.model.Status;
import com.demo.satellite.persistence.entity.SatelliteEvent;
import com.demo.satellite.persistence.entity.repository.SatelliteEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("SatelliteEventData")
public class SatelliteEventApplication implements SatelliteEventData
{
    @Autowired
     private SatelliteEventRepository satelliteEventRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * @param events
     */
    @Override
    public Status saveEventInformation(Event events)
    {
        try
        {
            SatelliteEvent satelliteEvents = mapEventData(events);
            satelliteEventRepository.save(satelliteEvents);
            return prepareResponse(StatusMapper.SUCCESS,StatusMapper.SUCCESS_TYPE,StatusMapper.CREATE_SUCCESS);
        }
        catch (Exception e)
        {
            return prepareResponse(StatusMapper.ERROR,StatusMapper.ERROR_TYPE,StatusMapper.CREATE_FAILURE);
        }
    }

    /**
     * @param eventId
     */
    @Override
    @Transactional
    public Status deleteEvent(String eventId)
    {
        try
        {
            List<SatelliteEvent> satelliteEvents = satelliteEventRepository.findBySatelliteName(eventId);
            if(!CollectionUtils.isEmpty(satelliteEvents))
            {
                for (SatelliteEvent satelliteEvent : satelliteEvents)
                {
                    entityManager.remove(satelliteEvent);
                }
            }
            return prepareResponse(StatusMapper.SUCCESS,StatusMapper.SUCCESS_TYPE,StatusMapper.DELETION_SUCCESS);
        }
        catch (Exception e)
        {
            return prepareResponse(StatusMapper.ERROR,StatusMapper.ERROR_TYPE,StatusMapper.DELETION_ERROR);
        }
    }

    /**
     * @param satelliteId
     * @return
     */
    @Override
    public List<Event> getEventInformation(String satelliteId)
    {
        try
        {
            List<Event> eventList = new ArrayList<>();
            List<SatelliteEvent> satelliteEvents = findBySatelliteId(satelliteId);
            satelliteEvents.forEach(satelliteEvent -> {
                eventList.add(populateEventsData(satelliteEvent));
            });
            return eventList;
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        return java.util.Collections.emptyList();
    }

    /**
     * @return
     */
    @Override
    public List<Event> getEventsInformation()
    {
        try
        {
            List<Event> eventList = new ArrayList<>();
            List<SatelliteEvent> satelliteEvents = satelliteEventRepository.findAll();
            satelliteEvents.forEach(satelliteEvent -> {
                eventList.add(populateEventsData(satelliteEvent));
            });
            return eventList;
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        return java.util.Collections.emptyList();
    }

    /**
     * @param satelliteEvent
     * @return
     */
    private Event populateEventsData(SatelliteEvent satelliteEvent)
    {
        Event event = new Event();
        event.setSatelliteName(satelliteEvent.getSatelliteName());
        event.setPriority(satelliteEvent.getPriority());
        event.setDescription(satelliteEvent.getDescription());
        event.setDate(satelliteEvent.getSatelliteDate());
        return event;
    }

    /**
     * @param satelliteId
     * @return
     */
    List<SatelliteEvent> findBySatelliteId(String satelliteId)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SatelliteEvent> cq = cb.createQuery(SatelliteEvent.class);

        Root<SatelliteEvent> satelliteEventRoot = cq.from(SatelliteEvent.class);
        List<Predicate> predicates = new ArrayList<>();

        if (satelliteId != null)
        {
            predicates.add(cb.equal(satelliteEventRoot.get("satelliteName"), satelliteId));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

    /**
     * @param event
     * @return
     */
    private SatelliteEvent mapEventData(Event event)
    {
            SatelliteEvent satelliteEvent = new SatelliteEvent();
            satelliteEvent.setSatelliteName(event.getSatelliteName());
            satelliteEvent.setDescription(event.getDescription());
            satelliteEvent.setPriority(event.getPriority());
            satelliteEvent.setSatelliteDate(event.getDate());
        return satelliteEvent;
    }

    /**
     * @param code
     * @param type
     * @param message
     * @return
     */
    private Status prepareResponse(int code, String type, String message)
    {
        Status status = new Status();
        status.setCode(code);
        status.setType(type);
        status.setMessage(message);
        return status;
    }
}
