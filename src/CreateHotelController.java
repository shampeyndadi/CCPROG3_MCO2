import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents a collection of hotels with management functionalities.
 */
public class CreateHotelController {
    private ArrayList<Hotel> HotelList;
    private MainView mainView;
    private CreateHotelView createView;

    /**
     * Constructor to initialize the Hotels object.
     */
    public CreateHotelController(MainView mainView, CreateHotelView createView){
        this.HotelList = new ArrayList<>(); 
        this.mainView = mainView;
        this.createView = createView;

        this.createView.setAddBtn(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Button pressed");
                String hotelName = createView.getInput();
                int amountOfRooms = createView.getAmountOfRooms();

                if (!doesHotelExist(HotelList, hotelName)){
                    if (amountOfRooms <= 50){
                        HotelList.add(new Hotel(hotelName, amountOfRooms));
                        System.out.println("Hotel added");
                        createView.setStatusInput(hotelName + " created succesfully!");
                    }else{
                        System.out.println("Error rooms exceeded");
                        createView.setStatusInput("Error! Maximum of 50 rooms only");
                    }
                }else{
                    System.out.println("Error hotel already exists");
                    createView.setStatusInput("Error! hotel already exists");
                }
            }
        });

        this.createView.setCleartextfield(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Clear textfield button pressed");
                createView.clearInput();
            }
        });

        this.createView.setExitBtn(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Exit button pressed");
                createView.setVisible(false);
                mainView.setVisible(true);
            }  
        });

    }

     /**
     * Returns the list of hotels.
     * @return ArrayList of Hotel objects.
     */
    public ArrayList<Hotel> listOfHotels(){
        return HotelList;
    }

    /**
     * Creates a new hotel if it doesn't already exist.
     */
    public void createHotel(String Hotelname, int amountOfRooms){
        if (!doesHotelExist(HotelList, Hotelname)){
            HotelList.add(new Hotel(Hotelname, amountOfRooms));
            System.out.println("\n" + Hotelname + " created succesfully\n");
        }else{
            System.out.println("\nCreation unsuccessful, hotel already exists\n");
        }

    }

    /**
     * Displays the list of hotels.
     */
    public void DisplayHotels(){
        System.out.println("[LIST OF HOTELS]\n");
        for (int i = 0; i < listOfHotels().size(); i++){
            if (!(listOfHotels().get(i).viewRooms().isEmpty()))
                System.out.println(i + 1 + ". " + listOfHotels().get(i).getHotelName());
            else
                System.out.println(i + 1 + ". " + listOfHotels().get(i).getHotelName() + " [No rooms available yet]");
        }
    }

    /**
     * Checks if a hotel with the given name already exists.
     * @param hotels List of Hotel objects to search within.
     * @param name Name of the hotel to check.
     * @return true if the hotel exists, false otherwise.
     */
    public boolean doesHotelExist(ArrayList<Hotel> Hotel, String name){
        
        for (int i = 0; i < Hotel.size(); i++){
            if (Hotel.get(i).getHotelName().equals(name)){
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a room with the given name exists in the specified hotel.
     * @param hotel The Hotel object to check within.
     * @param roomName Name of the room to check.
     * @return true if the room exists in the hotel, false otherwise.
     */
    public boolean doesRoomExist(Hotel hotel, String roomName) {
        for (Room room : hotel.viewRooms()) {
            if (room.getRoomName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if there are any hotels with rooms available.
     * @param hotelLists The Hotels object containing the list of hotels.
     * @return true if there are hotels with rooms available, false otherwise.
     */
    public boolean checkHotels(Hotels hotelLists) {
        for (Hotel hotel : hotelLists.listOfHotels()) {
            if (!(hotel.viewRooms().isEmpty())) {
                return true; 
            }
        }
        return false;
    }

    /**
     * Checks if there are any reservations made in any hotels.
     * @param hotelLists The Hotels object containing the list of hotels.
     * @return true if there are reservations in any hotel, false otherwise.
     */
    public boolean checkReservation(Hotels hotelLists) {
        for (Hotel hotel : hotelLists.listOfHotels()) {
            if (!(hotel.viewReservations().isEmpty())) {
                return true; 
            }
        }
        return false;
    }

    /**
     * Retrieves a list of removable rooms from the chosen hotel that are not currently reserved.
     * @param chosenHotel The Hotel object from which removable rooms are to be retrieved.
     * @return ArrayList of Room objects that are removable (not currently reserved).
     */
    public ArrayList<Room> getRemovableRooms(Hotel chosenHotel) {
        ArrayList<Room> removableRooms = new ArrayList<>();
        ArrayList<Reservation> reservations = chosenHotel.viewReservations();
    
        for (Room room : chosenHotel.viewRooms()) {
            boolean hasReservation = false;
    
            for (Reservation reservation : reservations) {
                if (reservation.room().equals(room)) {
                    hasReservation = true;
                }
            }
    
            if (!hasReservation) {
                removableRooms.add(room);
            }
        }
    
        return removableRooms;
    }

}
