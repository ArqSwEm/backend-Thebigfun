package com.thebigfun.payment_service.dtos;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;@Data@AllArgsConstructor@NoArgsConstructorpublic class PaymentRequest {    private String token;    private double amount;    private String currency;    private String description;    private Long userId;    private Long bookingId;    public String getToken() {        return token;    }    public void setToken(String token) {        this.token = token;    }    public double getAmount() {        return amount;    }    public void setAmount(double amount) {        this.amount = amount;    }    public String getCurrency() {        return currency;    }    public void setCurrency(String currency) {        this.currency = currency;    }    public String getDescription() {        return description;    }    public void setDescription(String description) {        this.description = description;    }    public Long getUserId() {        return userId;    }    public void setUserId(Long userId) {        this.userId = userId;    }    public Long getBookingId() {        return bookingId;    }    public void setBookingId(Long bookingId) {        this.bookingId = bookingId;    }}