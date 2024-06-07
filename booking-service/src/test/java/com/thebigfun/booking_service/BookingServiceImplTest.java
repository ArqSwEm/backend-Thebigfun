package com.thebigfun.booking_service;

import com.thebigfun.booking_service.dto.TicketDTO;
import com.thebigfun.booking_service.model.Booking;
import com.thebigfun.booking_service.model.BookingMapper;
import com.thebigfun.booking_service.repository.Booking_ServiceRepository;
import com.thebigfun.booking_service.service.impl.BookingServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class BookingServiceImplTest {

    @Mock
    private Booking_ServiceRepository bookingServiceRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private WebClient.Builder webClientBuilder;

    private MockWebServer mockWebServer;



    private Booking booking;


    @BeforeEach
    void setUp() throws IOException {



        // Inicializar MockWebServer
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // Crear un WebClient con la URL del MockWebServer
        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        if (webClientBuilder == null) {
            webClientBuilder = mock(WebClient.Builder.class);
        }


        // Configurar los mocks para WebClient.Builder
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);

        // Inicializar la instancia de BookingServiceImpl con el WebClient configurado
        bookingService = new BookingServiceImpl(bookingServiceRepository, bookingMapper, webClientBuilder, "http://localhost");



        // Inicializar un objeto de Booking para las pruebas
        booking = new Booking();
        booking.setNumberOfTickets(2L);
        booking.setTotalPrice(200.0);
        booking.setReservationDate(LocalDateTime.now());
        booking.setExpirationDate(LocalDateTime.now().plusMinutes(1));
        booking.setNotes("Sample notes");
        booking.setUserId(1L);
        booking.setEventId(1L);
        booking.setPaymentId(1L);
        booking.setStatus(Booking.BookingStatus.PENDING);
        booking.setPaymentStatus(Booking.PaymentStatus.AWAITING_PAYMENT);

    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void createBooking() {



        when(bookingServiceRepository.save(any(Booking.class))).thenReturn(booking);

        Booking createdBooking = bookingService.createBooking(booking);

        assertNotNull(createdBooking);
        assertEquals(booking.getReservationDate(), createdBooking.getReservationDate());
        verify(bookingServiceRepository, times(1)).save(booking);
    }

    @Test
    void getAllBookings() {
        when(bookingServiceRepository.findAll()).thenReturn(Arrays.asList(booking));

        List<Booking> bookings = bookingService.getAllBookings();

        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        verify(bookingServiceRepository, times(1)).findAll();
    }

    @Test
    void getBookingById() {
        when(bookingServiceRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        Booking foundBooking = bookingService.getBookingById(1L);

        assertNotNull(foundBooking);
        assertEquals(booking.getReservationDate(), foundBooking.getReservationDate());
        verify(bookingServiceRepository, times(1)).findById(1L);
    }

    @Test
    void updateBooking() {
        when(bookingServiceRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        when(bookingServiceRepository.save(any(Booking.class))).thenReturn(booking);

        Booking updatedBooking = bookingService.updateBooking(1L, booking);

        assertNotNull(updatedBooking);
        assertEquals(booking.getReservationDate(), updatedBooking.getReservationDate());
        verify(bookingServiceRepository, times(1)).findById(1L);
        verify(bookingServiceRepository, times(1)).save(booking);
    }

    @Test
    void deleteBooking() {
        when(bookingServiceRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        booking.setStatus(Booking.BookingStatus.CANCELLED);
        when(bookingServiceRepository.save(any(Booking.class))).thenReturn(booking);

        bookingService.deleteBooking(1L);

        assertEquals(Booking.BookingStatus.CANCELLED, booking.getStatus());
        verify(bookingServiceRepository, times(1)).findById(1L);
        verify(bookingServiceRepository, times(1)).save(booking);
    }

    @Test
    void existsBookingByBookingId() {
        when(bookingServiceRepository.existsById(anyLong())).thenReturn(true);

        boolean exists = bookingService.existsBookingByBookingId(1L);

        assertTrue(exists);
        verify(bookingServiceRepository, times(1)).existsById(1L);
    }

    @Test
    void updatePaymentStatus() {
        when(bookingServiceRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        booking.setPaymentStatus(Booking.PaymentStatus.PAID);
        when(bookingServiceRepository.save(any(Booking.class))).thenReturn(booking);

        bookingService.updatePaymentStatus(1L, Booking.PaymentStatus.PAID);

        assertEquals(Booking.PaymentStatus.PAID, booking.getPaymentStatus());
        verify(bookingServiceRepository, times(1)).findById(1L);
        verify(bookingServiceRepository, times(1)).save(booking);
    }

    @Test
    void calculateTotalPriceForUser() throws InterruptedException {
        List<TicketDTO> tickets = Arrays.asList(
                new TicketDTO(1L, "Category1", 100.0, "ACTIVE", "QRCode1", 1L, 1L, true),
                new TicketDTO(2L, "Category2", 200.0, "ACTIVE", "QRCode2", 1L, 1L, true)
        );

        mockWebServer.enqueue(new MockResponse()
                .setBody("[{\"id\":1,\"category\":\"Category1\",\"price\":100.0,\"status\":\"ACTIVE\",\"qrCode\":\"QRCode1\",\"eventId\":1,\"userId\":1,\"isActive\":true},{\"id\":2,\"category\":\"Category2\",\"price\":200.0,\"status\":\"ACTIVE\",\"qrCode\":\"QRCode2\",\"eventId\":1,\"userId\":1,\"isActive\":true}]")
                .addHeader("Content-Type", "application/json"));

        Mono<Double> totalPriceMono = bookingService.calculateTotalPriceForUser(1L);

        Double totalPrice = totalPriceMono.block();
        assertEquals(300.0, totalPrice);
    }
}
