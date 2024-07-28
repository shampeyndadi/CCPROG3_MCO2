public class Executive extends Room{
    public Executive(String RoomName){
        super(RoomName);
        this.basePrice = super.getRoomPrice() * 1.35;
    }

    public void updatePrice() {
        this.basePrice = super.getRoomPrice() * 1.35;

    }

    public String getRoomType(){return "Executive";}
}