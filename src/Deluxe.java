/**
 * Deluxe represents a deluxe room in the hotel.
 * It extends the Room class and provides specific functionality for deluxe rooms,
 * such as calculating the base price with a specific markup.
 */
public class Deluxe extends Room{

    /**
     * Constructs a new Deluxe room with the specified room name.
     *
     * @param RoomName the name of the deluxe room
     */
    public Deluxe(String RoomName){
        super(RoomName);
        this.basePrice = super.getRoomPrice() * 1.2;
    }

    /**
     * Updates the base price of the deluxe room.
     * The base price is set to 120% of the base price defined in the Room class.
     */
    public void updatePrice() {
        this.basePrice = super.getRoomPrice() * 1.2;
    }

    /**
     * Returns the type of the room as a string.
     *
     * @return the room type, which is "Deluxe"
     */
    public String getRoomType(){
        return "Deluxe";
    }
}



