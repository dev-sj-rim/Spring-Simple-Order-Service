package dev.sj.simple_order_service.common.outboxmessagerelay;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "outbox")
@Entity
public class Outbox {

    @Id
    @Comment("Outbox 레코드 고유 ID (PK)")
    private Long id;

    @Column(name = "event_id", nullable = false, unique = true)
    @Comment("메시지 전역 고유 ID (UUID)")
    private String eventId;

    @Column(name = "aggregate_type", nullable = false)
    @Comment("이벤트 발생 도메인 타입 (예: ORDER)")
    private String aggregateType;

    @Column(name = "aggregate_id", nullable = false)
    @Comment("도메인 객체 ID (Kafka Key로 사용)")
    private String aggregateId;

    @Column(name = "event_type", nullable = false)
    @Comment("이벤트 유형 (예: ORDER_CREATED)")
    private String eventType;

    @Column(name = "topic", nullable = false)
    @Comment("Kafka Topic 이름")
    private String topic;

    @Column(name = "payload", nullable = false, columnDefinition = "TEXT")
    @Comment("Kafka로 보낼 메시지 JSON 본문")
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Comment("처리 상태 (PENDING, PUBLISHED, FAILED)")
    private OutboxStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment("생성 시각 (트랜잭션 커밋 시점)")
    private LocalDateTime createdAt;

    @Column(name = "processed_at")
    @Comment("Kafka 발행 성공 시각")
    private LocalDateTime processedAt;

    @Column(name = "retry_count", nullable = false)
    @Builder.Default
    @Comment("Kafka 발행 재시도 횟수")
    private Integer retryCount = 0;

    @Column(name = "last_attempt_at")
    @Comment("마지막 재시도 시각")
    private LocalDateTime lastAttemptAt;

    @Column(name = "error_message", columnDefinition = "TEXT")
    @Comment("최종 실패 시 오류 메시지")
    private String errorMessage;

    public enum OutboxStatus {
        PENDING, PUBLISHED, FAILED
    }
}
