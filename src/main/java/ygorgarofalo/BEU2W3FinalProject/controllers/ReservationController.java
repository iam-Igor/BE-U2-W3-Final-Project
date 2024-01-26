package ygorgarofalo.BEU2W3FinalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ygorgarofalo.BEU2W3FinalProject.entities.Reservation;
import ygorgarofalo.BEU2W3FinalProject.payload.ReservationPayloadDTO;
import ygorgarofalo.BEU2W3FinalProject.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {


    @Autowired
    ReservationService reservationService;


    // accessibile solo a manager
    @GetMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public Page<Reservation> getAllReservations(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String order) {
        return reservationService.getReservations(page, size, order);
    }

    // accessibile solo a manager
    @GetMapping("/{reserv_id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Reservation getReservation(@PathVariable long reserv_id) {
        return reservationService.findById(reserv_id);
    }


    // accessibile solo a manager
    @PutMapping("/{reserv_id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('MANAGER')")
    public Reservation modifyReservation(@PathVariable long reserv_id, @RequestBody ReservationPayloadDTO payload) {
        return reservationService.findByIdAndUpdate(reserv_id, payload);
    }


    // accessibile a tutti
    @PostMapping("/public")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation saveReservation(@RequestBody ReservationPayloadDTO payload) {
        return reservationService.saveReservation(payload);
    }


    @DeleteMapping("/{reserv_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('MANAGER')")
    public void deletereservation(@PathVariable long reserv_id) {
        reservationService.deleteReservation(reserv_id);
    }

}
