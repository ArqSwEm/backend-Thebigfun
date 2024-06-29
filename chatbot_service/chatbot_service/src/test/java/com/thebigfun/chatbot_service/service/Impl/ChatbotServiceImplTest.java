package com.thebigfun.chatbot_service.service.Impl;

import com.thebigfun.chatbot_service.model.Chatbot;
import com.thebigfun.chatbot_service.repository.ChatbotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ChatbotServiceImplTest {

    @Mock
    private ChatbotRepository chatbotRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ChatbotServiceImpl chatbotService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateChatAndReturnResponse_Success() {

        String message = "{\"message\": \"Hola\"}";
        String expectedResponse = "Hey! Bienvenido a theBigFun Platform. Un lugar donde podras adquirir entradas a eventos";
        LocalDateTime currentTime = LocalDateTime.now();


        when(chatbotRepository.save(any(Chatbot.class))).thenReturn(new Chatbot());
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                "[{\"text\": \"" + expectedResponse + "\"}]",
                HttpStatus.OK
        );
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);


        String actualResponse = chatbotService.createChatAndReturnResponse(message);


        assertEquals(expectedResponse, actualResponse);
        verify(chatbotRepository, times(2)).save(any(Chatbot.class));
    }

    @Test
    public void testCreateChatAndReturnResponse_EmptyResponse() {

        String message = "{\"message\": \"¿Dónde será el próximo evento?\"}";
        String expectedResponse = "Error: Response body is empty";


        when(chatbotRepository.save(any(Chatbot.class))).thenReturn(new Chatbot());
        ResponseEntity<String> responseEntity = new ResponseEntity<>("", HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);


        String actualResponse = chatbotService.createChatAndReturnResponse(message);


        assertEquals(expectedResponse, actualResponse);
        verify(chatbotRepository, times(1)).save(any(Chatbot.class));
    }

    @Test
    public void testCreateChatAndReturnResponse_JsonException() {

        String message = "invalid json";
        String expectedResponse = "Error: JSON Exception";


        String actualResponse = chatbotService.createChatAndReturnResponse(message);


        assertEquals(expectedResponse, actualResponse);
        verify(chatbotRepository, times(0)).save(any(Chatbot.class));
    }

    @Test
    public void testCreateChatAndReturnResponse_Exception() {

        String message = "{\"message\": \"¿Dónde será el próximo evento?\"}";
        String expectedResponse = "Error: Exception";


        when(chatbotRepository.save(any(Chatbot.class))).thenThrow(new RuntimeException("Test Exception"));


        String actualResponse = chatbotService.createChatAndReturnResponse(message);


        assertEquals(expectedResponse, actualResponse);
        verify(chatbotRepository, times(1)).save(any(Chatbot.class));
    }
}







