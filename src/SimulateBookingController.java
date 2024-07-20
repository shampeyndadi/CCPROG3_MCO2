import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class SimulateBookingController {

    private SimulateBookingView simulateBookingView;
    private BookHotelView bookHotelView;
    private MainView mainView;
    private Hotels myHotels;
   

    public SimulateBookingController(SimulateBookingView simulateBookingView, MainView mainView, Hotels myHotels){

        this.simulateBookingView = simulateBookingView;
        this.mainView = mainView;
        
        this.simulateBookingView.setActionHotelButtons(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String hotelName = source.getText();
                for (Hotel hotel : myHotels.HotelList()) {
                    if (hotel.getHotelName().equals(hotelName)) {
                        bookHotelView = new BookHotelView(hotel);
                        simulateBookingView.setVisible(false);
                        bookHotelView.setVisible(true);
                        System.out.println("You clicked on " + hotelName);

                        bookHotelView.setSubmitButton(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Date checkInDay = new Date(bookHotelView.getCheckInDay());
                                Date checkOutDay = new Date(bookHotelView.getCheckOutDay());

                                if (checkInDay.after(checkOutDay) || checkInDay.equals(checkOutDay)){
                                    System.out.println("Invalid date");
                                    bookHotelView.setStatusInput("Check-out day must be after check-in day. Please rebook again");
                                }else{
                                    bookHotelView.setStatusInput("");
                                    System.out.println("Select rooms");
                                    bookHotelView.addRoomButtons();
                                    bookRoom(hotel);
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

    private void bookRoom(Hotel chosenHotel){
        this.bookHotelView.setRoomActions(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String roomName = source.getText();
                for (Room room : chosenHotel.viewRooms()){
                    if (room.getRoomName().equals(roomName)){
                        System.out.println(roomName);
                    }
                }
            }
        });
    }

}
