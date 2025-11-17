package jsp.flightBooking.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import jsp.flightBooking.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	Optional<Passenger> findByContactNo(String contactno);
	List<Passenger> findByBookingIsNotNull();
	
}
