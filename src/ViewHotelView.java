import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewHotelView extends JPanel {


    private JButton viewHotel;
    private JButton viewRoom;
    private JButton viewReservation;
    private Hotels myHotels;
    private JButton exitButton;
    private ArrayList<JButton> buttonHotels;
    private JScrollPane hotelScroll;
    private JPanel centerPanel;


    public ViewHotelView(Hotels myHotels) {

        super();
        setLayout(new BorderLayout());
        setSize(500, 500);

        this.myHotels = myHotels;
        this.buttonHotels = new ArrayList<>();

        init();

        setBackground(Color.decode("#FFFFFF"));

    }

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


    public void addExitViewListener(ActionListener listen) {
        this.exitButton.addActionListener(listen);
    }

    public void setHotelButtons(ActionListener listen){
        for (int i = 0; i < buttonHotels.size(); i++){
            buttonHotels.get(i).addActionListener(listen);
        }
    }

    public void addHotelInfoListener(ActionListener listen) {
        this.viewHotel.addActionListener(listen);
    }

    public void addRoomInfoListener(ActionListener listen) {
        this.viewRoom.addActionListener(listen);
    }

    public void addReservationInfoListener(ActionListener listen) {
        this.viewReservation.addActionListener(listen);
    }

}