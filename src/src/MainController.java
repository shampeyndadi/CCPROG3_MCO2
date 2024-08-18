import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MainController manages the interactions between the main view and various hotel management views.
 * It handles user actions from the main menu and directs the flow to the appropriate functionalities.
 */
public class MainController{
    
    private MainView mainView;
    private CreateHotelController createHotelController;
    private Hotels HotelList;

    /**
     * Constructs a new MainController with the specified hotel list, main view, and create hotel view.
     *
     * @param HotelList the list of hotels
     * @param mainView the main view of the application
     * @param createHotelView the view for creating a new hotel
     */
    public MainController(Hotels HotelList, MainView mainView, CreateHotelView createHotelView){
        this.mainView = mainView;
        this.HotelList = HotelList;

        this.createHotelController = new CreateHotelController(HotelList, mainView, createHotelView);

        this.mainView.setbtnSelect1(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                mainView.switchContentPane(createHotelView);
            }
        });

        this.mainView.setbtnSelect2(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (HotelList.HotelList().isEmpty()){
                    JOptionPane.showMessageDialog(mainView, "No hotels found! Please create a hotel first");
                }else {
                    ManageHotelView manageHotelView = new ManageHotelView(HotelList);
                    new ManageHotelController(mainView, manageHotelView, HotelList);
                    mainView.switchContentPane(manageHotelView);
                }
            }
        });

        this.mainView.setbtnSelect3(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (HotelList.HotelList().isEmpty()){
                    JOptionPane.showMessageDialog(mainView, "No hotels found! Please create a hotel first");
                }else {
                    SimulateBookingView simulateBookingView = new SimulateBookingView(HotelList);
                    new SimulateBookingController(simulateBookingView, mainView, HotelList);
                    mainView.setContentPane(simulateBookingView);
                    mainView.revalidate(); // Revalidate the main view
                    mainView.repaint(); // Repaint the main view
                }
            }
        });

        this.mainView.setbtnSelect4(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (HotelList.HotelList().isEmpty()) {
                    JOptionPane.showMessageDialog(mainView, "No hotels found! Please create a hotel first");
                }else {
                    ViewHotelView viewHotelView = new ViewHotelView(HotelList);
                    new ViewHotelController(viewHotelView, mainView, HotelList);
                    mainView.setContentPane(viewHotelView);
                    mainView.revalidate(); // Revalidate the main view
                    mainView.repaint(); // Repaint the main view
                }
            }
        });

        this.mainView.setbtnSelect5(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

    }

}
