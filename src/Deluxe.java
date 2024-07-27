public class Deluxe extends Room{

    public Deluxe(String RoomName){
        super(RoomName);
        this.basePrice = super.getRoomPrice() * 1.2;
    }

    public void updatePrice() {
        this.basePrice = super.getRoomPrice() * 1.2;
    }

    public String getRoomType(){
        return "Deluxe";
    }
}



