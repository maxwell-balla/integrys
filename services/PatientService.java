package com.integrys.backend.services;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrys.backend.entities.AutresParametreSoin;
import com.integrys.backend.entities.InfoSupplementaire;
import com.integrys.backend.entities.ParametreSoin;
import com.integrys.backend.entities.Patient;
import com.integrys.backend.repositories.AutresParametreSoinRepository;
import com.integrys.backend.repositories.InfoSupplementaireRepository;
import com.integrys.backend.repositories.ParametreSoinRepository;
import com.integrys.backend.repositories.PatientRepository;



@Service("patientService")
@Transactional
public class PatientService {
	 private final PatientRepository patientRepository;
	 private final ParametreSoinRepository parametreSoinRepository;
	 private final AutresParametreSoinRepository autresparametreSoinRepository;
	 private final InfoSupplementaireRepository infoSupplementaireRepository;

	    @Autowired
	    public PatientService(PatientRepository patientRepository,
	    		ParametreSoinRepository parametreSoinRepository,
	    		AutresParametreSoinRepository autresparametreSoinRepository,
	    		InfoSupplementaireRepository infoSupplementaireRepository) {
	        this.patientRepository = patientRepository;
	        this.parametreSoinRepository = parametreSoinRepository;
	        this.autresparametreSoinRepository = autresparametreSoinRepository;
	        this.infoSupplementaireRepository = infoSupplementaireRepository;
	    }
	    
	    
	    public Patient create(Patient patient) {
	    	// enregistrons et obtenons l'id du patient pour ensuite ecoder et obtenir le nip
	    	patient = this.patientRepository.save(patient);
	    	patient.setNip(encodeNIP(patient));
	    	patient.setCodeBarre(encodeNIP(patient));
	    	return this.patientRepository.save(patient);
	    }
	    
	    
	    public ParametreSoin createParametreDeSoin(ParametreSoin parametre, Long patientId) {
	    	if (this.patientRepository.existsById(patientId)) {
	    		Patient p = new Patient(); p.setId(patientId);
	    		parametre.setPatient(p);
	    		return this.parametreSoinRepository.save(parametre);
	    	}
	    	return null;
	    }
	    
	    public AutresParametreSoin createAutresParametreDeSoin(AutresParametreSoin parametre, Long parametreDeBaseID) {
	    	if (this.parametreSoinRepository.existsById(parametreDeBaseID)) {
	    		ParametreSoin p = new ParametreSoin(); p.setId(parametreDeBaseID);
	    		parametre.setPamaretreDeSoin(p);
	    		return this.autresparametreSoinRepository.save(parametre);
	    	}
	    	return null;
	    }
	    
	    public Patient readById(Long id) {
	    	if (this.patientRepository.existsById(id)) {
		    	return this.patientRepository.findById(id).get();	
	    	} else {
	    		return null;
	    	}
	    }
	    
	    public InfoSupplementaire addField( InfoSupplementaire field, Long patientId) {
	    	if ( this.patientRepository.existsById(patientId) ) {
	    		Patient p = new Patient(); p.setId(patientId);
	    		field.setPatient( p );
		    	return this.infoSupplementaireRepository.save(field);
	    	} else {
	    		return null;
	    	}
	    
	    }
	    
	    public InfoSupplementaire updateInfoSupplementaire( InfoSupplementaire infoSupplementaire, Long infoSupplementaireId  ) {
	    	 if ( this.infoSupplementaireRepository.existsById(infoSupplementaireId) ) {
	    		 InfoSupplementaire existingInfoSupp = this.infoSupplementaireRepository.findById(infoSupplementaireId).get();
	    		 existingInfoSupp.setCle(infoSupplementaire.getCle());
	    		 existingInfoSupp.setValeur(infoSupplementaire.getValeur());
	    		 return this.infoSupplementaireRepository.save(existingInfoSupp);
	    	 } else {
	    		 return null;
	    	 }
	    }
	    
	    public boolean removeField(Long infoSupplementaireId) {
	    	if ( this.infoSupplementaireRepository.existsById(infoSupplementaireId) ) {
	    		try {
					this.infoSupplementaireRepository.delete( this.infoSupplementaireRepository.findById(infoSupplementaireId).get() );
				return true;
	    		} catch (Exception e) {
					// TODO: handle exception
	    			return false;
				}
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public String encodeNIP(Patient patient) {
	    	return "PA" +
	    			Integer.toString( Calendar.getInstance(). get(Calendar. YEAR)).substring(2)
	    			+
	    			String.format("%05d", patient.getId());
	    }

}
