package com.thebigfun.payment_service;


import com.thebigfun.payment_service.model.Payment;
import com.thebigfun.payment_service.repository.PaymentRepository;
import com.thebigfun.payment_service.service.Impl.PaymentServiceImpl;
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
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPayment() {
        Payment payment = new Payment();
        payment.setId(1L);

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment createdPayment = paymentService.createPayment(payment);

        assertNotNull(createdPayment);
        assertEquals(payment.getId(), createdPayment.getId());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void getAllPayments() {
        Payment payment1 = new Payment();
        Payment payment2 = new Payment();

        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment1, payment2));

        List<Payment> payments = paymentService.getAllPayments();

        assertFalse(payments.isEmpty());
        assertEquals(2, payments.size());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void getPaymentById() {
        Payment payment = new Payment();
        payment.setId(1L);

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        Payment foundPayment = paymentService.getPaymentById(1L);

        assertNotNull(foundPayment);
        assertEquals(payment.getId(), foundPayment.getId());
        verify(paymentRepository, times(1)).findById(1L);
    }

    @Test
    void updatePayment() {
        Payment payment = new Payment();
        payment.setId(1L);

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment updatedPayment = paymentService.updatePayment(1L, payment);

        assertNotNull(updatedPayment);
        assertEquals(payment.getId(), updatedPayment.getId());
        verify(paymentRepository, times(1)).findById(1L);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void deletePayment() {
        Payment payment = new Payment();
        payment.setId(1L);

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        paymentService.deletePayment(1L);

        assertFalse(payment.getIsActive());
        assertNotNull(payment.getDeletedAt());
        verify(paymentRepository, times(1)).findById(1L);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void existsPaymentByPaymentId() {
        when(paymentRepository.existsById(1L)).thenReturn(true);

        boolean exists = paymentService.existsPaymentByPaymentId(1L);

        assertTrue(exists);
        verify(paymentRepository, times(1)).existsById(1L);
    }
}

