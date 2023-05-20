package com.Ketaba.ketaba.controllers;

import com.Ketaba.ketaba.models.Event;
import com.Ketaba.ketaba.models.Hotel;
import com.Ketaba.ketaba.models.Sight;
import com.Ketaba.ketaba.repo.EventRepository;
import com.Ketaba.ketaba.repo.HotelRepository;
import com.Ketaba.ketaba.repo.SightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
@PreAuthorize("hasAuthority('ADMIN')")

@Controller
public class AdminController {

    //    Hotel Rooms
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/admin/hotels")
        public String hotels(Model model, Authentication authentication) {
        Iterable<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);
        return "Hotels";

    }
    @PostMapping("admin/filter")public String filter1(@RequestParam String filter, Map<String, Object> model, Authentication authentication){
        Iterable <Hotel> hotels;    if (filter !=null && !filter.isEmpty()){
            hotels= hotelRepository.findByTitleContaining(filter);    }
        else {        hotels = hotelRepository.findAll();
        }    model.put("hotels", hotels);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.put("admin", isAdmin);

        return "Hotels";}

    @PostMapping("admin/filter2")public String filter2(@RequestParam String filter, Map<String, Object> model, Authentication authentication){
        Iterable <Hotel> hotels;    if (filter !=null && !filter.isEmpty()){
            hotels= hotelRepository.findByRegionContaining(filter);    }
        else {        hotels = hotelRepository.findAll();
        }    model.put("hotels", hotels);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.put("admin", isAdmin);
        return "Hotels";}

    @PostMapping("admin/filter3")public String filter3(@RequestParam String filter, Map<String, Object> model, Authentication authentication){
        Iterable <Hotel> hotels;    if (filter !=null && !filter.isEmpty()){
            hotels= hotelRepository.findByBedContaining(filter);    }
        else {        hotels = hotelRepository.findAll();
        }    model.put("hotels", hotels);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.put("admin", isAdmin);
        return "Hotels";}


    @GetMapping("/admin/hotels/add")
    public String hotelsAdd(Model model,Authentication authentication)
    {        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);
        return "Hotels-add";
    }
    @PostMapping("/admin/hotels/add")
    public String hotelsAdd2(@RequestParam String title,@RequestParam String photo1,@RequestParam String type,@RequestParam String region,@RequestParam String bed,@RequestParam String services,@RequestParam String price,@RequestParam int capacity,@RequestParam int size,@RequestParam String email,@RequestParam String number,@RequestParam String address,@RequestParam String map,@RequestParam String photo2,@RequestParam String photo3,@RequestParam String photo4,@RequestParam String photo5,@RequestParam String photo6, @RequestParam String description, Model model){
        Hotel hotel=new Hotel(title,photo1,type,region,bed, services,price,capacity,size,email,number,address,map,photo2,photo3,photo4,photo5,photo6, description);
        hotelRepository.save(hotel);
        return  "redirect:/admin/hotels";
    }

    @GetMapping("/admin/hotels/{id}")
    public String HotelDetails(@PathVariable(value = "id") long id, Model model,Authentication authentication) {
        if(!hotelRepository.existsById(id)){
            return "redirect:/admin/hotels";
        }
        Optional<Hotel> hotel= hotelRepository.findById(id);
        ArrayList<Hotel> res = new ArrayList<>();
        hotel.ifPresent(res::add);
        model.addAttribute("hotel",res);
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(role -> role.getAuthority().equals("ADMIN"));
            model.addAttribute("admin", isAdmin);

        return "Hotel-Details";
    }












    @GetMapping("/admin/hotels/{id}/edit")
    public String HotelEdit(@PathVariable(value = "id") long id, Model model,Authentication authentication) {
        if(!hotelRepository.existsById(id)){
            return "redirect:/admin/hotels";
        }
        Optional<Hotel> post= hotelRepository.findById(id);
        ArrayList<Hotel> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("hotel",res);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);

        return "Hotel-Edit";
    }

    @PostMapping("/admin/hotels/{id}/edit")
    public String HotelRUpdate(@PathVariable(value="id") long id,@RequestParam String title,@RequestParam String photo1,@RequestParam String type,@RequestParam String region,@RequestParam String bed,@RequestParam String services,@RequestParam String price,@RequestParam int capacity,@RequestParam int size,@RequestParam String email,@RequestParam String number,@RequestParam String address,@RequestParam String map,@RequestParam String photo2,@RequestParam String photo3,@RequestParam String photo4,@RequestParam String photo5,@RequestParam String photo6,@RequestParam String description, Model model){
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        hotel.setTitle(title);
        hotel.setPhoto1(photo1);
        hotel.setPhoto2(photo2);
        hotel.setPhoto3(photo3);
        hotel.setPhoto4(photo4);
        hotel.setPhoto5(photo5);
        hotel.setPhoto6(photo6);
        hotel.setType(type);
        hotel.setRegion(region);
        hotel.setCapacity(capacity);
        hotel.setAddress(address);
        hotel.setBed(bed);
        hotel.setEmail(email);
        hotel.setNumber(number);
        hotel.setServices(services);
        hotel.setSize(size);
        hotel.setPrice(price);
        hotel.setMap(map);
        hotel.setDescription(description);
        hotelRepository.save(hotel);
        return "redirect:/admin/hotels";
    }
    @PostMapping("/admin/hotels/{id}/remove")
    public String HotelRDelete(@PathVariable(value="id") long id, Model model){
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        hotelRepository.delete(hotel);
        return "redirect:/admin/hotels";
    }



    //    Events
    @Autowired
    private EventRepository eventRepository;
    @GetMapping("/admin/events")
    public String events(Model model,Authentication authentication) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);

        return "Events";
    }

    @PostMapping("admin/filterEv")public String filterEv(@RequestParam String filter, Map<String, Object> model, Authentication authentication){
        Iterable <Event> events;    if (filter !=null && !filter.isEmpty()){
            events= eventRepository.findByPlaceContaining(filter);    }
        else {        events = eventRepository.findAll();
        }    model.put("events", events);

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.put("admin", isAdmin);

        return "Events";}


    @GetMapping("/admin/events/add")
    public String eventsAdd(Model model,Authentication authentication){
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);

        return "Events-add";
    }
    @PostMapping("/admin/events/add")
    public String eventsAdd2(@RequestParam String title,@RequestParam String description1,@RequestParam String description2,@RequestParam String place,@RequestParam String date,@RequestParam String photo1,@RequestParam String photo2,@RequestParam String photo3,@RequestParam String photo4,@RequestParam String address, Model model){
        Event event=new Event(title,description1,description2,place,date,photo1,photo2,photo3,photo4,address);
        eventRepository.save(event);
        return  "redirect:/admin/events";}

    @GetMapping("/admin/events/{id}")
    public String eventDetails(@PathVariable(value = "id") long id, Model model,Authentication authentication) {
        if(!eventRepository.existsById(id)){
            return "redirect:/admin/events";
        }
        Optional<Event> event= eventRepository.findById(id);
        ArrayList<Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event",res);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);

        return "Event-Details";
    }

    @GetMapping("/admin/events/{id}/edit")
    public String EventEdit(@PathVariable(value = "id") long id, Model model,Authentication authentication) {
        if(!eventRepository.existsById(id)){
            return "redirect:/admin/events";
        }
        Optional<Event> event= eventRepository.findById(id);
        ArrayList<Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event",res);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);

        return "Event-Edit";
    }

    @PostMapping("/admin/events/{id}/edit")
    public String EventRUpdate(@PathVariable(value="id") long id,@RequestParam String title,@RequestParam String description1,@RequestParam String description2,@RequestParam String place,@RequestParam String date,@RequestParam String photo1,@RequestParam String photo2,@RequestParam String photo3,@RequestParam String photo4,@RequestParam String address, Model model){
        Event event = eventRepository.findById(id).orElseThrow();
        event.setDate(date);
        event.setAddress(address);
        event.setTitle(title);
        event.setDescription2(description2);
        event.setDescription1(description1);
        event.setPhoto1(photo1);
        event.setPhoto2(photo2);
        event.setPhoto3(photo3);
        event.setPhoto4(photo4);
        event.setPlace(place);

        eventRepository.save(event);
        return "redirect:/admin/events";
    }
    @PostMapping("/admin/events/{id}/remove")
    public String EventRDelete(@PathVariable(value="id") long id, Model model){
        Event event = eventRepository.findById(id).orElseThrow();
        eventRepository.delete(event);
        return "redirect:/admin/events";
    }





    //    Sights
    @Autowired
    private SightRepository sightRepository;
    @GetMapping("/admin/sights")
    public String sights(Model model,  Authentication authentication) {
        Iterable<Sight> sights = sightRepository.findAll();
        model.addAttribute("sights", sights);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);

        return "Sights";
    }
    @PostMapping("admin/filterS")public String filterS(@RequestParam String filter, Map<String, Object> model,Authentication authentication){
        Iterable <Sight> sights;    if (filter !=null && !filter.isEmpty()){
            sights= sightRepository.findByPlaceContaining(filter);    }
        else {        sights = sightRepository.findAll();
        }    model.put("sights", sights);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.put("admin", isAdmin);

        return "Sights";}

    @GetMapping("/admin/sights/add")
    public String sightsAdd(Model model,Authentication authentication){

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);
        return "Sights-add";
    }
    @PostMapping("/admin/sights/add")
    public String sightsAdd2(@RequestParam String title,@RequestParam String description1,@RequestParam String description2,@RequestParam String place,@RequestParam String photo1,@RequestParam String photo2,@RequestParam String photo3,@RequestParam String photo4,@RequestParam String address, Model model){
        Sight sights=new Sight(title,description1,description2,place,photo1,photo2,photo3,photo4,address);

        sightRepository.save(sights);
        return  "redirect:/admin/sights";}

    @GetMapping("/admin/sights/{id}")
    public String sigthDetails(@PathVariable(value = "id") long id, Model model,Authentication authentication) {
        if(!sightRepository.existsById(id)){
            return "redirect:/admin/sights";
        }
        Optional<Sight> sight= sightRepository.findById(id);
        ArrayList<Sight> res = new ArrayList<>();
        sight.ifPresent(res::add);
        model.addAttribute("sight",res);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);

        return "Sight-Details";
    }

    @GetMapping("/admin/sights/{id}/edit")
    public String SightEdit(@PathVariable(value = "id") long id, Model model,Authentication authentication) {
        if(!sightRepository.existsById(id)){
            return "redirect:/admin/sights";
        }
        Optional<Sight> sight= sightRepository.findById(id);
        ArrayList<Sight> res = new ArrayList<>();
        sight.ifPresent(res::add);
        model.addAttribute("sight",res);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);

        return "Sight-Edit";
    }

    @PostMapping("/admin/sights/{id}/edit")
    public String sightRUpdate(@PathVariable(value="id") long id,@RequestParam String title,@RequestParam String description1,@RequestParam String description2,@RequestParam String place,@RequestParam String photo1,@RequestParam String photo2,@RequestParam String photo3,@RequestParam String photo4,@RequestParam String address, Model model){
        Sight sight = sightRepository.findById(id).orElseThrow();
        sight.setAddress(address);
        sight.setTitle(title);
        sight.setDescription2(description2);
        sight.setDescription1(description1);
        sight.setPhoto1(photo1);
        sight.setPhoto2(photo2);
        sight.setPhoto3(photo3);
        sight.setPhoto4(photo4);
        sight.setPlace(place);

        sightRepository.save(sight);
        return "redirect:/admin/sights";
    }
    @PostMapping("/admin/sights/{id}/remove")
    public String sightRDelete(@PathVariable(value="id") long id, Model model){
        Sight sight = sightRepository.findById(id).orElseThrow();
        sightRepository.delete(sight);
        return "redirect:/admin/sights";
    }









    /*Contacts*/
    @GetMapping("/admin/contacts")
    public String contacts(Model model,Authentication authentication){
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        model.addAttribute("admin", isAdmin);

        return "Contact";
    }


}


