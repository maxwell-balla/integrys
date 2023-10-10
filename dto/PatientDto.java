package com.integrys.backend.dto;

import com.integrys.backend.entities.Patient;
import com.integrys.backend.entities.enums.Sexe;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDto {
	private String adresse;
	private String nationalite = "Camerounaise";
	private String nom;
	private String prenom;
	private String profession;
	private String religion;
	private Sexe sexe = Sexe.FEMININ;
	private String telephone;
	private String dateDeNaissance;
	private String email;
	private String lieuDeNaissance;
	
	public Patient toPatient() {
		Patient patient = new Patient();
		patient.setAdresse(this.adresse); 
		patient.setNationalite(this.nationalite);
		patient.setNom(this.nom);
		patient.setPrenom(this.prenom);
		patient.setProfession(this.profession);
		patient.setReligion(this.religion);
		patient.setSexe(this.sexe.toString());
		patient.setTelelephone(this.telephone);
		patient.setDateDeNaissance(this.dateDeNaissance);
		patient.setLieuDeNaissance(this.lieuDeNaissance);
		patient.setEmail(this.email);
		return patient;

	}
	
}
