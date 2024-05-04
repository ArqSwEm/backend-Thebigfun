package com.thebigfun.booking_service.dto;import com.fasterxml.jackson.annotation.JsonFormat;import com.thebigfun.booking_service.model.Booking;import lombok.AllArgsConstructor;import lombok.Builder;import lombok.Data;import lombok.NoArgsConstructor;import java.time.LocalDateTime;@Data@AllArgsConstructor@NoArgsConstructor@Builderpublic class BookingDTO {    private Long id;    private Long numberOfTickets;    private Double totalPrice;    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")    private LocalDateTime reservationDate;    private String notes;    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")    private LocalDateTime expirationDate;    private Long userId;    private Long eventId;    private Booking.BookingStatus status;    private Booking.PaymentStatus paymentStatus;}