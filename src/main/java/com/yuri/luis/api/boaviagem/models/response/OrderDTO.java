package com.yuri.luis.api.boaviagem.models.response;

import com.yuri.luis.api.boaviagem.models.Address;
import com.yuri.luis.api.boaviagem.models.Client;
import com.yuri.luis.api.boaviagem.models.Payment;
import com.yuri.luis.api.boaviagem.models.Product;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    @NotBlank
    private Double totalPrice;
    @NotBlank
    private Client client;
    @NotBlank
    private Address deliveryAddress;
    @NotBlank
    private Payment payment;
    @NotBlank
    private List<Product> products;
    private LocalDateTime dateTime;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
