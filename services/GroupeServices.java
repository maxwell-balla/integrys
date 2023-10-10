package com.integrys.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integrys.backend.entities.GroupeP;
import com.integrys.backend.entities.Patient;
import com.integrys.backend.repositories.GroupePatientRepository;
import com.integrys.backend.repositories.PatientRepository;

@Service("GroupeServices")
@Transactional
public class GroupeServices {
		 private final PatientRepository patientRepository;
		 private final GroupePatientRepository groupePatientRepository;

		    @Autowired
		    public GroupeServices(PatientRepository patientRepository,
		    		GroupePatientRepository groupePatientRepository) {
		        this.patientRepository = patientRepository;
		        this.groupePatientRepository = groupePatientRepository;
		    }
		    
		    public GroupeP create(GroupeP groupe) {
		    	return this.groupePatientRepository.save(groupe);
		    }
		    
		    public GroupeP read(Long id) {
		    	return this.groupePatientRepository.existsById(id)?
		    		 this.groupePatientRepository.findById(id).get() : null;
		    }
		    
		    public GroupeP update(GroupeP groupe) {
		    	if ( this.groupePatientRepository.existsById( groupe.getId() ) ) {
		    		GroupeP existingGroupe = this.groupePatientRepository.
		    								 findById( groupe.getId() ).get();
		    		existingGroupe.setNomGroupe(groupe.getNomGroupe());
		    		return this.groupePatientRepository.save(existingGroupe);
		    	}else {
		    		return null;
		    	}
		    }
		    
		    public boolean delete(Long groupeId) {
		    	if ( this.groupePatientRepository.existsById(groupeId) ) {
		    		try {
		    			 GroupeP g = new GroupeP(); g.setId(groupeId);
						this.groupePatientRepository.delete(g);
						return true;
					} catch (Exception e) {
						// TODO: handle exception
						return false;
					}
		    	} else {
		    		return false;
		    	}
		    }
		    
		    public Patient addPatientToGroupe( Long patientId, Long groupeId ) { 
		    	if ( this.groupePatientRepository.existsById(groupeId) && this.patientRepository.existsById(patientId) )
		    	   {
		    		Patient p = new Patient();
		    		p = this.patientRepository.findById(patientId).get();
		    		p.getGroupes()
		    		 .add( this.groupePatientRepository.findById(groupeId).get()  );
		    		return p;
		    	   } else {
		    		return null;
		    	}
		    }
		    
		   public boolean removePatientFormGroupe( Long patientId, Long groupeId ) {
			   if ( this.groupePatientRepository.existsById(groupeId) && this.patientRepository.existsById(patientId) )
	    	   {
	    		Patient p = new Patient();
	    		p = this.patientRepository.findById(patientId).get();
	    		 return p.getGroupes().remove( this.groupePatientRepository.findById(groupeId).get() );
	    		
	    	   } else {
	    		return false;
	    	}
			   
		   }
		   
		   public boolean isGroupeIdExist(Long groupeId) {
			   return this.groupePatientRepository.existsById(groupeId);
		   }
}
