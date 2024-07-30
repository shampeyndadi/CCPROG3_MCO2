import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a hotel with rooms, reservations, and earnings management.
 */
public class Hotel {

    private String name;
    private final ArrayList<Room> listOfRooms;
    private final ArrayList<Reservation> listOfReservations;
    private double earnings;
    private int basic;
    private int deluxe;
    private int executive;
    private int countedBasic;
    private int countedDeluxe;
    private int countedExecutive;
    private ArrayList<String> generatedRoomNumbers;



    /**
     * Constructor to initialize a Hotel with a given name.
     * @param name The name of the hotel.
     */
    public Hotel (String name){
        this.name = name;
        this.listOfRooms = new ArrayList<>();
        this.listOfReservations = new ArrayList<>();
        this.basic = 0;
        this.deluxe = 0;
        this.executive = 0;
        this.countedBasic = 0;
        this.countedDeluxe = 0;
        this.countedExecutive = 0;
        this.generatedRoomNumbers = new ArrayList<>();
    }

    public void InitializeRooms(){
        int i;

        for (i = this.countedBasic; i < this.basic; i++){
            listOfRooms.add(new Room("Basic " + generateRoomName()));
        }

        for (i = this.countedDeluxe; i < this.deluxe; i++){
            listOfRooms.add(new Deluxe("Deluxe " + generateRoomName()));
        }

        for (i = this.countedExecutive; i < this.executive; i++){
            listOfRooms.add(new Executive("Executive " + generateRoomName()));

        }

        this.countedBasic = this.basic;
        this.countedDeluxe = this.deluxe;
        this.countedExecutive = this.executive;

    }


    /**
     * Adds a room to the hotel.
     * @param room The Room object to add.
     * @return true if the room was added successfully, false if the room limit (50 rooms) is reached.
     */
    public boolean addRoom(Room room) { // creates room for hotel

        if (listOfRooms.size() < 50){
            listOfRooms.add(room);
            return true;
        }

        return false;
    }

    private String generateRoomName() {
        char[] digits = "1234567890".toCharArray();
        Random random = new Random();
        int length = 4;

        String randomNumber;
        do {

            StringBuilder randomNumberBuilder = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(digits.length);
                randomNumberBuilder.append(digits[index]);
            }
            randomNumber = randomNumberBuilder.toString();
        } while (isDuplicate(randomNumber));

