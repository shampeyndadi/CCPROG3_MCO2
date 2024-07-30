import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewHotelController{
    private ViewHotelView viewHotelView;
    private MainView mainView;

    public ViewHotelController(ViewHotelView viewHotelView, MainView mainView, Hotels myHotels){
        this.viewHotelView = viewHotelView;
        this.mainView = mainView;

        viewHotelView.setHotelButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String hotelName = source.getText();
                for (Hotel hotel : myHotels.HotelList()) {
                    if (hotel.getHotelName().equals(hotelName)) {
                        Inquire inquire = new Inquire(hotel);

                        initializeInquireView(inquire);
                        DisplayHotelInformation(inquire, hotel);
                        DisplayRoomInformation(inquire, hotel);
                        DisplayReservationInformation(inquire, hotel);

                        mainView.switchContentPane(inquire);

                        mainView.revalidate();
                        mainView.repaint();
                    }
                }
            }
        });

        viewHotelView.addExitViewListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToMainPanel();
            }
        });
    }

    private void initializeInquireView(Inquire inquire){
        inquire.setExit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToMainPanel();
            }
        });
    }

    private void DisplayHotelInformation(Inquire inquire, Hotel hotel){

        inquire.setSubmit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int checkInDay = 0;
                int checkOutDay = 0;

                try{
                    checkInDay = inquire.getCheckIn();
                    checkOutDay = inquire.getCheckOut();
                }catch(Exception E){
                    JOptionPane.showMessageDialog(inquire,
                            "Invalid input. Please input a valid day for input",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    inquire.clearTextFields();
                    return;
                }

                Date checkIn = new Date(checkInDay);
                Date checkOut = new Date(checkOutDay);

                if ((checkIn.getDay() >= 1 && checkIn.getDay() <= 31) && (checkOut.getDay() >= 1 && checkOut.getDay() <= 31)){
                    int bookedRooms = hotel.roomsOnDate(checkIn, checkOut);
                    int availableRooms = hotel.viewRooms().size() - bookedRooms;

                    inquire.setRoomInfo(Integer.toString(availableRooms), Integer.toString(bookedRooms));

                    inquire.clearTextFields();
                }
            }
        });

    }

    private void DisplayRoomInformation(Inquire inquire, Hotel hotel){
        inquire.setRoomItems(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomName = inquire.getSelectedRoom();

                for (Room room : hotel.viewRooms()){
                    if (room.getRoomName().equals(roomName)){
                        ArrayList<Reservation> reserve = hotel.viewReservations();
                        ArrayList<Integer> datesAvailable = room.getDatesAvailable(room, reserve);

                        System.out.println(room.getRoomName());

                        inquire.displayRoomInfo(room, datesAvailable);
                    }
                }
            }
        });
    }

    public void DisplayReservationInformation(Inquire inquire, Hotel hotel){
        inquire.setReservationItems(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guestName = inquire.getSelectedReservation();

                for (Reservation reservation : hotel.viewReservations()){
                    if (reservation.guestName().equals(guestName)){
                        inquire.setConfirmationMessage(reservation.displayConfirmation(reservation));
                    }
                }
            }
        });
    }
}
