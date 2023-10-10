package com.integrys.backend.controllers;


import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.integrys.backend.dto.ParametreDeSoinDto;
import com.integrys.backend.dto.PatientDto;
import com.integrys.backend.entities.AutresParametreSoin;
import com.integrys.backend.entities.InfoSupplementaire;
import com.integrys.backend.entities.Patient;
import com.integrys.backend.services.PatientService;

@RestController
@CrossOrigin
@Transactional
public class PatientController {
	private final PatientService patientService;
	@Autowired
	public PatientController(PatientService patientService ) {
		this.patientService = patientService;
	}
	
	@PostMapping("/patients/")
	public ResponseEntity<Patient> ajouterUnPatien(@RequestBody PatientDto patientDto){
		return ResponseEntity.ok(this.patientService.create(patientDto.toPatient()));
	}
	
	@GetMapping("/patients/{id}/")
	public ResponseEntity<?> getPatientById(
			@PathVariable(name = "id") Long id) {
		if ( id!=null ) {
			Patient patient = this.patientService.readById(id);
			return (patient != null) ?
				 ResponseEntity.ok().body( patient ) :
				 ResponseEntity.badRequest().body( "patient introuvable" );
		} else {
			return ResponseEntity.badRequest().body("Passez un identifiant en parametre");
		}
	}
	
	@PostMapping("/patients/{idPatient}/parametre-de-soins")
	public ResponseEntity<?> EnregistreUnParametreDeSoin(
			@PathVariable(name = "idPatient") Long idPatient, @RequestBody ParametreDeSoinDto parametreDto) {
			return (idPatient != null) ?
				 ResponseEntity.ok().body( this.patientService.createParametreDeSoin(parametreDto.toParametreSoin(), idPatient) ) :
				 ResponseEntity.badRequest().body("Passez l'identifiant du patient en parametre ");
		} 
	

@PostMapping("/patients/{idParametreDeBase}/autres-parametres-de-soins")
public ResponseEntity<?> EnregistreAutresParametreDeSoin(
		@PathVariable(name = "idParametreDeBase") Long idParametreDeBase, @RequestBody AutresParametreSoin parametre) {
		return (idParametreDeBase != null) ?
			 ResponseEntity.ok().body( this.patientService.createAutresParametreDeSoin(parametre, idParametreDeBase) ) :
			 ResponseEntity.badRequest().body("Passez l'identifiant du parametre de soin de base  ");
	} 

@PostMapping("/patients/{idPatient}/infos-supplementaires")
public ResponseEntity<?> AjouterUneInfoSupplementaire(
		@PathVariable(name = "idPatient") Long idPatient, @RequestBody InfoSupplementaire info) {
		return (idPatient != null ) ?
			 ResponseEntity.ok().body( this.patientService.addField(info, idPatient) ) :
			 ResponseEntity.badRequest().body("le patient est introuvable, rassurez-vous de passer un identifiant correct");
	} 


@PutMapping("/patients/{idInfoSupp}/infos-supplementaires")
public ResponseEntity<?> ModifierUneInfoSupplementaire(
		@PathVariable(name = "idInfoSupp") Long idInfoSupp, @RequestBody InfoSupplementaire info) {
		return (idInfoSupp != null ) ?
			 ResponseEntity.ok().body( this.patientService.updateInfoSupplementaire(info, idInfoSupp) ) :
			 ResponseEntity.badRequest().body("l'information supplementaire est introuvable, rassurez-vous de passer un identifiant correct");
	} 


@DeleteMapping("/patients/{idInfoSuppDeBase}/infos-supplementaires")
public ResponseEntity<?> SupprimerUneInfoSupplementaire(
		@PathVariable(name = "idInfoSuppDeBase") Long idInfoSuppDeBase) {
		return (idInfoSuppDeBase != null ) ? ( this.patientService.removeField( idInfoSuppDeBase ) ? ResponseEntity.ok().body("Supprime avec success" ): ResponseEntity.badRequest(). body( " impossible de supprimer " ) ) :
			 ResponseEntity.badRequest().body("l'information supplementaire est introuvable, rassurez-vous de passer un identifiant correct");
	} 
	
	
	
	 @GetMapping(value = "/barbecue/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
	    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode)
	    throws Exception {
	        return null;
	        		//ResponseEntity.ok().body(PatientService.generateEAN13BarcodeImage(barcode));
	    }

}