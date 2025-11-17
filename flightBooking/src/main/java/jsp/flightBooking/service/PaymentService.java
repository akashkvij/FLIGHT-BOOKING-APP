package jsp.flightBooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.flightBooking.dao.PaymentDao;
import jsp.flightBooking.dto.PaymentMode;
import jsp.flightBooking.dto.PaymentStatus;
import jsp.flightBooking.dto.ResponseStructure;
import jsp.flightBooking.entity.Payment;
import jsp.flightBooking.exception.NoRecordAvailableException;

@Service
public class PaymentService {
	@Autowired
	PaymentDao paymentdao;

	
	//save the record
	public ResponseEntity<ResponseStructure<Payment>>recordPayment(Payment payment){
		ResponseStructure<Payment>response=new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("payment created");
		response.setData(paymentdao.recordPayment(payment));
		return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.CREATED);
	}
	
	
	
	//get all payment
	public ResponseEntity<ResponseStructure<List<Payment>>>getAllPayment(){
		ResponseStructure<List<Payment>>response=new ResponseStructure<List<Payment>>();
		List<Payment>payments=paymentdao.getAllPayment();
		if(payments.isEmpty())
			throw new NoRecordAvailableException("payment records are not available");
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("here is your payments");
		response.setData(payments);
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
	}
	
	
	//get payment by id 
	public ResponseEntity<ResponseStructure<Payment>>getPaymentById(Integer id){
		ResponseStructure<Payment>response=new ResponseStructure<Payment>();
		Optional<Payment>payment=paymentdao.getPaymentById(id);
		if(payment.isEmpty())
			throw new NoRecordAvailableException("no record available on this id"+id);
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("here is your record");
		response.setData(payment.get());
		return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.OK);
		
	}
	
	//get payment by status
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByStatus(PaymentStatus status){
		ResponseStructure<List<Payment>>response=new ResponseStructure<List<Payment>>();
		List<Payment>payments=paymentdao.getPaymentByStatus(status);
		if(payments.isEmpty())
			throw new NoRecordAvailableException("payment records are not available based on status"+status);
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("here is your payments");
		response.setData(payments);
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
		
	}
	
	//get payment greater then particular amount
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentWhereAmountGreaterThan(double amount){
		
		ResponseStructure<List<Payment>>response=new ResponseStructure<List<Payment>>();
		List<Payment>payments=paymentdao.getPaymentWhereAmountGreaterThan(amount);
		if(payments.isEmpty())
			throw new NoRecordAvailableException("payment records are not available greater then :"+amount);
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("here is your payments");
		response.setData(payments);
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
	}
	
	//get payment by payment mode
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByMode(PaymentMode mode){
		
		ResponseStructure<List<Payment>>response=new ResponseStructure<List<Payment>>();
		List<Payment>payments=paymentdao.getPaymentByMode(mode);
		if(payments.isEmpty())
			throw new NoRecordAvailableException("payment records are not available on this mode:"+mode);
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("here is your payments");
		response.setData(payments);
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
	}
	
	//update payment status by taking status, payment id
	public ResponseEntity<ResponseStructure<Payment>>updatePaymentStatus(Integer id,PaymentStatus status){
		ResponseStructure<Payment> response=new ResponseStructure<Payment>();
		Optional<Payment> payment=paymentdao.getPaymentById(id);
		if(payment.isEmpty())
			throw new NoRecordAvailableException("payment not exixt on this id:"+id);
		
		payment.get().setStatus(status);
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("updated successfully");
		response.setData(paymentdao.recordPayment(payment.get()));
		return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.OK);

		
	}
	
	
	//get payment by pagination and sorting
	public ResponseEntity<ResponseStructure<Page<Payment>>>getPaymentByPageAndSort(Integer pageNumber,Integer pageSize,String field){
		ResponseStructure<Page<Payment>>response=new ResponseStructure<Page<Payment>>();
		Page<Payment>payments=paymentdao.getPaymentByPageAndSort(pageNumber, pageSize, field);
		if(payments.isEmpty())
			throw new NoRecordAvailableException("records are not avilable in db");
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("here your records");
		response.setData(payments);
		return new ResponseEntity<ResponseStructure<Page<Payment>>>(response,HttpStatus.OK);
	}


}
