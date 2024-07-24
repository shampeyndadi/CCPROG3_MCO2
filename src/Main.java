public class  Main {
    public static void main(String[] args){

        Hotels HotelList = new Hotels();

        MainView mainView = new MainView();
        ManageHotelView manageView = new ManageHotelView();
        ViewHotelView viewHotelView = new ViewHotelView();
        CreateHotelView createHotelView = new CreateHotelView();

        new MainController(HotelList, mainView, manageView, viewHotelView, createHotelView);

    }
}
 