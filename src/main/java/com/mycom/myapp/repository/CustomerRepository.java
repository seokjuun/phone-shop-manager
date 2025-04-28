package com.mycom.myapp.repository;

import com.mycom.myapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
    select count(c)
    from Customer c
    where function('month', c.registerDate) = function('month', current_date)
""")
    Long countNewCustomersThisMonth();
}