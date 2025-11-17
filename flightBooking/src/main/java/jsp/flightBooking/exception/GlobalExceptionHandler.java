package jsp.flightBooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jsp.flightBooking.dto.ResponseStructure;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(NoRecordAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handlenorecordavailablexception(NoRecordAvailableException exception){
		ResponseStructure<String> response=new ResponseStructure<String> ();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("No record found");
		response.setData(exception.getMessage());
			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleidnotfoundexception(NoRecordAvailableException exception){
		ResponseStructure<String> response=new ResponseStructure<String> ();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("No record found");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	

}
