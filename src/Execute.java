import java.util.Scanner;

/**
 * Execute serves as the main controller for the hotel management system,
 * handling various operations such as managing hotels, simulating bookings, and viewing hotel details.
 */
public class Execute{
    private Hotels HRS;
    private ManageHotel HotelManager;
    private SimulateBooking BookHotel;
    private Scanner sc;
    private Interface layout;
    private ViewHotel hotelFinder;

    /**
     * Constructs a new Execute instance with the specified Hotels object.
     *
     * @param HRS the Hotels object containing the list of hotels
     */
    public Execute(Hotels HRS){
        this.HRS = HRS;
        this.HotelManager = new ManageHotel(HRS);
        this.BookHotel = new SimulateBooking(HRS);
        this.sc = new Scanner(System.in);
        this.layout = new Interface();
        this.hotelFinder = new ViewHotel(HRS);
    }

    /**
     * Clears the console screen.
     */
    public void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; ++i) 
                System.out.println();
        } 
    }

    /**
     * Prompts the user to press the Enter key to continue.
     */
    public void pressEnterToContinue()
    { 
           System.out.println("Press Enter key to continue...");
           try
           {
                System.in.read();
           }  
           catch(Exception e)
           {}  
    }

    /**
     * Manages the hotel-related operations, allowing the user to add rooms, change hotel names,
     * remove rooms, update prices, remove reservations, and remove hotels.
     */
    private void ManageHotel(){
        boolean run = true;
        int option = 0;
    
        do{
            layout.ManageHotel();
            System.out.print("\n          [Enter option]: ");
            try{
                option = sc.nextInt();
            } catch (Exception E){
                System.out.println("Invalid input");
                sc.nextLine();
                continue;
            }
            switch(option){
                case 1:
                    if (!HRS.HotelList().isEmpty()){
                        clearConsole();
                        HotelManager.AddRooms();
                        pressEnterToContinue();
                        clearConsole();
                    }else{ 
                        clearConsole();
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                        clearConsole();
                    }
                    break;
                case 2:
                    if (!HRS.HotelList().isEmpty()){
                        clearConsole();
                        HotelManager.changeHotelName();
                        pressEnterToContinue();
                        clearConsole();
                    }else{
                        clearConsole();
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                        clearConsole();
                    }
                    break;
                case 3:
                    if (!HRS.HotelList().isEmpty()){
                        clearConsole();
                        HotelManager.RemoveRooms();
                        pressEnterToContinue();
                        clearConsole();
                    }else{
                        clearConsole();
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                        clearConsole();
                    }
                    break;
                case 4:
                    if (!HRS.HotelList().isEmpty()){
                        clearConsole();
                        HotelManager.changeBasePrice();
                        pressEnterToContinue();
                        clearConsole();
                    }else{
                        clearConsole();
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                        clearConsole();
                    }
                    break;
                case 5:
                    if (!HRS.HotelList().isEmpty()){
                        clearConsole();
                        HotelManager.removeReservation();
                        pressEnterToContinue();
                        clearConsole();
                    }else{
                        clearConsole();
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                        clearConsole();
                    }
                    break;
                case 6:
                    if (!HRS.HotelList().isEmpty()){
                        clearConsole();
                        HotelManager.datePriceModifier();
                        pressEnterToContinue();
                        clearConsole();
                    }else{
                        clearConsole();
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                        clearConsole();
                    }
                    break;
                case 7:
                    if (!HRS.HotelList().isEmpty()){
                        clearConsole();
                        HotelManager.removeHotel();
                        pressEnterToContinue();
                        clearConsole();
                    }else{
                        clearConsole();
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                        clearConsole();
                    }
                    break;
                case 8:
                    clearConsole();
                    run = false;
                    clearConsole();
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter a valid option.");
                    pressEnterToContinue();
                    clearConsole();
                    break;
            }
        } while (run != false);
    }

    /**
     * Provides the user with options to view hotel information, including high-level information
     * and specific details about hotels, rooms, and reservations.
     */
    private void ViewHotel(){
        boolean run = true;
        int option = 0;
    
        do{
            layout.ViewHotel();
            System.out.print("\n          [Enter option]: ");
            try{
                option = sc.nextInt();
            } catch (Exception E){
                System.out.println("Invalid input");
                sc.nextLine();
                continue;
            }
            switch(option){
                case 1:
                    clearConsole();
                    hotelFinder.displayHighInformation();
                    break;
                case 2:
                    clearConsole();
                    SpecificInformation();
                    break;
                case 3: 
                    clearConsole();
                    run = false;
                    clearConsole();
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter a valid option.");
                    pressEnterToContinue();
                    clearConsole();
                    break;
            }
        } while (run != false);
    }

    /**
     * Provides the user with options to view specific information about hotels, rooms, and reservations.
     */
    private void SpecificInformation(){
        boolean run = true;
        int option = 0;
    
        do{
            layout.SpecificInformation();
            System.out.print("\n          [Enter option]: ");
            try{
                option = sc.nextInt();
            } catch (Exception E){
                System.out.println("Invalid input");
                sc.nextLine();
                continue;
            }
            switch(option){
                case 1:
                    clearConsole();
                    hotelFinder.displayHotelInformation();
                    break;
                case 2:
                    clearConsole();
                    hotelFinder.displayRoomInformation();
                    break;
                case 3:
                    clearConsole();
                    hotelFinder.displayReservationInformation();
                    break;
                case 4: 
                    clearConsole();
                    run = false;
                    clearConsole();
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter a valid option.");
                    pressEnterToContinue();
                    clearConsole();
                    break;
            }
        } while (run != false);
    }

    /**
     * Executes the main program loop, providing the user with the main menu and options
     * for creating hotels, managing hotels, simulating bookings, viewing hotels, and exiting the program.
     */
    public void execute(){
        boolean run = true;
        int option = 0;
        
        do{
            layout.MainMenu();
            System.out.print("\n          [Enter option]: ");
            try{
                option = sc.nextInt();
            } catch (Exception E){
                System.out.println("Invalid input");
                sc.nextLine();
                continue;
            }

            switch(option){
                case 1:
                    clearConsole();
                    HotelManager.createHotel();
                    pressEnterToContinue();
                    clearConsole();
                    break;
                case 2:
                    clearConsole();
                    ManageHotel();
                    break;
                case 3: 
                    clearConsole();
                    BookHotel.bookHotel();
                    break;
                case 4: 
                    clearConsole();
                    ViewHotel();
                    break;
                case 5: 
                    run = false;
                    clearConsole();
                    System.out.println("[Program End]");
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter a valid option.");
                    pressEnterToContinue();
                    clearConsole();
                    break;
            }
        } while (run != false);

        System.exit(0);
        
    }
}   