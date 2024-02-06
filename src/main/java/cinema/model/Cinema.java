package cinema.model;

import cinema.model.exceptions.*;


public class Cinema {
    private static final int HALLS_COUNT = 5;
    private static final int ROWS_COUNT = 10;
    private static final int SEATS_COUNT = 20;

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BRIGHT_BLACK_BACKGROUND = "\u001B[100m";

    public static final String ANSI_FG_WHITE = "\u001B[30m";
    public static final String ANSI_FG_GREEN = "\u001B[32m";
    public static final String ANSI_FG_RED = "\u001B[31m";

    private boolean[][][] seats = new boolean[HALLS_COUNT][ROWS_COUNT][SEATS_COUNT];

    public Cinema() {
        for (int i = 0; i < seats.length; i++) {
            boolean[][] hall = seats[i];
            for (int j = 0; j < hall.length ; j++) {
                boolean[] row = hall[j];
                for (int k = 0; k < row.length; k++) {
                    row[k] = false;
                }
            }
        }
    }

    public void bookSeats(int hallNumber, int rowNumber, int[] seatsNumber)
        throws HallIsOutOfCinema,
            RowIsOutOfHall,
            SeatIsOutOfRow,
            SeatIsOccupiedException {
        if (hallNumber > this.seats.length) {
            throw new HallIsOutOfCinema(hallNumber);
        }
        boolean[][] hall = this.seats[hallNumber - 1];
        if (rowNumber > hall.length) {
            throw new RowIsOutOfHall(hallNumber, rowNumber);
        }
        boolean[] row = hall[rowNumber - 1];
        for (int seatNumber : seatsNumber) {
            setSeatBooked(row, hallNumber, rowNumber, seatNumber, true);
        }
    }

    public void cancelBooking(int hallNumber, int rowNumber, int[] seatsNumber)
        throws HallIsOutOfCinema,
            RowIsOutOfHall,
            SeatIsOutOfRow,
            SeatIsOccupiedException {
        if (hallNumber > this.seats.length) {
            throw new HallIsOutOfCinema(hallNumber);
        }
        boolean[][] hall = this.seats[hallNumber - 1];
        if (rowNumber > hall.length) {
            throw new RowIsOutOfHall(hallNumber, rowNumber);
        }
        boolean[] row = hall[rowNumber - 1];
        for (int seatNumber : seatsNumber) {
            setSeatBooked(row, hallNumber, rowNumber, seatNumber, false);
        }
    }

    public boolean checkAvailability(int hallNumber, int seatsCount) {
        for (boolean[][] rows : this.seats) {
            for (boolean[] row : rows) {
                int count = 0;
                for (boolean seat : row) {
                    if (!seat) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == seatsCount) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printHallSchema(int hallNumber) {
        boolean[][] hall = this.seats[hallNumber - 1];

        this.printSeatsNumbers();
        for (int i = 0; i < hall.length; i++) {
            boolean[] row = hall[i];
            printRow(i, row);
        }
        this.printSeatsNumbers();
    }

    private void setSeatBooked(boolean[] row, int hallNumber, int rowNumber, int seatNumber, boolean value)
        throws SeatIsOutOfRow, SeatIsOccupiedException {
        if (seatNumber > row.length) {
            throw new SeatIsOutOfRow(seatNumber, rowNumber);
        }
        if (value && row[seatNumber - 1]) {
            throw new SeatIsOccupiedException(hallNumber, rowNumber, seatNumber);
        }
        row[seatNumber - 1] = value;
    }

    private void printRow(int rowNumber, boolean[] row) {
        System.out.print(ANSI_BRIGHT_BLACK_BACKGROUND);
        System.out.printf(" %2s |", rowNumber + 1);
        System.out.print(ANSI_BLACK_BACKGROUND);
        for (int i = 0; i < row.length; i++) {
            this.printSeat(row[i]);
        }
        System.out.print(ANSI_BRIGHT_BLACK_BACKGROUND + ANSI_FG_WHITE);
        System.out.printf("| %-2s ", rowNumber + 1);
        System.out.println(ANSI_BLACK_BACKGROUND);
    }

    private void printSeat(boolean seatValue) {
        if (seatValue) {
            System.out.print(ANSI_YELLOW_BACKGROUND);
            System.out.print(ANSI_FG_RED);
        } else {
            System.out.print(ANSI_FG_GREEN);
            System.out.print(ANSI_BLACK_BACKGROUND);
        }
        System.out.print(seatValue ? "  1 " : "  O ");

    }

    private void printSeatsNumbers() {
        System.out.print(ANSI_BRIGHT_BLACK_BACKGROUND + "     ");
        for (int i = 0; i < SEATS_COUNT; i++) {
            System.out.printf("%3s ", i + 1);
        }
        System.out.print("     ");
        System.out.println(ANSI_BLACK_BACKGROUND);
    }
}
