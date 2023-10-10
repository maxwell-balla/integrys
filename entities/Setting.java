package com.integrys.backend.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setting extends BaseEntity{
	private static final long serialVersionUID = -6036351023882160319L;
	@Id
    @GeneratedValue
    private Long id;
	@NotNull
	private String nom;
	private String nom2;
	private String adresse;
	private String boitePostale;
	private String ville;
	private String pats;
	private String telephone;
	private String fax;
	private String rc;
	private String numstat;
	private String commentaire;
	private String banque;
	private String numCompte;
	private String juridique;
	private String capital;
	private String email;
	private String logo1;
	private String logo2;
	private String entete;
}
