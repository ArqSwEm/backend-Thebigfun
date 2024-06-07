package com.thebigfun.booking_service.service;import com.thebigfun.booking_service.dto.BookingDTO;import com.thebigfun.booking_service.model.Booking;import org.springframework.stereotype.Service;import reactor.core.publisher.Mono;import java.util.List;@Servicepublic interface Booking_Service {    public abstract Booking createBooking(Booking booking);    public abstract List<Booking> getAllBookings();    public abstract Booking getBookingById(Long  booking_id);    public abstract Booking updateBooking(Long booking_id, Booking booking);    public abstract void deleteBooking(Long booking_id);    public abstract boolean existsBookingByBookingId(Long booking_id);    //boolean existsBookingByBookingId(Long booking_id);    // Método adicional para manejar el estado del pago si se considera necesario    void updatePaymentStatus(Long booking_id, Booking.PaymentStatus paymentStatus);    // Métodos adicionales para manejar la expiración y el estado del pago    void checkAndReleaseExpiredBookings();  // Este método podría ser parte de una tarea programada    void cancelBooking(Long bookingId);    List<BookingDTO>getBookingsByUserId(Long userId);    // Agregar método para calcular el total del precio de los tickets comprados por un usuario    // Añadimos los métodos nuevos para calcular    Mono<Double> calculateTotalPriceForUser(Long userId);    void updateBookingTotalPrice(Long userId, Double totalPrice);    Mono<Void>  calculateAndUpdateBooking(Long userId);}