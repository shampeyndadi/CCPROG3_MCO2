import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

public class CreateHotelView extends JPanel{

    private JTextField text;
    private JTextField numRooms;

    private JButton buttonAdd;
    private JButton clearTextFields;
    private JButton exit;

    private JButton addBasic;
    private JButton addDeluxe;
    private JButton addExecutive;

    private JButton deductBasic;
    private JButton deductDeluxe;
    private JButton deductExecutive;

    private JLabel statusLabel;
    private JLabel basicRoomCounter;
    private JLabel deluxeRoomCounter;
    private JLabel executiveRoomCounter;

    public CreateHotelView(){
        super();

        setLayout(new BorderLayout());

        init();

        setSize(500, 500);

        setBackground(Color.decode("#FFFFFF"));
    }

    private void init(){
        JPanel topPanel = new JPanel();
        JLabel topLabel = new JLabel();

        topPanel.setLayout(new FlowLayout());
        topLabel.setText("Create Hotel");
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

        JLabel hotelName = new JLabel();
        hotelName.setText("Enter hotel name: ");
        hotelName.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        hotelName.setForeground(Color.decode("#800080"));

        text = new JTextField();
        text.setPreferredSize(new Dimension(100, 20));
        text.setMinimumSize(new Dimension(100, 20));
        text.setMaximumSize(new Dimension(100, 20));

        JLabel numberOfBasicRooms = new JLabel();
        numberOfBasicRooms.setText("Basic Rooms");
        numberOfBasicRooms.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        numberOfBasicRooms.setForeground(Color.decode("#800080"));

        JLabel numberOfDeluxeRooms = new JLabel();
        numberOfDeluxeRooms.setText("Deluxe Rooms");
        numberOfDeluxeRooms.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        numberOfDeluxeRooms.setForeground(Color.decode("#800080"));

        JLabel numberOfExecutiveRooms = new JLabel();
        numberOfExecutiveRooms.setText("Executive Rooms");
        numberOfExecutiveRooms.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        numberOfExecutiveRooms.setForeground(Color.decode("#800080"));

        basicRoomCounter = new JLabel("0");
        deluxeRoomCounter = new JLabel("0");
        executiveRoomCounter = new JLabel("0");

        addBasic = new JButton("+");
        deductBasic = new JButton("-");

        addDeluxe = new JButton("+");
        deductDeluxe = new JButton("-");

        addExecutive = new JButton("+");
        deductExecutive = new JButton("-");

        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        statusLabel.setForeground(Color.decode("#800080"));

        buttonAdd = new JButton("Submit");
        buttonAdd.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        buttonAdd.setForeground(Color.decode("#800080"));
        buttonAdd.setBackground(Color.decode("#FFFFFF"));

        clearTextFields = new JButton("Clear text fields");
        clearTextFields.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        clearTextFields.setForeground(Color.decode("#800080"));
        clearTextFields.setBackground(Color.decode("#FFFFFF"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        topCenterPanel.add(hotelName, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        topCenterPanel.add(text, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        topCenterPanel.add(numberOfBasicRooms, gbc);

        gbc.gridx = 1;
        topCenterPanel.add(addBasic, gbc);

        gbc.gridx = 2;
        topCenterPanel.add(deductBasic, gbc);

        gbc.gridx = 3;
        topCenterPanel.add(basicRoomCounter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        topCenterPanel.add(numberOfDeluxeRooms, gbc);

        gbc.gridx = 1;
        topCenterPanel.add(addDeluxe, gbc);

        gbc.gridx = 2;
        topCenterPanel.add(deductDeluxe, gbc);

        gbc.gridx = 3;
        topCenterPanel.add(deluxeRoomCounter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        topCenterPanel.add(numberOfExecutiveRooms, gbc);

        gbc.gridx = 1;
        topCenterPanel.add(addExecutive, gbc);

        gbc.gridx = 2;
        topCenterPanel.add(deductExecutive, gbc);

        gbc.gridx = 3;
        topCenterPanel.add(executiveRoomCounter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        topCenterPanel.add(buttonAdd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        topCenterPanel.add(clearTextFields, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        topCenterPanel.add(statusLabel, gbc);

        exit = new JButton("Exit");
        exit.setFont(new Font("Times New Roman", Font.ITALIC, 10));
        exit.setForeground(Color.decode("#800080"));
        exit.setBackground(Color.decode("#FFFFFF"));

        centerPanel.add(topCenterPanel, BorderLayout.NORTH);

        southPanel.add(exit);

        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);

        topPanel.setBackground(Color.WHITE);
        centerPanel.setBackground(Color.WHITE);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    }

    public void setAddBtn(ActionListener listener) {
        this.buttonAdd.addActionListener(listener);
    }

    public void setCleartextfield(ActionListener listener){
        this.clearTextFields.addActionListener(listener);
    }

    // Method to register the ActionListener for the "Exit" button
    public void setExitBtn(ActionListener listener) {
        this.exit.addActionListener(listener);
    }

    // Method to get the input for the hotel name
    public String getInput() {
        return text.getText();
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

    // Method to update the status label with a given status message
    public void setStatusInput(String status) {
        statusLabel.setText(status);
    }

    public void clearInput(){
        text.setText("");
        basicRoomCounter.setText("0");
        deluxeRoomCounter.setText("0");
        executiveRoomCounter.setText("0");
    }

    public void resetRoomCounts() {
        basicRoomCounter.setText("0");
        deluxeRoomCounter.setText("0");
        executiveRoomCounter.setText("0");
    }

    public JLabel getLabel(){
        return statusLabel;
    }
}
