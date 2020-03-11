package com.producthop.softuni.repository;

import com.producthop.softuni.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    public List<Order> findAllByOrderByOrderDateAsc();
}
