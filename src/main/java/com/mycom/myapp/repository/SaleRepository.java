package com.mycom.myapp.repository;

import com.mycom.myapp.dto.SaleTopEmployeeDto;
import com.mycom.myapp.dto.SalesStatDto;
import com.mycom.myapp.entity.Sale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("""
        select new com.mycom.myapp.dto.SalesStatDto(
            (select count(s) from Sale s where s.employee.employeeId = :employeeId and date(s.saleDate) = current_date),
            (select count(s) from Sale s where s.employee.employeeId = :employeeId and function('month', s.saleDate) = function('month', current_date)),
            (select sum(s.deviceFinalPrice + s.monthlyPlanPayment) from Sale s where s.employee.employeeId = :employeeId and function('month', s.saleDate) = function('month', current_date))
        )
        from Sale s
        where s.employee.employeeId = :employeeId
        group by s.employee.employeeId
        """)
    Optional<SalesStatDto> findSalesStatByEmployeeId(@Param("employeeId") Long employeeId);



    @Query("""
    select new com.mycom.myapp.dto.SalesStatDto(
        (select count(s) from Sale s where date(s.saleDate) = current_date),
        (select count(s) from Sale s where function('month', s.saleDate) = function('month', current_date)),
        (select sum(s.deviceFinalPrice + s.monthlyPlanPayment) from Sale s where function('month', s.saleDate) = function('month', current_date))
    )
    from Sale s
    group by function('month', s.saleDate)
""")
    Optional<SalesStatDto> findSalesStatAll();

    @Query("""
    select new com.mycom.myapp.dto.SaleTopEmployeeDto(
        s.employee.name,
        sum(s.deviceFinalPrice + s.monthlyPlanPayment)
    )
    from Sale s
    where function('month', s.saleDate) = function('month', current_date)
    group by s.employee.name
    order by sum(s.deviceFinalPrice + s.monthlyPlanPayment) desc
""")
    List<SaleTopEmployeeDto> findTop3EmployeesThisMonth(Pageable pageable);

    // function() :  JPA 가 직접 SQL 함수를 쓰게 하는 문법
    // function('month', s.saleDate) = function('month', current_date)
    // MONTH(sale_date) = MONTH(CURRENT_DATE)
    // 	sale_date 컬럼의 달(MONTH 값)이 현재 날짜의 달(MONTH 값)과 같은지를 비교

}