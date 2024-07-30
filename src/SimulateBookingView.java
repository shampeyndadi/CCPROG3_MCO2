import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The SimulateBookingView class represents the GUI for simulating bookings in the hotel reservation system.
 * It displays a list of hotels and provides functionality for selecting a hotel to book and exiting the view.
 */
public class SimulateBookingView extends JPanel{

    private Hotels myHotels;
    private JButton exitButton;
    private ArrayList<JButton> buttonHotels;
    private JScrollPane hotelScroll;

    /**
     * Constructs a new SimulateBookingView with the provided Hotels model.
     *
     * @param myHotels The model containing the list of hotels.
     */
    public SimulateBookingView(Hotels myHotels){

        super();

        this.myHotels = myHotels;
        this.buttonHotels = new ArrayList<>();

        setLayout(new BorderLayout());

        init();

        setSize(500, 500);

        setBackground(Color.decode("#FFFFFF"));
    }

    /**
     * Initializes the GUI components of the view.
     */
    private void init(){
 
        JPanel topPanel = new JPanel();
        JLabel topLabel = new JLabel();
    
        topPanel.setLayout(new FlowLayout());
        topLabel.setText("Simulate Booking");
        topLabel.setFont(new Font("Times New Roman", Font.ITALIC, 32));
        topLabel.setForeground(Color.decode("#800080"));
    
        topPanel.add(topLabel);
    
        JPanel centerPanel = new JPanel();
    
        centerPanel.setLayout(new GridLayout(0, 1, 7, 7));
        
        for (Hotel hotels : myHotels.HotelList()){
            JButton button = new JButton(hotels.getHotelName());
            button.setFont(new Font("Times New Roman", Font.BOLD, 20));
            button.setBackground(Color.decode("#FFFFFF"));
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(24, 32));
            button.setForeground(Color.decode("#800080"));
            centerPanel.add(button);

            buttonHotels.add(button);
        }

        hotelScroll = new JScrollPane(centerPanel);
        hotelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        hotelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        hotelScroll.setPreferredSize(new Dimension(400, 350));

        JPanel CenterPanel = new JPanel();
        CenterPanel.add(hotelScroll);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        exitButton.setBackground(Color.decode("#FFFFFF"));
        exitButton.setForeground(Color.decode("#800080"));
        exitButton.setBorderPainted(false);

        southPanel.add(exitButton);

        topPanel.setBackground(Color.WHITE);
        centerPanel.setBackground(Color.WHITE);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        this.add(topPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(CenterPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(southPanel, BorderLayout.SOUTH);

    }

    /**
     * Sets the action listener for the exit button.
     *
     * @param listener The ActionListener to be set.
     */
    public void setExitBtn (ActionListener listener){
        this.exitButton.addActionListener(listener);
    }

    /**
     * Sets the action listener for the hotel buttons.
     *
     * @param listener The ActionListener to be set.
     */
    public void setActionHotelButtons(ActionListener listener){
        for (int i = 0; i < buttonHotels.size(); i++){
            buttonHotels.get(i).addActionListener(listener);
        }
    }

    /**
     * Returns the list of hotel buttons.
     *
     * @return The list of JButton objects representing the hotels.
     */
    public ArrayList<JButton> getButtons(){
        return buttonHotels;
    }


}