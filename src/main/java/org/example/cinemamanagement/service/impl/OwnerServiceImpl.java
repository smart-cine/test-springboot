package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.repository.BusinessRepository;
import org.example.cinemamanagement.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public List<Object[]> getAmountOfFilm() {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysAgo = today.minus(30, ChronoUnit.DAYS);

        return businessRepository.getAmountOfFilm(thirtyDaysAgo, today);
    }
}
