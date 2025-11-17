package jsp.flightBooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.flightBooking.dto.PaymentMode;
import jsp.flightBooking.dto.PaymentStatus;
import jsp.flightBooking.entity.Payment;
import jsp.flightBooking.repository.PaymentRepository;

@Repository
public class PaymentDao {
	@Autowired
	PaymentRepository paymentrepository;
	
	//record payment
	public Payment recordPayment(Payment payment) {
		return paymentrepository.save(payment);
	}
	
	//get all payment
	public List<Payment>getAllPayment(){
		return paymentrepository.findAll();
	}
	
	//get payment by id
	
	public Optional<Payment>getPaymentById(Integer id){
		return paymentrepository.findById(id);
	}
	
	//get payment by status
	
	public List<Payment>getPaymentByStatus(PaymentStatus status){
		return paymentrepository.findByStatus(status);
	}
	
	//get payment where amount is greater then particular amount
	
	public List<Payment> getPaymentWhereAmountGreaterThan(double amount){
		return paymentrepository.findByAmountGreaterThan(amount);
	}
	
	//get payment by mode of transaction
	public List<Payment> getPaymentByMode(PaymentMode mode){
		return paymentrepository.findByMode(mode);
	}
	
	//get payment by pagination and sorting
	public Page<Payment>getPaymentByPageAndSort(Integer pageNumber,Integer pageSize,String field){
		return paymentrepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(field)));
		
//		return PaymentRepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(field)));
	}

}
