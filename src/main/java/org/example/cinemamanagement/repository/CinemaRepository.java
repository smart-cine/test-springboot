package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.Cinema;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, UUID>, JpaSpecificationExecutor<Cinema> {
    public Optional<Cinema> findById(UUID id);
}
