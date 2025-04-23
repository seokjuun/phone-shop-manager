package com.mycom.myapp.dto;

import lombok.*;
import com.mycom.myapp.entity.Plan.NetworkType;
import com.mycom.myapp.entity.Plan.TargetGroup;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDto {
    private Long planId;
    private String planName;
    private NetworkType networkType;
    private TargetGroup targetGroup;
    private Integer baseFee;
    private Boolean eligibleOptionalContract;
}