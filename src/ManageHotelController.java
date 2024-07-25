import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                        EditHotel(customHotel, hotel);

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

    private void EditHotel(CustomizeHotelView customizeHotelView, Hotel hotel){
        customizeHotelView.setAddRooms(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.addRoomsView();
            }
        });

        customizeHotelView.setChangeHotelName(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.changeHotelName();
            }
        });

        customizeHotelView.setRemovableRooms(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.removeRooms();
            }
        });

        customizeHotelView.setUpdateBasePrice(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.changeBasePrice();
            }
        });

        customizeHotelView.setRemoveReservation(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.removeReservation();
            }
        });

        customizeHotelView.setModifyPrice(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.chooseRoomToModify();
                modifyPrice(customizeHotelView, hotel);
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
                    }
                }
            }
        });
    }


}
