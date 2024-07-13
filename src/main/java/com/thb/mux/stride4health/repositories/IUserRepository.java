package com.thb.mux.stride4health.repositories;


import com.thb.mux.stride4health.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;


@RepositoryDefinition(domainClass = Profile.class, idClass = Long.class)
public interface IUserRepository extends CrudRepository<Profile, Long> {
    Optional<Profile> findByEmail(String email);
    List<Profile> findByCourtId(Long id);
}
