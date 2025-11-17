package jsp.flightBooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.flightBooking.entity.Flight;
import jsp.flightBooking.repository.FlightRepository;

@Repository
public class FlightDao {
	@Autowired
	FlightRepository flightrepository;

	public Flight saveflight(Flight flight) {
		return flightrepository.save(flight);
	}

	public List<Flight> saveflightall(List<Flight> flight) {
		return flightrepository.saveAll(flight);
	}

	public List<Flight> findAll() {
		return flightrepository.findAll();
	}

	public Optional<Flight> findById(Integer id) {
		return flightrepository.findById(id);
	}

	public List<Flight> findBySourceAndDestination(String source, String destination) {
		return flightrepository.findBySourceAndDestination(source, destination);
	}

	public List<Flight> findByAirline(String airline) {
		return flightrepository.findByAirline(airline);
	}

	public void delete(Flight flight) {
		flightrepository.delete(flight);
		
	}

	

	

	

	
	
	

}
