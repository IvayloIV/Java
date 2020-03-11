package com.producthop.softuni.service.impl;

import com.producthop.softuni.domain.entities.Order;
import com.producthop.softuni.domain.entities.Product;
import com.producthop.softuni.domain.models.services.OrderServiceModel;
import com.producthop.softuni.domain.models.services.ProductServiceModel;
import com.producthop.softuni.domain.models.views.order.OrderListViewModel;
import com.producthop.softuni.repository.OrderRepository;
import com.producthop.softuni.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderServiceModel save(OrderServiceModel orderServiceModel) {
        try {
            orderServiceModel.setOrderDate(LocalDateTime.now());
            Order order = this.modelMapper.map(orderServiceModel, Order.class);
            return this.modelMapper.map(this.orderRepository.save(order), OrderServiceModel.class);
        } catch (Exception err) {
            return null;
        }
    }

    @Override
    public List<OrderServiceModel> getAll() {
        return this.orderRepository.findAllByOrderByOrderDateAsc()
                .stream()
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderServiceModel getById(String orderId) {
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order id."));
        return this.modelMapper.map(order, OrderServiceModel.class);
    }
}
