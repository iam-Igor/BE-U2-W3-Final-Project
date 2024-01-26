package ygorgarofalo.BEU2W3FinalProject.services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ygorgarofalo.BEU2W3FinalProject.entities.Event;
import ygorgarofalo.BEU2W3FinalProject.exceptions.NotFoundException;
import ygorgarofalo.BEU2W3FinalProject.payload.EventPayloadDTO;
import ygorgarofalo.BEU2W3FinalProject.repositories.EventRepo;

import java.io.IOException;

@Service
public class EventService {

    @Autowired
    EventRepo eventRepo;

    @Autowired
    private Cloudinary cloudinary;


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
    public Event findByIdAndUpdate(long id, EventPayloadDTO body) {
        Event found = this.findById(id);
        found.setEventDate(body.eventDate());
        found.setDescription(body.description());
        found.setTitle(body.title());
        found.setLocation(body.location());
        found.setAvailableSeats(body.availableSeats());
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


    public String uploadImage(MultipartFile file, long eventId) throws IOException {

        Event found = this.findById(eventId);

        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");

        found.setImageUrl(url);

        eventRepo.save(found);

        return url;
    }
}
