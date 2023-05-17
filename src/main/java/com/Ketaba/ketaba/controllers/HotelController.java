package com.Ketaba.ketaba.controllers;

import com.Ketaba.ketaba.models.Event;
import com.Ketaba.ketaba.models.Hotel;
import com.Ketaba.ketaba.models.Sight;
import com.Ketaba.ketaba.repo.EventRepository;
import com.Ketaba.ketaba.repo.HotelRepository;
import com.Ketaba.ketaba.repo.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
@PreAuthorize("hasAuthority('USER')")
@Controller
public class HotelController {
//    Hotel Rooms
    @Autowired
    private HotelRepository hotelRepository;
    @GetMapping("/hotels")
    public String hotels(Model model) {
        Iterable<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "Hotels";
    }
    @PostMapping("filter")public String filter1(@RequestParam String filter, Map<String, Object> model){
        Iterable <Hotel> hotels;    if (filter !=null && !filter.isEmpty()){
            hotels= hotelRepository.findByTitleContaining(filter);    }
        else {        hotels = hotelRepository.findAll();
        }    model.put("hotels", hotels);
        return "Hotels";}

    @PostMapping("filter2")public String filter2(@RequestParam String filter, Map<String, Object> model){
        Iterable <Hotel> hotels;    if (filter !=null && !filter.isEmpty()){
            hotels= hotelRepository.findByRegionContaining(filter);    }
        else {        hotels = hotelRepository.findAll();
        }    model.put("hotels", hotels);
        return "Hotels";}

    @PostMapping("filter3")public String filter3(@RequestParam String filter, Map<String, Object> model){
        Iterable <Hotel> hotels;    if (filter !=null && !filter.isEmpty()){
            hotels= hotelRepository.findByBedContaining(filter);    }
        else {        hotels = hotelRepository.findAll();
        }    model.put("hotels", hotels);
        return "Hotels";}


    @GetMapping("/hotels/add")
    public String hotelsAdd(Model model){
    return "Hotels-add";
    }
    @PostMapping("/hotels/add")
    public String hotelsAdd2(@RequestParam String title,@RequestParam String photo,@RequestParam String type,@RequestParam String region,@RequestParam String bed,@RequestParam String services,@RequestParam String price,@RequestParam int capacity,@RequestParam int size,@RequestParam String email,@RequestParam String number,@RequestParam String address,@RequestParam String map, Model model){
    Hotel hotel=new Hotel(title,photo,type,region,bed, services,price,capacity,size,email,number,address,map);
    hotelRepository.save(hotel);
    return  "redirect:/hotels";
    }

    @GetMapping("/hotels/{id}")
    public String HotelDetails(@PathVariable(value = "id") long id, Model model) {
        if(!hotelRepository.existsById(id)){
            return "redirect:/hotels";
        }
        Optional<Hotel> hotel= hotelRepository.findById(id);
        ArrayList<Hotel> res = new ArrayList<>();
        hotel.ifPresent(res::add);
        model.addAttribute("hotel",res);


        return "Hotel-Details";
    }












    @GetMapping("/hotels/{id}/edit")
    public String HotelEdit(@PathVariable(value = "id") long id, Model model) {
        if(!hotelRepository.existsById(id)){
            return "redirect:/hotels";
        }
        Optional<Hotel> post= hotelRepository.findById(id);
        ArrayList<Hotel> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("hotel",res);
        return "Hotel-Edit";
    }

    @PostMapping("/hotels/{id}/edit")
    public String HotelRUpdate(@PathVariable(value="id") long id,@RequestParam String title,@RequestParam String photo,@RequestParam String type,@RequestParam String region,@RequestParam String bed,@RequestParam String services,@RequestParam String price,@RequestParam int capacity,@RequestParam int size,@RequestParam String email,@RequestParam String number,@RequestParam String address,@RequestParam String map, Model model){
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        hotel.setTitle(title);
        hotel.setPhoto(photo);
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
        hotelRepository.save(hotel);
        return "redirect:/hotels";
    }
    @PostMapping("/hotels/{id}/remove")
    public String HotelRDelete(@PathVariable(value="id") long id, Model model){
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        hotelRepository.delete(hotel);
        return "redirect:/hotels";
    }



    //    Events
    @Autowired
    private EventRepository eventRepository;
    @GetMapping("/events")
    public String events(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "Events";
    }

