package ygorgarofalo.BEU2W3FinalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ygorgarofalo.BEU2W3FinalProject.entities.Event;
import ygorgarofalo.BEU2W3FinalProject.payload.EventPayloadDTO;
import ygorgarofalo.BEU2W3FinalProject.services.EventService;

import java.io.IOException;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;


    // accessibile a tutti
    @GetMapping("/public")
    public Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String order) {
        return eventService.getEvents(page, size, order);
    }


    // accessibile a tutti
    @GetMapping("/public/{event_id}")
    public Event getEveny(@PathVariable long event_id) {
        return eventService.findById(event_id);
    }


    // accessibile solo a manager
    @PutMapping("/{event_id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Event updateEvent(@PathVariable long event_id, EventPayloadDTO payload) {
        return eventService.findByIdAndUpdate(event_id, payload);
    }


    // accessibile solo a manager
    @DeleteMapping("/{event_id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public void deleteEvent(@PathVariable long event_id) {
        eventService.findByIdAndDelete(event_id);
    }


    // accessibile a tutti
    @GetMapping("public/{title}")
    public Event getEventByTitle(@PathVariable String title) {
        return eventService.findByTitle(title);
    }


    // accessibile a tutti
    @GetMapping("public/{location}")
    public Event getEventByLocation(@PathVariable String location) {
        return eventService.findByLocation(location);
    }


    // accessibile solo a manager
    @PatchMapping("/{event_id}/upload")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String uploadEventImg(@RequestParam("image") MultipartFile imageFile, @PathVariable long event_id) throws IOException {
        return eventService.uploadImage(imageFile, event_id);
    }


}
