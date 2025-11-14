CREATE TABLE `event_log` (
     `id` BIGINT NOT NULL COMMENT '로그 레코드 고유 ID (PK)',
     `event_id` CHAR(36) NOT NULL COMMENT '수신한 메시지의 전역 고유 ID (발행자의 event_id)',
     `consumer_name` VARCHAR(100) NOT NULL COMMENT '이 이벤트를 처리한 Consumer Group 이름',
     `aggregate_type` VARCHAR(50) NOT NULL COMMENT '처리된 이벤트의 출처 도메인 (예: INVENTORY)',
     `aggregate_id` VARCHAR(255) NOT NULL COMMENT '이벤트가 영향을 준 로컬 도메인 ID (예: Order ID)',
     `event_type` VARCHAR(100) NOT NULL COMMENT '수신한 이벤트 유형',
     `topic` VARCHAR(100) NOT NULL COMMENT '수신된 Kafka Topic',
     `consumed_partition` INT NOT NULL COMMENT 'Kafka 파티션 번호',
     `consumed_offset` BIGINT NOT NULL COMMENT 'Kafka 오프셋 값',
     `consumed_at` DATETIME(6) NOT NULL COMMENT '메시지 처리 완료 시각',

     PRIMARY KEY (`id`),
     UNIQUE KEY `uq_event_consumer` (`event_id`, `consumer_name`),
     KEY `idx_eventlog_consumer_time` (`consumer_name`, `consumed_at` DESC) COMMENT 'consumer 별 로그 추적 인덱스'
) COMMENT '외부 이벤트 수신 및 멱등성 처리 로그';