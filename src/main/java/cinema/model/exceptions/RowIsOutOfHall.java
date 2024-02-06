package cinema.model.exceptions;

public class RowIsOutOfHall extends Exception {
    public RowIsOutOfHall(int hall, int row) {
        super(String.format("Row %d is out of hall %d", row, hall));
    }
}
