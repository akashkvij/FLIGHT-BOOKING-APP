package jsp.flightBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.flightBooking.dto.ResponseStructure;
import jsp.flightBooking.entity.Passenger;
import jsp.flightBooking.repository.PassengerRepository;
import jsp.flightBooking.service.PassengerService;

@RequestMapping("/passenger")
@RestController
public class PassengerController {
	
	@Autowired
	private PassengerService passengerservice;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Passenger>> savepassenger(@RequestBody Passenger passenger){
		return passengerservice.savepassenger(passenger);
	}
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Passenger>>> savepassengerAll(@RequestBody List<Passenger> passenger){
		return passengerservice.savepassengerAll(passenger);
		
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Passenger>>> getallpassenger(){
		return passengerservice.getallpassenger();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> getpassengerbyid(@PathVariable Integer id){
		return passengerservice.getpassengerbyid(id);
		
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Passenger>> updatepassenger(@RequestBody Passenger passenger){
		return passengerservice.updatepassenger(passenger);
		
	}
	@GetMapping("/contactnumber/{contactNumber}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNumber(@PathVariable String contactNumber){
		return passengerservice.getpassengerbycontactno(contactNumber);
	}
	
	
	@GetMapping("/pagenumber/{pageNumber}/pagesize/{pageSize}/field/{field}")
	public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerBypageAndSort(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field){
		return passengerservice.getPassengerBypageAndSort(pageNumber, pageSize, field);
	}

	 @GetMapping("/status/with-booking")
	    public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersWithBooking() {
	        return passengerservice.getPassengersWithBooking();
	    }
	

}
