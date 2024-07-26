import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;

public class ManageHotelController {

    private MainView mainView;
    private ManageHotelView manageHotelView; 

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

                        System.out.println("You clicked on " + hotelName);

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

    public void initializeCustomHotelView(CustomizeHotelView customizeHotelView){
        customizeHotelView.setExit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToMainPanel();
            }
        });
    }

    private void EditHotel(CustomizeHotelView customizeHotelView, Hotels myHotels, Hotel hotel){
        customizeHotelView.setAddRooms(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.addRoomsView();
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
                        if (!myHotels.doesHotelExist(newName)){
                            hotel.changeHotelName(newName);
                            System.out.println("Hotel name changed to " + newName);
                            customizeHotelView.clearNewNameCollector();
                            JOptionPane.showMessageDialog(customizeHotelView, "Hotel successfully changed to " + newName);
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
                                hotel.removeRoom(room);
                                JOptionPane.showMessageDialog(customizeHotelView, roomName + " successfully removed");
                                customizeHotelView.refreshRoomList(hotel.getRemovableRooms(), this);
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
                        Double newPrice = customizeHotelView.getModifiedPrice();

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
                        }else{
                            JOptionPane.showMessageDialog(customizeHotelView, "Price is below standard price");
                            System.out.println("\nError! The new price must be >= 100.0!\n");
                        }
                    }
                });
            }
        });

        customizeHotelView.setRemoveReservation(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.removeReservation();
                removeReservation(customizeHotelView, myHotels, hotel);
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

    private void modifyPrice(CustomizeHotelView customizeHotelView, Hotel hotel){
        customizeHotelView.setModifiedRooms(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String roomName = source.getText();
                for (Room room : hotel.getRemovableRooms()){
                    if (room.getRoomName().equals(roomName)){
                        System.out.println("You selected " + roomName);
                        customizeHotelView.ModifyPrice(room);

                        customizeHotelView.setSubmitButton(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                double newPercentage = customizeHotelView.getModifiedPerecentage();
                                int date = customizeHotelView.getDate();

                                if (date >= 1 && date <= 31) {
                                    room.setPriceModifier(newPercentage, date);
                                    JOptionPane.showMessageDialog(customizeHotelView, "Date price successfully modified");
                                    customizeHotelView.clearTextfields();
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

    private void addRooms(CustomizeHotelView customizeHotelView, Hotels myHotels, Hotel hotel){
        customizeHotelView.setAddBasicButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        customizeHotelView.setDeductBasic(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        customizeHotelView.setAddDeluxeButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        customizeHotelView.setDeductDeluxe(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        customizeHotelView.setAddExecutiveButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        customizeHotelView.setDeductExecutive(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }



}
