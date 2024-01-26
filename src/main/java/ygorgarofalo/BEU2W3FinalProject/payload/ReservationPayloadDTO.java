package ygorgarofalo.BEU2W3FinalProject.payload;

import jakarta.validation.constraints.NotEmpty;

public record ReservationPayloadDTO(


        @NotEmpty(message = "Il campo user_id non può essere vuoto")
        long user_id,

        @NotEmpty(message = "Il campo event_id non può essere vuoto")
        long event_id
) {
}
