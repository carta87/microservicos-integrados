package com.microservice.auth.jpa.repository;

import com.microservice.auth.jpa.entity.RoleEntity;
import com.microservice.auth.jpa.entity.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
