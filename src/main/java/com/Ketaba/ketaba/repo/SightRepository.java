package com.Ketaba.ketaba.repo;


import com.Ketaba.ketaba.models.Event;
import com.Ketaba.ketaba.models.Sight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface   SightRepository extends CrudRepository<Sight,Long> {
    List<Sight> findByPlaceContaining(String place);
}
