package cinema;

import java.util.Scanner;
import java.util.stream.Stream;

import cinema.model.Cinema;


public class Main
{
    public static Cinema cinema = new Cinema();

    private static void initCinema() {
    }

    public static void main(String[] args)
    {
        initCinema();
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            boolean doBreak = false;
            printMenu();
            String str = scanner.nextLine();
            switch(str.trim()) {
                case "1":
                    System.out.println("Enter the hall number to print:");
                    int hallNumber = Integer.parseInt(scanner.nextLine());
                    cinema.printHallSchema(hallNumber);
                    break;
                case "2":
                    System.out.println("Enter the hall number to book:");
                    hallNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the row number to book:");
                    int rowNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the seats numbers to book:");
                    String[] seatsNumbers = scanner.nextLine().split(" ");
                    int[] seats = Stream.of(seatsNumbers).mapToInt(Integer::parseInt).toArray();
                    try {
                        cinema.bookSeats(hallNumber, rowNumber, seats);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("Enter the hall number to cancel booking:");
                    hallNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the row number to cancel booking:");
                    rowNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the seats numbers to cancel booking:");
                    seatsNumbers = scanner.nextLine().split(" ");
                    seats = Stream.of(seatsNumbers).mapToInt(Integer::parseInt).toArray();
                    try {
                        cinema.cancelBooking(hallNumber, rowNumber, seats);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Enter the hall number to check availability:");
                    hallNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the seats count to check availability:");
                    int seatsCount = Integer.parseInt(scanner.nextLine());
                    System.out.println(cinema.checkAvailability(hallNumber, seatsCount) ? "Seats are available" : "Seats are not available");
                    break;

                default:
                    doBreak = true;
                    break;
            }
            if (doBreak) {
                break;
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("1. Print hall schema.");
        System.out.println("2. Book seats.");
        System.out.println("3. Cancel booking.");
        System.out.println("4. Check availability.");
        System.out.println("5. Exit");
    }

}

