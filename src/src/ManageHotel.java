import java.util.Scanner;
import java.util.ArrayList;

/**
 * The ManageHotel class provides functionalities to manage hotels within the hotel reservation system.
 * It includes methods for creating hotels, adding rooms, removing rooms, changing room prices, modifying date prices, removing reservations, and changing hotel names.
 */
public class ManageHotel {
    private Scanner sc;
    private Hotels Hotel;

    /**
     * Constructs a new ManageHotel object with the specified Hotels list.
     *
     * @param Hotel the Hotels object containing the list of hotels to manage
     */
    public ManageHotel(Hotels Hotel){
        this.sc = new Scanner(System.in);
        this.Hotel = Hotel;
    }

    /**
     * Creates a new hotel with user-specified name and number of rooms.
     * The total number of rooms (basic, deluxe, executive) should not exceed 50.
     */
    public void createHotel(){
        System.out.print("Enter Hotel name: ");
        String name = sc.nextLine();
        if (!Hotel.doesHotelExist(name)){
            int basicRooms = 0;
            int deluxeRooms = 0;
            int executiveRooms = 0;

            System.out.print("Enter number of basic rooms: ");
            try{
                basicRooms = sc.nextInt();
            }catch (Exception E){
                System.out.println("\nNumeric inputs only");
                sc.nextLine();
                return;
            }

            System.out.print("Enter number of deluxe rooms: ");
            try{
                deluxeRooms = sc.nextInt();
            }catch (Exception E){
                System.out.println("\nNumeric inputs only");
                sc.nextLine();
                return;
            }

            System.out.print("Enter number of executive rooms: ");
            try{
                executiveRooms = sc.nextInt();
            }catch (Exception E){
                System.out.println("\nNumeric inputs only");
                sc.nextLine();
                return;
            }

            int totalRooms = basicRooms + deluxeRooms + executiveRooms;

            if (totalRooms <= 50 && totalRooms > 0 && basicRooms >= 0 && deluxeRooms >= 0 && executiveRooms >= 0) {
                Hotel createdHotel = new Hotel(name);
                Hotel.createHotel(createdHotel);

                for (int i = 0; i < basicRooms; i++) {
                    createdHotel.addBasicRoom();
                }
                for (int i = 0; i < deluxeRooms; i++) {
                    createdHotel.addDeluxeRoom();
                }
                for (int i = 0; i < executiveRooms; i++) {
                    createdHotel.addExecutiveRoom();
                }

                createdHotel.InitializeRooms();

                System.out.println("\n" + name + " created succesfully\n");
                sc.nextLine();

            }else{
                System.out.println("\nCreation unsuccessful, Invalid number of rooms\n");
                sc.nextLine();
            }
        }else{
            System.out.println("\nCreation unsuccessful, hotel already exists\n");
            sc.nextLine();
        }

    }

