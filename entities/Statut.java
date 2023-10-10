package com.integrys.backend.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statut extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 private Float tauxDefaut;
	 private String tauxFormule;
	 @ManyToOne(fetch = FetchType.EAGER)
	 private Patient patient;
	 @OneToMany( mappedBy = "statut")
	 private Collection<TierStatut> tierStatut;
		
}
