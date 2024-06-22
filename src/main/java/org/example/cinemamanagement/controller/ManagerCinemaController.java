package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaManagerDTO;
import org.example.cinemamanagement.payload.request.AddAndDeleteManagerRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.CinemaManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerCinemaController {
    @Autowired
    private CinemaManagerService cinemaManagerService;

    @PostMapping("/add-manager")
    public ResponseEntity<?> addManager(@RequestBody AddAndDeleteManagerRequest addAndDeleteManagerRequest) {
        if (addAndDeleteManagerRequest.getEmailUser() == null || addAndDeleteManagerRequest.getIdCinema() == null) {
            return ResponseEntity.badRequest().body("Email or id cinema is null");
        }

        CinemaManagerDTO cinemaManagerDTO = cinemaManagerService.
                addCinemaManager(addAndDeleteManagerRequest.getEmailUser(),
                        addAndDeleteManagerRequest.getIdCinema()
                );

        if (cinemaManagerDTO == null) {
            return ResponseEntity.badRequest().body("User is already a manager of this cinema");
        }
        return ResponseEntity.ok(cinemaManagerDTO);
    }

    /*@DeleteMapping("/delete-manager")
    public ResponseEntity<?> deleteManagerOutOfCinema(@RequestBody AddAndDeleteManagerRequest addAndDeleteManagerRequest) {
        if (addAndDeleteManagerRequest.getEmailUser() == null || addAndDeleteManagerRequest.getIdCinema() == null) {
            return ResponseEntity.badRequest().body("Email or id cinema is null");
        }

        CinemaManagerDTO cinemaManagerDTO = cinemaManagerService.
                deleteCinemaManagerOutOfCinema(addAndDeleteManagerRequest.getEmailUser(),
                        addAndDeleteManagerRequest.getIdCinema()
                );

        if (cinemaManagerDTO == null) {
            return ResponseEntity.badRequest().body("User is not a manager of this cinema");
        }
        return ResponseEntity.ok(cinemaManagerDTO);
    }*/

    @GetMapping("/all")
    public ResponseEntity<?> getAllManager() {
        System.out.println("Hello world");
        return ResponseEntity.ok("All manager");
    }

    @GetMapping("/get-amount-cinema")
    public ResponseEntity<?> getTotalAmountOfCinemaInMonth() {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get total amount successfully");
        dataResponse.setData(cinemaManagerService.getTotalAmountOfCinemaInMonth());

        return ResponseEntity.ok(dataResponse);
    }

}
