package jsp.flightBooking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.flightBooking.dao.BookingDao;
import jsp.flightBooking.dao.FlightDao;
import jsp.flightBooking.dto.BookingStatus;
import jsp.flightBooking.dto.ResponseStructure;
import jsp.flightBooking.entity.Booking;
import jsp.flightBooking.entity.Flight;
import jsp.flightBooking.entity.Passenger;
import jsp.flightBooking.entity.Payment;
import jsp.flightBooking.exception.IdNotFoundException;
import jsp.flightBooking.exception.NoRecordAvailableException;

@Service
public class BookingService {

    @Autowired
    BookingDao bookingDao;

    @Autowired
    FlightDao flightDao;

    // CREATE BOOKING
    public ResponseEntity<ResponseStructure<Booking>> createBooking(Booking booking) {

        ResponseStructure<Booking> response = new ResponseStructure<>();

        // Validate Flight
        if (booking.getFlight() == null || booking.getFlight().getId() == null)
            throw new IdNotFoundException("Flight ID must be provided.");

        Optional<Flight> optionalFlight = flightDao.findById(booking.getFlight().getId());
        if (optionalFlight.isEmpty())
            throw new IdNotFoundException("No flight found with ID: " + booking.getFlight().getId());

        Flight flight = optionalFlight.get();

       
        if (booking.getPayment() != null) {
            booking.getPayment().setBooking(booking);
        }

      
        if (booking.getPassengers() != null) {
            for (Passenger p : booking.getPassengers()) {
                p.setBooking(booking);
            }
        }

        
        Booking savedBooking = bookingDao.createBooking(booking);

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Booking created successfully");
        response.setData(savedBooking);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET ALL
    public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings() {
        List<Booking> bookings = bookingDao.findAll();
        if (bookings.isEmpty()) throw new NoRecordAvailableException("No bookings found");

        ResponseStructure<List<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All bookings fetched");
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET BY ID
    public ResponseEntity<ResponseStructure<Booking>> getBookingById(Integer id) {
        Optional<Booking> opt = bookingDao.findById(id);
        if (opt.isEmpty()) throw new IdNotFoundException("Booking not found");

        ResponseStructure<Booking> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Booking fetched");
        response.setData(opt.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET BY FLIGHT ID
    public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlightId(Integer id) {
        List<Booking> bookings = bookingDao.findByFlightId(id);
        if (bookings.isEmpty()) throw new NoRecordAvailableException("No bookings for this flight");

        ResponseStructure<List<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Bookings for flight id " + id);
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET BY DATE
    public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(LocalDate date) {
        List<Booking> bookings = bookingDao.findByDate(date);
        if (bookings.isEmpty()) throw new NoRecordAvailableException("No bookings on this date");

        ResponseStructure<List<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Bookings for date " + date);
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET BY STATUS
    public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(BookingStatus status) {
        List<Booking> bookings = bookingDao.findByStatus(status);
        if (bookings.isEmpty()) throw new NoRecordAvailableException("No bookings with this status");

        ResponseStructure<List<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Bookings with status " + status);
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET ALL PASSENGERS
    public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengersInBookings() {
        List<Passenger> passengers = bookingDao.findAllPassengers();
        if (passengers.isEmpty()) throw new NoRecordAvailableException("No passengers found");

        ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All passengers fetched");
        response.setData(passengers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET PAYMENT DETAILS
    public ResponseEntity<ResponseStructure<Payment>> getPaymentDetailsOfBooking(Integer bookingId) {
        Optional<Payment> payment = bookingDao.findPaymentByBookingId(bookingId);
        if (payment.isEmpty())
            throw new IdNotFoundException("Payment not found for booking id " + bookingId);

        ResponseStructure<Payment> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payment details fetched");
        response.setData(payment.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // UPDATE STATUS
    public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(Integer bookingId, BookingStatus status) {
        Optional<Booking> opt = bookingDao.findById(bookingId);
        if (opt.isEmpty()) throw new IdNotFoundException("Booking not found");

        Booking booking = opt.get();
        booking.setStatus(status);

        Booking updated = bookingDao.createBooking(booking);

        ResponseStructure<Booking> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Status updated");
        response.setData(updated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // DELETE
    public ResponseEntity<ResponseStructure<String>> deleteBooking(Integer bookingId) {
        Optional<Booking> opt = bookingDao.findById(bookingId);
        if (opt.isEmpty()) throw new IdNotFoundException("Booking not found");

        bookingDao.delete(opt.get());

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Booking deleted");
        response.setData("Deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // PAGINATION + SORT
    public ResponseEntity<ResponseStructure<Page<Booking>>> getBookingByPageAndSort(
            Integer pageNumber, Integer pageSize, String field) {

        Page<Booking> bookings = bookingDao.getByPageAndSort(pageNumber, pageSize, field);
        if (bookings.isEmpty())
            throw new NoRecordAvailableException("No bookings found");

        ResponseStructure<Page<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Paged bookings fetched");
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
