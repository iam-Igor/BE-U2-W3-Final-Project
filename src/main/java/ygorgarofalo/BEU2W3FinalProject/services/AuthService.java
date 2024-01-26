package ygorgarofalo.BEU2W3FinalProject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ygorgarofalo.BEU2W3FinalProject.entities.Role;
import ygorgarofalo.BEU2W3FinalProject.entities.User;
import ygorgarofalo.BEU2W3FinalProject.exceptions.BadRequestException;
import ygorgarofalo.BEU2W3FinalProject.exceptions.UnauthorizedException;
import ygorgarofalo.BEU2W3FinalProject.payload.UserLoginDTO;
import ygorgarofalo.BEU2W3FinalProject.payload.UserPayloadDTO;
import ygorgarofalo.BEU2W3FinalProject.repositories.UserRepo;
import ygorgarofalo.BEU2W3FinalProject.security.JWTTools;

@Service
public class AuthService {


    @Autowired
    private UserService userService;


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;


    public String authenticateUser(UserLoginDTO body) {
        User user = userService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }


    //POST /users + payload

    public User saveUser(UserPayloadDTO payload) {
        User newUser = new User();
        newUser.setRole(Role.NORMAL_USER);
        newUser.setSurname(payload.surname());
        newUser.setName(payload.name());
        newUser.setEmail(payload.email());
        newUser.setUsername(payload.username());
        newUser.setPassword(bcrypt.encode(payload.password()));
        if (userRepo.existsByEmail(payload.email())) {
            throw new BadRequestException("L'email " + payload.email() + " è gia presente nel sistema.");
        } else if (userRepo.existsByUsername(payload.username())) {
            throw new BadRequestException("Lo username " + payload.username() + " è gia presente nel sistema.");
        } else {
            return userRepo.save(newUser);
        }

    }


}
