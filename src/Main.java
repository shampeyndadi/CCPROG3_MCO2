public class  Main {
    public static void main(String[] args){

        Hotels HotelList = new Hotels();
        MainView mainView = new MainView();
        CreateHotelView createHotelView = new CreateHotelView();

        Execute exe = new Execute(HotelList);

        new MainController(HotelList, mainView, createHotelView);

        exe.execute();

    }
}
 