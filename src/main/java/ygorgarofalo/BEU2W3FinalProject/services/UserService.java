package ygorgarofalo.BEU2W3FinalProject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ygorgarofalo.BEU2W3FinalProject.entities.User;
import ygorgarofalo.BEU2W3FinalProject.exceptions.NotFoundException;
import ygorgarofalo.BEU2W3FinalProject.repositories.UserRepo;

@Service
public class UserService {


    @Autowired
    UserRepo userRepo;


    //GET richiamata da controller user su /users solo con token admin in header
    public Page<User> getUsers(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return userRepo.findAll(pageable);
    }


    public User findById(long id) {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        userRepo.delete(found);
    }


    public User findByEmail(String email) throws NotFoundException {
        return userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }


}
