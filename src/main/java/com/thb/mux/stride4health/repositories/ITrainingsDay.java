package com.thb.mux.stride4health.repositories;


import com.thb.mux.stride4health.entities.TrainingsDay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass = TrainingsDay.class, idClass = Long.class)
public interface ITrainingsDay extends CrudRepository<TrainingsDay,Long> {
}
