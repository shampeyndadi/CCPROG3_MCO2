import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageHotelController {

    private MainView mainView;
    private ManageHotelView manageHotelView; 

    public ManageHotelController(MainView mainView, ManageHotelView manageHotelView){
        this.mainView = mainView;
        this.manageHotelView = manageHotelView;

        this.manageHotelView.setbtnSelect7(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Exit button pressed");
                mainView.switchToMainPanel();
            }  
        });
    }
}
