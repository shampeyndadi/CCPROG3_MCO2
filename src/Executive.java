/**
 * Executive represents a type of room in a hotel, extending the base Room class.
 * This class sets the room type to "Executive" and applies a specific pricing formula.
 */
public class Executive extends Room{

    /**
     * Constructs a new Executive room with the specified room name.
     *
     * @param RoomName the name of the room
     */
    public Executive(String RoomName){
        super(RoomName);
        this.basePrice = super.getRoomPrice() * 1.35;
    }

    /**
     * Updates the base price of the Executive room.
     * The price is calculated as 135% of the base room price.
     */
    public void updatePrice() {
        this.basePrice = super.getRoomPrice() * 1.35;

    }

    /**
     * Returns the room type as "Executive".
     *
     * @return the room type as a String
     */
    public String getRoomType(){return "Executive";}
}