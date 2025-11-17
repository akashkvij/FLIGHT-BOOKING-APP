package jsp.flightBooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.flightBooking.dao.FlightDao;
import jsp.flightBooking.dto.ResponseStructure;
import jsp.flightBooking.entity.Flight;
import jsp.flightBooking.exception.IdNotFoundException;
import jsp.flightBooking.exception.NoRecordAvailableException;

@Service
public class FlightService {
	
	@Autowired
	FlightDao flightdao;
	
	public ResponseEntity<ResponseStructure<Flight>> saveflight(Flight flight){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Flight record saved");
		response.setData(flightdao.saveflight(flight));
		return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> saveallflightall(List<Flight> flight){
		ResponseStructure<List<Flight>> response= new ResponseStructure<List<Flight>>();
		response.setStatusCode(HttpStatus.CREATED.OK.value());
		response.setMessage("All records have been inserted");
		response.setData(flightdao.saveflightall(flight));
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> getallflight(){
		List<Flight> flight=flightdao.findAll();
		ResponseStructure<List<Flight>> response=new ResponseStructure<List<Flight>>();
		if(!flight.isEmpty()) {
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All datas have been fetched");
		response.setData(flightdao.findAll());
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
	}
		else {
			throw new NoRecordAvailableException("Table doesn't contain any records");
			
		}
	}
	
	public ResponseEntity<ResponseStructure<Flight>> getflightbyid(Integer id){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		Optional<Flight> opt=flightdao.findById(id);
		
		if(opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(opt.get());
			response.setMessage("flight details reterived");
			return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("invalid id");
		}
		
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> getflightbysourceanddestination( String source,String destination){
	List<Flight> flight = flightdao.findBySourceAndDestination(source,destination);
	ResponseStructure<List<Flight>> response=new ResponseStructure<>();
	if(!flight.isEmpty()) {
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("flight details with source "+source+"and destination "+destination+"has been fetched");
		response.setData(flight);
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
	}
	else {
		throw new NoRecordAvailableException("flight details with source "+source+"and destination "+destination+"is not available");
	}
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> getflightbyairline(String airline){
		List<Flight> flight=flightdao. findByAirline(airline);
		ResponseStructure<List<Flight>> response=new ResponseStructure<List<Flight>>();
		if(!flight.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("flight details with airline "+airline+"has been fetched");
			response.setData(flight);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("flight details with airline "+airline+"is not available");
		}
		
	}
	public ResponseEntity<ResponseStructure<Flight>> updateflight(Flight flight){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		Optional<Flight> opt=flightdao.findById(flight.getId());
		if(flight.getId()==null) {
			throw new NoRecordAvailableException("no record available");
			
		}
		if(opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Flight record has been updated sucessfully");
			response.setData(flightdao.saveflight(flight));
			return new ResponseEntity<ResponseStructure<Flight>> (response,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("no id found");
		}
		
	}
	public ResponseEntity<ResponseStructure<String>> deleteflight(Integer id){
		ResponseStructure<String> response = new ResponseStructure<String>();
		Optional<Flight> opt=flightdao.findById(id);
		if(opt.isPresent()) {
			flightdao.delete(opt.get());
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book Record with id "+ id + "deleted");
			response.setData("sucess");
			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("can't be deleted no id found");
		}
	}
	
	
	
}
