package jsp.flightBooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jsp.flightBooking.dao.PassengerDao;
import jsp.flightBooking.dto.ResponseStructure;
import jsp.flightBooking.entity.Passenger;
import jsp.flightBooking.exception.IdNotFoundException;
import jsp.flightBooking.exception.NoRecordAvailableException;


@Service
public class PassengerService {
	@Autowired
	PassengerDao passengerdao;
	
	public ResponseEntity<ResponseStructure<Passenger>> savepassenger(Passenger passenger){
		ResponseStructure<Passenger> response=new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("passenger record saved");
		response.setData(passengerdao.savepassenger(passenger));
		return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<List<Passenger>>>  savepassengerAll(List<Passenger> passenger){
		ResponseStructure<List<Passenger>> response=new ResponseStructure<List<Passenger>>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("passenger record saved");
		response.setData(passengerdao.savepassengerAll(passenger));
		return new ResponseEntity<ResponseStructure<List<Passenger>>> (response,HttpStatus.CREATED);
		
	}
	
public ResponseEntity<ResponseStructure<List<Passenger>>> getallpassenger(){
		
		List<Passenger> passengers=passengerdao.findAll();
		ResponseStructure<List<Passenger>> response=new ResponseStructure<List<Passenger>>();
		
		if(!passengers.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("passenger records are reterived");
			response.setData(passengerdao.findAll());
			return new ResponseEntity<ResponseStructure<List<Passenger>>>(response,HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("Table doesn't contain any records");
		}
	}
public ResponseEntity<ResponseStructure<Passenger>> getpassengerbyid(Integer id){
	ResponseStructure<Passenger> response=new ResponseStructure<Passenger>();
	Optional<Passenger> opt=passengerdao.findById(id);
	if(!opt.isEmpty()) {
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("passenger details reterived sucessfully");
		response.setData(opt.get());
		return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.OK);
	}
	else {
		throw new IdNotFoundException("table doesnot contain any records");
	}
}

public ResponseEntity<ResponseStructure<Passenger>> updatepassenger(Passenger passenger){
	ResponseStructure<Passenger> response=new ResponseStructure<Passenger>();
	Optional<Passenger> opt=passengerdao.findById(passenger.getId());
	if(passenger.getId()==null) {
		throw new IdNotFoundException("id not found cant update");
	}
	if(opt.isPresent()) {
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("passenger record updated sucessfully");
		response.setData(passengerdao.savepassenger(passenger));
		return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.OK);
		
	}
	else {
		throw new NoRecordAvailableException("no record found to update");
	}
}
public ResponseEntity<ResponseStructure<Passenger>> getpassengerbycontactno(String contactno){
	ResponseStructure<Passenger> response=new ResponseStructure<Passenger>();
	Optional<Passenger> opt= passengerdao.findByContactNo(contactno);
	if(opt.isPresent()) {
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(opt.get());
		response.setMessage("passenger  records with author - "+contactno+" is found sucessfully");
		return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.OK);
	}else {
		throw new NoRecordAvailableException("no contact number is found");
	}
}

public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerBypageAndSort(Integer pageNumber,Integer pageSize,String field){
	ResponseStructure<Page<Passenger>>response =new ResponseStructure<Page<Passenger>>();
	Page<Passenger>passengers=passengerdao.getPassengerBypageAndSort(pageNumber, pageSize, field);
	if(passengers.isEmpty())
		throw new NoRecordAvailableException("Recoreds not available ");
	
	response.setStatusCode(HttpStatus.OK.value());
	response.setMessage("here is your records ");
	response.setData(passengers);
	return new ResponseEntity<ResponseStructure<Page<Passenger>>>(response,HttpStatus.OK);

}
public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersWithBooking() {
    List<Passenger> passengers = passengerdao.findPassengersWithBooking();
    if (passengers.isEmpty()) throw new NoRecordAvailableException("No passengers found with bookings");

    ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage("Passengers with bookings retrieved");
    response.setData(passengers);
    return new ResponseEntity<>(response, HttpStatus.OK);
}

}
