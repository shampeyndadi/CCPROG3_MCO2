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

                        addRoomsView(customHotel);
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

    private void addRoomsView(CustomizeHotelView customizeHotelView){
        customizeHotelView.setAddRooms(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customizeHotelView.addRoomsView();
            }
        });
    }

}
