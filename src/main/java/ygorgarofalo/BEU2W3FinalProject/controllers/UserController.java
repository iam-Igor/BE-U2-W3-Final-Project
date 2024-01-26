package ygorgarofalo.BEU2W3FinalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ygorgarofalo.BEU2W3FinalProject.entities.User;
import ygorgarofalo.BEU2W3FinalProject.exceptions.BadRequestException;
import ygorgarofalo.BEU2W3FinalProject.payload.UserPayloadDTO;
import ygorgarofalo.BEU2W3FinalProject.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('MANAGER')")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String order) {
        return userService.getUsers(page, size, order);
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User user) {
        return user;
    }

    @DeleteMapping("/me")
    public void getMeAnDelete(@AuthenticationPrincipal User currentUser) {
        userService.findByIdAndDelete(currentUser.getId());
    }


    // per modificare il proprio profilo servirà inserire nell'header il token e un body di tipo
    // user payloadDTO

    @PutMapping("/me")
    public User updateUser(@AuthenticationPrincipal User currentUser, @RequestBody UserPayloadDTO updatedUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        } else {
            return userService.findByIdAndUpdate(currentUser.getId(), updatedUser);
        }
    }


    @DeleteMapping("/me")
    public void deleteUser(@AuthenticationPrincipal User currentUser) {
        userService.findByIdAndDelete(currentUser.getId());
    }


    // l'accesso diretto tramite path variable per cercare un utente tramite id sarà
    //disponibile solo ad un MANAGER
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public User findById(@PathVariable long id) {
        return userService.findById(id);
    }


}
