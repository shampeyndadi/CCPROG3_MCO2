import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimulateBookingView extends JPanel{

    private Hotels myHotels;
    private JButton exitButton;
    private ArrayList<JButton> buttonHotels;

    public SimulateBookingView(Hotels myHotels){

        super();

        this.myHotels = myHotels;
        this.buttonHotels = new ArrayList<>();

        setLayout(new BorderLayout());

        init();

        setSize(500, 500);

        setBackground(Color.decode("#FFFFFF"));
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
            button.setForeground(Color.decode("#800080"));
            centerPanel.add(button);

            buttonHotels.add(button);
        }

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

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

    }

    public void setExitBtn (ActionListener listener){
        this.exitButton.addActionListener(listener);
    }

    public void setActionHotelButtons(ActionListener listener){
        for (int i = 0; i < buttonHotels.size(); i++){
            buttonHotels.get(i).addActionListener(listener);
        }
    }

    public ArrayList<JButton> getButtons(){
        return buttonHotels;
    }


}