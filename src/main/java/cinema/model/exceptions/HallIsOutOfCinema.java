package cinema.model.exceptions;

public class HallIsOutOfCinema extends Exception {
    public HallIsOutOfCinema(int hall) {
        super(String.format("Hall %d is out of cinema", hall));
    }
}
