package com.producthop.softuni.service;

import com.producthop.softuni.domain.models.services.OrderServiceModel;

import java.util.List;

public interface OrderService {

    public OrderServiceModel save(OrderServiceModel orderServiceModel);

    public List<OrderServiceModel> getAll();

    public OrderServiceModel getById(String orderId);
}
