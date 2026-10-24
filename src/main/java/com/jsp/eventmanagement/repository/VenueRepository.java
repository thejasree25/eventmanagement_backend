
package com.jsp.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.eventmanagement.entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
}
