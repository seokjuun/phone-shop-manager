package com.mycom.myapp.repository;

import com.mycom.myapp.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {}