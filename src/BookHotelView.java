import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class BookHotelView extends JFrame{

    private JTextField checkInDay;
    private JTextField checkOutDay;
    private JLabel statusLabel;
    private JButton submit;

    public BookHotelView(){
        super("Hotel Reservation System");

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

        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        JPanel southPanel = new JPanel();

        JPanel centerPanel = new JPanel();

        JPanel topCenterPanel = new JPanel();

        topCenterPanel.setLayout(new GridLayout(6, 1, 3, 3));

        JLabel checkIn = new JLabel();
        checkIn.setText("Enter day of the month you wish to check in: ");
        checkIn.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        checkIn.setForeground(Color.decode("#800080"));
        checkInDay = new JTextField();
        checkInDay.setPreferredSize(new Dimension(2, 10));

        JLabel checkOut = new JLabel();
        checkOut.setText("Enter day of the month you wish to check out: ");
        checkOut.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        checkOut.setForeground(Color.decode("#800080"));
        checkOutDay = new JTextField();
        checkOutDay.setPreferredSize(new Dimension(2, 10));

        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        statusLabel.setForeground(Color.decode("#800080"));

        submit = new JButton("Submit");
        submit.setFont(new Font("Times New Roman", Font.ITALIC, 10));
        submit.setForeground(Color.decode("#800080"));
        submit.setBackground(Color.decode("#FFFFFF"));

        topCenterPanel.add(checkIn);
        topCenterPanel.add(checkInDay);

        topCenterPanel.add(checkOut);
        topCenterPanel.add(checkOutDay);

        topCenterPanel.add(statusLabel);

        topCenterPanel.add(submit);

        centerPanel.add(topCenterPanel, BorderLayout.NORTH);

        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);

        topPanel.setBackground(Color.WHITE);
        centerPanel.setBackground(Color.WHITE);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    }

    public void setStatusInput(String input){
        statusLabel.setText(input);
    }

    public int getCheckInDay(){
        return Integer.parseInt(checkInDay.getText());
    }

    public int getCheckOutDay(){
        return Integer.parseInt(checkOutDay.getText());
    }

    public void setSubmitButton(ActionListener listener){
        submit.addActionListener(listener);
    }
}
