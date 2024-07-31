import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The ViewHotelView class represents the view component in the hotel reservation system
 * for displaying hotel-related information, such as hotel, room, and reservation details.
 */
public class ViewHotelView extends JPanel {


    private JButton viewHotel;
    private JButton viewRoom;
    private JButton viewReservation;
    private Hotels myHotels;
    private JButton exitButton;
    private ArrayList<JButton> buttonHotels;
    private JScrollPane hotelScroll;
    private JPanel centerPanel;

    /**
     * Constructs a ViewHotelView with the specified hotel data.
     *
     * @param myHotels The Hotels object containing the list of hotels to be displayed.
     */
    public ViewHotelView(Hotels myHotels) {

        super();
        setLayout(new BorderLayout());
        setSize(500, 500);

        this.myHotels = myHotels;
        this.buttonHotels = new ArrayList<>();

        init();

        setBackground(Color.decode("#FFFFFF"));

    }

    /**
     * Initializes the components of the view, setting up the layout and adding the necessary buttons.
     */
    private void init() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Inquire");

        titleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 32));
        titleLabel.setForeground(Color.decode("#800080"));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);

        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.decode("#FFFFFF"));
        panel.add(titleLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10 , 10));

        centerPanel = new JPanel();

        JPanel hotelPanel = new JPanel();
        hotelPanel.setLayout(new GridLayout(0, 1, 7, 7));

        for (Hotel hotels : myHotels.HotelList()){
            JButton button = new JButton(hotels.getHotelName());
            button.setFont(new Font("Times New Roman", Font.BOLD, 20));
            button.setPreferredSize(new Dimension(24, 32));
            button.setBorderPainted(false);
            button.setBackground(Color.decode("#FFFFFF"));
            button.setForeground(Color.decode("#800080"));

            hotelPanel.add(button);

            buttonHotels.add(button);
        }

        hotelScroll = new JScrollPane(hotelPanel);
        hotelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        hotelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        hotelScroll.setPreferredSize(new Dimension(400, 200));

        centerPanel.add(hotelScroll);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exitButton.setBorderPainted(false);
        exitButton.setBackground(Color.decode("#FFFFFF"));
        exitButton.setForeground(Color.decode("#800080"));

        southPanel.add(exitButton);

        panel.setBackground(Color.WHITE);
        centerPanel.setBackground(Color.WHITE);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        this.add(panel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);

    }

    /**
     * Adds an ActionListener for the exit button.
     *
     * @param listen The ActionListener to be added.
     */
    public void addExitViewListener(ActionListener listen) {
        this.exitButton.addActionListener(listen);
    }

    /**
     * Sets the ActionListener for each hotel button, allowing for actions to be triggered when a button is clicked.
     *
     * @param listen The ActionListener to be set for the hotel buttons.
     */
    public void setHotelButtons(ActionListener listen){
        for (int i = 0; i < buttonHotels.size(); i++){
            buttonHotels.get(i).addActionListener(listen);
        }
    }

    /**
     * Adds an ActionListener for viewing hotel information.
     *
     * @param listen The ActionListener to be added.
     */
    public void addHotelInfoListener(ActionListener listen) {
        this.viewHotel.addActionListener(listen);
    }

    /**
     * Adds an ActionListener for viewing room information.
     *
     * @param listen The ActionListener to be added.
     */
    public void addRoomInfoListener(ActionListener listen) {
        this.viewRoom.addActionListener(listen);
    }

    /**
     * Adds an ActionListener for viewing reservation information.
     *
     * @param listen The ActionListener to be added.
     */
    public void addReservationInfoListener(ActionListener listen) {
        this.viewReservation.addActionListener(listen);
    }

}