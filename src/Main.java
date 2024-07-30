public class  Main {
    public static void main(String[] args){

        Hotels HotelList = new Hotels();
        MainView mainView = new MainView();
        CreateHotelView createHotelView = new CreateHotelView();

        new MainController(HotelList, mainView, createHotelView);

    }
}
 