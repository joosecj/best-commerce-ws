package com.github.bestcommerce.repositories;

import com.github.bestcommerce.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface StoreRepository extends JpaRepository<Store, UUID> {
}
