package ygorgarofalo.BEU2W3FinalProject.payload;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record EventPayloadDTO(

        @NotEmpty(message = "Il campo title non può essere vuoto")
        String title,
        @NotEmpty(message = "Il campo description non può essere vuoto")
        String description,
        @NotEmpty(message = "Il campo eventDate non può essere vuoto")
        LocalDate eventDate,
        @NotEmpty(message = "Il campo location non può essere vuoto")
        String location,
        @NotEmpty(message = "Il campo availableSeats non può essere vuoto")
        int availableSeats

) {
}
