import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;

/**
 * CreateHotelView is a custom JPanel that provides the user interface for creating a hotel.
 * It includes fields for entering the hotel name and buttons for managing room counts (Basic, Deluxe, Executive).
 * The view also provides buttons for submitting the data, clearing inputs, and exiting the creation process.
 */
public class CreateHotelView extends JPanel{

    private JTextField text;

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

    /**
     * Constructs a new CreateHotelView with the specified settings.
     */
    public CreateHotelView(){
        super();

        setLayout(new BorderLayout());

        init();

        setSize(500, 500);

        setBackground(Color.decode("#FFFFFF"));
    }

    /**
     * Initializes the components and layout of the view.
     */
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

    /**
     * Sets an ActionListener for the submit button.
     *
     * @param listener the ActionListener to be set for the submit button
     */
    public void setAddBtn(ActionListener listener) {
        this.buttonAdd.addActionListener(listener);
    }

    /**
     * Sets an ActionListener for the clear text fields button.
     *
     * @param listener the ActionListener to be set for the clear text fields button
     */
    public void setCleartextfield(ActionListener listener){
        this.clearTextFields.addActionListener(listener);
    }

    /**
     * Sets an ActionListener for the exit button.
     *
     * @param listener the ActionListener to be set for the exit button
     */
    public void setExitBtn(ActionListener listener) {
        this.exit.addActionListener(listener);
    }

    /**
     * Gets the hotel name input by the user.
     *
     * @return the hotel name as a String
     */
    public String getInput() {
        return text.getText();
    }

    /**
     * Updates the display for the number of basic rooms.
     *
     * @param count the new number of basic rooms as a String
     */
    public void UpdateNumberOfBasicRooms(String count){
        basicRoomCounter.setText(count);
    }

    /**
     * Updates the display for the number of deluxe rooms.
     *
     * @param count the new number of deluxe rooms as a String
     */
    public void UpdateNumberOfDeluxeRooms(String count){
        deluxeRoomCounter.setText(count);
    }

    /**
     * Updates the display for the number of executive rooms.
     *
     * @param count the new number of executive rooms as a String
     */
    public void UpdateNumberOfExecutiveRooms(String count){
        executiveRoomCounter.setText(count);
    }

    /**
     * Sets an ActionListener for the button to add a basic room.
     *
     * @param listener the ActionListener to be set for the add basic room button
     */
    public void setAddBasicButtons(ActionListener listener){
        this.addBasic.addActionListener(listener);
    }

    /**
     * Sets an ActionListener for the button to add a deluxe room.
     *
     * @param listener the ActionListener to be set for the add deluxe room button
     */
    public void setAddDeluxeButtons(ActionListener listener){
        this.addDeluxe.addActionListener(listener);
    }

    /**
     * Sets an ActionListener for the button to add an executive room.
     *
     * @param listener the ActionListener to be set for the add executive room button
     */
    public void setAddExecutiveButtons(ActionListener listener){
        this.addExecutive.addActionListener(listener);
    }

    /**
     * Sets an ActionListener for the button to deduct a basic room.
     *
     * @param listener the ActionListener to be set for the deduct basic room button
     */
    public void setDeductBasic(ActionListener listener){
        this.deductBasic.addActionListener(listener);
    }

    /**
     * Sets an ActionListener for the button to deduct a deluxe room.
     *
     * @param listener the ActionListener to be set for the deduct deluxe room button
     */
    public void setDeductDeluxe(ActionListener listener){
        this.deductDeluxe.addActionListener(listener);
    }

    /**
     * Sets an ActionListener for the button to deduct an executive room.
     *
     * @param listener the ActionListener to be set for the deduct executive room button
     */
    public void setDeductExecutive(ActionListener listener){
        this.deductExecutive.addActionListener(listener);
    }

    /**
     * Updates the status label with the provided status message.
     *
     * @param status the status message to display
     */
    public void setStatusInput(String status) {
        statusLabel.setText(status);
    }

    /**
     * Clears all input fields and resets the room counters to zero.
     */
    public void clearInput(){
        text.setText("");
        basicRoomCounter.setText("0");
        deluxeRoomCounter.setText("0");
        executiveRoomCounter.setText("0");
    }

    /**
     * Resets the room counters to zero.
     */
    public void resetRoomCounts() {
        basicRoomCounter.setText("0");
        deluxeRoomCounter.setText("0");
        executiveRoomCounter.setText("0");
    }
}
