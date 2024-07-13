import javax.swing.*;
import java.awt.*;

public class SimulateBookingView extends JFrame{

    private Hotels myHotels;
    private JButton exitButton;

    public SimulateBookingView(Hotels myHotels){

        super("Hotel Reservation System");

        this.myHotels = myHotels;

        setSize(500, 500);

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
    
        JPanel centerPanel = new JPanel();
    
        centerPanel.setLayout(new GridLayout(0, 1, 7, 7));
        
        for (Hotel hotels : myHotels.HotelList()){
            System.out.println("Adding button for: " + hotels.getHotelName());
            JButton button = new JButton(hotels.getHotelName());
            button.setFont(new Font("Times New Roman", Font.BOLD, 20));
            button.setBackground(Color.decode("#FFFFFF"));
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(24, 32));
            centerPanel.add(button);
        }


        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        JPanel southPanel = new JPanel();
        
        southPanel.setLayout(new FlowLayout());

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        exitButton.setBackground(Color.decode("#FFFFFF"));
        exitButton.setBorderPainted(false);

        southPanel.add(exitButton);

        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);

        topPanel.setBackground(Color.WHITE);
        centerPanel.setBackground(Color.WHITE);
    
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);


    }
}