CREATE TABLE `inventory` (
         `id` BIGINT NOT NULL COMMENT '재고 레코드 고유 ID (PK)',
         `product_id` BIGINT NOT NULL COMMENT '상품 ID (재고 추적 키)',
         `inventory_quantity` INT NOT NULL COMMENT '현재 재고 수량',
         `version` INT NOT NULL COMMENT '낙관적 잠금을 위한 버전 번호',
         `created_at` DATETIME(6) NOT NULL COMMENT '생성 시각',
         `updated_at` DATETIME(6) NOT NULL COMMENT '마지막 업데이트 시각',

         PRIMARY KEY (`id`),
         UNIQUE KEY `uq_product_id` (`product_id`) COMMENT '상품별 재고는 유일'
) COMMENT '재고 마스터 정보';