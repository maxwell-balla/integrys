package com.integrys.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
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
public class PEC implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Patient patient;
	@ManyToOne
	private TiersPayeur tiersPayeur;
	private String contrat;
	private String societe;
	private String ticketModerateurValide;
	@OneToMany(mappedBy = "pec")
	private Collection<PECPersonnel> pecPersonnel;

}
