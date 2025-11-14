package dev.sj.simple_order_service.inventory.repository;

import dev.sj.simple_order_service.inventory.domain.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
