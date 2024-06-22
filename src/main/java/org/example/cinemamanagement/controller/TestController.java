package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaManagerDTO;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.User;
import org.example.cinemamanagement.payload.request.AddAndDeleteManagerRequest;
import org.example.cinemamanagement.repository.CinemaRepository;
import org.example.cinemamanagement.repository.UserRepository;
import org.example.cinemamanagement.service.CinemaManagerService;
import org.example.cinemamanagement.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CinemaRepository cinemaRepository;


    @Autowired
    CinemaManagerService cinemaManagerService;

    @Autowired
    CinemaService cinemaService;

    @GetMapping("/getalluser/{id}")
    public ResponseEntity<?> GetAllUser(@PathVariable UUID id) {
        return null;
    }

    @PostMapping("/addcinema")
    public void addCinema(@RequestBody CinemaDTO cinemaDTO) {
        Cinema cinema = Cinema.builder().
                variant(cinemaDTO.getVariant()).
                name(cinemaDTO.getName()).
                build();
        cinemaRepository.save(cinema);
    }

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

    @DeleteMapping("/delete-cinema/{id}")
    public void deleteCinema(@PathVariable UUID id) {
        cinemaService.deleteCinema(id);
    }

   /* @GetMapping("/get-all-manager/{id}")
    public ResponseEntity<?> getAllManager(@PathVariable UUID id) {
        if(id == null) {
            return ResponseEntity.badRequest().body("Id is null");
        }
        return ResponseEntity.ok(cinemaManagerService.getAllCinemaManager(id));
    }*/

    @GetMapping("/test")
    public User test() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
        );

        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }
}