
package com.jsp.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jsp.eventmanagement.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
