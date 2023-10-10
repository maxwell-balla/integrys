package com.integrys.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GroupeP extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Size(min = 3, max = 255, message = "")
    @Column(nullable = false, unique = true)
    private String nomGroupe;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Patient.class)
    private Collection<Patient> patients;
}