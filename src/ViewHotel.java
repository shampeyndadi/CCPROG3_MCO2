import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The ViewHotel class provides methods to display detailed information about hotels, including hotel details,
 * room information, and reservations. It interacts with the Hotels model and uses the Execute class for console operations.
 */
public class ViewHotel {
    private Hotels Hotel;
    private Scanner sc;

    /**
     * Constructs a ViewHotel object with the specified Hotels model.
     *
     * @param Hotel The Hotels object containing the list of hotels.
     */
    public ViewHotel(Hotels Hotel) {
        this.sc = new Scanner(System.in);
        this.Hotel = Hotel;
    }

    /**
     * Displays high-level information about a selected hotel, including the name, total rooms, and estimated monthly earnings.
     */
    public void displayHighInformation() {
        Execute exe = new Execute(Hotel);

        if (!(Hotel.checkHotels())) {
            exe.clearConsole();
            System.out.println("\nSorry hotels are still preparing their rooms! They will add in rooms soon\n");
            exe.pressEnterToContinue();
            exe.clearConsole();
        } else {
            Hotel.DisplayHotels();

            System.out.print("\nEnter hotel index: ");
            int hotelIndex = 0;
            try {
                hotelIndex = sc.nextInt();
                sc.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Numerical inputs only.");
                sc.nextLine(); // Clear the buffer
                return;
            }

            while (hotelIndex <= 0 || hotelIndex > Hotel.HotelList().size() || Hotel.HotelList().get(hotelIndex - 1).viewRooms().isEmpty()) {
                if (hotelIndex <= 0 || hotelIndex > Hotel.HotelList().size()) {
                    System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                } else {
                    System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                }

                try {
                    hotelIndex = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Numerical inputs only.");
                    sc.nextLine(); // Clear the buffer
                    return;
                }
            }

            Hotel chosenHotel = Hotel.HotelList().get(hotelIndex - 1);

            exe.clearConsole();

            System.out.println("*----------------------------------------------*");
            System.out.println("Hotel Name: " + chosenHotel.getHotelName());
            System.out.println("Total Rooms: " + chosenHotel.viewRooms().size());
            System.out.println("Estimated Monthly Earnings: " + chosenHotel.getTotalEarnings());

            System.out.println();

            exe.clearConsole();
        }
    }

