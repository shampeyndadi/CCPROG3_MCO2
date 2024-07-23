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
    private Hotel createdHotel;

    /**
     * Constructor to initialize the Hotels object.
     */
    public CreateHotelController(Hotels HotelList, MainView mainView, CreateHotelView createView){
        this.HotelList = HotelList; 
        this.mainView = mainView;
        this.createView = createView;

        this.createView.setAddBtn(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (createdHotel != null) {
                    if (!HotelList.doesHotelExist(createdHotel.getHotelName())) {
                        if (createdHotel.getTotalRooms() > 0 && createdHotel.getTotalRooms() <= MAX_ROOMS) {
                            createdHotel.addRooms();
                            HotelList.createHotel(createdHotel);
                            System.out.println("Hotel added");
                            createView.setStatusInput(createdHotel.getHotelName() + " created succesfully!");
                        } else {
                            System.out.println("Error rooms exceeded");
                            createView.setStatusInput("Error! Invalid number of rooms");
                        }
                    } else {
                        System.out.println("Error hotel already exists");
                        createView.setStatusInput("Error! hotel already exists");
                    }
                }else{
                    createView.setStatusInput("Error! No hotel submitted.");
                }
            }
        });


        this.createView.setCleartextfield(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Clear textfield button pressed");
                createView.clearInput();
                resetHotelCreation();
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
                resetHotelCreation();

                mainView.switchToMainPanel();

            }  
        });

        customizeHotel();
    }

    private void customizeHotel(){
        this.createView.setAddBasicButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.addBasicRoom();
                    System.out.println("Basic room added");
                    createView.UpdateNumberOfBasicRooms(Integer.toString(createdHotel.getNumberOfBasicRooms()));
                }
            }
        });

        this.createView.setAddDeluxeButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.addDeluxeRoom();
                    System.out.println("Deluxe room added");
                    createView.UpdateNumberOfDeluxeRooms(Integer.toString(createdHotel.getNumberOfDeluxeRooms()));
                }
            }
        });

        this.createView.setAddExecutiveButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.addExecutiveRoom();
                    System.out.println("Executive room added");
                    createView.UpdateNumberOfExecutiveRooms(Integer.toString(createdHotel.getNumberOfExecutiveRooms()));
                }
            }
        });

        this.createView.setDeductBasic(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.deductBasicRoom();
                    System.out.println("Basic room deducted");
                    createView.UpdateNumberOfBasicRooms(Integer.toString(createdHotel.getNumberOfBasicRooms()));
                }
            }
        });

        this.createView.setDeductDeluxe(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.deductDeluxeRoom();
                    System.out.println("Deluxe room deducted");
                    createView.UpdateNumberOfDeluxeRooms(Integer.toString(createdHotel.getNumberOfDeluxeRooms()));
                }
            }
        });

        this.createView.setDeductExecutive(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.deductExecutiveRoom();
                    System.out.println("Executive room deducted");
                    createView.UpdateNumberOfExecutiveRooms(Integer.toString(createdHotel.getNumberOfExecutiveRooms()));
                }
            }
        });
    }

    private boolean initializeHotel() {
        if (createdHotel == null) {
            String hotelName = createView.getInput();
            if (hotelName != null && !hotelName.trim().isEmpty()) {
                createdHotel = new Hotel(hotelName);
                return true;
            } else {
                createView.setStatusInput("Please enter a hotel name.");
                return false;
            }
        }

        return true;
    }

    private void resetHotelCreation() {
        createdHotel = null;
        createView.resetRoomCounts();
    }

}