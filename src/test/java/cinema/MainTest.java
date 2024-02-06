
package cinema;

import cinema.model.*;
import cinema.model.exceptions.SeatIsOccupiedException;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
    private Cinema cinema;

    @BeforeEach
    public void init() {
        cinema = new Cinema();
    }

    @Test
    public void seatsBookingTest() throws Exception {
        cinema.bookSeats(1, 1, new int[]{ 3, 7, 11, 12,15,18 });

        // test trying to seat on already booked seat
        try {
            cinema.bookSeats(1, 1, new int[]{ 1, 2, 3 });
        } catch (Exception e) {
            assert(e instanceof SeatIsOccupiedException);
            assertEquals("Seat 1-1-3 is already occupied", e.getMessage());
        }
    }

    @Test
    public void seatsCancelBookingTest() throws Exception {
        cinema.bookSeats(1, 1, new int[]{ 3, 7, 11, 12,15,18 });
        cinema.cancelBooking(1, 1, new int[]{ 3, 7, 11, 12,15,18 });

        // test trying to cancel booking on already free seat
        try {
            cinema.cancelBooking(1, 1, new int[]{ 3, 7, 11, 12,15,18 });
        } catch (Exception e) {
            assert(e instanceof SeatIsOccupiedException);
            assertEquals("Seat 1-1-3 is already occupied", e.getMessage());
        }
    }

    @Test
    public void availabilitySeatsTest() throws Exception {
        // fill all seats
        for (int hallNumber = 0; hallNumber < 5; hallNumber ++) {
            for (int rowNumber = 0; rowNumber < 10; rowNumber ++) {
                cinema.bookSeats(hallNumber + 1, rowNumber + 1, new int[] { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 });
            }
        }
        // test trying to book seat in full hall
        boolean hasAvailability = cinema.checkAvailability(0, 2);
        assertEquals(false, hasAvailability);
        cinema.printHallSchema(1);
    }
}
