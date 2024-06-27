package com.thebigfun.event_service;

import com.thebigfun.event_service.model.Event;
import com.thebigfun.event_service.repository.EventRepository;
import com.thebigfun.event_service.service.Impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private Event event;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        event = new Event();
        event.setTitle("Sample Event");
        event.setDescription("This is a sample event description.");
        event.setStartDate(LocalDateTime.of(2024, 7, 1, 10, 0));
        event.setEndDate(LocalDateTime.of(2024, 7, 1, 12, 0));
        event.setLocation("Sample Location");
        event.setIsActive(true);
        event.setOrganizerId(1L);
    }

    @Test
    void createEvent() {
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event createdEvent = eventService.createEvent(event);

        assertNotNull(createdEvent);
        assertEquals(event.getTitle(), createdEvent.getTitle());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void getAllEvents() {
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event));

        List<Event> events = eventService.getAllEvents();

        assertFalse(events.isEmpty());
        assertEquals(1, events.size());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void getEventById() {
        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event));

        Event foundEvent = eventService.getEventById(1L);

        assertNotNull(foundEvent);
        assertEquals(event.getTitle(), foundEvent.getTitle());
        verify(eventRepository, times(1)).findById(1L);
    }

    @Test
    void updateEvent() {
        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event updatedEvent = eventService.updateEvent(1L, event);

        assertNotNull(updatedEvent);
        assertEquals(event.getTitle(), updatedEvent.getTitle());
        verify(eventRepository, times(1)).findById(1L);
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void deleteEvent() {
        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event));
        event.setIsActive(false);
        event.setDeletedAt(LocalDateTime.now());
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        eventService.deleteEvent(1L);

        assertFalse(event.getIsActive());
        assertNotNull(event.getDeletedAt());
        verify(eventRepository, times(1)).findById(1L);
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void existsEventByEventId() {
        when(eventRepository.existsById(anyLong())).thenReturn(true);

        boolean exists = eventService.existsEventByEventId(1L);

        assertTrue(exists);
        verify(eventRepository, times(1)).existsById(1L);
    }

    @Test
    void findEventsByOrganizerId() {
        when(eventRepository.findByOrganizerId(anyLong())).thenReturn(Arrays.asList(event));

        List<Event> events = eventService.findEventsByOrganizerId(1L);

        assertFalse(events.isEmpty());
        assertEquals(1, events.size());
        verify(eventRepository, times(1)).findByOrganizerId(1L);
    }
}
