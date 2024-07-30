import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MainView serves as the main window of the Hotel Reservation System.
 * It provides a graphical user interface with buttons for different functionalities like creating hotels, managing hotels, simulating bookings, viewing hotel details, and exiting the application.
 */
public class MainView extends JFrame{

    private JPanel mainPanel;

    private JButton btnSelect1;
    private JButton btnSelect2;
    private JButton btnSelect3;
    private JButton btnSelect4;
    private JButton btnSelect5;

    /**
     * Constructs a new MainView with the default settings for the application window.
     */
    public MainView(){
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(450, 500);

        init();

        setIconImage(new ImageIcon(getClass().getResource("/HRS.png")).getImage());
        
        setVisible(true);
        setResizable(false);

        setBackground(Color.decode("#FFFFFF"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initializes the components of the main panel.
     */
    private void init(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel();

        label.setText("Hotel Reservation System");
        label.setFont(new Font("Times New Roman", Font.ITALIC, 32));
        label.setForeground(Color.decode("#800080"));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.decode("#FFFFFF"));
        panel.add(label);   

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 8, 8));
        
        btnSelect1 = new JButton("Create Hotel");
        btnSelect2 = new JButton("Manage Hotel");
        btnSelect3 = new JButton("Simulate Booking");
        btnSelect4 = new JButton("View Hotel Details");
        btnSelect5 = new JButton("Exit");

        btnSelect1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnSelect2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnSelect3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnSelect4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnSelect5.setFont(new Font("Times New Roman", Font.BOLD, 20));

        btnSelect1.setForeground(Color.decode("#800080"));
        btnSelect2.setForeground(Color.decode("#800080"));
        btnSelect3.setForeground(Color.decode("#800080"));
        btnSelect4.setForeground(Color.decode("#800080"));
        btnSelect5.setForeground(Color.decode("#800080"));

        btnSelect1.setBackground(Color.decode("#FFFFFF"));
        btnSelect2.setBackground(Color.decode("#FFFFFF"));
        btnSelect3.setBackground(Color.decode("#FFFFFF"));
        btnSelect4.setBackground(Color.decode("#FFFFFF"));
        btnSelect5.setBackground(Color.decode("#FFFFFF"));

        btnSelect1.setBorderPainted(false);
        btnSelect2.setBorderPainted(false);
        btnSelect3.setBorderPainted(false);
        btnSelect4.setBorderPainted(false);
        btnSelect5.setBorderPainted(false);

        buttonPanel.add(btnSelect1);
        buttonPanel.add(btnSelect2);
        buttonPanel.add(btnSelect3);
        buttonPanel.add(btnSelect4);
        buttonPanel.add(btnSelect5);

        mainPanel.add(panel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        this.add(mainPanel);

    }

    /**
     * Sets the action listener for the "Create Hotel" button.
     *
     * @param listen the ActionListener to be set
     */
    public void setbtnSelect1(ActionListener listen){
        this.btnSelect1.addActionListener(listen);
    }

    /**
     * Sets the action listener for the "Manage Hotel" button.
     *
     * @param listen the ActionListener to be set
     */
    public void setbtnSelect2(ActionListener listen){
        this.btnSelect2.addActionListener(listen);
    }

    /**
     * Sets the action listener for the "Simulate Booking" button.
     *
     * @param listen the ActionListener to be set
     */
    public void setbtnSelect3(ActionListener listen){
        this.btnSelect3.addActionListener(listen);
    }

    /**
     * Sets the action listener for the "View Hotel Details" button.
     *
     * @param listen the ActionListener to be set
     */
    public void setbtnSelect4(ActionListener listen){
        this.btnSelect4.addActionListener(listen);
    }

    /**
     * Sets the action listener for the "Exit" button.
     *
     * @param listen the ActionListener to be set
     */
    public void setbtnSelect5(ActionListener listen){
        this.btnSelect5.addActionListener(listen);
    }

    /**
     * Switches the content pane to the specified new content.
     *
     * @param newContent the new content to be displayed
     */
    public void switchContentPane(Container newContent) {
        setContentPane(newContent);
        revalidate();
        repaint();
    }

    /**
     * Switches the content pane back to the main panel.
     */
    public void switchToMainPanel() {
        setContentPane(mainPanel);
        revalidate();
        repaint();
    }
}