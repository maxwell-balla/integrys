package com.integrys.backend.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiersPayeur extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String codeTiers;
	private String libelle;
	private String telephone;
	private String logo;
	private String compte;
	private String nif;
	private String nomContact;
	private String prenomContact;
	private String email;
	private String adresse;
	private String civilite;
	@OneToMany(mappedBy = "tiersPayeur")
	private Collection<OptionTiersPayeur> options;
	@OneToMany(mappedBy = "tiersPayeur")
	private Collection<TierStatut> tierStatut;
    @OneToMany(mappedBy = "tiersPayeur")
    private Collection<PEC> prisesEnCharge;
    
	

}
