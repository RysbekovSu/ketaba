package com.Ketaba.ketaba.repo;


import com.Ketaba.ketaba.models.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface   HotelRepository extends CrudRepository<Hotel,Long> {
    List<Hotel> findByTitleContaining(String title);
    List<Hotel> findByRegionContaining(String region);
    List<Hotel> findByBedContaining(String bed);
//    List<Hotel> findByRegionContaining(String capacity);
}

