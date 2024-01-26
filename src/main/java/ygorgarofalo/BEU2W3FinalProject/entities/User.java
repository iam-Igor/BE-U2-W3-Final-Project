package ygorgarofalo.BEU2W3FinalProject.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String username;

    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservationList;
}
