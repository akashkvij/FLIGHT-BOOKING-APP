package jsp.flightBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jsp.flightBooking.dto.ResponseStructure;
import jsp.flightBooking.entity.Flight;
import jsp.flightBooking.repository.FlightRepository;
import jsp.flightBooking.service.FlightService;


@RestController
@RequestMapping("/flight")
public class FlightController 


{
	
	
	@Autowired
	private FlightService flightservice;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Flight>> saveflight(@RequestBody Flight flight){
		return flightservice.saveflight(flight);
		
	}
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Flight>>> saveflightall(@RequestBody List<Flight> flight){
		return flightservice.saveallflightall(flight);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Flight>>> getallflight(){
		return flightservice.getallflight();
	}
	@GetMapping("{id}")
	public ResponseEntity<ResponseStructure<Flight>> getflightbyid(@PathVariable Integer id){
		return flightservice.getflightbyid(id);
		
	}
	@GetMapping("/source/{source}/destination/{destination}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getflightbysourceanddestination(@PathVariable String source,@PathVariable String destination){
		return flightservice.getflightbysourceanddestination(source, destination);
		
	}
	@GetMapping("/airline/{airline}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getflightbyairline(@PathVariable String airline){
		return flightservice.getflightbyairline(airline);
		
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Flight>> updateflight(@RequestBody Flight flight){
		return flightservice.updateflight(flight);
	}
	
	

}
