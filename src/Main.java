public class Main {
    public static void main(String[] args){
        MainView mainView = new MainView();
        ManageHotelView manageView = new ManageHotelView();
        ViewHotelView viewHotelView = new ViewHotelView();
        CreateHotelView createHotelView = new CreateHotelView();

        new MainController(mainView, manageView, viewHotelView, createHotelView);

    }
}
