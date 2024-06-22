package org.example.cinemamanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinema")
public class Cinema {

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "cinema_layout_and_cinema_relationship",
            joinColumns = @JoinColumn(name = "cinema_id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_layout_id"))
    List<CinemaLayout> cinemaLayouts;

    @JsonIgnore
    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<CinemaRoom> cinemaRooms;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cinema_manager",
            joinColumns = @JoinColumn(name = "cinema_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> cinemaManagers;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "variant")
    private String variant;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private List<Payment> payments;

    public void addUser(User user) {
        if (this.cinemaManagers == null) {
            this.cinemaManagers = new ArrayList<>();
        }
        this.cinemaManagers.add(user);
    }

    public void addCinemaLayout(CinemaLayout cinemaLayout) {
        if (this.cinemaLayouts == null) {
            this.cinemaLayouts = new ArrayList<>();
        }
        this.cinemaLayouts.add(cinemaLayout);
    }
}
