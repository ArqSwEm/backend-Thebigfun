package com.thebigfun.event_service.controller;import com.thebigfun.event_service.model.entity.Event;import com.thebigfun.event_service.service.Impl.EventServiceImpl;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import java.util.List;@RestController@RequestMapping("/api/event_services")public class Event_ServiceController {private final EventServiceImpl eventServiceImpl;@Autowiredpublic Event_ServiceController(EventServiceImpl eventServiceService){    eventServiceImpl = eventServiceService;}    // Crear un nuevo evento    @PostMapping    public ResponseEntity<Event> createEvent(@RequestBody Event event){    Event createdEvent = eventServiceImpl.createEvent(event);    return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);    }    // Obtener todos los eventos    @GetMapping    public ResponseEntity<List<Event>>getAllEvents(){    List<Event> eventList = eventServiceImpl.getAllEvents();    return new ResponseEntity<>(eventList, HttpStatus.OK);    }    // Obtener un evento específico por ID    @GetMapping("/{id}")    public ResponseEntity<Event> getEventById(@PathVariable Long id) {    try {        Event event = eventServiceImpl.getEventById(id);        return ResponseEntity.ok(event);    }catch (RuntimeException ex){        return ResponseEntity.notFound().build();    }}    // Actualizar un evento existente    @PutMapping("/{id}")    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {        try {            Event updatedEvent = eventServiceImpl.updateEvent(id, event);            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);        } catch (RuntimeException e) {            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }    }    // Eliminar un evento    @DeleteMapping("/{id}")    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {        try {            eventServiceImpl.deleteEvent(id);            return ResponseEntity.noContent().build();        } catch (RuntimeException e) {            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }    }    @GetMapping("/events/byOrganizer/{organizerId}")    public ResponseEntity<List<Event>> getEventsByOrganizer(@PathVariable Long organizerId) {        List<Event> events = eventServiceImpl.findEventsByOrganizerId(organizerId);        if (events.isEmpty()) {            return ResponseEntity.notFound().build();        }        return ResponseEntity.ok(events);    }}