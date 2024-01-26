package ygorgarofalo.BEU2W3FinalProject.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Event {


    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String description;

    private LocalDate eventDate;

    private String location;

    private int availableSeats;

    private String imageUrl;

    @OneToMany(mappedBy = "event")
    private List<Reservation> reservationList;


}
