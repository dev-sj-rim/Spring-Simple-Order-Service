package dev.sj.simple_order_service.common.eventconsumerlog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLog, Long> {
}
