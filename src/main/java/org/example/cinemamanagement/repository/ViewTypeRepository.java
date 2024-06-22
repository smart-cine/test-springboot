package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.ViewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViewTypeRepository extends JpaRepository<ViewType, Long> {
    @Query("select v from ViewType v where v.viewType = ?1")
    Optional<ViewType> findByViewType(String viewType);
}
