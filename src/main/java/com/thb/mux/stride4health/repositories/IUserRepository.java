package com.thb.mux.stride4health.repositories;


import com.thb.mux.stride4health.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Optional;


@RepositoryDefinition(domainClass = UserEntity.class, idClass = Long.class)
public interface IUserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
