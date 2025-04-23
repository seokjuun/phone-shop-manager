package com.mycom.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    private String modelName;

    private String color;

    private String capacity;

    private Integer launchPrice;

    private Integer deviceSubsidy;

    private Integer portingSupport;

    private Integer stockQuantity;
}