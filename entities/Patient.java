package com.integrys.backend.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.integrys.backend.sequences.IdPatientGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nip;

    @Column(updatable = false)
    private String codeBarre;

    @Column(updatable = false)
    private String empreinteDigital;


    @Column(nullable = false)
    private String nom;

    @Column
    private String adresse;

    @Column
    private String telelephone;

    @Column
    private String nationalite;

    @Column
    private String sexe;

    @Column
    private String dateDeNaissance;
    
    @Column
    private String lieuDeNaissance;

    @Column
    private String profession;

    @Column
    private String religion;
    
    private String prenom;
    
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "priseCharge_id", referencedColumnName = "id")
    @JsonIgnore
    private ParametrePriseCharge parametrePriseCharge;
    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<GroupeP> groupes;
    
    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    private Collection< ParametreSoin > parametresDeSoins ;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Collection<InfoSupplementaire> infoSupplementaire;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private Collection<Statut> statuts;
    @OneToMany(mappedBy = "patient")
    private Collection<PEC> prisesEnCharge;
    
    
}