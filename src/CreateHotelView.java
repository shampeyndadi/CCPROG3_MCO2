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

        setVisible(false);

        setResizable(true);

    }

    private void init(){
        JPanel topPanel = new JPanel();

        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel topLabel = new JLabel();

        topLabel.setText("Create Hotel");
        topLabel.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        topLabel.setForeground(Color.decode("#800080"));
        
        topPanel.add(topLabel);

        this.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new FlowLayout());

        this.add(centerPanel, BorderLayout.WEST);

        JPanel centerMidPanel = new JPanel();

        centerMidPanel.setLayout(new GridLayout(4, 2, 4, 4));
        centerMidPanel.setBackground(Color.decode("#FFFFFF"));

        centerPanel.add(centerMidPanel, BorderLayout.NORTH);

        JLabel indicator = new JLabel();
        indicator.setText("Enter hotel name: ");
        indicator.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        indicator.setForeground(Color.decode("#800080"));
        text = new JTextField(15);
        text.setPreferredSize(new Dimension(20, 20));

        JLabel indicator2 = new JLabel();
        indicator2.setText("Enter number of rooms: ");
        indicator2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        indicator2.setForeground(Color.decode("#800080"));
        numRooms =  new JTextField(2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonAdd = new JButton("Add Hotel");
        clearTextFields = new JButton("Clear text fields");
        exit = new JButton("Exit");

        buttonAdd.setForeground(Color.decode("#800080"));
        buttonAdd.setFont(new Font("Times New Roman", Font.BOLD, 15));

        clearTextFields.setForeground(Color.decode("#800080"));
        clearTextFields.setFont(new Font("Times New Roman", Font.BOLD, 15));

        exit.setForeground(Color.decode("#800080"));
        exit.setFont(new Font("Times New Roman", Font.BOLD, 15));

        buttonAdd.setBackground(Color.decode("#FFFFFF"));
        clearTextFields.setBackground(Color.decode("#FFFFFF"));
        exit.setBackground(Color.decode("#FFFFFF"));

        buttonAdd.setBorderPainted(false);
        clearTextFields.setBorderPainted(false);
        exit.setBorderPainted(false);

        statusLabel = new JLabel();
        statusLabel.setForeground(Color.decode("#800080"));

        buttonPanel.add(buttonAdd);
        buttonPanel.add(clearTextFields);
        buttonPanel.add(exit);  

        this.add(buttonPanel, BorderLayout.SOUTH);

        centerMidPanel.add(indicator);
        centerMidPanel.add(text);
        centerMidPanel.add(indicator2);
        centerMidPanel.add(numRooms);
        centerMidPanel.add(statusLabel);
      
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