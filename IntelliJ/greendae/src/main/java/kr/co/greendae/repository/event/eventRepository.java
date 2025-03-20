package kr.co.greendae.repository.event;

import kr.co.greendae.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface eventRepository extends JpaRepository<Event, Integer> {
}
