package ygorgarofalo.BEU2W3FinalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ygorgarofalo.BEU2W3FinalProject.entities.User;
import ygorgarofalo.BEU2W3FinalProject.exceptions.BadRequestException;
import ygorgarofalo.BEU2W3FinalProject.payload.UserLoginDTO;
import ygorgarofalo.BEU2W3FinalProject.payload.UserPayloadDTO;
import ygorgarofalo.BEU2W3FinalProject.responses.GlobalResponse;
import ygorgarofalo.BEU2W3FinalProject.responses.TokenResponse;
import ygorgarofalo.BEU2W3FinalProject.services.AuthService;
import ygorgarofalo.BEU2W3FinalProject.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public TokenResponse login(@RequestBody UserLoginDTO payload) {
        String accessToken = authService.authenticateUser(payload);
        return new TokenResponse(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public GlobalResponse saveUser(@RequestBody @Validated UserPayloadDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Errore nel body della richiesta");
        } else {
            User newuser = authService.saveUser(body);
            return new GlobalResponse(newuser.getId());
        }
    }


}

