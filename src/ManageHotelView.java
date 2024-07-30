import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents the view for managing hotels in the application.
 */
public class ManageHotelView extends JPanel {

    private JButton exitButton;
    private ArrayList<JButton> buttonHotels;
    private JScrollPane hotelScroll;
    private Hotels myHotels;
    private JPanel centerPanel;

    /**
     * Constructs a ManageHotelView with the specified Hotels.
     *
     * @param myHotels the model containing the list of hotels
     */
    public ManageHotelView(Hotels myHotels) {
        super();

        this.myHotels = myHotels;
        this.buttonHotels = new ArrayList<>();

        setLayout(new BorderLayout());

        init();

        setSize(500, 500);

        setBackground(Color.decode("#FFFFFF"));
    }

    /**
     * Initializes the components and layout of the ManageHotelView.
     */
    private void init() {
        JPanel topPanel = new JPanel();
        JLabel topLabel = new JLabel();

        topPanel.setLayout(new FlowLayout());
        topLabel.setText("Manage Hotel");
        topLabel.setFont(new Font("Times New Roman", Font.ITALIC, 32));
        topLabel.setForeground(Color.decode("#800080"));

        topPanel.add(topLabel);

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
        hotelScroll.setPreferredSize(new Dimension(400, 350));

        centerPanel.add(hotelScroll);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exitButton.setBorderPainted(false);
        exitButton.setBackground(Color.decode("#FFFFFF"));
        exitButton.setForeground(Color.decode("#800080"));

        southPanel.add(exitButton);

        topPanel.setBackground(Color.WHITE);
        centerPanel.setBackground(Color.WHITE);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);

    }

    /**
     * Sets the ActionListener for the exit button.
     *
     * @param listener the ActionListener to set
     */
    public void setExitButton(ActionListener listener){
        this.exitButton.addActionListener(listener);
    }

    /**
     * Sets the ActionListener for the hotel buttons.
     *
     * @param listener the ActionListener to set
     */
    public void setHotelButtons(ActionListener listener){
        for (int i = 0; i < buttonHotels.size(); i++){
            buttonHotels.get(i).addActionListener(listener);
        }
    }

    /**
     * Clears the center panel and repaints the component.
     */
    private void clearPanels(){
        centerPanel.removeAll();
        centerPanel.revalidate();
        centerPanel.repaint();
    }

}