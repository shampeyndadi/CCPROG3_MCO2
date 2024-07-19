import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;


public class SimulateBookingController {

    private SimulateBookingView simulateBookingView;
    private BookHotelView bookHotelView;
    private MainView mainView;
    private Hotels myHotels;
   

    public SimulateBookingController(SimulateBookingView simulateBookingView, MainView mainView, Hotels myHotels){

        this.simulateBookingView = simulateBookingView;
        this.mainView = mainView;
        this.bookHotelView = new BookHotelView();
        
        this.simulateBookingView.setActionHotelButtons(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String hotelName = source.getText();
                for (Hotel hotel : myHotels.HotelList()) {
                    if (hotel.getHotelName().equals(hotelName)) {
                        simulateBookingView.setVisible(false);
                        bookHotelView.setVisible(true);
                        System.out.println("You clicked on " + hotelName);

                        bookHotelView.setSubmitButton(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Date checkInDay = new Date(bookHotelView.getCheckInDay());
                                Date checkOutDay = new Date(bookHotelView.getCheckOutDay());

                                if (checkInDay.after(checkOutDay) || checkInDay.equals(checkOutDay)){
                                    bookHotelView.setStatusInput("Error: Check-out day must be after check-in day. Please rebook again");
                                }
                            }
                        });
                    }
                }
            }
        });

        this.simulateBookingView.setExitBtn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateBookingView.setVisible(false);
                mainView.setVisible(true);
            }
              
        });

    }

    /*private void bookHotel(Hotel hotel, Date checkInDay, Date checkOutDay){
        ArrayList<Room> availableRooms = hotel.roomAvailable(hotel.viewRooms(), hotel.viewReservations(), checkOutDay, checkInDay);


    }*/



    
}
