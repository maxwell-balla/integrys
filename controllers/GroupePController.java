package com.integrys.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrys.backend.entities.GroupeP;
import com.integrys.backend.services.GroupeServices;

@RestController
@CrossOrigin
public class GroupePController {
	
	private final GroupeServices groupeService;
	private static final  String  NOTFOUND_GROUP= " Il n'existe aucun groupe correspondant a l'id : %d ";
	
	@Autowired
	public GroupePController(GroupeServices groupeService) {
		this.groupeService = groupeService;
	}
	
	@PostMapping("/groupes")
	public GroupeP ajouterUnGroupePatient(@RequestBody GroupeP groupe) {
		return this.groupeService.create(groupe);
	}
	
	@GetMapping("/groupes/{groupeId}")
	public ResponseEntity<?> lireParId(@PathVariable(name="groupeId") Long groupeId) {
		GroupeP groupeP = new GroupeP();
		groupeP = this.groupeService.read(groupeId);
		return  groupeP!= null ? 
				ResponseEntity.ok().body(groupeP) : 
				ResponseEntity.badRequest().body( String.format(NOTFOUND_GROUP, groupeId) );
	}
	
	@PutMapping("/groupes/{groupeId}")
	public ResponseEntity<?> modifier(@PathVariable(name="groupeId") Long groupeId, @RequestBody GroupeP groupe) { 
		GroupeP groupeP = new GroupeP();
		groupeP = this.groupeService.update(groupe);
		return groupeP!= null ? 
				ResponseEntity.ok().body(groupeP) : 
				ResponseEntity.badRequest().body( String.format(NOTFOUND_GROUP, groupeId) );

	}
	
	@DeleteMapping("/groupes/{groupeId}")
	public ResponseEntity<?> supprimer(@PathVariable(name="groupeId") Long groupeId) {
		return this.groupeService.isGroupeIdExist( groupeId ) ?
				ResponseEntity.ok().body( this.groupeService.delete(groupeId) ) :
				ResponseEntity.badRequest().body( String.format(NOTFOUND_GROUP, groupeId) );
	}
}
