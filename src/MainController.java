import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController{
    
    private MainView mainView; 
    private ManageHotelView manageHotelView;
    private ViewHotelView viewHotelView;
    private CreateHotelView createHotelView;

    private CreateHotelController createHotelController;
    private ManageHotelController manageHotelController;
    private SimulateBookingController simulateBookingController;

    private Hotels HotelList;

    public MainController(Hotels HotelList, MainView mainView, ManageHotelView manageHotelView, ViewHotelView viewHotelView, CreateHotelView createHotelView){
        this.mainView = mainView; 
        this.manageHotelView = manageHotelView;
        this.viewHotelView = viewHotelView;
        this.HotelList = HotelList;

        this.createHotelController = new CreateHotelController(HotelList, mainView, createHotelView);
        this.manageHotelController = new ManageHotelController(mainView, manageHotelView);

        this.mainView.setbtnSelect1(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                mainView.setVisible(false);
                createHotelView.setVisible(true);
            }
        });

        this.mainView.setbtnSelect2(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                mainView.setVisible(false);
                manageHotelView.setVisible(true);
            }
        });

        this.mainView.setbtnSelect3(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainView.setVisible(false);
                SimulateBookingView simulateBookingView = new SimulateBookingView(HotelList);
                new SimulateBookingController(simulateBookingView, mainView, HotelList);
                simulateBookingView.setVisible(true);
            }
        });

        this.mainView.setbtnSelect4(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                mainView.setVisible(false);
                viewHotelView.setVisible(true);
            }
        });

        this.mainView.setbtnSelect5(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

    }

}
