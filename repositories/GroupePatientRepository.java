package com.integrys.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrys.backend.entities.GroupeP;

@Repository
public interface GroupePatientRepository extends JpaRepository<GroupeP, Long> {

}
