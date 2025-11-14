CREATE TABLE `orders` (
      `id` BIGINT NOT NULL COMMENT '주문 고유 ID (PK)',
      `user_id` BIGINT NOT NULL COMMENT '주문한 사용자 ID',
      `total_amount` DECIMAL(19,4) NOT NULL COMMENT '최종 주문 금액',
      `order_status` VARCHAR(30) NOT NULL COMMENT '주문 상태 (CREATED, PROCESSING, COMPLETED 등)',
      `version` INT NOT NULL COMMENT '낙관적 잠금을 위한 버전 번호',
      `created_at` DATETIME(6) NOT NULL COMMENT '주문 생성 시각',
      `updated_at` DATETIME(6) NOT NULL COMMENT '마지막 상태 변경 시각',

      PRIMARY KEY (`id`),
      KEY `idx_user_id` (`user_id`) COMMENT '특정 사용자의 주문 조회 인덱스'
) COMMENT '주문 정보';

CREATE TABLE `order_detail` (
    `id` BIGINT NOT NULL COMMENT '주문 상세 항목 ID (PK)',
    `order_id` BIGINT NOT NULL COMMENT '상위 주문 ID (논리 FK)',
    `product_id` BIGINT NOT NULL COMMENT '상품 ID (Inventory 서비스의 상품 ID)',
    `quantity` INT NOT NULL COMMENT '주문 수량',
    `unit_price` DECIMAL(19,4) NOT NULL COMMENT '주문 당시의 상품 단가',
    `version` INT NOT NULL COMMENT '낙관적 잠금을 위한 버전 번호',
    `created_at` DATETIME(6) NOT NULL COMMENT '생성 시각',

    PRIMARY KEY (`id`),
    KEY `idx_order_id` (`order_id`) COMMENT '특정 주문의 상세 항목 조회 인덱스'
) COMMENT '주문 상품 상세 정보';