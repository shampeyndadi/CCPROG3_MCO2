import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomizeHotelView extends JPanel {


    private Hotel chosenHotel;

    private JPanel accessPanel;
    private JPanel centerPanel;

    private JScrollPane scrollPane;

    private JButton addRooms;
    private JButton changeHotelName;
    private JButton removeRooms;
    private JButton updateBasePrice;
    private JButton removeReservation;
    private JButton removeHotel;
    private JButton modifyPrice;
    private JButton exit;

    private JButton addBasic;
    private JButton deductBasic;
    private JButton addDeluxe;
    private JButton deductDeluxe;
    private JButton addExecutive;
    private JButton deductExecutive;

    private JLabel numberOfBasicRooms;
    private JLabel numberOfDeluxeRooms;
    private JLabel numberOfExecutiveRooms;
    private JLabel basicRoomCounter;
    private JLabel deluxeRoomCounter;
    private JLabel executiveRoomCounter;


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

        accessPanel = new JPanel();

        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setViewportView(accessPanel);

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

        centerPanel.add(scrollPane);
        centerPanel.add(buttonPanels);

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel southPanel = new JPanel();

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
    }

    public void addRoomsView(){
        accessPanel.removeAll();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel topLabels = new JLabel();
        topLabels.setText("Add Rooms");
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));

        addBasic = new JButton("+");
        deductBasic = new JButton("-");

        addDeluxe = new JButton("+");
        deductDeluxe = new JButton("-");

        addExecutive = new JButton("+");
        deductExecutive = new JButton("-");

        basicRoomCounter = new JLabel("0");
        deluxeRoomCounter = new JLabel("0");
        executiveRoomCounter = new JLabel("0");

        numberOfBasicRooms = new JLabel();
        numberOfBasicRooms.setText("Basic Rooms: ");

        numberOfDeluxeRooms = new JLabel();
        numberOfDeluxeRooms.setText("Deluxe Rooms: ");

        numberOfExecutiveRooms = new JLabel();
        numberOfExecutiveRooms.setText("Executive Rooms: ");

        accessPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topLabels.setFont(new Font("Times New Roman", Font.ITALIC, 21));
        topLabels.setForeground(Color.decode("#800080"));
        accessPanel.add(topLabels, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        accessPanel.add(numberOfBasicRooms, gbc);

        gbc.gridx = 1;
        accessPanel.add(addBasic, gbc);

        gbc.gridx = 2;
        accessPanel.add(deductBasic, gbc);

        gbc.gridx = 3;
        accessPanel.add(basicRoomCounter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        accessPanel.add(numberOfDeluxeRooms, gbc);

        gbc.gridx = 1;
        accessPanel.add(addDeluxe, gbc);

        gbc.gridx = 2;
        accessPanel.add(deductDeluxe, gbc);

        gbc.gridx = 3;
        accessPanel.add(deluxeRoomCounter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        accessPanel.add(numberOfExecutiveRooms, gbc);

        gbc.gridx = 1;
        accessPanel.add(addExecutive, gbc);

        gbc.gridx = 2;
        accessPanel.add(deductExecutive, gbc);

        gbc.gridx = 3;
        accessPanel.add(executiveRoomCounter, gbc);

        accessPanel.repaint();
        accessPanel.revalidate();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void setAddRooms(ActionListener listener){
        this.addRooms.addActionListener(listener);
    }

    public void clearPanels(){
        accessPanel.removeAll();
        accessPanel.repaint();
        accessPanel.revalidate();
    }
}
