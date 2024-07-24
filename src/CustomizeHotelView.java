import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CustomizeHotelView extends JPanel {

    private Hotel chosenHotel;
    private JPanel centerPanel;

    private JButton addRooms;
    private JButton changeHotelName;
    private JButton removeRooms;
    private JButton updateBasePrice;
    private JButton removeReservation;
    private JButton removeHotel;
    private JButton modifyPrice;
    private JButton exit;

    public CustomizeHotelView(Hotel chosenHotel){
        super();

        setSize(500, 500);

        this.chosenHotel = chosenHotel;

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
        buttonPanels.add(removeHotel);
        buttonPanels.add(modifyPrice);
        buttonPanels.add(exit);

        centerPanel.add(buttonPanels, BorderLayout.SOUTH);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel southPanel = new JPanel();


        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
    }
}
