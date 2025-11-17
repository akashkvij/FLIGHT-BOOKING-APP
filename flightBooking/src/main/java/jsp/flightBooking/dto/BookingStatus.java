package jsp.flightBooking.dto;

public enum BookingStatus {
	 	PENDING,       // Booking created but payment not completed
	    CONFIRMED,     // Payment done, ticket issued
	    CANCELLED,     // User cancelled
	    CHECKED_IN,    // Passenger did web/airport check-in
	    COMPLETED,     // Flight finished
	    NO_SHOW,       // Passenger didn’t show up
	    REFUNDED       // Money returned after cancellation

}
