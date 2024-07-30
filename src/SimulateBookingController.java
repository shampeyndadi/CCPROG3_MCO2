import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

                        mainView.setContentPane(bookHotelView);
                        mainView.revalidate();
                        mainView.repaint();

                        System.out.println("You clicked on " + hotelName);
                        initializeBookHotelView();
                        startBooking(hotel);
                    }
                }
            }
        });

        this.simulateBookingView.setExitBtn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainView.switchToMainPanel();
            }
              
        });

    }

    private void initializeBookHotelView() {
        bookHotelView.setCancelBooking(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(bookHotelView,
                        "Do you really want to cancel the booking?",
                        "Cancel Booking", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    mainView.switchToMainPanel();
                }
            }
        });

        bookHotelView.setGoBackToMenuButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToMainPanel();
            }
        });
    }

    private void startBooking(Hotel hotel){
        this.bookHotelView.setSubmitButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int checkIn = 0;
                int checkOut = 0;

                try{
                    checkIn = bookHotelView.getCheckInDay();
                    checkOut = bookHotelView.getCheckOutDay();
                }catch (Exception E){
                    JOptionPane.showMessageDialog(simulateBookingView,
                            "Invalid input. Please input a valid day for input",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    bookHotelView.resetTextFields();
                    return;
                }

                Date checkInDay = new Date(checkIn);
                Date checkOutDay = new Date(checkOut);

                if ((checkInDay.getDay() >= 1 && checkInDay.getDay() <= 31) && (checkOutDay.getDay() >= 1 && checkOutDay.getDay() <= 31)) {

                    if (checkInDay.after(checkOutDay) || checkInDay.equals(checkOutDay)) {
                        System.out.println("Invalid date");
                        bookHotelView.setStatusInput("Check-out day must be after check-in day. Please rebook again");
                        bookHotelView.removeButtons();
                    } else {
                        bookHotelView.setStatusInput("");
                        System.out.println("Select rooms");

                        for (Room room : hotel.viewRooms()) {
                            System.out.println(room.getRoomName());
                        }

                        bookHotelView.addRoomButtons();
                        bookRoom(hotel, checkInDay, checkOutDay);
                    }
                }else{
                    System.out.println("Invalid date");
                    bookHotelView.setStatusInput("Invalid day");
                    bookHotelView.removeButtons();
                    bookHotelView.resetTextFields();
                }
            }
        });

    }

    private void bookRoom(Hotel chosenHotel, Date checkIn, Date checkOut){
        this.bookHotelView.setRoomActions(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String roomName = source.getText();
                for (Room room : chosenHotel.viewRooms()){
                    if (room.getRoomName().equals(roomName)){
                        System.out.println("You selected " + roomName);
                        bookHotelView.userPanel();
                        setInvoice(chosenHotel, checkIn, checkOut, room);
                    }
                }
            }
        });
    }

    private void setInvoice(Hotel chosenHotel, Date checkIn, Date checkOut, Room room){
        this.bookHotelView.setSubmitButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = bookHotelView.getName();

                if (customerName != null && !customerName.trim().isEmpty()) {
                    int result = bookHotelView.showConfirmationDialog("Do you want to confirm the reservation?\nCheck-In: " +
                            checkIn.getDayWithSuffix() + " of the month" + "\nCheck-Out: " + checkOut.getDayWithSuffix() + " of the month" + "\nRoom: " + room.getRoomName());
                    if (result == JOptionPane.YES_OPTION) {
                        Reserved(customerName, chosenHotel, checkIn, checkOut, room);
                    }
                }else{
                    System.out.println("No customer name provided");
                    bookHotelView.setStatusInput("Enter a customer name first");
                }
            }
        });
    }

    private void Reserved(String customerName, Hotel chosenHotel, Date checkIn, Date checkOut, Room room){
        Reservation reserved = new Reservation(customerName, checkIn, checkOut, room);

        String discountCode = bookHotelView.getDiscountCode();

        if (discountCode != null && !discountCode.trim().isEmpty()){
            String discountMessage  = reserved.applyDiscount(discountCode);
            int result = JOptionPane.showConfirmDialog(bookHotelView,
                    discountMessage,
                    "ALERT", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                chosenHotel.viewReservations().add(reserved);
            }
        }else{
            chosenHotel.viewReservations().add(reserved);
        }


        System.out.println("Hotel Reserved");

        System.out.println();
        System.out.println(reserved.displayConfirmation(reserved));

        bookHotelView.setConfirmationMessage(reserved.displayConfirmation(reserved));
        bookHotelView.confirmationView();
        bookHotelView.showGoBackToMenuButton();

    }
}