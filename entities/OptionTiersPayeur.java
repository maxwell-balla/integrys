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
public class OptionTiersPayeur extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean desactive;
	private boolean contratObligatoire;
	private float tva;
	@ManyToOne
	private TiersPayeur tiersPayeur;
	@OneToMany(mappedBy = "optionTierPayeur")
	private Collection<LimitePEC> limitesDePEC;
	
}
