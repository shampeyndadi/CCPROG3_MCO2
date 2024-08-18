import java.util.ArrayList;

/**
 * Represents a collection of hotels with management functionalities.
 */
public class Hotels {
    private ArrayList<Hotel> HotelList;

    /**
     * Constructor to initialize the Hotels object.
     */
    public Hotels(){
        this.HotelList = new ArrayList<>(); 
    }

     /**
     * Returns the list of hotels.
     * @return ArrayList of Hotel objects.
     */
    public ArrayList<Hotel> HotelList(){
        return HotelList;
    }

    public void createHotel(Hotel hotel){
        HotelList.add(hotel);
    }

    /**
     * Displays the list of hotels.
     */
    public void DisplayHotels(){
        System.out.println("[LIST OF HOTELS]\n");
        for (int i = 0; i < HotelList().size(); i++){
            if (!(HotelList().get(i).viewRooms().isEmpty()))
                System.out.println(i + 1 + ". " + HotelList().get(i).getHotelName());
            else
                System.out.println(i + 1 + ". " + HotelList().get(i).getHotelName() + " [No rooms available yet]");
        }
    }

    /**
     * Checks if a hotel with the given name already exists.
     * @param name Name of the hotel to check.
     * @return true if the hotel exists, false otherwise.
     */
    public boolean doesHotelExist(String name){
        
        for (int i = 0; i < HotelList.size(); i++){
            if (HotelList.get(i).getHotelName().equals(name)){
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if there are any hotels with rooms available.
     * @return true if there are hotels with rooms available, false otherwise.
     */
    public boolean checkHotels() {
        for (Hotel hotel : HotelList()) {
            if (!(hotel.viewRooms().isEmpty())) {
                return true; 
            }
        }
        return false;
    }

    /**
     * Checks if there are any reservations made in any hotels.
     * @return true if there are reservations in any hotel, false otherwise.
     */
    public boolean checkReservation() {
        for (Hotel hotel : HotelList()) {
            if (!(hotel.viewReservations().isEmpty())) {
                return true; 
            }
        }
        return false;
    }

}