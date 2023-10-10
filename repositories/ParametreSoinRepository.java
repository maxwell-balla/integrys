package com.integrys.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.integrys.backend.entities.ParametreSoin;

@Repository
public interface ParametreSoinRepository extends JpaRepository<ParametreSoin, Long> {

}
