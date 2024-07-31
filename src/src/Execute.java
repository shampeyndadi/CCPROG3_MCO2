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
                        HotelManager.AddRooms();
                        pressEnterToContinue();
                    }else{
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                    }
                    break;
                case 2:
                    if (!HRS.HotelList().isEmpty()){
                        HotelManager.changeHotelName();
                        pressEnterToContinue();
                    }else{
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                    }
                    break;
                case 3:
                    if (!HRS.HotelList().isEmpty()){
                        HotelManager.RemoveRooms();
                        pressEnterToContinue();
                    }else{
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                    }
                    break;
                case 4:
                    if (!HRS.HotelList().isEmpty()){
                        HotelManager.changeBasePrice();
                        pressEnterToContinue();
                    }else{
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                    }
                    break;
                case 5:
                    if (!HRS.HotelList().isEmpty()){
                        HotelManager.removeReservation();
                        pressEnterToContinue();
                    }else{
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                    }
                    break;
                case 6:
                    if (!HRS.HotelList().isEmpty()){
                        HotelManager.datePriceModifier();
                        pressEnterToContinue();
                    }else{
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                    }
                    break;
                case 7:
                    if (!HRS.HotelList().isEmpty()){
                        HotelManager.removeHotel();
                        pressEnterToContinue();
                    }else{
                        System.out.println("\nNo hotels yet! Hotels are yet to be built");
                        pressEnterToContinue();
                    }
                    break;
                case 8:
                    run = false;
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter a valid option.");
                    pressEnterToContinue();
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
                    hotelFinder.displayHighInformation();
                    break;
                case 2:
                    SpecificInformation();
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter a valid option.");
                    pressEnterToContinue();
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
                    hotelFinder.displayHotelInformation();
                    break;
                case 2:
                    hotelFinder.displayRoomInformation();
                    break;
                case 3:
                    hotelFinder.displayReservationInformation();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter a valid option.");
                    pressEnterToContinue();
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
                    HotelManager.createHotel();
                    pressEnterToContinue();
                    break;
                case 2:
                    ManageHotel();
                    break;
                case 3:
                    BookHotel.bookHotel();
                    break;
                case 4:
                    ViewHotel();
                    break;
                case 5: 
                    run = false;
                    System.out.println("[Program End]");
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter a valid option.");
                    pressEnterToContinue();
                    break;
            }
        } while (run != false);

        System.exit(0);
        
    }
}   