    /**
     * Adds rooms to an existing hotel selected by the user.
     * The total number of rooms (including existing ones) should not exceed 50.
     */
    public void AddRooms(){
        if (Hotel.HotelList().isEmpty()){
            System.out.println("No hotels present! Please create a hotel first");
        }else{
            System.out.println("[ADD ROOMS]\n");

            Hotel.DisplayHotels();

            int index = 0;

            System.out.print("\nEnter index: ");
            try {
                index = sc.nextInt();
                sc.nextLine();
            }catch (Exception E){
                System.out.println("Numeric inputs only");
                sc.nextLine();
                return;
            }

            while (index <= 0 || index > Hotel.HotelList().size()) {
                
                System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                try {
                    index = sc.nextInt();
                    sc.nextLine();
                }catch (Exception E){
                    System.out.println("Numeric inputs only");
                    sc.nextLine();
                    return;
                }
            }

            if (Hotel.HotelList().get(index-1).viewRooms().size() == 50){
                System.out.println("\nThis hotel has already reached the maximum capacity of rooms\n");
            }else{
                Hotel chosenHotel = Hotel.HotelList().get(index-1);

                int basicRooms = 0;
                int deluxeRooms = 0;
                int executiveRooms = 0;

                System.out.print("\nEnter number of basic rooms you wish to add: ");
                try {
                    basicRooms = sc.nextInt();
                    sc.nextLine();
                }catch (Exception e){
                    System.out.println("Numeric inputs only");
                    sc.nextLine();
                    return;
                }

                System.out.print("\nEnter number of basic rooms you wish to add: ");
                try {
                    deluxeRooms = sc.nextInt();
                    sc.nextLine();
                }catch (Exception e){
                    System.out.println("Numeric inputs only");
                    sc.nextLine();
                    return;
                }

                System.out.print("\nEnter number of basic rooms you wish to add: ");
                try {
                    executiveRooms = sc.nextInt();
                    sc.nextLine();
                }catch (Exception e){
                    System.out.println("Numeric inputs only");
                    sc.nextLine();
                    return;
                }

                int totalRooms = basicRooms + deluxeRooms + executiveRooms;

                if (totalRooms < 0 || basicRooms < 0 || deluxeRooms < 0 || executiveRooms < 0) {
                    System.out.println("\nRoom counts cannot be negative.");
                }else if (totalRooms + chosenHotel.viewRooms().size() > 50){
                    System.out.println("\nTotal rooms exceed the maximum allowed limit of 50.");
                } else {
                    for (int i = 0; i < basicRooms; i++) {
                        chosenHotel.addBasicRoom();
                    }
                    for (int i = 0; i < deluxeRooms; i++) {
                        chosenHotel.addDeluxeRoom();
                    }
                    for (int i = 0; i < executiveRooms; i++) {
                        chosenHotel.addExecutiveRoom();
                    }

                    chosenHotel.InitializeRooms();
                    System.out.println("\nRoom(s) created successfully!\n");
                }
            }
        }
    }

    /**
     * Removes rooms from a hotel that have no active reservations.
     */
    public void RemoveRooms(){
        
        if (!(Hotel.checkHotels())){
            System.out.println("\nSorry hotels are still preparing their rooms! They will add in rooms soon");
        }else{
            System.out.println("[REMOVE ROOMS]\n");
            Hotel.DisplayHotels();
            System.out.print("\nEnter index: ");
            int index = 0;
            try {
                index = sc.nextInt();
                sc.nextLine();
            }catch (Exception E){
                System.out.println("Numeric inputs only");
                sc.nextLine();
                return;
            }

            while (index <= 0 || index > Hotel.HotelList().size() || Hotel.HotelList().get(index - 1).viewRooms().isEmpty()) {
                if (index <= 0 || index > Hotel.HotelList().size()) {
                    System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                } else {
                    System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                }

                try {
                    index = sc.nextInt();
                    sc.nextLine();
                }catch (Exception E){
                    System.out.println("Numeric inputs only");
                    sc.nextLine();
                    return;
                }
            }

            Hotel chosenHotel = Hotel.HotelList().get(index-1);
            ArrayList<Room> removeRooms = chosenHotel.getRemovableRooms();

            if (removeRooms.isEmpty()){
                System.out.println("All rooms currently have a booking!\n");
            }else{
                System.out.println("\nRooms that doesn't have an active reservation:");
                System.out.println();

                for (int i = 0; i < removeRooms.size(); i++){
                    System.out.print((i+1) + ". " + removeRooms.get(i).getRoomName() + "| ");

                    if ((i + 1) % 5 == 0) {
                        System.out.println(); 
                    } else {
                        System.out.print(" "); 
                    }
                }

                System.out.println();
                
                System.out.print("\nEnter room index you wish to remove: ");
                int roomIndex = 0;

                try{
                    roomIndex = sc.nextInt();
                }catch (Exception E){
                    System.out.println("Invalid input! Numeric inputs only");
                    sc.nextLine();
                    return;
                }

                while (roomIndex <= 0 || roomIndex > removeRooms.size()){
                    System.out.print("\nError! Room index does not exist. Enter index: ");
                    try {
                        roomIndex = sc.nextInt();
                    }catch (Exception E){
                        System.out.println("Invalid input! Numeric inputs only");
                        sc.nextLine();
                        return;
                    }
                }

                chosenHotel.removeRoom(removeRooms.get(roomIndex-1));

                System.out.println("\nRoom successfully removed!\n");
            }
        }

    }

