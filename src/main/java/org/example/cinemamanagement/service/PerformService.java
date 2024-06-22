package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.PerformDTO;
import org.example.cinemamanagement.payload.request.AddPerformRequest;

import java.util.List;

public interface PerformService {
    List<PerformDTO> getAllPerforms();
    PerformDTO addPerform(AddPerformRequest addPerformRequest);
}
