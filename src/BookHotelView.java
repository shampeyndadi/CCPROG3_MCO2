import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class BookHotelView extends JFrame{

    private JTextField checkInDay;
    private JTextField checkOutDay;
    private JLabel statusLabel;
    private JButton submit;

    private JPanel roomPanels;
    private Hotel chosenHotel;
    private ArrayList<JButton> roomButtons;

    public BookHotelView(Hotel chosenHotel){
        super("Hotel Reservation System");

        setSize(500, 500);

        this.chosenHotel = chosenHotel;
        this.roomButtons = new ArrayList<>();

        setVisible(false);

        setBackground(Color.decode("#FFFFF"));

        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    private void init(){
        JPanel topPanel = new JPanel();
        JLabel topLabel = new JLabel();

        topPanel.setLayout(new FlowLayout());
        topLabel.setText("Simulate Booking");
        topLabel.setFont(new Font("Times New Roman", Font.ITALIC, 32));
        topLabel.setForeground(Color.decode("#800080"));

        topPanel.add(topLabel);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        JPanel southPanel = new JPanel();

        JPanel centerPanel = new JPanel();

        JPanel topCenterPanel = new JPanel();

        topCenterPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel checkIn = new JLabel();
        checkIn.setText("Enter day of the month you wish to check in: ");
        checkIn.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        checkIn.setForeground(Color.decode("#800080"));
        checkInDay = new JTextField();
        checkInDay.setPreferredSize(new Dimension(100, 20));
        checkInDay.setMinimumSize(new Dimension(100, 20));
        checkInDay.setMaximumSize(new Dimension(100, 20));

        JLabel checkOut = new JLabel();
        checkOut.setText("Enter day of the month you wish to check out: ");
        checkOut.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        checkOut.setForeground(Color.decode("#800080"));
        checkOutDay = new JTextField();
        checkOutDay.setPreferredSize(new Dimension(100, 20));
        checkOutDay.setMinimumSize(new Dimension(100, 20));
        checkOutDay.setMaximumSize(new Dimension(100, 20));

        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        statusLabel.setForeground(Color.decode("#800080"));

        submit = new JButton("Submit");
        submit.setFont(new Font("Times New Roman", Font.ITALIC, 10));
        submit.setForeground(Color.decode("#800080"));
        submit.setBackground(Color.decode("#FFFFFF"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        topCenterPanel.add(checkIn, gbc);

        gbc.gridx = 1;
        topCenterPanel.add(checkInDay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        topCenterPanel.add(checkOut, gbc);

        gbc.gridx = 1;
        topCenterPanel.add(checkOutDay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        topCenterPanel.add(submit, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        topCenterPanel.add(statusLabel, gbc);

        roomPanels = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(topCenterPanel, BorderLayout.NORTH);
        centerPanel.add(roomPanels, BorderLayout.CENTER);

        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);

        topPanel.setBackground(Color.WHITE);
        centerPanel.setBackground(Color.WHITE);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    }

    public void setStatusInput(String input){
        statusLabel.setText(input);
    }

    public int getCheckInDay(){
        return Integer.parseInt(checkInDay.getText());
    }

    public int getCheckOutDay(){
        return Integer.parseInt(checkOutDay.getText());
    }

    public void setSubmitButton(ActionListener listener){
        submit.addActionListener(listener);
    }

    public void addRoomButtons() {

        roomPanels.removeAll();
        roomPanels.setLayout(new FlowLayout());

        for (Room room : chosenHotel.viewRooms()) {
            JButton button = new JButton(room.getRoomName());
            button.setFont(new Font("Times New Roman", Font.BOLD, 15));
            button.setBackground(Color.decode("#FFFFFF"));
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(96, 48));
            button.setForeground(Color.decode("#800080"));

            roomPanels.add(button);
            roomButtons.add(button);
        }

        roomPanels.revalidate();
        roomPanels.repaint();
    }

    public void setRoomActions(ActionListener listener){
        for (int i = 0; i < roomButtons.size(); i++){
            roomButtons.get(i).addActionListener(listener);
        }
    }

    public ArrayList<JButton> getRoomButtons(){
        return roomButtons;
    }
}