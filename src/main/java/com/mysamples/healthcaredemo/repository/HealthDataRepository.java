package com.mysamples.healthcaredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysamples.healthcaredemo.domain.HealthData;

@Repository
public interface HealthDataRepository extends JpaRepository<HealthData, Long> {
}