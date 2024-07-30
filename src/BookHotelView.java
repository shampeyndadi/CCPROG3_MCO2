import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class BookHotelView extends JPanel{

    private JTextField checkInDay;
    private JTextField checkOutDay;
    private JTextField customerName;
    private JTextField discountCode;

    private JTextArea confirmationMessage;

    private JLabel statusLabel;

    private JButton submit;
    private JButton exit;
    private JButton back;

    private JPanel roomPanels;
    private JPanel topCenterPanel;

    private JScrollPane roomScroll;
    private JScrollPane invoiceScroll;

    private Hotel chosenHotel;
    private ArrayList<JButton> roomButtons;


    public BookHotelView(Hotel chosenHotel){
        super();

        setSize(500, 500);

        this.chosenHotel = chosenHotel;
        this.roomButtons = new ArrayList<>();

        setBackground(Color.decode("#FFFFF"));

        setLayout(new BorderLayout());

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

        topCenterPanel = new JPanel();
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
        roomPanels.setLayout(new GridLayout(0, 3, 10, 10));
        roomScroll = new JScrollPane(roomPanels);
        roomScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        roomScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        roomScroll.setPreferredSize(new Dimension(400, 200));

        centerPanel.add(topCenterPanel, BorderLayout.NORTH);
        centerPanel.add(roomScroll, BorderLayout.CENTER);

        exit = new JButton("Exit");
        exit.setFont(new Font("Times New Roman", Font.ITALIC, 10));
        exit.setForeground(Color.decode("#800080"));
        exit.setBackground(Color.decode("#FFFFFF"));

        southPanel.add(exit);

        back = new JButton("Go Back to Menu");
        back.setFont(new Font("Times New Roman", Font.ITALIC, 10));
        back.setForeground(Color.decode("#800080"));
        back.setBackground(Color.decode("#FFFFFF"));
        back.setVisible(false);  // Initially hidden
        southPanel.add(back);

        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);

        topPanel.setBackground(Color.WHITE);
        centerPanel.setBackground(Color.WHITE);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        confirmationMessage = new JTextArea(5, 30);
        confirmationMessage.setFont(new Font("Times New Roman", Font.ITALIC, 17));
        confirmationMessage.setForeground(Color.decode("#800080"));
        confirmationMessage.setLineWrap(true);
        confirmationMessage.setWrapStyleWord(true);
        confirmationMessage.setEditable(false);

        invoiceScroll = new JScrollPane(confirmationMessage);
        invoiceScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        invoiceScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        invoiceScroll.setPreferredSize(new Dimension(400, 350));

    }

    public void clearPanel(){

        topCenterPanel.removeAll();
        roomPanels.removeAll();
        topCenterPanel.revalidate();// Remove all components from the panel
        roomPanels.revalidate();
        topCenterPanel.repaint();// .revalidate(); // Revalidate the panel to update the layout
        roomPanels.repaint();
    }

    public void userPanel(){
        clearPanel();
        hideRoomScrollPane();

        JLabel label = new JLabel();
        label.setText("Enter customer name: ");
        customerName = new JTextField();

        JLabel discountLabel = new JLabel();
        discountLabel.setText("Enter discount code if you have any: ");
        discountCode = new JTextField();

        submit = new JButton("Submit");

        statusLabel = new JLabel();
        statusLabel.setText("");

        topCenterPanel.setLayout(new GridLayout(6, 1, 3, 3));
        topCenterPanel.add(label);
        topCenterPanel.add(customerName);

        topCenterPanel.add(discountLabel);
        topCenterPanel.add(discountCode);

        topCenterPanel.add(submit);

        topCenterPanel.add(statusLabel);

        topCenterPanel.revalidate();
        topCenterPanel.repaint();
    }

    public void confirmationView(){
        clearPanel();
        hideRoomScrollPane();

        topCenterPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        topCenterPanel.add(invoiceScroll, gbc);

        topCenterPanel.revalidate();
        topCenterPanel.repaint();

    }

    public void addRoomButtons() {

        roomPanels.removeAll();
        roomPanels.setLayout(new GridLayout(0, 3, 10, 10));

        ArrayList<Room> rooms = chosenHotel.viewRooms();
        ArrayList<Reservation> reservations = chosenHotel.viewReservations();

        Date checkIn = new Date(getCheckInDay());
        Date checkOut = new Date(getCheckOutDay());

        ArrayList<Room> availableRooms =  chosenHotel.roomAvailable(rooms, reservations, checkIn, checkOut);

        for (Room room : availableRooms) {
            JButton button = new JButton(room.getRoomName());
            button.setFont(new Font("Times New Roman", Font.BOLD, 11));
            button.setPreferredSize(new Dimension(96, 48));
            button.setForeground(Color.decode("#800080"));

            roomPanels.add(button);
            roomButtons.add(button);
        }

        roomPanels.revalidate();
        roomPanels.repaint();
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

    public void setCancelBooking(ActionListener listener){
        exit.addActionListener(listener);
    }

    public void setGoBackToMenuButton(ActionListener listener) {
        back.addActionListener(listener);
    }

    public String getName(){
        return customerName.getText();
    }

    public void removeButtons(){
        roomPanels.removeAll();
    }

    public void setConfirmationMessage(String input){
        confirmationMessage.setText(input);
    }

    public String getDiscountCode(){
        return discountCode.getText();
    }

    public void setStatusInput(String input){
        statusLabel.setText(input);
        System.out.println(input);
    }


    public void setRoomActions(ActionListener listener){
        for (int i = 0; i < roomButtons.size(); i++){
            roomButtons.get(i).addActionListener(listener);
        }
    }

    public void resetTextFields(){
        checkInDay.setText("");
        checkOutDay.setText("");
    }

    public ArrayList<JButton> getRoomButtons(){
        return roomButtons;
    }

    public int showConfirmationDialog(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Confirm Reservation", JOptionPane.YES_NO_OPTION);
    }

    public void showGoBackToMenuButton() {
        exit.setVisible(false);
        back.setVisible(true);
    }

    // Method to hide the room scroll pane
    public void hideRoomScrollPane() {
        roomScroll.setVisible(false);
    }

    // Method to show the room scroll pane
    public void showRoomScrollPane() {
        roomScroll.setVisible(true);
    }


}