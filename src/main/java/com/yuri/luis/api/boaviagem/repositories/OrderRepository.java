package com.yuri.luis.api.boaviagem.repositories;

import com.yuri.luis.api.boaviagem.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
