package com.integrys.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.integrys.backend.entities.Setting;

@RepositoryRestResource
public interface SettingRepository extends JpaRepository<Setting, Long> {

}
