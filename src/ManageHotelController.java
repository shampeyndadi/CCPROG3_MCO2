import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Controller class for managing hotel-related functionalities in the application.
 */
public class ManageHotelController {

    private final MainView mainView;
    private ManageHotelView manageHotelView;

    /**
     * Constructs a ManageHotelController with the specified MainView, ManageHotelView, and Hotels.
     *
     * @param mainView        the main view of the application
     * @param manageHotelView the view for managing hotels
     * @param myHotels        the model containing the list of hotels
     */
    public ManageHotelController(MainView mainView, ManageHotelView manageHotelView, Hotels myHotels){
        this.mainView = mainView;
        this.manageHotelView = manageHotelView;

        this.manageHotelView.setHotelButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String hotelName = source.getText();
                for (Hotel hotel : myHotels.HotelList()) {
                    if (hotel.getHotelName().equals(hotelName)) {
                        CustomizeHotelView customHotel = new CustomizeHotelView(hotel);

                        initializeCustomHotelView(customHotel);
                        EditHotel(customHotel, myHotels, hotel);

                        mainView.setContentPane(customHotel);
                        mainView.revalidate();
                        mainView.repaint();
                    }
                }
            }
        });

        this.manageHotelView.setExitButton(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Exit button pressed");
                mainView.switchToMainPanel();
            }
        });
    }

    /**
     * Initializes the CustomizeHotelView with necessary action listeners.
     *
     * @param customizeHotelView the view for customizing a specific hotel
     */
    public void initializeCustomHotelView(CustomizeHotelView customizeHotelView){
        customizeHotelView.setExit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToMainPanel();
            }
        });
    }

    /**
     * Sets up the customization options for a specific hotel.
     *
     * @param customizeHotelView the view for customizing a specific hotel
     * @param myHotels           the model containing the list of hotels
     * @param hotel              the hotel to be customized
     */
    private void EditHotel(CustomizeHotelView customizeHotelView, Hotels myHotels, Hotel hotel){
        customizeHotelView.setAddRooms(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.addRoomsView();

                customizeHotelView.UpdateNumberOfBasicRooms(Integer.toString(hotel.getNumberOfBasicRooms()));
                customizeHotelView.UpdateNumberOfDeluxeRooms(Integer.toString(hotel.getNumberOfDeluxeRooms()));
                customizeHotelView.UpdateNumberOfExecutiveRooms(Integer.toString(hotel.getNumberOfExecutiveRooms()));

                addRooms(customizeHotelView, myHotels, hotel);
            }
        });

        customizeHotelView.setChangeHotelName(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.changeHotelName();
                customizeHotelView.setSubmitButton(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newName = customizeHotelView.getNewName();
                        if (!myHotels.doesHotelExist(newName) && !newName.isEmpty()){
                            hotel.changeHotelName(newName);
                            System.out.println("Hotel name changed to " + newName);
                            customizeHotelView.clearNewNameCollector();
                            JOptionPane.showMessageDialog(customizeHotelView, "Hotel successfully changed to " + newName);
                        }else if (newName.isEmpty()){
                            JOptionPane.showMessageDialog(customizeHotelView, "Please name your hotel");
                        }else{
                            System.out.println("Hotel already exists!");
                            JOptionPane.showMessageDialog(customizeHotelView, "Unsuccessful hotel already exists");
                        }
                    }
                });
            }
        });

        customizeHotelView.setRemovableRooms(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.removeRooms();
                customizeHotelView.setRooms(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton source = (JButton) e.getSource();
                        String roomName = source.getText();
                        for (Room room : hotel.getRemovableRooms()){
                            if (room.getRoomName().equals(roomName)){

                                int result = customizeHotelView.showConfirmationDialog("Are you sure you want to delete " + roomName + "?");

                                if (result == JOptionPane.YES_OPTION) {
                                    hotel.removeRoom(room);

                                    if (room.getRoomType().equals("Base")){
                                        hotel.removeBasic();
                                    }else if (room.getRoomType().equals("Deluxe")){
                                        hotel.removeDeluxe();
                                    }else{
                                        hotel.removeExecutive();
                                    }

                                    JOptionPane.showMessageDialog(customizeHotelView, roomName + " successfully removed");
                                    customizeHotelView.refreshRoomList(hotel.getRemovableRooms(), this);
                                }
                            }
                        }
                    }
                });
            }
        });

        customizeHotelView.setUpdateBasePrice(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.changeBasePrice();
                customizeHotelView.setSubmitButton(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double newPrice = 0;

                        try{
                            newPrice = customizeHotelView.getModifiedPrice();
                        }catch(Exception E){
                            JOptionPane.showMessageDialog(customizeHotelView,
                                    "Invalid input. Please enter a valid number for the price.",
                                    "Input Error", JOptionPane.ERROR_MESSAGE);
                            System.out.println("\nError: Invalid input for price. Please enter a valid number.\n");
                            customizeHotelView.clearModifyPrice();
                            return;
                        }

                        if (newPrice >= 100){
                            for (Room room : hotel.viewRooms()) {
                                room.updatePrice(newPrice);

                            }
                            for(Room room : hotel.viewRooms()) {
                                if (room instanceof  Deluxe){
                                    ((Deluxe) room).updatePrice();
                                }
                            }
                            for (Room room: hotel.viewRooms()) {
                                if(room instanceof Executive){
                                    ((Executive) room).updatePrice();
                                }
                            }

                            JOptionPane.showMessageDialog(customizeHotelView, "Base price successfully modified");
                            System.out.println("\nBase price updated successfully!\n");
                            customizeHotelView.clearModifyPrice();
                        }else{
                            JOptionPane.showMessageDialog(customizeHotelView, "Price is below standard price");
                            System.out.println("\nError! The new price must be >= 100.0!\n");
                            customizeHotelView.clearModifyPrice();
                        }
                    }
                });
            }
        });

        customizeHotelView.setRemoveReservation(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotel.viewReservations().isEmpty()){
                    JOptionPane.showMessageDialog(customizeHotelView, hotel.getHotelName() + " currently has no reservations");
                }else {
                    customizeHotelView.removeReservation();
                    removeReservation(customizeHotelView, myHotels, hotel);
                }
            }
        });

        customizeHotelView.setModifyPrice(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.chooseRoomToModify();
                modifyPrice(customizeHotelView, hotel);
            }
        });

        customizeHotelView.setRemoveHotel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = customizeHotelView.showConfirmationDialog("Are you sure you want to delete hotel?\n" +
                        "Number of reservations: " + hotel.viewReservations().size() +
                        "\nNumber of rooms: " + hotel.viewRooms().size());
                if (result == JOptionPane.YES_OPTION) {
                    myHotels.HotelList().remove(hotel);
                    JOptionPane.showMessageDialog(customizeHotelView, hotel.getHotelName() + " Successfully removed");
                    mainView.switchToMainPanel();
                }
            }
        });

    }

    /**
     * Modifies the price of a specific room.
     *
     * @param customizeHotelView the view for customizing a specific hotel
     * @param hotel              the hotel containing the room to modify
     */
    private void modifyPrice(CustomizeHotelView customizeHotelView, Hotel hotel){
        customizeHotelView.setModifiedRooms(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String roomName = source.getText();
                for (Room room : hotel.getRemovableRooms()){
                    if (room.getRoomName().equals(roomName)){
                        customizeHotelView.ModifyPrice();

                        customizeHotelView.setSubmitButton(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                double newPercentage = 0;
                                int date = 0;

                                try{
                                    newPercentage = customizeHotelView.getModifiedPercentage();
                                    date = customizeHotelView.getDate();
                                }catch (Exception E){
                                    JOptionPane.showMessageDialog(customizeHotelView, "Invalid input. Inputs should only be numerics", "Input Error", JOptionPane.ERROR_MESSAGE);
                                    customizeHotelView.clearTextfields();
                                    return;
                                }

                                if (date >= 1 && date <= 31) {
                                    room.setPriceModifier(newPercentage, date);
                                    JOptionPane.showMessageDialog(customizeHotelView, "Date price successfully modified for" + roomName);
                                    customizeHotelView.clearTextfields();
                                    customizeHotelView.chooseRoomToModify();
                                }else{
                                    JOptionPane.showMessageDialog(customizeHotelView, "Date price modification unsuccessful\nDate must be invalid");
                                    customizeHotelView.clearTextfields();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * Removes a reservation from a specific hotel.
     *
     * @param customizeHotelView the view for customizing a specific hotel
     * @param myHotels           the model containing the list of hotels
     * @param hotel              the hotel containing the reservation to remove
     */
    private void removeReservation(CustomizeHotelView customizeHotelView, Hotels myHotels, Hotel hotel){
        customizeHotelView.setReservations(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String customerName = source.getText();

                Reservation toBeRemoved = null;

                for (Reservation reservation : hotel.viewReservations()){
                    if (reservation.guestName().equals(customerName)){
                        toBeRemoved = reservation;
                        break;
                    }
                }

                if (toBeRemoved != null) {
                    int result = customizeHotelView.showConfirmationDialog("Are you sure you want to void the reservation?\nCheck-In: " +
                            toBeRemoved.checkInDate().getDayWithSuffix() + " of the month" +
                            "\nCheck-Out: " + toBeRemoved.checkOutDate().getDayWithSuffix() +
                            " of the month"
                            + "\nGuest name: "
                            + customerName);
                    if (result == JOptionPane.YES_OPTION) {
                        hotel.removeReservation(toBeRemoved);
                        JOptionPane.showMessageDialog(customizeHotelView, "Reservation for " + customerName + " successfully removed!");
                        customizeHotelView.refreshReservationList(hotel.viewReservations(), this);
                    }
                }
            }
        });
    }

    /**
     * Adds rooms to a specific hotel.
     *
     * @param customizeHotelView the view for customizing a specific hotel
     * @param myHotels           the model containing the list of hotels
     * @param hotel              the hotel to add rooms to
     */
    private void addRooms(CustomizeHotelView customizeHotelView, Hotels myHotels, Hotel hotel){
        customizeHotelView.setAddBasicButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotel.getTotalRooms() < 50) {
                    hotel.addBasicRoom();
                    customizeHotelView.UpdateNumberOfBasicRooms(Integer.toString(hotel.getNumberOfBasicRooms()));
                }
            }
        });

        customizeHotelView.setDeductBasic(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotel.getNumberOfBasicRooms() > hotel.getNumberOfCountedBasicRooms()) {
                    hotel.deductBasicRoom();
                    customizeHotelView.UpdateNumberOfBasicRooms(Integer.toString(hotel.getNumberOfBasicRooms()));
                }
            }
        });

        customizeHotelView.setAddDeluxeButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotel.getTotalRooms() < 50) {
                    hotel.addDeluxeRoom();
                    customizeHotelView.UpdateNumberOfDeluxeRooms(Integer.toString(hotel.getNumberOfDeluxeRooms()));
                }
            }
        });

        customizeHotelView.setDeductDeluxe(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotel.getNumberOfDeluxeRooms() > hotel.getNumberOfCountedDeluxeRooms()) {
                    hotel.deductDeluxeRoom();
                    customizeHotelView.UpdateNumberOfDeluxeRooms(Integer.toString(hotel.getNumberOfDeluxeRooms()));
                }
            }
        });

        customizeHotelView.setAddExecutiveButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotel.getTotalRooms() < 50) {
                    hotel.addExecutiveRoom();
                    customizeHotelView.UpdateNumberOfExecutiveRooms(Integer.toString(hotel.getNumberOfExecutiveRooms()));
                }
            }
        });

        customizeHotelView.setDeductExecutive(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotel.getNumberOfExecutiveRooms() > hotel.getNumberOfCountedExecutiveRooms()) {
                    hotel.deductExecutiveRoom();
                    customizeHotelView.UpdateNumberOfExecutiveRooms(Integer.toString(hotel.getNumberOfExecutiveRooms()));
                }
            }
        });

        customizeHotelView.setSubmitButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotel.getNumberOfBasicRooms() == hotel.getNumberOfCountedBasicRooms() && hotel.getNumberOfExecutiveRooms() == hotel.getNumberOfExecutiveRooms() && hotel.getNumberOfExecutiveRooms() == hotel.getNumberOfCountedExecutiveRooms()){
                    JOptionPane.showMessageDialog(customizeHotelView, "No changes made, please add rooms", "Input Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    hotel.InitializeRooms();
                    JOptionPane.showMessageDialog(customizeHotelView, "Rooms added successfully");
                }
            }
        });
    }



}
