package com.thebigfun.booking_service.service;import com.thebigfun.booking_service.model.entity.Booking;import java.util.List;public interface Booking_Service {    public abstract Booking createBooking(Booking booking);    public abstract List<Booking> getAllBookings();    public abstract Booking getBookingById(Long  booking_id);    public abstract Booking updateBooking(Long booking_id, Booking booking);    public abstract void deleteBooking(Long booking_id);    public abstract boolean existsBookingByBookingId(Long booking_id);}