package ygorgarofalo.BEU2W3FinalProject.payload;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO(

        @NotEmpty
        String email,

        @NotEmpty
        String password) {
}
