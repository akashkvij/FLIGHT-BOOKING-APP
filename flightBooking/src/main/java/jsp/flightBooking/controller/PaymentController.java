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

import jsp.flightBooking.dto.PaymentMode;
import jsp.flightBooking.dto.PaymentStatus;
import jsp.flightBooking.dto.ResponseStructure;
import jsp.flightBooking.entity.Payment;
import jsp.flightBooking.service.PaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	
	//save the payment
	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>>recordPayment(@RequestBody Payment payment){
		return paymentService.recordPayment(payment);
	}
	
	// get all payments
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Payment>>>getAllPayment(){
		return paymentService.getAllPayment();
	}
	
	//get payment by id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Payment>>getPaymentById(@PathVariable Integer id){
		return paymentService.getPaymentById(id);
	}
	
	//get payment by status
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByStatus(@PathVariable PaymentStatus status){
		return paymentService.getPaymentByStatus(status);
	}

	
	//get payment great then  this amount
	@GetMapping("/amount/{amount}")
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentWhereAmountGreaterThan(@PathVariable double amount){
		return paymentService.getPaymentWhereAmountGreaterThan(amount);
	}


	//get payment by mode of transaction
	@GetMapping("/mode/{mode}")
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByMode(@PathVariable PaymentMode mode){
		return paymentService.getPaymentByMode(mode);
	}
	
	//update payment status by taking payment id and status has to be update
	@PutMapping("/id/{id}/status/{status}")
	public ResponseEntity<ResponseStructure<Payment>>updatePaymentStatus(@PathVariable Integer id,@PathVariable PaymentStatus status){
		return paymentService.updatePaymentStatus(id, status);
		
	}
	
	//get payments by pagination and sorting 
	@GetMapping("/pagenumber/{pageNumber}/pagesize/{pageSize}/field/{field}")
	public ResponseEntity<ResponseStructure<Page<Payment>>>getPaymentByPageAndSort(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field){
		return paymentService.getPaymentByPageAndSort(pageNumber, pageSize, field);
	}





}
