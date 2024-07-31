import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the simulation of booking reservations for hotels.
 */
public class SimulateBooking {
    private Hotels HotelLists;
    private Scanner sc;

    /**
     * Constructor to initialize the SimulateBooking with a list of hotels.
     * @param HotelLists The list of hotels.
     */
    public SimulateBooking(Hotels HotelLists){
        this.HotelLists = HotelLists;
        this.sc = new Scanner(System.in);
    }

    /**
     * Simulates the booking process by prompting the user to select a hotel, specify check-in
     * and check-out dates, and choose a room. It also handles applying discount codes if provided.
     */
    public void bookHotel(){
        Execute exe = new Execute(HotelLists);

        if (HotelLists.HotelList().isEmpty()){
            System.out.println("\nNo hotels yet! Hotels are yet to be built");
            exe.pressEnterToContinue();
        }else if (!(HotelLists.checkHotels())){
            System.out.println("\nSorry hotels are still preparing their rooms! They will add in rooms soon");
            exe.pressEnterToContinue();
        }else {
            System.out.println("[BOOK A RESERVATION]\n");

            HotelLists.DisplayHotels();

            int index = 0;
            System.out.print("\nChoose hotel [Enter index]: ");
            try {
                index = sc.nextInt();
            } catch (Exception E) {
                System.out.println("Invalid input! Numerical Inputs only");
                sc.nextLine();
                return;
            }

            while (index <= 0 || index > HotelLists.HotelList().size() || HotelLists.HotelList().get(index - 1).viewRooms().isEmpty()) {
                if (index <= 0 || index > HotelLists.HotelList().size()) {
                    System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                } else {
                    System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                }

                try {
                    index = sc.nextInt();
                } catch (Exception E) {
                    System.out.println("Invalid input! Numerical Inputs only");
                    sc.nextLine();
                    return;
                }
            }

            Hotel chosenHotel = HotelLists.HotelList().get(index - 1);

            if (!chosenHotel.isFullyBookedForMonth()) {
                System.out.println("\nEnter preferred check in day: ");
                System.out.print("*------------------------------------*\n\n");
                System.out.print("Enter day of the month you wish to check in: ");
                int checkInDay = 0;
                try {
                    checkInDay = sc.nextInt();
                } catch (Exception E) {
                    System.out.println("Invalid input! Numerical Inputs only");
                    sc.nextLine();
                    return;
                }

                while (checkInDay < 1 || checkInDay > 31) {
                    System.out.print("\nError invalid day! Enter day: ");
                    try {
                        checkInDay = sc.nextInt();
                    } catch (Exception E) {
                        System.out.println("Invalid input! Numerical Inputs only");
                        sc.nextLine();
                        return;
                    }
                }

                System.out.println();

                System.out.println("\nEnter preferred check out day: ");
                System.out.print("*------------------------------------*\n\n");
                System.out.print("Enter day of the month you wish to check out: ");
                int checkOutDay = 0;
                try {
                    checkOutDay = sc.nextInt();
                } catch (Exception E) {
                    System.out.println("Invalid input! Numerical Inputs only");
                    sc.nextLine();
                    return;
                }

                while (checkOutDay < 1 || checkOutDay > 31) {
                    System.out.print("\nError invalid day! Enter day: ");
                    try {
                        checkOutDay = sc.nextInt();
                    } catch (Exception E) {
                        System.out.println("Invalid input! Numerical Inputs only");
                        sc.nextLine();
                        return;
                    }
                }

                System.out.println();

                Date checkIn = new Date(checkInDay);
                Date checkOut = new Date(checkOutDay);

                if (checkIn.after(checkOut) || checkIn.equals(checkOut)) {
                    System.out.println("\nError: Check-out day must be after check-in day. Please rebook again\n");

                    exe.pressEnterToContinue();
                } else {
                    ArrayList<Room> roomsOfChosenHotel = chosenHotel.viewRooms();
                    ArrayList<Reservation> reservations = chosenHotel.viewReservations();

                    System.out.println(chosenHotel.getHotelName() + "'s available rooms on your specified dates\n");

                    ArrayList<Room> availableRooms = chosenHotel.roomAvailable(roomsOfChosenHotel, reservations, checkOut, checkIn);

                    for (int i = 0; i < availableRooms.size(); i++) {
                        System.out.print((i + 1) + ". " + availableRooms.get(i).getRoomName() + "| ");
                        if ((i + 1) % 5 == 0) {
                            System.out.println();
                        } else {
                            System.out.print(" ");
                        }
                    }

                    System.out.println();

                    System.out.print("*------------------------------------*\n\n");

                    System.out.print("Enter room index: ");
                    int roomIndex = 0;
                    try {
                        roomIndex = sc.nextInt();
                    } catch (Exception E) {
                        System.out.println("Invalid input! Numerical Inputs only");
                        sc.nextLine();
                        return;
                    }

                    while (!(roomIndex <= availableRooms.size() && roomIndex != 0)) {
                        System.out.print("Error! room doesn't exist. Enter room index again: ");
                        try {
                            roomIndex = sc.nextInt();
                        } catch (Exception E) {
                            System.out.println("Invalid input! Numerical Inputs only");
                            sc.nextLine();
                            return;
                        }
                    }

                    sc.nextLine();
                    System.out.println();

                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();

                    System.out.println();

                    System.out.print("Enter discount code (if any): ");
                    String discountCode = sc.nextLine().trim();

                    Room chosenRoom = availableRooms.get(roomIndex - 1);

                    Reservation reserved = new Reservation(name, checkIn, checkOut, chosenRoom);

                    if (discountCode != null && !discountCode.trim().isEmpty()) {
                        String discountMessage = reserved.applyDiscount(discountCode);
                        System.out.println(discountMessage);
                        System.out.println();
                        chosenHotel.viewReservations().add(reserved);
                    } else {
                        chosenHotel.viewReservations().add(reserved);
                    }

                    System.out.println("\nThank you for choosing " + chosenHotel.getHotelName() + ", your reservation was succesfully booked!");
                    System.out.println("*-------------------------------*");

                    System.out.println(reserved.displayConfirmation(reserved));

                    System.out.println();

                }
            }else{
                System.out.println("\nApologies, hotel is fully booked for the month. We will inform you if we added rooms or reservations have been cancelled\n");
            }
        }
    }

}
