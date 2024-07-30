import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CreateHotelController manages the creation of hotels within the application.
 * It handles user input and interactions from the CreateHotelView and manages the HotelList.
 * The controller ensures the creation of valid hotels, including setting the number of rooms and
 * preventing duplicate hotel entries.
 */
public class CreateHotelController {
    private static final int MAX_ROOMS = 50;

    private Hotels HotelList;
    private MainView mainView;
    private CreateHotelView createView;
    private Hotel createdHotel;

    /**
     * Constructor to initialize the CreateHotelController with necessary views and models.
     *
     * @param HotelList the Hotels object containing the list of all hotels
     * @param mainView the main view of the application
     * @param createView the view for creating a new hotel
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

    /**
     * Customizes the hotel by setting up listeners for room type buttons (Basic, Deluxe, Executive).
     */
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

    /**
     * Initializes the hotel based on the input from the user.
     *
     * @return true if the hotel was successfully initialized; false otherwise
     */
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

    /**
     * Resets the hotel creation process, clearing any temporary data.
     */
    private void resetHotelCreation() {
        createdHotel = null;
        createView.resetRoomCounts();
    }

}