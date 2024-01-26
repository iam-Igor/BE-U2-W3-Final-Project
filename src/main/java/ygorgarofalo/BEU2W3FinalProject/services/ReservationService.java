package ygorgarofalo.BEU2W3FinalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ygorgarofalo.BEU2W3FinalProject.entities.Event;
import ygorgarofalo.BEU2W3FinalProject.entities.Reservation;
import ygorgarofalo.BEU2W3FinalProject.entities.User;
import ygorgarofalo.BEU2W3FinalProject.exceptions.NotFoundException;
import ygorgarofalo.BEU2W3FinalProject.payload.ReservationPayloadDTO;
import ygorgarofalo.BEU2W3FinalProject.repositories.EventRepo;
import ygorgarofalo.BEU2W3FinalProject.repositories.ReservationRepo;

import java.time.LocalDate;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;


    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepo eventRepo;


    // get /reservations accessibile solo agli admin
    public Page<Reservation> getReservations(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return reservationRepo.findAll(pageable);
    }


    //GEt accessibile solo agli admin
    public Reservation findById(long id) {
        return reservationRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    // PUT accessibile solo agli admin
    public Reservation findByIdAndUpdate(long id, ReservationPayloadDTO payload) {
        Reservation resFound = this.findById(id);
        User userFound = userService.findById(payload.user_id());
        Event eventFound = eventService.findById(payload.event_id());

        resFound.setUser(userFound);
        resFound.setEvent(eventFound);

        return reservationRepo.save(resFound);

    }


    //POST accessibile a tutti in quanto nel momento in cui l'utente effettua una prenotazione
    //verrà richiamato questo metodo
    public Reservation saveReservation(ReservationPayloadDTO payload) {
        Reservation reservation = new Reservation();
        User userFound = userService.findById(payload.user_id());
        Event eventFound = eventService.findById(payload.event_id());

        reservation.setUser(userFound);
        reservation.setEvent(eventFound);
        reservation.setReservationDate(LocalDate.now());
        eventFound.setAvailableSeats(eventFound.getAvailableSeats() - 1);
        eventRepo.save(eventFound);
        return reservationRepo.save(reservation);

    }

    // DELETE accessibile solo agli admin

    public void deleteReservation(long id) {
        Reservation resFound = this.findById(id);
        reservationRepo.delete(resFound);
    }

}
