package com.wendellemos.data.repository;

import com.wendellemos.data.entity.CarModelAnalyticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarModelAnalyticsRepository extends JpaRepository<CarModelAnalyticsEntity, Long> {
    Optional<CarModelAnalyticsEntity> findByModel(String model);
}
