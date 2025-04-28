package com.mycom.myapp.repository;

import com.mycom.myapp.entity.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeviceModelRepository extends JpaRepository<DeviceModel, Long> {

    @Query("""
    select count(d)
    from DeviceModel d
    where d.stockQuantity <= 5
""")
    Long countLowStockDevices();
}