package com.thb.mux.stride4health.repositories;


import com.thb.mux.stride4health.entities.Court;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.UUID;

@RepositoryDefinition(domainClass = Court.class, idClass = Long.class)
public interface ICourtRepository extends CrudRepository<Court,Long> {
}
