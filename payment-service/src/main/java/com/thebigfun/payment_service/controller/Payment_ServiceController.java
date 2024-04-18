package com.thebigfun.payment_service.controller;import com.thebigfun.payment_service.model.entity.Payment_Service;import com.thebigfun.payment_service.service.Payment_ServiceServiceImpl;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import java.util.List;import java.util.Optional;@RestController@RequestMapping("/api/payments_services")public class Payment_ServiceController {    private final Payment_ServiceServiceImpl payment_serviceService;    public Payment_ServiceController(Payment_ServiceServiceImpl paymentServiceService){        this.payment_serviceService = paymentServiceService;    }    // Crear un nuevo Payment    @PostMapping    public ResponseEntity<Payment_Service> createPayment(@RequestBody Payment_Service payment_service){        Payment_Service createdPayment = payment_serviceService.create( payment_service);        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);    }    // Obtener todos los ticket    @GetMapping    public ResponseEntity<List<Payment_Service>>getAllPayments(){        List<Payment_Service> paymentServices = payment_serviceService.fetchAll();        return new ResponseEntity<>(paymentServices, HttpStatus.OK);    }    // Obtener un ticket específico por ID    @GetMapping("/{id}")    public ResponseEntity<Payment_Service> getEventById(@PathVariable Integer id) {        Optional<Payment_Service> event = payment_serviceService.fetchById(id);        return event.map(value -> new ResponseEntity<>(value, HttpStatus.OK))                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));    }    // Actualizar un ticket existente    @PutMapping("/{id}")    public ResponseEntity<Payment_Service> updatePayment(@PathVariable Integer id, @RequestBody Payment_Service payment) {        try {            Payment_Service updatedpayment = payment_serviceService.update(id, payment);            return new ResponseEntity<>(updatedpayment, HttpStatus.OK);        } catch (RuntimeException e) {            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }    }    // Eliminar un ticket    @DeleteMapping("/{id}")    public ResponseEntity<Void> deletePayment(@PathVariable Integer id) {        try {            payment_serviceService.delete(id);            return new ResponseEntity<>(HttpStatus.NO_CONTENT);        } catch (RuntimeException e) {            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }    }}