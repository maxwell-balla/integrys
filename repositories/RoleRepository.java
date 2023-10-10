package com.integrys.backend.repositories;


import com.integrys.backend.entities.Role;
import com.integrys.backend.entities.enums.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(Authority authority);
}
