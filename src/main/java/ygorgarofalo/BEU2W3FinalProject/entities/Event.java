package ygorgarofalo.BEU2W3FinalProject.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", eventDate=" + eventDate +
                ", location='" + location + '\'' +
                ", availableSeats=" + availableSeats +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
