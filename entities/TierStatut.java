package com.integrys.backend.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TierStatut extends BaseEntity {
	@EmbeddedId
	private TierStatutCle id;
	@ManyToOne
	@MapsId("statutId")
	private Statut statut;
	@ManyToOne
	@MapsId("tierPayeurId")
	private TiersPayeur tiersPayeur;
	private float ticketModerateur;
	
	
	

}
