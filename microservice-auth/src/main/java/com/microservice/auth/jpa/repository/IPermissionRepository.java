package com.microservice.auth.jpa.repository;

import com.microservice.auth.jpa.entity.PermissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IPermissionRepository extends CrudRepository<PermissionEntity, Long> {
    Optional<PermissionEntity> findByName(String name);
}
