package cinema.model.exceptions;

public class SeatIsOutOfRow extends Exception {
    public SeatIsOutOfRow(int seat, int row) {
        super(String.format("Seat %d is out of row %d", seat, row));
    }
}
