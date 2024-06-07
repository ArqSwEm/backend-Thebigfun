package com.thebigfun.payment_service.service.Impl;import com.thebigfun.payment_service.dtos.PaymentRequest;import com.thebigfun.payment_service.service.PaymentGatewayService;import org.springframework.beans.factory.annotation.Qualifier;import org.springframework.beans.factory.annotation.Value;import org.springframework.core.ParameterizedTypeReference;import org.springframework.http.HttpHeaders;import org.springframework.http.MediaType;import org.springframework.stereotype.Service;import org.springframework.web.reactive.function.client.WebClient;import reactor.core.publisher.Mono;import java.util.HashMap;import java.util.Map;@Service@Qualifier("culqiService")public class CulqiServiceImpl implements PaymentGatewayService {    @Value("${culqi.apiKey}")    private String apiKey;    @Value("${culqi.apiBaseUrl}")    private String apiBaseUrl;    private final WebClient webClient;    public CulqiServiceImpl(WebClient.Builder webClientBuilder) {        this.webClient = webClientBuilder.baseUrl(apiBaseUrl).build();    }    @Override    public Mono<Map<String, Object>> processPayment(PaymentRequest request) {        String url = "/charges";        Map<String, Object> requestBody = new HashMap<>();        requestBody.put("amount", (long) (request.getAmount() * 100)); // Cantidad en centavos        requestBody.put("currency_code", request.getCurrency());        requestBody.put("email", "customer@example.com");        requestBody.put("source_id", request.getToken());        requestBody.put("description", request.getDescription());        return webClient.post()                .uri(url)                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)                .contentType(MediaType.APPLICATION_JSON)                .bodyValue(requestBody)                .retrieve()                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});    }}