package com.mycom.myapp.repository;

import com.mycom.myapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e JOIN FETCH e.roles")
    List<Employee> findAllWithRoles();

    @Query("SELECT e FROM Employee e JOIN FETCH e.roles WHERE e.employeeId = :id")
    Optional<Employee> findByIdWithRoles(@Param("id") Long id);

    @Query("SELECT e FROM Employee e JOIN FETCH e.roles WHERE e.email = :email")
    Optional<Employee> findByEmail(@Param("email") String email);

}
