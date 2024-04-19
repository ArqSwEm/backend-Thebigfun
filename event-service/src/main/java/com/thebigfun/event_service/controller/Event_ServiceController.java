package com.thebigfun.event_service.controller;import com.thebigfun.event_service.model.entity.Event;import com.thebigfun.event_service.service.Impl.EventServiceImpl;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import java.util.List;import java.util.Optional;@RestController@RequestMapping("/api/event_services")//@AllArgsConstructorpublic class Event_ServiceController {private final EventServiceImpl event_serviceService;@Autowiredpublic Event_ServiceController(EventServiceImpl eventServiceService){    event_serviceService = eventServiceService;}    // Crear un nuevo evento    @PostMapping    public ResponseEntity<Event> createEvent(@RequestBody Event event_){    Event createdEvent = event_serviceService.create(event_);    return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);    }    // Obtener todos los eventos    @GetMapping    public ResponseEntity<List<Event>>getAllEvents(){    List<Event> eventServices = event_serviceService.fetchAll();    return new ResponseEntity<>(eventServices, HttpStatus.OK);    }    // Obtener un evento específico por ID    @GetMapping("/{id}")    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {        Optional<Event> event = event_serviceService.fetchById(id);        return event.map(value -> new ResponseEntity<>(value, HttpStatus.OK))                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));    }    // Actualizar un evento existente    @PutMapping("/{id}")    public ResponseEntity<Event> updateEvent(@PathVariable Integer id, @RequestBody Event event) {        try {            Event updatedEvent = event_serviceService.update(id, event);            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);        } catch (RuntimeException e) {            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }    }    // Eliminar un evento    @DeleteMapping("/{id}")    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id) {        try {            event_serviceService.delete(id);            return ResponseEntity.noContent().build();        } catch (RuntimeException e) {            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }    }}