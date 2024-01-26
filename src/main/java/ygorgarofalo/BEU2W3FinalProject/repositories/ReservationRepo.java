package ygorgarofalo.BEU2W3FinalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ygorgarofalo.BEU2W3FinalProject.entities.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {


}
