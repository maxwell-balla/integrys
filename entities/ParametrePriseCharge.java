package com.integrys.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Data
@Table
public class ParametrePriseCharge extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String tiersPayant;

    @Column(nullable = false)
    private String matriculePatient;

    @Column(nullable = false)
    private String contratPatient;

    @Column
    private String PriseEnChargePersoo;

    @Column
    private String Societe;



}