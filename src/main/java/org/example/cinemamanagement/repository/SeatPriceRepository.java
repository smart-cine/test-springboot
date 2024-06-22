package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.SeatPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeatPriceRepository extends JpaRepository<SeatPrice, UUID> {
    public SeatPrice findByPerform_IdAndXAndY(UUID performId, int x, int y);
}
