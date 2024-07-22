import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

public class CreateHotelView extends JFrame{

    private JTextField text;
    private JTextField numRooms;
    private JButton buttonAdd;
    private JButton clearTextFields;
    private JButton exit;
    private JLabel statusLabel;

    public CreateHotelView(){
        super("Hotel Reservation System");
        setLayout(new BorderLayout());

        init();

        setSize(500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setBackground(Color.decode("#FFFFFF"));

        setVisible(false);

        setResizable(true);

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

        JLabel hotelName = new JLabel();
        hotelName.setText("Enter hotel name: ");
        hotelName.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        hotelName.setForeground(Color.decode("#800080"));

        text = new JTextField();
        text.setPreferredSize(new Dimension(100, 20));
        text.setMinimumSize(new Dimension(100, 20));
        text.setMaximumSize(new Dimension(100, 20));

        JLabel numberOfRooms = new JLabel();
        numberOfRooms.setText("Enter number of rooms you wish to add: ");
        numberOfRooms.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        numberOfRooms.setForeground(Color.decode("#800080"));

        numRooms = new JTextField();
        numRooms.setPreferredSize(new Dimension(100, 20));
        numRooms.setMinimumSize(new Dimension(100, 20));
        numRooms.setMaximumSize(new Dimension(100, 20));

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
        topCenterPanel.add(hotelName, gbc);

        gbc.gridx = 1;
        topCenterPanel.add(text, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        topCenterPanel.add(numberOfRooms, gbc);

        gbc.gridx = 1;
        topCenterPanel.add(numRooms, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        topCenterPanel.add(buttonAdd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        topCenterPanel.add(clearTextFields, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
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
    
    // Method to get the input for the number of rooms
    public int getAmountOfRooms() {
        return Integer.parseInt(numRooms.getText());
    }
    
    // Method to update the status label with a given status message
    public void setStatusInput(String status) {
        statusLabel.setText(status);
    }

    public void clearInput(){
        text.setText("");
        numRooms.setText("");
    }

    public JLabel getLabel(){
        return statusLabel;
    }

}
