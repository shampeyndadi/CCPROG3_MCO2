import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulateBookingController {

    SimulateBookingView simulateBookingView;
    MainView mainView;

    public SimulateBookingController(SimulateBookingView simulateBookingView, MainView mainView){

        this.simulateBookingView = simulateBookingView;
        this.mainView = mainView;

        this.simulateBookingView.setExitBtn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulateBookingView.setVisible(false);
                mainView.setVisible(true);
            }
              
        });

    }
}
