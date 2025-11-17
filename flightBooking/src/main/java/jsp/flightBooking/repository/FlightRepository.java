package jsp.flightBooking.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import jsp.flightBooking.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
	List<Flight> findBySourceAndDestination(String source,String destination);
	List<Flight> findByAirline(String airline);
}
