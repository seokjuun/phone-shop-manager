package com.mycom.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    private String planName;

    @Enumerated(EnumType.STRING)
    private NetworkType networkType;

    @Enumerated(EnumType.STRING)
    private TargetGroup targetGroup;

    private Integer baseFee;

    private Boolean eligibleOptionalContract;

    public enum NetworkType { LTE, G5 }
    public enum TargetGroup { 일반, 청소년, 시니어 }
}