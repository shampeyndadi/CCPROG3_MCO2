import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Inquire extends JPanel {
    private JTabbedPane Infos;
    private Hotel chosenHotel;
    private JTextField checkInDay;
    private JTextField checkOutDay;
    private JButton submit;
    private JButton exit;

    public Inquire(Hotel chosenHotel) {

        super();
        setLayout(new BorderLayout());
        setSize(500, 500);

        this.chosenHotel = chosenHotel;

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

        JPanel topCenterPanel = new JPanel();
        topCenterPanel.add(centerPanel);

        RoomAvailability.add(topCenterPanel, BorderLayout.CENTER);
        /*---------------------------------------------------------------------------------*/

        JPanel roomDetails = new JPanel();







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

    public void setExit(ActionListener listener){
        this.exit.addActionListener(listener);
    }

}
