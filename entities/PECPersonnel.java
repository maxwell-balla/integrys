package com.integrys.backend.entities;

import java.util.Collection;

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
public class PECPersonnel extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String plafond;
	private String taux;
	private String tauxPatient;
	private boolean modifiablePatient;
	@ManyToOne
	private PEC pec;
	@OneToMany( mappedBy = "pecPersonnel")
	private Collection<OptionValidite> optionsValidite;

}
