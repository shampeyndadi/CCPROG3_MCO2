import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController{
    
    private MainView mainView; 
    private ManageHotelView manageHotelView;
    private CreateHotelView createHotelView;

    private CreateHotelController createHotelController;
    private ManageHotelController manageHotelController;
    private SimulateBookingController simulateBookingController;

    private Hotels HotelList;

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
                ManageHotelView manageHotelView = new ManageHotelView(HotelList);
                new ManageHotelController(mainView, manageHotelView, HotelList);
                mainView.switchContentPane(manageHotelView);
            }
        });

        this.mainView.setbtnSelect3(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SimulateBookingView simulateBookingView = new SimulateBookingView(HotelList);
                new SimulateBookingController(simulateBookingView, mainView, HotelList);

                mainView.setContentPane(simulateBookingView);
                mainView.revalidate(); // Revalidate the main view
                mainView.repaint(); // Repaint the main view
            }
        });

        this.mainView.setbtnSelect4(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ViewHotelView viewHotelView = new ViewHotelView(HotelList);
                new ViewHotelController(viewHotelView, mainView, HotelList);

                mainView.setContentPane(viewHotelView);
                mainView.revalidate(); // Revalidate the main view
                mainView.repaint(); // Repaint the main view
            }
        });

        this.mainView.setbtnSelect5(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

    }

}
