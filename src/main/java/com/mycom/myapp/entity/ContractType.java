package com.mycom.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractTypeId;

    @Enumerated(EnumType.STRING)
    private ContractCategory type;

    public enum ContractCategory { 신규, 번호이동, 기기변경 }
}