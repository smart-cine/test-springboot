package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.CinemaLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CinemaLayoutRepository extends JpaRepository<CinemaLayout, UUID> {
    @Query("select c_l from CinemaLayout c_l where c_l.xIndex = ?1 and c_l.yIndex = ?2")
    Optional<CinemaLayout> findByXIndexAndYIndex(Integer xIndex, Integer yIndex);
}
