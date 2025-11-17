package jsp.flightBooking.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.flightBooking.dto.BookingStatus;
import jsp.flightBooking.entity.Booking;
import jsp.flightBooking.entity.Passenger;
import jsp.flightBooking.entity.Payment;
import jsp.flightBooking.repository.BookingRepository;

@Repository
public class BookingDao {

    @Autowired
    BookingRepository bookingRepo;

    public Booking createBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

    public List<Booking> findAll() {
        return bookingRepo.findAll();
    }

    public Optional<Booking> findById(Integer id) {
        return bookingRepo.findById(id);
    }

    public List<Booking> findByFlightId(Integer flightId) {
        return bookingRepo.getBookingByFlightId(flightId);
    }

    public List<Booking> findByDate(LocalDate date) {
        return bookingRepo.findByBookingDate(date);
    }

    public List<Booking> findByStatus(BookingStatus status) {
        return bookingRepo.findByStatus(status);
    }

    public List<Passenger> findAllPassengers() {
        return bookingRepo.getAllPassengersInBookings();
    }

    public Optional<Payment> findPaymentByBookingId(Integer bookingId) {
        return bookingRepo.getPaymentDetailsOfBooking(bookingId);
    }

    public void delete(Booking b) {
        bookingRepo.delete(b);
    }

    public Page<Booking> getByPageAndSort(Integer pageNumber, Integer pageSize, String field) {
        return bookingRepo.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field)));
    }
}
