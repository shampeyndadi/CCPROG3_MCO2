import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomizeHotelView extends JPanel {

    private ArrayList<Room> removableRooms;
    private ArrayList<Reservation> removableReservations;

    private Hotel chosenHotel;
    private Hotels myHotels;

    private JPanel accessPanel;
    private JPanel centerPanel;

    private JScrollPane scrollPane;

    private ArrayList<JButton> rooms;
    private ArrayList<JButton> modifiedRooms;
    private ArrayList<JButton> reservations;

    private JButton addRooms;
    private JButton changeHotelName;
    private JButton removeRooms;
    private JButton updateBasePrice;
    private JButton removeReservation;
    private JButton removeHotel;
    private JButton modifyPrice;
    private JButton exit;
    private JButton submit;

    private JButton addBasic;
    private JButton deductBasic;
    private JButton addDeluxe;
    private JButton deductDeluxe;
    private JButton addExecutive;
    private JButton deductExecutive;

    private JLabel numberOfBasicRooms;
    private JLabel numberOfDeluxeRooms;
    private JLabel numberOfExecutiveRooms;
    private JLabel basicRoomCounter;
    private JLabel deluxeRoomCounter;
    private JLabel executiveRoomCounter;
    private JLabel statusLabel;

    private JTextField dateCollector;
    private JTextField newNameCollector;
    private JTextField newPriceCollector;
    private JTextField newPercentageCollector;


    public CustomizeHotelView(Hotel chosenHotel){
        super();

        setSize(500, 500);

        this.chosenHotel = chosenHotel;
        this.removableRooms = chosenHotel.getRemovableRooms();
        this.removableReservations = chosenHotel.viewReservations();

        this.modifiedRooms = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();

        setBackground(Color.decode("#FFFFFF"));

        setLayout(new BorderLayout());

        init();
    }

    private void init(){
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel topLabel = new JLabel();
        topLabel.setText("Manage Hotel");
        topLabel.setFont(new Font("Times New Roman", Font.ITALIC, 32));
        topLabel.setForeground(Color.decode("#800080"));

        topPanel.add(topLabel);

        centerPanel = new JPanel();

        accessPanel = new JPanel();

        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setViewportView(accessPanel);

        JPanel buttonPanels = new JPanel();
        buttonPanels.setLayout(new GridLayout(4, 2, 10, 10));

        addRooms = new JButton("Add Rooms");
        changeHotelName = new JButton("Change Hotel Name");
        removeRooms = new JButton("Remove Roms");
        updateBasePrice = new JButton("Update Base Price");
        removeReservation = new JButton("Remove Reservation");
        removeHotel = new JButton("Remove Hotel");
        modifyPrice = new JButton("Modify Price");
        exit = new JButton("Exit");

        buttonPanels.add(addRooms);
        buttonPanels.add(changeHotelName);
        buttonPanels.add(removeRooms);
        buttonPanels.add(updateBasePrice);
        buttonPanels.add(removeReservation);
        buttonPanels.add(modifyPrice);
        buttonPanels.add(removeHotel);
        buttonPanels.add(exit);

        centerPanel.add(scrollPane);
        centerPanel.add(buttonPanels);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel southPanel = new JPanel();

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
    }

    public void addRoomsView(){
        accessPanel.removeAll();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel topLabels = new JLabel();
        topLabels.setText("Add Rooms");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));

        addBasic = new JButton("+");
        deductBasic = new JButton("-");

        addDeluxe = new JButton("+");
        deductDeluxe = new JButton("-");

        addExecutive = new JButton("+");
        deductExecutive = new JButton("-");

        basicRoomCounter = new JLabel("0");
        deluxeRoomCounter = new JLabel("0");
        executiveRoomCounter = new JLabel("0");

        numberOfBasicRooms = new JLabel();
        numberOfBasicRooms.setText("Basic Rooms: ");

        numberOfDeluxeRooms = new JLabel();
        numberOfDeluxeRooms.setText("Deluxe Rooms: ");

        numberOfExecutiveRooms = new JLabel();
        numberOfExecutiveRooms.setText("Executive Rooms: ");

        submit = new JButton("Submit");

        statusLabel = new JLabel();
        statusLabel.setText("");

        accessPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));
        accessPanel.add(topLabels, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        accessPanel.add(numberOfBasicRooms, gbc);

        gbc.gridx = 1;
        accessPanel.add(addBasic, gbc);

        gbc.gridx = 2;
        accessPanel.add(deductBasic, gbc);

        gbc.gridx = 3;
        accessPanel.add(basicRoomCounter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        accessPanel.add(numberOfDeluxeRooms, gbc);

        gbc.gridx = 1;
        accessPanel.add(addDeluxe, gbc);

        gbc.gridx = 2;
        accessPanel.add(deductDeluxe, gbc);

        gbc.gridx = 3;
        accessPanel.add(deluxeRoomCounter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        accessPanel.add(numberOfExecutiveRooms, gbc);

        gbc.gridx = 1;
        accessPanel.add(addExecutive, gbc);

        gbc.gridx = 2;
        accessPanel.add(deductExecutive, gbc);

        gbc.gridx = 3;
        accessPanel.add(executiveRoomCounter, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        accessPanel.add(submit, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        accessPanel.add(statusLabel, gbc);

        accessPanel.repaint();
        accessPanel.revalidate();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void changeHotelName(){
        accessPanel.removeAll();

        accessPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel topLabels = new JLabel();
        topLabels.setText("Change Hotel Name");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));
        accessPanel.add(topLabels, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel newName = new JLabel();
        newName.setText("Enter new hotel name: ");
        accessPanel.add(newName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        newNameCollector = new JTextField();
        accessPanel.add(newNameCollector, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        submit = new JButton("Submit");
        accessPanel.add(submit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        statusLabel = new JLabel();
        statusLabel.setText("");
        accessPanel.add(statusLabel, gbc);

        accessPanel.repaint();
        accessPanel.revalidate();
        scrollPane.revalidate();
        scrollPane.repaint();

    }

    public void removeRooms(){
        accessPanel.removeAll();

        accessPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel topLabels = new JLabel();
        topLabels.setText("Remove Rooms");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));

        topPanel.add(topLabels);

        accessPanel.add(topPanel, BorderLayout.NORTH);

        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(0, 3, 10, 10));

        for (Room room : removableRooms){
            JButton buttons = new JButton(room.getRoomName());
            buttons.setFont(new Font("Times New Roman", Font.ITALIC, 11));
            buttons.setForeground(Color.decode("#800080"));

            roomPanel.add(buttons);
            rooms.add(buttons);
        }

        accessPanel.add(roomPanel, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel southPanel = new JPanel();


        accessPanel.add(eastPanel, BorderLayout.EAST);
        accessPanel.add(westPanel, BorderLayout.WEST);

        accessPanel.repaint();
        accessPanel.revalidate();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void changeBasePrice(){
        accessPanel.removeAll();

        accessPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel topLabels = new JLabel();
        topLabels.setText("Change Room Prices");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));
        accessPanel.add(topLabels, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel topLabel = new JLabel();
        topLabel.setText("Enter new prices: ");
        accessPanel.add(topLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        newPriceCollector = new JTextField();
        accessPanel.add(newPriceCollector, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        submit = new JButton("Submit");
        accessPanel.add(submit, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        statusLabel = new JLabel();
        statusLabel.setText("");
        accessPanel.add(statusLabel, gbc);

        accessPanel.repaint();
        accessPanel.revalidate();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void removeReservation(){
        accessPanel.removeAll();

        accessPanel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel topLabels = new JLabel();
        topLabels.setText("Remove Reservation");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));
        titlePanel.add(topLabels);

        accessPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanels = new JPanel();
        buttonPanels.setLayout(new GridLayout(0, 3, 10, 10));

        for (Reservation reservation : removableReservations){
            JButton buttons = new JButton(reservation.guestName());
            buttons.setFont(new Font("Times New Roman", Font.ITALIC, 11));
            buttons.setForeground(Color.decode("#800080"));

            buttonPanels.add(buttons);
            reservations.add(buttons);
        }

        accessPanel.add(buttonPanels, BorderLayout.CENTER);

        accessPanel.repaint();
        accessPanel.revalidate();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void chooseRoomToModify(){
        accessPanel.removeAll();

        accessPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel topLabels = new JLabel();
        topLabels.setText("Modify Room Price");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));
        topPanel.add(topLabels);

        accessPanel.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3, 10, 10));

        for (Room room : removableRooms){
            JButton buttons = new JButton(room.getRoomName());
            buttons.setFont(new Font("Times New Roman", Font.ITALIC, 11));
            buttons.setForeground(Color.decode("#800080"));

            buttonPanel.add(buttons);
            modifiedRooms.add(buttons);
        }

        accessPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel westPanel = new JPanel();
        JPanel eastPanel = new JPanel();

        accessPanel.add(westPanel, BorderLayout.WEST);
        accessPanel.add(eastPanel, BorderLayout.EAST);

        accessPanel.repaint();
        accessPanel.revalidate();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void ModifyPrice(Room room){
        accessPanel.removeAll();

        accessPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel topLabels = new JLabel();
        topLabels.setText("Modify Room Prices");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));
        accessPanel.add(topLabels, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel dateLabel = new JLabel();
        dateLabel.setText("Enter date you wish to modify the price: ");
        accessPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        dateCollector = new JTextField();
        accessPanel.add(dateCollector, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel enterPercentage = new JLabel();
        enterPercentage.setText("Enter percentage: ");
        accessPanel.add(enterPercentage, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        newPercentageCollector = new JTextField();
        accessPanel.add(newPercentageCollector, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        submit = new JButton("Submit");
        accessPanel.add(submit, gbc);

        accessPanel.repaint();
        accessPanel.revalidate();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void refreshRoomList(ArrayList<Room> updatedRooms, ActionListener listener) {
        accessPanel.removeAll();
        accessPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel topLabels = new JLabel();
        topLabels.setText("Remove Rooms");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));

        topPanel.add(topLabels);

        accessPanel.add(topPanel, BorderLayout.NORTH);

        this.removableRooms = updatedRooms;
        rooms.clear();

        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(0, 3, 10, 10));

        for (Room room : removableRooms) {
            JButton button = new JButton(room.getRoomName());
            button.setFont(new Font("Times New Roman", Font.ITALIC, 11));
            button.setForeground(Color.decode("#800080"));
            roomPanel.add(button);
            rooms.add(button);
            button.addActionListener(listener);
        }

        accessPanel.add(roomPanel, BorderLayout.CENTER);
        accessPanel.revalidate();
        accessPanel.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void refreshReservationList(ArrayList<Reservation> updatedReservations, ActionListener listener){
        accessPanel.removeAll();
        accessPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel topLabels = new JLabel();
        topLabels.setText("Remove Reservation");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));

        topPanel.add(topLabels);

        accessPanel.add(topPanel, BorderLayout.NORTH);

        this.removableReservations = updatedReservations;
        reservations.clear();

        JPanel reservationPanel = new JPanel();
        reservationPanel.setLayout(new GridLayout(0, 3, 10, 10));

        for (Reservation reservation : removableReservations){
            JButton button = new JButton(reservation.guestName());
            button.setFont(new Font("Times New Roman", Font.ITALIC, 11));
            button.setForeground(Color.decode("#800080"));
            reservationPanel.add(button);
            reservations.add(button);
            button.addActionListener(listener);
        }

        accessPanel.add(reservationPanel, BorderLayout.CENTER);
        accessPanel.revalidate();
        accessPanel.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void UpdateNumberOfBasicRooms(String count){
        basicRoomCounter.setText(count);
    }

    public void UpdateNumberOfDeluxeRooms(String count){
        deluxeRoomCounter.setText(count);
    }

    public void UpdateNumberOfExecutiveRooms(String count){
        executiveRoomCounter.setText(count);
    }

    public void setAddBasicButtons(ActionListener listener){
        this.addBasic.addActionListener(listener);
    }

    public void setAddDeluxeButtons(ActionListener listener){
        this.addDeluxe.addActionListener(listener);
    }

    public void setAddExecutiveButtons(ActionListener listener){
        this.addExecutive.addActionListener(listener);
    }

    public void setDeductBasic(ActionListener listener){
        this.deductBasic.addActionListener(listener);
    }

    public void setDeductDeluxe(ActionListener listener){
        this.deductDeluxe.addActionListener(listener);
    }

    public void setDeductExecutive(ActionListener listener){
        this.deductExecutive.addActionListener(listener);
    }

    public void setAddRooms(ActionListener listener){
        this.addRooms.addActionListener(listener);
    }

    public void setChangeHotelName(ActionListener listener){
        this.changeHotelName.addActionListener(listener);
    }

    public void setRemovableRooms(ActionListener listener){
        this.removeRooms.addActionListener(listener);
    }

    public void setUpdateBasePrice(ActionListener listener){
        this.updateBasePrice.addActionListener(listener);
    }

    public void setRemoveReservation(ActionListener listener){
        this.removeReservation.addActionListener(listener);
    }

    public void setModifyPrice(ActionListener listener){
        this.modifyPrice.addActionListener(listener);
    }

    public void setSubmitButton(ActionListener listener){
        this.submit.addActionListener(listener);
    }

    public void setRemoveHotel(ActionListener listener){
        this.removeHotel.addActionListener(listener);
    }

    public  void setExit(ActionListener listener){
        this.exit.addActionListener(listener);
    }

    public String getNewName(){
        return newNameCollector.getText();
    }

    public double getModifiedPrice(){
        return Double.parseDouble(newPriceCollector.getText());
    }
    public double getModifiedPerecentage(){
        return Double.parseDouble(newPercentageCollector.getText());
    }


    public int getDate(){
        return Integer.parseInt(dateCollector.getText());
    }

    public void setStatusLabel(String name){
        statusLabel.setText(name);
    }

    public void setRooms(ActionListener listener){
        for (int i = 0; i < rooms.size(); i++){
            rooms.get(i).addActionListener(listener);
        }
    }

    public void setReservations(ActionListener listener){
        for (int i = 0; i < reservations.size(); i++){
            reservations.get(i).addActionListener(listener);
        }
    }

    public void setModifiedRooms(ActionListener listener){
        for (int i = 0; i < modifiedRooms.size(); i++){
            modifiedRooms.get(i).addActionListener(listener);
        }
    }

    public int showConfirmationDialog(String message) {
        return JOptionPane.showConfirmDialog(this, message, "Confirm Reservation", JOptionPane.YES_NO_OPTION);
    }

    public void clearNewNameCollector(){
        newNameCollector.setText("");
    }

    public void clearTextfields(){
        dateCollector.setText("");
        newPercentageCollector.setText("");
    }

    public void clearPanels(){
        accessPanel.removeAll();
        accessPanel.repaint();
        accessPanel.revalidate();
    }
}