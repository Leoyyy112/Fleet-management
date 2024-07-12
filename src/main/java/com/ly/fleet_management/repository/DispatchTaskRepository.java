package com.ly.fleet_management.repository;

import com.ly.fleet_management.entity.DispatchTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchTaskRepository extends JpaRepository<DispatchTask, Long> {
}
