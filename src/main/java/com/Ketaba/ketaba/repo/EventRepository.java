package com.Ketaba.ketaba.repo;

import com.Ketaba.ketaba.models.Event;
import com.Ketaba.ketaba.models.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface   EventRepository extends CrudRepository<Event,Long> {
    List<Event> findByPlaceContaining(String place);
}