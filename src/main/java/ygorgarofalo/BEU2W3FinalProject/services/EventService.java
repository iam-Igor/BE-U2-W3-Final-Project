package ygorgarofalo.BEU2W3FinalProject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ygorgarofalo.BEU2W3FinalProject.entities.Event;
import ygorgarofalo.BEU2W3FinalProject.exceptions.NotFoundException;
import ygorgarofalo.BEU2W3FinalProject.repositories.EventRepo;

@Service
public class EventService {

    @Autowired
    EventRepo eventRepo;


    //GET accessibile a tutti
    public Page<Event> getEvents(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return eventRepo.findAll(pageable);
    }


    //GET accessibile a tutti
    public Event findById(long id) {
        return eventRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    //PUT accessibile solo da admin con token admin in header
    public Event findByIdAndUpdate(long id, Event body) {
        Event found = this.findById(id);
        found.setEventDate(body.getEventDate());
        found.setDescription(body.getDescription());
        found.setTitle(body.getTitle());
        found.setLocation(body.getLocation());
        found.setAvailableSeats(body.getAvailableSeats());
        return eventRepo.save(found);

    }


    // DELETE accessibile solo da admin con token admin in header
    public void findByIdAndDelete(long id) {
        Event found = this.findById(id);
        eventRepo.delete(found);
    }


    //GET accessibile a tutti
    public Event findByTitle(String title) {
        return eventRepo.findByTitle(title);
    }


    //GET accessibile a tutti
    public Event findByLocation(String location) {
        return eventRepo.findByLocation(location);
    }

}
