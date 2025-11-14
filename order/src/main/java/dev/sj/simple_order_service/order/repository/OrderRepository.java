package dev.sj.simple_order_service.order.repository;

import dev.sj.simple_order_service.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