    /**
     * Displays detailed information about a selected hotel, including booked and available rooms on specified dates.
     */
    public void displayHotelInformation() {
        Execute exe = new Execute(Hotel);

        if (!(Hotel.checkHotels())) {
            exe.clearConsole();
            System.out.println("\nSorry hotels are still preparing their rooms! They will add in rooms soon\n");
            exe.pressEnterToContinue();
            exe.clearConsole();
        } else {
            Hotel.DisplayHotels();

            System.out.print("\nEnter hotel index: ");
            int hotelIndex = 0;
            try {
                hotelIndex = sc.nextInt();
                sc.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Numerical inputs only.");
                sc.nextLine(); // Clear the buffer
                return;
            }

            while (hotelIndex <= 0 || hotelIndex > Hotel.HotelList().size() || Hotel.HotelList().get(hotelIndex - 1).viewRooms().isEmpty()) {
                if (hotelIndex <= 0 || hotelIndex > Hotel.HotelList().size()) {
                    System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                } else {
                    System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                }

                try {
                    hotelIndex = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Numerical inputs only.");
                    sc.nextLine(); // Clear the buffer
                    return;
                }
            }

            exe.clearConsole();

            System.out.println("Select date");
            System.out.println("*------------------------*");
            System.out.print("Enter day of the month you wish to check in: ");
            int checkInDay = 0;
            try {
                checkInDay = sc.nextInt();
                sc.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Numerical inputs only.");
                sc.nextLine(); // Clear the buffer
                return;
            }

            while (checkInDay < 1 || checkInDay > 31) {
                System.out.print("Error invalid day! Enter day: ");
                try {
                    checkInDay = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Numerical inputs only.");
                    sc.nextLine(); // Clear the buffer
                    return;
                }
            }

            System.out.println();

            System.out.println("Select date");
            System.out.println("*------------------------*");
            System.out.print("Enter day of the month you wish to check out: ");
            int checkOutDay = 0;
            try {
                checkOutDay = sc.nextInt();
                sc.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Numerical inputs only.");
                sc.nextLine(); // Clear the buffer
                return;
            }

            while (checkOutDay < 1 || checkOutDay > 31) {
                System.out.print("Error invalid day! Enter day: ");
                try {
                    checkOutDay = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Numerical inputs only.");
                    sc.nextLine(); // Clear the buffer
                    return;
                }
            }

            System.out.println();

            exe.clearConsole();

            Date checkIn = new Date(checkInDay);
            Date checkOut = new Date(checkOutDay);
            Hotel chosenHotel = Hotel.HotelList().get(hotelIndex - 1);

            if (checkIn.after(checkOut) || checkIn.equals(checkOut)) {
                exe.clearConsole();

                System.out.println("Error: Check-out day must be after check-in day. Please enter valid dates\n");

                exe.pressEnterToContinue();
                exe.clearConsole();
            } else {
                int bookedRooms = chosenHotel.roomsOnDate(checkIn, checkOut);

                System.out.println("Hotel name: " + chosenHotel.getHotelName());
                System.out.println("*-------------------------*");
                System.out.println("Total booked rooms: " + bookedRooms + " | Available rooms: " + (chosenHotel.viewRooms().size() - bookedRooms));

                System.out.println();

                exe.clearConsole();
            }
        }
    }

