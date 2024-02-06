package cinema.model.exceptions;

public class SeatIsOccupiedException extends Exception {
    public SeatIsOccupiedException(int hall, int row, int seat) {
        super(String.format("Seat %d-%d-%d is already occupied", hall, row, seat));
    }
}