    /**
     * Changes the base price of all rooms in a specified hotel.
     * The hotel must not have any active reservations.
     */
    public void changeBasePrice(){
        if (!(Hotel.checkHotels())){
            System.out.println("\nSorry hotels are still preparing their rooms! They will add in rooms soon");
        }else{
            System.out.println("[UPDATE BASE PRICE OF ROOMS]\n");

            Hotel.DisplayHotels();
            System.out.print("\nEnter index: ");
            int index = 0;
            try {
                index = sc.nextInt();
                sc.nextLine();
            }catch (Exception E){
                System.out.println("Numeric inputs only");
                sc.nextLine();
                return;
            }

            while (index <= 0 || index > Hotel.HotelList().size() || Hotel.HotelList().get(index - 1).viewRooms().isEmpty()) {
                if (index <= 0 || index > Hotel.HotelList().size()) {
                    System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                } else {
                    System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                }

                try {
                    index = sc.nextInt();
                    sc.nextLine();
                }catch(Exception E){
                    System.out.println("\nInvalid input! Numeric inputs only");
                    return;
                }
            }

            Hotel chosenHotel = Hotel.HotelList().get(index-1);
            
            if (!chosenHotel.viewReservations().isEmpty()){
                System.out.println("\nThis room currently has reservations. You cannot change the price for now\n");
            }else{
                double newPrice = 0;

                System.out.print("\nEnter new base price for all rooms: ");
                try {
                    newPrice = sc.nextDouble();
                }catch(Exception E){
                    System.out.println("\nInvalid input! Numeric inputs only");
                    return;
                }
                
                while (newPrice < 100.0){
                    System.out.print("\nError! The new price must be >= 100.0. Enter price: ");
                    try {
                        newPrice = sc.nextDouble();
                    }catch(Exception E){
                        System.out.println("\nInvalid input! Numeric inputs only");
                        return;
                    }
                }

                for (Room room : chosenHotel.viewRooms()) {
                    room.updatePrice(newPrice);

                }
                for(Room room : chosenHotel.viewRooms()) {
                    if (room instanceof  Deluxe){
                        ((Deluxe) room).updatePrice();
                    }
                }
                for (Room room: chosenHotel.viewRooms()) {
                    if(room instanceof Executive){
                        ((Executive) room).updatePrice();
                    }
                }

                System.out.println("\nBase price updated successfully!\n");
            }
        }
    }