    /**
     * Displays detailed information about a specific room, including available dates and price per night.
     */
    public void displayRoomInformation() {
        Execute exe = new Execute(Hotel);

        if (!(Hotel.checkHotels())) {
            exe.clearConsole();
            System.out.println("\nSorry hotels are still preparing their rooms! They will add in rooms soon\n");
            exe.pressEnterToContinue();
            exe.clearConsole();
        } else {
            Hotel.DisplayHotels();

            System.out.print("\nEnter hotel index: ");
            int hotelIndex = 0;
            try {
                hotelIndex = sc.nextInt();
                sc.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Numerical inputs only.");
                sc.nextLine(); // Clear the buffer
                return;
            }

            while (hotelIndex <= 0 || hotelIndex > Hotel.HotelList().size() || Hotel.HotelList().get(hotelIndex - 1).viewRooms().isEmpty()) {
                if (hotelIndex <= 0 || hotelIndex > Hotel.HotelList().size()) {
                    System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                } else {
                    System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                }

                try {
                    hotelIndex = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Numerical inputs only.");
                    sc.nextLine(); // Clear the buffer
                    return;
                }
            }

            Hotel chosenHotel = Hotel.HotelList().get(hotelIndex - 1);

            System.out.println("Available rooms in this hotel: ");
            System.out.println();

            for (int i = 0; i < chosenHotel.viewRooms().size(); i++) {
                System.out.print((i + 1) + ". " + chosenHotel.viewRooms().get(i).getRoomName() + "| ");
                if ((i + 1) % 5 == 0) {
                    System.out.println();
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();

            System.out.print("\nEnter room index: ");
            int roomIndex = 0;
            try {
                roomIndex = sc.nextInt();
                sc.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Numerical inputs only.");
                sc.nextLine(); // Clear the buffer
                return;
            }

            while (roomIndex <= 0 || roomIndex > chosenHotel.viewRooms().size()) {
                System.out.print("Invalid room index. Enter a new index: ");
                try {
                    roomIndex = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Numerical inputs only.");
                    sc.nextLine(); // Clear the buffer
                    return;
                }
            }

            exe.clearConsole();

            Room chosenRoom = chosenHotel.viewRooms().get(roomIndex - 1);
            ArrayList<Reservation> reserve = chosenHotel.viewReservations();

            ArrayList<Integer> datesAvailable = chosenRoom.getDatesAvailable(chosenRoom, reserve);

            if (datesAvailable.isEmpty()) {
                System.out.println("\n" + "Unfortunately " + chosenRoom.getRoomName() + " is fully booked for the month");
            } else {
                System.out.println(chosenRoom.getRoomName() + "'s available days for the month\n");

                for (int i = 0; i < datesAvailable.size(); i++) {
                    System.out.print(datesAvailable.get(i) + " ");

                    if ((i + 1) % 5 == 0) {
                        System.out.println();
                    } else {
                        System.out.print(" ");
                    }
                }
            }

            System.out.println();

            System.out.println("\nRoom name: " + chosenRoom.getRoomName());
            System.out.println("\nPrice per night: " + chosenRoom.getRoomPrice());

            System.out.println();

            exe.clearConsole();
        }
    }

    /**
     * Displays detailed information about reservations in a specific hotel.
     */
    public void displayReservationInformation() {
        Execute exe = new Execute(Hotel);

        if (!(Hotel.checkReservation())) {
            exe.clearConsole();
            System.out.println("\nSorry hotels haven't received any reservations yet!\n");
            exe.pressEnterToContinue();
            exe.clearConsole();
        } else {
            Hotel.DisplayHotels();

            System.out.print("\nEnter hotel index: ");
            int hotelIndex = 0;
            try {
                hotelIndex = sc.nextInt();
                sc.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Numerical inputs only.");
                sc.nextLine(); // Clear the buffer
                return;
            }

            while (hotelIndex <= 0 || hotelIndex > Hotel.HotelList().size() || Hotel.HotelList().get(hotelIndex - 1).viewRooms().isEmpty() || Hotel.HotelList().get(hotelIndex - 1).viewReservations().isEmpty()) {
                if (hotelIndex <= 0 || hotelIndex > Hotel.HotelList().size()) {
                    System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                } else if (Hotel.HotelList().get(hotelIndex - 1).viewReservations().isEmpty()) {
                    System.out.print("\nThis hotel has no reservations. Please choose another hotel [Enter index]: ");
                } else {
                    System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                }

                try {
                    hotelIndex = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Numerical inputs only.");
                    sc.nextLine(); // Clear the buffer
                    return;
                }
            }

            System.out.println();

            Hotel chosenHotel = Hotel.HotelList().get(hotelIndex - 1);
            ArrayList<Reservation> hotelsReserved = chosenHotel.viewReservations();

            for (int i = 0; i < hotelsReserved.size(); i++) {
                System.out.print("|Reservation " + (i + 1) + "| ");

                if ((i + 1) % 5 == 0) {
                    System.out.println();
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();

            if (!(chosenHotel.viewReservations().isEmpty())) {
                System.out.print("\nEnter reservation index to view: ");
                int reservationIndex = 0;
                try {
                    reservationIndex = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Numerical inputs only.");
                    sc.nextLine(); // Clear the buffer
                    return;
                }

                while (reservationIndex <= 0 || reservationIndex > hotelsReserved.size() || hotelsReserved.isEmpty()) {
                    if (reservationIndex <= 0 || reservationIndex > hotelsReserved.size()) {
                        System.out.print("\nInvalid reservation index. Please enter a valid index: ");
                    } else {
                        System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                    }

                    try {
                        reservationIndex = sc.nextInt();
                        sc.nextLine(); // Clear the buffer
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Numerical inputs only.");
                        sc.nextLine(); // Clear the buffer
                        return;
                    }
                }

                exe.clearConsole();

                Reservation reserved = hotelsReserved.get(reservationIndex - 1);

                System.out.println(reserved.displayConfirmation(reserved));

                System.out.println();

                exe.clearConsole();
            } else {
                System.out.println("\n This hotel currently has no reservations");
            }
        }
    }
}
