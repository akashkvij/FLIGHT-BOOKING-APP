package jsp.flightBooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.flightBooking.entity.Passenger;
import jsp.flightBooking.repository.PassengerRepository;

@Repository
public class PassengerDao {
	@Autowired
	PassengerRepository passengerrepository;

	public Passenger savepassenger(Passenger passenger) {
		return passengerrepository.save(passenger);
	}

	public List<Passenger> savepassengerAll(List<Passenger> passenger) {
		return passengerrepository.saveAll(passenger);
	}

	public List<Passenger> findAll() {
		return passengerrepository.findAll();
	}

	public Optional<Passenger> findById(Integer id) {
		return passengerrepository.findById(id);
	}

	public Optional<Passenger> findByContactNo(String contactno) {
		return passengerrepository.findByContactNo(contactno);
	}
	

	public Page<Passenger> getPassengerBypageAndSort(Integer pageNumber, Integer pageSize, String field) {
        return passengerrepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field)));
    }
	
	public List<Passenger> findPassengersWithBooking() {
        return passengerrepository.findByBookingIsNotNull();
    }
	

}
