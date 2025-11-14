package dev.sj.simple_order_service.common.eventconsumerlog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "event_log")
@Entity
public class EventLog {

    @Id
    @Column(name = "id", nullable = false)
    @Comment("로그 레코드 고유 ID (PK)")
    private Long id;

    @Column(name = "event_id", nullable = false)
    @Comment("수신한 메시지의 전역 고유 ID (발행자의 event_id)")
    private String eventId;

    @Column(name = "consumer_name", nullable = false)
    @Comment("이 이벤트를 처리한 Consumer Group 이름")
    private String consumerName;

    @Column(name = "aggregate_type", nullable = false)
    @Comment("처리된 이벤트의 출처 도메인 (예: INVENTORY)")
    private String aggregateType;

    @Column(name = "aggregate_id", nullable = false)
    @Comment("이벤트가 영향을 준 로컬 도메인 ID (예: Order ID)")
    private String aggregateId;

    @Column(name = "event_type", nullable = false)
    @Comment("수신한 이벤트 유형")
    private String eventType;

    @Column(name = "topic", nullable = false)
    @Comment("수신된 Kafka Topic")
    private String topic;

    @Column(name = "consumed_partition", nullable = false)
    @Comment("Kafka 파티션 번호")
    private Integer partition;

    @Column(name = "consumed_offset", nullable = false)
    @Comment("Kafka 오프셋 값")
    private Long offset;

    @Column(name = "consumed_at", nullable = false)
    @Comment("메시지 처리 완료 시각")
    private LocalDateTime consumedAt;


}
