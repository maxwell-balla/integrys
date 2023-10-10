package com.integrys.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrys.backend.entities.InfoSupplementaire;

@Repository
public interface InfoSupplementaireRepository extends JpaRepository<InfoSupplementaire, Long> {

}
