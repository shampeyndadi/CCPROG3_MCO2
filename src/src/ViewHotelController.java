import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The ViewHotelController class is responsible for handling the actions and interactions in the View Hotel
 * view of the hotel management system. It connects the ViewHotelView and MainView with the hotel data.
 */
public class ViewHotelController{
    private ViewHotelView viewHotelView;
    private MainView mainView;

    /**
     * Constructs a ViewHotelController with the specified view and model components.
     *
     * @param viewHotelView The ViewHotelView object representing the view for displaying hotel information.
     * @param mainView      The MainView object representing the main view of the application.
     * @param myHotels      The Hotels object containing the list of hotels.
     */
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

    /**
     * Initializes the inquire view by setting up the exit button listener.
     *
     * @param inquire The Inquire object representing the view for detailed hotel inquiries.
     */
    private void initializeInquireView(Inquire inquire){
        inquire.setExit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToMainPanel();
            }
        });
    }

    /**
     * Displays high-level hotel information, such as the number of booked and available rooms, for a given hotel.
     *
     * @param inquire The Inquire object for displaying hotel information.
     * @param hotel   The Hotel object containing the hotel's data.
     */
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

    /**
     * Displays detailed room information, including available dates, for a selected room in the given hotel.
     *
     * @param inquire The Inquire object for displaying room information.
     * @param hotel   The Hotel object containing the hotel's data.
     */
    private void DisplayRoomInformation(Inquire inquire, Hotel hotel){
        inquire.setRoomItems(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomName = inquire.getSelectedRoom();

                for (Room room : hotel.viewRooms()){
                    if (room.getRoomName().equals(roomName)){
                        ArrayList<Reservation> reserve = hotel.viewReservations();
                        ArrayList<Integer> datesAvailable = room.getDatesAvailable(room, reserve);

                        inquire.displayRoomInfo(room, datesAvailable);
                    }
                }
            }
        });
    }

    /**
     * Displays detailed reservation information for a selected reservation in the given hotel.
     *
     * @param inquire The Inquire object for displaying reservation information.
     * @param hotel   The Hotel object containing the hotel's data.
     */
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
