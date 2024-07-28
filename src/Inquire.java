import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Inquire extends JPanel {

    private Hotel chosenHotel;
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    private JComboBox<String> comboBox;

    private JTabbedPane Infos;

    private JTextField checkInDay;
    private JTextField checkOutDay;

    private JButton submit;
    private JButton exit;
    private ArrayList<JButton> roomButtons;

    private JLabel roomInfo;
    private JLabel roomInfo1;
    private JLabel roomName;
    private JLabel topRoomLabel;

    private JPanel details;
    private JPanel roomDetails;

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
        roomDetailPanel.setLayout(new FlowLayout());

        JLabel title3 = new JLabel();
        title3.setText("Room details");
        title3.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        title3.setForeground(Color.decode("#800080"));

        roomDetailPanel.add(title3);

        roomDetails.add(roomDetailPanel, BorderLayout.NORTH);

        JPanel centerRoomPanel = new JPanel();
        centerRoomPanel.setLayout(new FlowLayout());

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        comboBox.setPreferredSize(new Dimension(150, 30));

        for (Room room : rooms){
            comboBox.addItem(room.getRoomName());
        }

        centerRoomPanel.add(comboBox);
        roomDetails.add(centerRoomPanel, BorderLayout.CENTER);

        /*---------------------------------------------------------------------------------*/

        JPanel reservationDetails = new JPanel();

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

    public String getSelectedRooms(){
        return (String) comboBox.getSelectedItem();
    }

    public void setRoomItems(ActionListener listener){
        comboBox.addActionListener(listener);
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
