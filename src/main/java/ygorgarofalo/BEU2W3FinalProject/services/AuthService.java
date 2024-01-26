package ygorgarofalo.BEU2W3FinalProject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ygorgarofalo.BEU2W3FinalProject.repositories.UserRepo;

@Service
public class AuthService {


    @Autowired
    private UserService userService;


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder bcrypt;


}
