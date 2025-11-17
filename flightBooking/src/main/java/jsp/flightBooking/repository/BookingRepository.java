package jsp.flightBooking.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.flightBooking.dto.BookingStatus;
import jsp.flightBooking.entity.Booking;
import jsp.flightBooking.entity.Passenger;
import jsp.flightBooking.entity.Payment;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("select b from Booking b where b.flight.id = ?1")
    List<Booking> getBookingByFlightId(Integer id);

    List<Booking> findByBookingDate(LocalDate date);

    List<Booking> findByStatus(BookingStatus status);

    @Query("select b.passengers from Booking b")
    List<Passenger> getAllPassengersInBookings();

    @Query("select b.payment from Booking b where b.id = ?1")
    Optional<Payment> getPaymentDetailsOfBooking(Integer bookingId);
}
