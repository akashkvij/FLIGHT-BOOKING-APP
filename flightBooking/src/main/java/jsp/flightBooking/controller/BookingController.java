package jsp.flightBooking.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.flightBooking.dto.BookingStatus;
import jsp.flightBooking.dto.ResponseStructure;
import jsp.flightBooking.entity.Booking;
import jsp.flightBooking.entity.Passenger;
import jsp.flightBooking.entity.Payment;
import jsp.flightBooking.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create booking
    @PostMapping
    public ResponseEntity<ResponseStructure<Booking>> createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Get booking by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable Integer id) {
        return bookingService.getBookingById(id);
    }

    // Get bookings by flight ID
    @GetMapping("/flight/id/{id}")
    public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlightId(@PathVariable Integer id) {
        return bookingService.getBookingByFlightId(id);
    }

    // Get bookings by date
    @GetMapping("/date/{date}")
    public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(@PathVariable LocalDate date) {
        return bookingService.getBookingByDate(date);
    }

    // Get booking by status
    @GetMapping("/status/{status}")
    public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(@PathVariable BookingStatus status) {
        return bookingService.getBookingByStatus(status);
    }

    // Get all passengers in all bookings
    @GetMapping("/passengers")
    public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengersInBookings() {
        return bookingService.getAllPassengersInBookings();
    }

    // Get payment details for a booking
    @GetMapping("/{bookingId}/payment")
    public ResponseEntity<ResponseStructure<Payment>> getPaymentDetails(@PathVariable Integer bookingId) {
        return bookingService.getPaymentDetailsOfBooking(bookingId);
    }

    // Update booking status
    @PostMapping("/id/{bookingId}/status/{status}")
    public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(@PathVariable Integer bookingId,
                                                                          @PathVariable BookingStatus status) {
        return bookingService.updateBookingStatus(bookingId, status);
    }

    // Delete booking
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<ResponseStructure<String>> deleteBooking(@PathVariable Integer bookingId) {
        return bookingService.deleteBooking(bookingId);
    }

    // Pagination + Sorting
    @GetMapping("/pagenumber/{pageNumber}/pagesize/{pageSize}/field/{field}")
    public ResponseEntity<ResponseStructure<Page<Booking>>> getBookingsByPageAndSort(
            @PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
        return bookingService.getBookingByPageAndSort(pageNumber, pageSize, field);
    }
}
