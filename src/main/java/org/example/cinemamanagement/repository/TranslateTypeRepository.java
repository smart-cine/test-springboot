package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.TranslateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TranslateTypeRepository extends JpaRepository<TranslateType, UUID> {
    Optional<TranslateType> findByTranslateType(String translateType);
}