    @PostMapping("filterEv")public String filterEv(@RequestParam String filter, Map<String, Object> model){
        Iterable <Event> events;    if (filter !=null && !filter.isEmpty()){
            events= eventRepository.findByPlaceContaining(filter);    }
        else {        events = eventRepository.findAll();
        }    model.put("events", events);
        return "Events";}


    @GetMapping("/events/add")
    public String eventsAdd(Model model){
        return "Events-add";
    }
    @PostMapping("/events/add")
    public String eventsAdd2(@RequestParam String title,@RequestParam String description1,@RequestParam String description2,@RequestParam String place,@RequestParam String date,@RequestParam String photo1,@RequestParam String photo2,@RequestParam String photo3,@RequestParam String photo4,@RequestParam String address, Model model){
        Event event=new Event(title,description1,description2,place,date,photo1,photo2,photo3,photo4,address);
        eventRepository.save(event);
        return  "redirect:/events";}

    @GetMapping("/events/{id}")
    public String eventDetails(@PathVariable(value = "id") long id, Model model) {
        if(!eventRepository.existsById(id)){
            return "redirect:/events";
        }
        Optional<Event> event= eventRepository.findById(id);
        ArrayList<Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event",res);
        return "Event-Details";
    }

    @GetMapping("/events/{id}/edit")
    public String EventEdit(@PathVariable(value = "id") long id, Model model) {
        if(!eventRepository.existsById(id)){
            return "redirect:/events";
        }
        Optional<Event> event= eventRepository.findById(id);
        ArrayList<Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event",res);
        return "Event-Edit";
    }

    @PostMapping("/events/{id}/edit")
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
        return "redirect:/events";
    }
    @PostMapping("/events/{id}/remove")
    public String EventRDelete(@PathVariable(value="id") long id, Model model){
        Event event = eventRepository.findById(id).orElseThrow();
        eventRepository.delete(event);
        return "redirect:/events";
    }





//    Sights
    @Autowired
    private SightRepository sightRepository;
    @GetMapping("/sights")
    public String sights(Model model) {
        Iterable<Sight> sights = sightRepository.findAll();
        model.addAttribute("sights", sights);
        return "Sights";
    }
    @PostMapping("filterS")public String filterS(@RequestParam String filter, Map<String, Object> model){
        Iterable <Sight> sights;    if (filter !=null && !filter.isEmpty()){
            sights= sightRepository.findByPlaceContaining(filter);    }
        else {        sights = sightRepository.findAll();
        }    model.put("sights", sights);
        return "Sights";}

    @GetMapping("/sights/add")
    public String sightsAdd(Model model){
        return "Sights-add";
    }
    @PostMapping("/sights/add")
    public String sightsAdd2(@RequestParam String title,@RequestParam String description1,@RequestParam String description2,@RequestParam String place,@RequestParam String photo1,@RequestParam String photo2,@RequestParam String photo3,@RequestParam String photo4,@RequestParam String address, Model model){
        Sight sights=new Sight(title,description1,description2,place,photo1,photo2,photo3,photo4,address);

        sightRepository.save(sights);
        return  "redirect:/sights";}

    @GetMapping("/sights/{id}")
    public String sigthDetails(@PathVariable(value = "id") long id, Model model) {
        if(!sightRepository.existsById(id)){
            return "redirect:/sights";
        }
        Optional<Sight> sight= sightRepository.findById(id);
        ArrayList<Sight> res = new ArrayList<>();
        sight.ifPresent(res::add);
        model.addAttribute("sight",res);
        return "Sight-Details";
    }

    @GetMapping("/sights/{id}/edit")
    public String SightEdit(@PathVariable(value = "id") long id, Model model) {
        if(!sightRepository.existsById(id)){
            return "redirect:/sights";
        }
        Optional<Sight> sight= sightRepository.findById(id);
        ArrayList<Sight> res = new ArrayList<>();
        sight.ifPresent(res::add);
        model.addAttribute("sight",res);
        return "Sight-Edit";
    }

    @PostMapping("/sights/{id}/edit")
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
        return "redirect:/sights";
    }
    @PostMapping("/sights/{id}/remove")
    public String sightRDelete(@PathVariable(value="id") long id, Model model){
        Sight sight = sightRepository.findById(id).orElseThrow();
        sightRepository.delete(sight);
        return "redirect:/sights";
    }









    /*Contacts*/
    @GetMapping("/contacts")
    public String contacts(Model model){
        return "Contact";
    }


        }