        generatedRoomNumbers.add(randomNumber);
        return randomNumber;
    }

    private boolean isDuplicate(String number) {
        for (String existingNumber : generatedRoomNumbers) {
            if (existingNumber.equals(number)) {
                return true;
            }
        }
        return false;
    }


    public void addBasicRoom(){
        if (getTotalRooms() < 50){
            this.basic++;
        }
    }

    public int getNumberOfBasicRooms(){
        return basic;
    }

    public int getNumberOfCountedBasicRooms(){
        return countedBasic;
    }

    public void addDeluxeRoom(){
        if (getTotalRooms() < 50) {
            this.deluxe++;
        }
    }

    public int getNumberOfDeluxeRooms(){
        return deluxe;
    }

    public int getNumberOfCountedDeluxeRooms(){
        return countedDeluxe;
    }

    public void addExecutiveRoom(){
        if (getTotalRooms() < 50) {
            this.executive++;
        }
    }

    public int getNumberOfExecutiveRooms(){
        return executive;
    }

    public int getNumberOfCountedExecutiveRooms(){
        return countedExecutive;
    }

    public void deductBasicRoom(){
        if (basic > countedBasic) {
            this.basic--;
        }
    }

    public void deductDeluxeRoom(){
        if (deluxe > countedDeluxe) {
            this.deluxe--;
        }
    }

    public void deductExecutiveRoom(){
        if (executive > countedExecutive) {
            this.executive--;
        }
    }

    public void removeBasic(){
        this.basic--;
        this.countedBasic--;
    }

    public void removeDeluxe(){
        this.deluxe--;
        this.countedDeluxe--;
    }

    public void removeExecutive(){
        this.executive--;
        this.countedExecutive--;
    }

    public int getTotalRooms(){
        return this.basic + this.deluxe + this.executive;
    }

    /**
     * Checks if a room with the given name exists in the specified hotel.
     * @param roomName Name of the room to check.
     * @return true if the room exists in the hotel, false otherwise.
     */
    public boolean doesRoomExist(String roomName) {
        for (Room room : viewRooms()) {
            if (room.getRoomName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Changes the name of the hotel.
     * @param newName The new name for the hotel.
     */
    public void changeHotelName(String newName) {
        this.name = newName;
    }

    /**
     * Removes a room from the hotel.
     * @param room The Room object to be removed.
     */
    public void removeRoom(Room room) { // removes room 
        listOfRooms.remove(room);
        room = null;
    }

    public void removeReservation(Reservation reservation) { // removes room
        listOfReservations.remove(reservation);
        reservation = null;
    }

    /**
     * Checks if a room exists in the hotel.
     * @param name The name of the room to check.
     * @return true if the room exists in the hotel, false otherwise.
     */
    public boolean checkRoom(String name){
        for (int i = 0; i < listOfRooms.size(); i++){
            if (listOfRooms.get(i).getRoomName().equals(name)){
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves the list of reservations in the hotel.
     * @return ArrayList of Reservation objects.
     */
    public ArrayList<Reservation> viewReservations(){
        return listOfReservations; // view reservations
    }

    /**
     * Retrieves the list of rooms in the hotel.
     * @return ArrayList of Room objects.
     */
    public ArrayList<Room> viewRooms(){
        return listOfRooms; // view rooms 
    }

    /**
     * Retrieves the name of the hotel.
     * @return The name of the hotel.
     */
    public String getHotelName(){
        return name; // gets hotel name 
    }

    /**
     * Calculates and retrieves the total earnings from reservations in the hotel.
     * @return The total earnings of the hotel.
     */
    public double getTotalEarnings(){
        for(int i = 0; i < listOfReservations.size(); i++){
            earnings += listOfReservations.get(i).calculatedTotalPrice();

        }
        
        return earnings;
    }

    /**
     * Calculates the number of rooms booked between specified check-in and check-out dates.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @return The number of rooms booked during the specified dates.
     */
    public int roomsOnDate(Date checkIn, Date checkOut){

        int bookedRooms = 0;    

        ArrayList<Reservation> reserved = viewReservations();

        for (Reservation reservation : reserved) {
            Date reservationCheckIn = reservation.checkInDate();
            Date reservationCheckOut = reservation.checkOutDate();

            if (!(checkOut.before(reservationCheckIn) || checkIn.after(reservationCheckOut))) {
                bookedRooms++;
            }
        }

        return bookedRooms;
    }   

    /**
     * Retrieves a list of available rooms in the hotel between specified check-in and check-out dates.
     * @param roomsOfChosenHotel The list of rooms in the chosen hotel.
     * @param reservations The list of reservations in the chosen hotel.
     * @param checkOut The check-out date.
     * @param checkIn The check-in date.
     * @return ArrayList of available Room objects.
     */
    public ArrayList<Room> roomAvailable(ArrayList<Room> roomsOfChosenHotel, ArrayList<Reservation> reservations, Date checkIn, Date checkOut) {
        ArrayList<Room> availableRooms = new ArrayList<>();

        for (Room room : roomsOfChosenHotel) {
            boolean isAvailable = true;

            for (Reservation reservation : reservations) {
                if (room.equals(reservation.room())) {
                    // Check if the requested dates overlap with an existing reservation
                    boolean isOverlapping = !(checkOut.before(reservation.checkInDate()) || checkIn.after(reservation.checkOutDate()));

                    // Adjust the logic to account for check-in date matching reservation check-out date
                    boolean isAdjacent = checkIn.equals(reservation.checkOutDate());

                    if (isOverlapping && !isAdjacent) {
                        isAvailable = false;
                        break; // No need to check further if we found an overlap
                    }
                }
            }

            room.changeAvailability(isAvailable);

            if (isAvailable) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }


    public ArrayList<Room> getRemovableRooms() {
        ArrayList<Room> removableRooms = new ArrayList<>();
        ArrayList<Reservation> reservations = viewReservations();

        for (Room room : viewRooms()) {
            boolean hasReservation = false;

            for (Reservation reservation : reservations) {
                if (reservation.room().equals(room)) {
                    hasReservation = true;
                }
            }

            if (!hasReservation) {
                removableRooms.add(room);
            }
        }

        return removableRooms;
    }
 
}
