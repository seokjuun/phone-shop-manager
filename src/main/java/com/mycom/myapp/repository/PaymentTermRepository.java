package com.mycom.myapp.repository;

import com.mycom.myapp.entity.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Long> {}