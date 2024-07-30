import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Inquire extends JPanel {

    private Hotel chosenHotel;
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox1;

    private JTabbedPane Infos;

    private JScrollPane reservationScroll;

    private JTextField checkInDay;
    private JTextField checkOutDay;

    private JButton submit;
    private JButton exit;
    private ArrayList<JButton> roomButtons;

    private JLabel roomInfo;
    private JLabel roomInfo1;

    private JPanel roomDetails;
    private JPanel roomInformationPanel;
    private JPanel reservationInfoPanel;

    private JTextArea confirmationMessage;

    public Inquire(Hotel chosenHotel) {

        super();
        setLayout(new BorderLayout());
        setSize(500, 500);

        this.roomButtons = new ArrayList<>();

        this.chosenHotel = chosenHotel;
        this.rooms = chosenHotel.viewRooms();
        this.reservations = chosenHotel.viewReservations();

        init();

        setBackground(Color.decode("#FFFFFF"));

    }

    private void init(){
        Infos = new JTabbedPane();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel topLabel = new JLabel();
        topLabel.setText("Inquire");
        topLabel.setFont(new Font("Times New Roman", Font.ITALIC, 32));
        topLabel.setForeground(Color.decode("#800080"));
        topPanel.add(topLabel);

        /*---------------------------------------------------------------------------------*/
        JPanel BasicInformation = new JPanel();
        BasicInformation.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel topLabels = new JLabel();
        topLabels.setText("Basic Information");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));
        BasicInformation.add(topLabels, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel hotelName = new JLabel();
        hotelName.setText("Hotel name: " + chosenHotel.getHotelName());
        hotelName.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        BasicInformation.add(hotelName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel numberOfRooms = new JLabel();
        numberOfRooms.setText("Number of rooms: " + chosenHotel.viewRooms().size());
        numberOfRooms.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        BasicInformation.add(numberOfRooms, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel monthlyEarnings = new JLabel();
        monthlyEarnings.setText("Monthly earnings: " + chosenHotel.getTotalEarnings());
        monthlyEarnings.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        BasicInformation.add(monthlyEarnings, gbc);
        /*---------------------------------------------------------------------------------*/
        JPanel RoomAvailability = new JPanel();

        RoomAvailability.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Available rooms");
        titleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        titleLabel.setForeground(Color.decode("#800080"));
        titlePanel.add(titleLabel);

        RoomAvailability.add(titlePanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbd = new GridBagConstraints();
        gbd.insets = new Insets(3, 3, 3, 3);
        gbd.fill = GridBagConstraints.HORIZONTAL;
        gbd.anchor = GridBagConstraints.NORTH;

        JLabel checkIn = new JLabel();
        checkIn.setText("Enter day of the month you wish to check in: ");
        checkIn.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        checkIn.setForeground(Color.decode("#800080"));
        gbd.gridx = 0;
        gbd.gridy = 0;
        centerPanel.add(checkIn, gbd);

        checkInDay = new JTextField();
        checkInDay.setPreferredSize(new Dimension(100, 20));
        checkInDay.setMinimumSize(new Dimension(100, 20));
        checkInDay.setMaximumSize(new Dimension(100, 20));
        gbd.gridx = 1;
        centerPanel.add(checkInDay, gbd);

        JLabel checkOut = new JLabel();
        checkOut.setText("Enter day of the month you wish to check out: ");
        checkOut.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        checkOut.setForeground(Color.decode("#800080"));
        gbd.gridx = 0;
        gbd.gridy = 1;
        centerPanel.add(checkOut, gbd);

        checkOutDay = new JTextField();
        checkOutDay.setPreferredSize(new Dimension(100, 20));
        checkOutDay.setMinimumSize(new Dimension(100, 20));
        checkOutDay.setMaximumSize(new Dimension(100, 20));
        gbd.gridx = 1;
        centerPanel.add(checkOutDay, gbd);

        submit = new JButton("Submit");
        submit.setFont(new Font("Times New Roman", Font.ITALIC, 10));
        submit.setForeground(Color.decode("#800080"));
        submit.setBackground(Color.decode("#FFFFFF"));
        gbd.gridx = 0;
        gbd.gridy = 2;
        gbd.gridwidth = 2;
        centerPanel.add(submit, gbd);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbi = new GridBagConstraints();
        gbi.insets = new Insets(3, 3, 3, 3);
        gbi.fill = GridBagConstraints.HORIZONTAL;
        gbi.anchor = GridBagConstraints.NORTHWEST;

        JLabel bookedRooms = new JLabel("Total Booked Rooms: ");
        gbi.gridx = 0;
        gbi.gridy = 0;
        infoPanel.add(bookedRooms, gbi);

        roomInfo = new JLabel("0");
        roomInfo.setPreferredSize(new Dimension(100, 20));
        gbi.gridx = 1;
        gbi.gridy = 0;
        infoPanel.add(roomInfo, gbi);

        JLabel availableRooms = new JLabel("Available rooms: ");
        gbi.gridx = 2;
        gbi.gridy = 0;
        infoPanel.add(availableRooms, gbi);

        roomInfo1 = new JLabel("0");
        roomInfo1.setPreferredSize(new Dimension(100, 20));
        gbi.gridx = 3;
        gbi.gridy = 0;
        infoPanel.add(roomInfo1, gbi);

        gbd.gridy = 3;
        gbd.gridwidth = 2;
        centerPanel.add(infoPanel, gbd);

        RoomAvailability.add(centerPanel, BorderLayout.CENTER);
        /*---------------------------------------------------------------------------------*/
        roomDetails = new JPanel();

        roomDetails.setLayout(new BorderLayout());

        JPanel roomDetailPanel = new JPanel();
        roomDetailPanel.setLayout(new GridLayout(2, 1, 3, 3));

        JLabel title3 = new JLabel();
        title3.setText("Room details");
        title3.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        title3.setForeground(Color.decode("#800080"));
        JPanel titlePanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel3.add(title3);

        roomDetailPanel.add(titlePanel3);

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        comboBox.setPreferredSize(new Dimension(150, 30));

        for (Room room : rooms){
            comboBox.addItem(room.getRoomName());
        }

        roomDetailPanel.add(comboBox);

        roomDetails.add(roomDetailPanel, BorderLayout.NORTH);

        roomInformationPanel = new JPanel();

        roomDetails.add(roomInformationPanel, BorderLayout.CENTER);
        /*---------------------------------------------------------------------------------*/
        JPanel reservationDetails = new JPanel();

        reservationDetails.setLayout(new BorderLayout());

        JPanel reservationDetailPanel = new JPanel();
        reservationDetailPanel.setLayout(new GridLayout(2, 1, 3, 3));

        JLabel title4 = new JLabel();
        title4.setText("Reservation details");
        title4.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        title4.setForeground(Color.decode("#800080"));
        JPanel titlePanel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel4.add(title4);

        reservationDetailPanel.add(titlePanel4);

        comboBox1 = new JComboBox<>();
        comboBox1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        comboBox1.setPreferredSize(new Dimension(150, 30));

        for (Reservation reservation : reservations){
            comboBox1.addItem(reservation.guestName());
        }

        reservationDetails.add(reservationDetailPanel, BorderLayout.NORTH);

        if (!chosenHotel.viewReservations().isEmpty()) {

            reservationDetailPanel.add(comboBox1);

            confirmationMessage = new JTextArea(5, 30);
            confirmationMessage.setFont(new Font("Times New Roman", Font.ITALIC, 17));
            confirmationMessage.setForeground(Color.decode("#800080"));
            confirmationMessage.setLineWrap(true);
            confirmationMessage.setWrapStyleWord(true);
            confirmationMessage.setEditable(false);

            reservationScroll = new JScrollPane(confirmationMessage);
            reservationScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            reservationScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            reservationScroll.setPreferredSize(new Dimension(400, 350));

            reservationDetails.add(reservationScroll, BorderLayout.CENTER);
        }else {

            JLabel infoLabel = new JLabel();
            infoLabel.setText(chosenHotel.getHotelName() + " currently has no reservations");
            infoLabel.setFont(new Font("Times New Roman", Font.ITALIC, 17));
            infoLabel.setForeground(Color.decode("#800080"));

            JPanel ReservationInfoPanel = new JPanel();
            ReservationInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            ReservationInfoPanel.add(infoLabel);

            reservationDetails.add(ReservationInfoPanel, BorderLayout.CENTER);
        }
        /*---------------------------------------------------------------------------------*/
        Infos.add("Basic Information", BasicInformation);
        Infos.add("Room Availability", RoomAvailability);
        Infos.add("Room Details", roomDetails);
        Infos.add("Reservation Details", reservationDetails);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel southPanel = new JPanel();

        exit = new JButton("Exit");
        exit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exit.setBorderPainted(false);
        exit.setBackground(Color.decode("#FFFFFF"));
        exit.setForeground(Color.decode("#800080"));
        southPanel.add(exit);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(Infos, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public void displayRoomInfo(Room room, ArrayList<Integer> availableDates){
        roomInformationPanel.removeAll();

        roomInformationPanel.setLayout(new BorderLayout());
        JLabel roomName = new JLabel();
        roomName.setText(room.getRoomName() + "'s available dates");
        roomName.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        roomName.setForeground(Color.decode("#800080"));
        JPanel roomNamePanel = new JPanel();
        roomNamePanel.add(roomName);

        roomInformationPanel.add(roomNamePanel, BorderLayout.NORTH);

        if (availableDates.isEmpty()){
            JLabel infoLabel = new JLabel();
            infoLabel.setText("Unfortunately " + room.getRoomName() + " is fully booked for the month");
            infoLabel.setFont(new Font("Times New Roman", Font.ITALIC, 17));
            infoLabel.setForeground(Color.decode("#800080"));
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            infoPanel.add(infoLabel);

            roomInformationPanel.add(infoPanel, BorderLayout.CENTER);
        }else{
            JPanel calendarPanel = new JPanel();
            calendarPanel.setLayout(new GridLayout(0, 7, 1, 3));

            for (int date : availableDates){
                JLabel day = new JLabel();
                day.setText(Integer.toString(date));
                calendarPanel.add(day);
            }

            roomInformationPanel.add(calendarPanel, BorderLayout.CENTER);
        }

        JLabel pricePerNight = new JLabel();
        pricePerNight.setText("Price per night " + room.getRoomPrice());
        pricePerNight.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        pricePerNight.setForeground(Color.decode("#800080"));

        JPanel pricePerNightPanel = new JPanel();
        pricePerNightPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pricePerNightPanel.add(pricePerNight);

        roomInformationPanel.add(pricePerNightPanel, BorderLayout.SOUTH);

        roomInformationPanel.revalidate();
        roomInformationPanel.repaint();
    }

    public void setConfirmationMessage(String input){
        confirmationMessage.setText(input);
    }

    public String getSelectedRoom(){
        return (String) comboBox.getSelectedItem();
    }

    public String getSelectedReservation(){
        return (String) comboBox1.getSelectedItem();
    }

    public void setRoomItems(ActionListener listener){
        comboBox.addActionListener(listener);
    }

    public void setReservationItems(ActionListener listener){
        comboBox1.addActionListener(listener);
    }

    public void setRoomInfo(String availableRooms, String totalBookedRooms){
        roomInfo.setText(totalBookedRooms);
        roomInfo1.setText(availableRooms);
    }

    public void clearTextFields(){
        checkInDay.setText("");
        checkOutDay.setText("");
    }

    public int getCheckIn(){
        return Integer.parseInt(checkInDay.getText());
    }

    public int getCheckOut(){
        return Integer.parseInt(checkOutDay.getText());
    }

    public void setSubmit(ActionListener listener){
        this.submit.addActionListener(listener);
    }

    public void setExit(ActionListener listener){
        this.exit.addActionListener(listener);
    }

}
