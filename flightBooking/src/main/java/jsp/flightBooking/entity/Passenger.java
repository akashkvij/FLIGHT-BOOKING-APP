package jsp.flightBooking.entity;

import jakarta.persistence.*;
import jsp.flightBooking.dto.PassengerGender;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class Passenger {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private PassengerGender gender;
    private String aadharNumber;
    private String contactNo;

    
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
    
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() 
	{
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	public synchronized PassengerGender getGender() {
		return gender;
	}

	public synchronized void setGender(PassengerGender gender) {
		this.gender = gender;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	

	

    
}
