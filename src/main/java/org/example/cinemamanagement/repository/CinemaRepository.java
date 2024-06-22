package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, UUID> {
    public Optional<Cinema> findById(UUID id);
}
