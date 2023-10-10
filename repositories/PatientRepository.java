package com.integrys.backend.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.integrys.backend.entities.Patient;

import java.util.List;

@RepositoryRestResource
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(value = "SELECT p FROM Patient as p WHERE ( p.nip like %:inputString% or " +
            "( p.nom like %:inputString%) or ( p.prenom like %:inputString%) or " +
            " ( p.adresse like %:inputString%) or  ( p.telelephone like %:inputString%) or "+
            " ( p.religion like %:inputString%) )"
    )
    Page<Patient> findAllByInputString(String inputString, Pageable pageable);
}
