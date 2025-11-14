CREATE TABLE `outbox` (
        `id` BIGINT NOT NULL COMMENT 'Outbox 레코드 고유 ID (PK)',
        `event_id` CHAR(36) NOT NULL COMMENT '메시지 전역 고유 ID (UUID)',
        `aggregate_type` VARCHAR(50) NOT NULL COMMENT '이벤트 발생 도메인 타입 (예: ORDER)',
        `aggregate_id` VARCHAR(255) NOT NULL COMMENT '도메인 객체 ID (Kafka Key로 사용)',
        `event_type` VARCHAR(100) NOT NULL COMMENT '이벤트 유형 (예: ORDER_CREATED)',
        `topic` VARCHAR(100) NOT NULL COMMENT 'Kafka Topic 이름',
        `payload` TEXT NOT NULL COMMENT 'Kafka로 보낼 메시지 JSON 본문',
        `status` VARCHAR(20) NOT NULL COMMENT '처리 상태 (PENDING, PUBLISHED, FAILED)',
        `created_at` DATETIME(6) NOT NULL COMMENT '생성 시각 (트랜잭션 커밋 시점)',
        `processed_at` DATETIME(6) NULL COMMENT 'Kafka 발행 성공 시각',
        `retry_count` INT NOT NULL DEFAULT 0 COMMENT 'Kafka 발행 재시도 횟수',
        `last_attempt_at` DATETIME(6) NULL COMMENT '마지막 재시도 시각',
        `error_message` TEXT NULL COMMENT '최종 실패 시 오류 메시지',

        PRIMARY KEY (`id`),
        UNIQUE KEY `uq_event_id` (`event_id`),
        KEY `idx_outbox_status_attempt` (`status`, `last_attempt_at`) COMMENT '스케줄러 폴링을 위한 인덱스'
) COMMENT '이벤트 발행을 보장하는 Outbox 테이블';