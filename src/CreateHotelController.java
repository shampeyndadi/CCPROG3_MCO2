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
                            createdHotel.InitializeRooms();
                            HotelList.createHotel(createdHotel);
                            createView.setStatusInput(createdHotel.getHotelName() + " created succesfully!");
                            resetHotelCreation();
                            createView.clearInput();

                        } else {
                            createView.setStatusInput("Error! Invalid number of rooms");
                        }
                    } else {
                        createView.setStatusInput("Error! hotel already exists");
                        resetHotelCreation();
                        createView.clearInput();
                    }
                }else{
                    createView.setStatusInput("Error! Please create a valid hotel first.");
                }
            }
        });


        this.createView.setCleartextfield(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                createView.clearInput();
                resetHotelCreation();
                createView.clearInput();
            }
        });

        this.createView.setExitBtn(new ActionListener() {
            public void actionPerformed(ActionEvent e){
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
                    createView.UpdateNumberOfBasicRooms(Integer.toString(createdHotel.getNumberOfBasicRooms()));
                }
            }
        });

        this.createView.setAddDeluxeButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.addDeluxeRoom();
                    createView.UpdateNumberOfDeluxeRooms(Integer.toString(createdHotel.getNumberOfDeluxeRooms()));
                }
            }
        });

        this.createView.setAddExecutiveButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.addExecutiveRoom();
                    createView.UpdateNumberOfExecutiveRooms(Integer.toString(createdHotel.getNumberOfExecutiveRooms()));
                }
            }
        });

        this.createView.setDeductBasic(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.deductBasicRoom();
                    createView.UpdateNumberOfBasicRooms(Integer.toString(createdHotel.getNumberOfBasicRooms()));
                }
            }
        });

        this.createView.setDeductDeluxe(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.deductDeluxeRoom();
                    createView.UpdateNumberOfDeluxeRooms(Integer.toString(createdHotel.getNumberOfDeluxeRooms()));
                }
            }
        });

        this.createView.setDeductExecutive(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initializeHotel()) {
                    createdHotel.deductExecutiveRoom();
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