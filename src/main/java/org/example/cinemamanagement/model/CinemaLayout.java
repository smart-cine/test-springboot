package org.example.cinemamanagement.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cinema_layout")
public class CinemaLayout {
    @JsonIgnore
    @ManyToMany(mappedBy = "cinemaLayouts")
    private List<Cinema> cinemas;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cinemaLayout")
    private List<CinemaRoom> cinemaRooms;


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "x_index")
    private Integer xIndex;

    @Column(name = "y_index")
    private Integer yIndex;

    public void addCinema(Cinema cinema) {
        if (this.cinemas == null) {
            this.cinemas = new ArrayList<>();
        }
        this.cinemas.add(cinema);
    }

    public void addCinemaRoom(CinemaRoom cinemaRoom) {
        if (this.cinemaRooms == null) {
            this.cinemaRooms = new ArrayList<>();
        }
        this.cinemaRooms.add(cinemaRoom);
    }
}
