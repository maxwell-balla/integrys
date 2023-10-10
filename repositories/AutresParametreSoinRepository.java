package com.integrys.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrys.backend.entities.AutresParametreSoin;

@Repository
public interface AutresParametreSoinRepository extends JpaRepository<AutresParametreSoin, Long>{

}
