package dev.sj.simple_order_service.order.repository;

import dev.sj.simple_order_service.order.domain.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
