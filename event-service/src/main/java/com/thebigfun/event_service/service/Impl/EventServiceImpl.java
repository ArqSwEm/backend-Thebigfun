package com.thebigfun.event_service.service.Impl;import com.thebigfun.event_service.model.entity.Event;import com.thebigfun.event_service.repository.EventRepository;import com.thebigfun.event_service.service.EventService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.List;@Servicepublic class EventServiceImpl implements EventService {    private final EventRepository eventRepository;    @Autowired    public EventServiceImpl(EventRepository event_serviceRepository) {        this.eventRepository = event_serviceRepository;    }    @Override    @Transactional    public Event createEvent(Event event) {        return eventRepository.save(event);    }    @Override    @Transactional    public List<Event> getAllEvents() {        return eventRepository.findAll();    }    @Override    @Transactional(readOnly = true)    public Event getEventById(Long event_id) {        return eventRepository.findById(event_id)                .orElseThrow(() -> new RuntimeException("Event not found"));    }    @Override    @Transactional    public Event updateEvent(Long event_id, Event eventDetails) {        Event existingEvent = eventRepository.findById(event_id)                .orElseThrow(() -> new RuntimeException("Event not found"));        existingEvent.setTitle(eventDetails.getTitle());        existingEvent.setDescription(eventDetails.getDescription());        existingEvent.setStartDate(eventDetails.getStartDate());        existingEvent.setEndDate(eventDetails.getEndDate());        existingEvent.setLocation(eventDetails.getLocation());        // actualiza otros campos necesarios        return eventRepository.save(existingEvent);    }    @Override    @Transactional    public void deleteEvent(Long event_id) {        if (!eventRepository.existsById(event_id)){            throw new IllegalArgumentException("Event not found with ID: "+ event_id);        }        eventRepository.deleteById(event_id);    }    @Override    @Transactional    public boolean existsEventByEventId(Long event_id) {        return eventRepository.existsById(event_id);    }    @Override    public List<Event> findEventsByOrganizerId(Long organizerId) {        return eventRepository.findByOrganizerId(organizerId);    }}