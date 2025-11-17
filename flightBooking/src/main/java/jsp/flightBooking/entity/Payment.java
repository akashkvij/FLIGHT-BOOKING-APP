package jsp.flightBooking.entity;

import jakarta.persistence.*;
import jsp.flightBooking.dto.PaymentMode;
import jsp.flightBooking.dto.PaymentStatus;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    private LocalDate paymentDate;
    private double amount;
    @Enumerated(EnumType.STRING)
	private PaymentMode mode;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	
    

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "bookingid")
    private Booking booking;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	

	public synchronized PaymentMode getMode() {
		return mode;
	}

	public synchronized void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	public synchronized PaymentStatus getStatus() {
		return status;
	}

	public synchronized void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

    
}