    /**
     * Modifies the price percentage of a room for a specific date.
     */
    public void datePriceModifier(){
        if (!(Hotel.checkHotels())){
            System.out.println("\nSorry hotels are still preparing their rooms! They will add in rooms soon");
        }else{
            System.out.println("[MODIFY DATE PRICE]\n");

            Hotel.DisplayHotels();
            System.out.print("\nEnter index: ");
            int index = 0;
            try {
                index = sc.nextInt();
                sc.nextLine();
            }catch (Exception E){
                System.out.println("Numeric inputs only");
                sc.nextLine();
                return;
            }

            while (index <= 0 || index > Hotel.HotelList().size() || Hotel.HotelList().get(index - 1).viewRooms().isEmpty()) {
                if (index <= 0 || index > Hotel.HotelList().size()) {
                    System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                } else {
                    System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                }

                try {
                    index = sc.nextInt();
                    sc.nextLine();
                }catch(Exception E){
                    System.out.println("\nInvalid input! Numeric inputs only");
                    return;
                }
            }

            Hotel chosenHotel = Hotel.HotelList().get(index-1);
            ArrayList<Room> Rooms = chosenHotel.getRemovableRooms();

            for (int i = 0; i < Rooms.size(); i++){
                System.out.print((i+1) + ". " + Rooms.get(i).getRoomName() + "| ");

                if ((i + 1) % 5 == 0) {
                    System.out.println();
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();

            System.out.print("\nEnter room index you wish to change the date percentage: ");
            int roomIndex = 0;

            try{
                roomIndex = sc.nextInt();
            }catch (Exception E){
                System.out.println("Invalid input! Numeric inputs only");
                sc.nextLine();
                return;
            }

            while (roomIndex <= 0 || roomIndex > Rooms.size()){
                System.out.print("\nError! Room index does not exist. Enter index: ");
                try {
                    roomIndex = sc.nextInt();
                }catch (Exception E){
                    System.out.println("\nInvalid input! Numeric inputs only");
                    sc.nextLine();
                    return;
                }
            }

            System.out.println();

            Room room = Rooms.get(roomIndex-1);

            double newPercentage = 0;
            int date = 0;

            try{
                System.out.print("Enter percentage: ");
                newPercentage = sc.nextDouble();
                System.out.print("Enter day you wish to modify: ");
                date = sc.nextInt();
            }catch (Exception E){
                System.out.println("Invalid input! Numeric inputs only");
                sc.nextLine();
                return;
            }

            if (date >= 1 && date <= 31 && newPercentage > 0) {
                room.setPriceModifier(newPercentage, date);
                System.out.println("\nDate price successfully changed");
            }else{
                System.out.println("Date or percentage must be invalid");
            }

        }
    }

    /**
     * Removes a reservation from a specified hotel.
     */
    public void removeReservation(){
        if (!(Hotel.checkReservation())){
            System.out.println("\nSorry hotels haven't received any reservations yet!");
        }else{
            System.out.println("[REMOVE RESERVATION]\n");

            Hotel.DisplayHotels();
            int index = 0;
            System.out.print("\nEnter index: ");
            try {
                index = sc.nextInt();
            }catch(Exception E){
                System.out.println("\nInvalid input! Numeric inputs only");
                sc.nextLine();
                return;
            }

            while (index <= 0 || index > Hotel.HotelList().size() || Hotel.HotelList().get(index - 1).viewRooms().isEmpty() || Hotel.HotelList().get(index-1).viewReservations().isEmpty()) {
                if (index <= 0 || index > Hotel.HotelList().size()) {
                    System.out.print("\nInvalid hotel index. Please enter a valid index: ");
                } else if (Hotel.HotelList().get(index-1).viewReservations().isEmpty()) {
                    System.out.print("\nThis hotel currently has no reservations. Please choose another hotel [Enter index]: ");
                } else {
                    System.out.print("\nThis hotel has no rooms yet. Please choose another hotel [Enter index]: ");
                }

                try {
                    index = sc.nextInt();
                }catch(Exception E){
                    System.out.println("\nInvalid input! Numeric inputs only");
                    sc.nextLine();
                    return;
                }
            }

            System.out.println();

            Hotel chosenHotel = Hotel.HotelList().get(index - 1);

            if (!(chosenHotel.viewReservations().isEmpty())){
                for (int i = 0; i < chosenHotel.viewReservations().size(); i++){
                    System.out.println("Reservation " + (i + 1));
                    System.out.println("\n" + chosenHotel.viewReservations().get(i).displayConfirmation(chosenHotel.viewReservations().get(i)));
                    System.out.println("*-------------------*\n\n");
                }

                int reservationIndex = 0;
                System.out.print("Enter reservation index: ");
                try {
                    reservationIndex = sc.nextInt();
                }catch(Exception E){
                    System.out.println("\nInvalid input! Numeric inputs only");
                    sc.nextLine();
                    return;
                }

                while (reservationIndex <= 0 || reservationIndex > chosenHotel.viewReservations().size()){
                    System.out.print("\nInvalid reservation index. Enter index again: ");
                    try {
                        reservationIndex = sc.nextInt();
                    }catch(Exception E){
                        System.out.println("\nInvalid input! Numeric inputs only");
                        sc.nextLine();
                        return;
                    }
                }

                chosenHotel.viewReservations().remove(chosenHotel.viewReservations().get(reservationIndex-1));

                System.out.println("\nReservation successfully voided!\n");
            } else{
                System.out.println("\nThis hotel has no reservations to remove\n");
            }
        }
    }

    /**
     * Changes the name of a specified hotel.
     */
    public void changeHotelName(){
        System.out.println("[CHANGE HOTEL NAME]\n");

        Hotel.DisplayHotels();
        System.out.print("\nEnter index: ");
        int index = 0;
        try {
            index = sc.nextInt();
        }catch(Exception E){
            System.out.println("\nInvalid input! Numeric inputs only");
            sc.nextLine();
            return;
        }

        sc.nextLine();

        while (index <= 0 || index > Hotel.HotelList().size()) {
            System.out.print("\nInvalid hotel index. Please enter a valid index: ");
            try {
                index = sc.nextInt();
            }catch(Exception E){
                System.out.println("\nInvalid input! Numeric inputs only");
                sc.nextLine();
                return;
            }
        }

        System.out.println();

        System.out.print("Enter new name: ");
        String newName = sc.nextLine();

        if (Hotel.doesHotelExist(newName)){
            System.out.println("\nHotel already exists please choose a new name\n");
        }else{
            Hotel.HotelList().get(index - 1).changeHotelName(newName);
            System.out.println("\nHotel name successfully changed!\n");
        }
    }

    /**
     * Removes a hotel from the system.
     */
    public void removeHotel(){
        System.out.println("[REMOVE HOTEL]\n");

        Hotel.DisplayHotels();
        System.out.print("\nEnter index: ");
        int index = 0;
        try {
            index = sc.nextInt();
        }catch(Exception E){
            System.out.println("\nInvalid input! Numeric inputs only");
            sc.nextLine();
            return;
        }

        while (index <= 0 || index > Hotel.HotelList().size()) {
                
            System.out.print("\nInvalid hotel index. Please enter a valid index: ");
            try {
                index = sc.nextInt();
            }catch(Exception E){
                System.out.println("\nInvalid input! Numeric inputs only");
                sc.nextLine();
                return;
            }
        }

        Hotel chosenHotel = Hotel.HotelList().get(index - 1);

        if (!(chosenHotel.viewReservations().isEmpty())){
            System.out.print("This hotel currently has reservations. Are you sure you want to proceed Y/N: ");
            char answer = ' ';

            try {
                answer = sc.nextLine().trim().charAt(0);
            }catch (Exception E){
                System.out.println("Invalid input for characters");
                sc.nextLine();
                return;
            }

            while (answer != 'Y' && answer != 'N' && answer != 'y' && answer != 'n'){
                System.out.print("\nInvalid input. Input answer again: ");
                try {
                    answer = sc.nextLine().trim().charAt(0);
                }catch (Exception E){
                    System.out.println("\nInvalid input for characters");
                    sc.nextLine();
                    return;
                }
            }

            if (answer == 'Y' || answer == 'y'){
                Hotel.HotelList().remove(Hotel.HotelList().get(index - 1));
                System.out.println("\nHotel successfully removed!\n");
            }else 
                System.out.println("\nHotel removal cancelled!\n");
        }else{
            Hotel.HotelList().remove(Hotel.HotelList().get(index - 1));
            System.out.println("\nHotel succesfully removed!\n");
        }
    }


}
