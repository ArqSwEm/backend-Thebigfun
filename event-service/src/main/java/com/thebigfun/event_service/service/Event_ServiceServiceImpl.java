package com.thebigfun.event_service.service;import com.thebigfun.event_service.model.entity.Event_Service;import com.thebigfun.event_service.repository.Event_ServiceRepository;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;import java.util.Optional;@Servicepublic class Event_ServiceServiceImpl {    private final Event_ServiceRepository event_serviceRepository;    @Autowired    public Event_ServiceServiceImpl(Event_ServiceRepository event_serviceRepository) {        this.event_serviceRepository = event_serviceRepository;    }    // Leer - Obtener todos los registros    public List<Event_Service> fetchAll() {        return event_serviceRepository.findAll();    }    // Leer - Obtener un registro por ID    public Optional<Event_Service> fetchById(Integer id) {        return  event_serviceRepository.findById(id);    }    // Crear - Añadir un nuevo registro    public Event_Service create(Event_Service event_service){        return  event_serviceRepository.save(event_service);    }    // Actualizar - Actualizar un registro existente    public Event_Service update(Integer id, Event_Service event_service){        Event_Service event_service_update = event_serviceRepository.findById(id)                .orElseThrow(()->new RuntimeException("Event not found with id" + id));        event_service_update.setTitle(event_service.getTitle());        event_service_update.setDescription(event_service.getDescription());        event_service_update.setStartDate(event_service.getStartDate());        event_service_update.setEndDate(event_service.getEndDate());        event_service_update.setLocation(event_service.getLocation());        // Aquí se deberían copiar todos los campos que se pueden actualizar        // Omitiendo userId si no se debe cambiar después de la creación        return event_serviceRepository.save(event_service_update);    }    // Eliminar - Borrar un registro    public void delete(Integer id) {        Event_Service event_service = event_serviceRepository.findById(id)                .orElseThrow(()-> new RuntimeException("Event not found wiht id" + id));        event_serviceRepository.delete(event_service);    }}