package com.mycom.myapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceModelDto {
    private Long modelId;
    private String manufacturerName;
    private String modelName;
    private String color;
    private String capacity;
    private Integer launchPrice;
    private Integer deviceSubsidy;
    private Integer portingSupport;
    private Integer stockQuantity;
}