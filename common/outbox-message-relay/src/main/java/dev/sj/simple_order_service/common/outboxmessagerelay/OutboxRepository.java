package dev.sj.simple_order_service.common.outboxmessagerelay;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
}
