package com.integrys.backend.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametreSoin extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String poids;
	private String temperature;
	private String imc;
	private String taille;
	private Date dateDePriseDeParametre;
	@ManyToOne
	private Patient patient;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pamaretreDeSoin")
    private Collection<AutresParametreSoin> parametresoins;
	
}
