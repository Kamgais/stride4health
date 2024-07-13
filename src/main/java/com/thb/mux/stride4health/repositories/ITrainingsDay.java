package com.thb.mux.stride4health.repositories;


import com.thb.mux.stride4health.entities.TrainingsDay;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RepositoryDefinition(domainClass = TrainingsDay.class, idClass = Long.class)
public interface ITrainingsDay extends CrudRepository<TrainingsDay,Long> {
    @Query("SELECT td FROM TrainingsDay td WHERE td.user.id = :userId AND SUBSTRING(CAST(td.day AS string), 1, 10) = SUBSTRING(CAST(:day AS string), 1, 10)")
    Optional<TrainingsDay> findByUserIdAndDay(@Param("userId") Long userId, @Param("day") Date day);
    List<TrainingsDay> findByUserId(Long userId);

    @Query("SELECT td FROM TrainingsDay td WHERE td.user.id = :userId AND td.day >= :startOfWeek AND td.day < :endOfWeek")
    List<TrainingsDay> findByUserIdAndWeek(@Param("userId") Long userId, @Param("startOfWeek") Date startOfWeek, @Param("endOfWeek") Date endOfWeek);
}
