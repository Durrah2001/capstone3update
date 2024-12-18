package org.example.capstone3.Repository;

import org.example.capstone3.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findEventById(Integer id);
}
