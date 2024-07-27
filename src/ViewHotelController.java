import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewHotelController{
    private ViewHotelView viewHotelView;
    private MainView mainView;

    public ViewHotelController(ViewHotelView viewHotelView, MainView mainView, Hotels myHotels){
        this.viewHotelView = viewHotelView;
        this.mainView = mainView;

        viewHotelView.setHotelButtons(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String hotelName = source.getText();
                for (Hotel hotel : myHotels.HotelList()) {
                    if (hotel.getHotelName().equals(hotelName)) {
                        Inquire inquire = new Inquire(hotel);

                        initializeInquireView(inquire);
                        mainView.switchContentPane(inquire);

                        mainView.revalidate();
                        mainView.repaint();

                        System.out.println("You clicked on " + hotelName);

                    }
                }
            }
        });

        viewHotelView.addExitViewListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToMainPanel();
            }
        });
    }

    private void initializeInquireView(Inquire inquire){
        inquire.setExit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToMainPanel();
            }
        });
    }
}
