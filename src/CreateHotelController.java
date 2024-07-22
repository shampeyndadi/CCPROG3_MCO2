import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a collection of hotels with management functionalities.
 */
public class CreateHotelController {
    private static final int MAX_ROOMS = 50;

    private Hotels HotelList;
    private MainView mainView;
    private CreateHotelView createView;

    /**
     * Constructor to initialize the Hotels object.
     */
    public CreateHotelController(Hotels HotelList, MainView mainView, CreateHotelView createView){
        this.HotelList = HotelList; 
        this.mainView = mainView;
        this.createView = createView;

        this.createView.setAddBtn(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String hotelName = createView.getInput();
                int amountOfRooms = createView.getAmountOfRooms();

                if (!HotelList.doesHotelExist(hotelName)){
                    if (amountOfRooms > 0 && amountOfRooms <= 50){
                        Hotel createdHotel = new Hotel(hotelName, amountOfRooms);
                        HotelList.createHotel(createdHotel);;
                        System.out.println("Hotel added");
                        createView.setStatusInput(hotelName + " created succesfully!");
                    }else{
                        System.out.println("Error rooms exceeded");
                        createView.setStatusInput("Error! Invalid number of rooms");
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
                for (Hotel hotel : HotelList.HotelList()){
                    System.out.println(hotel.getHotelName());
                }

                createView.setStatusInput("");
                createView.clearInput();

                mainView.switchToMainPanel();

            }  
        });

    }

}