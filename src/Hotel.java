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

    /**
     * Initializes rooms in the hotel based on the number of basic, deluxe, and executive rooms specified.
     */
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
     * Generates a unique room number for the hotel.
     *
     * @return A unique room number as a String.
     */
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

    /**
     * Checks if a generated room number is a duplicate.
     *
     * @param number The room number to check.
     * @return true if the room number is a duplicate, false otherwise.
     */
    private boolean isDuplicate(String number) {
        for (String existingNumber : generatedRoomNumbers) {
            if (existingNumber.equals(number)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a basic room to the hotel.
     */
    public void addBasicRoom(){
        if (getTotalRooms() < 50){
            this.basic++;
        }
    }

    /**
     * Gets the total number of basic rooms in the hotel.
     *
     * @return The total number of basic rooms.
     */
    public int getNumberOfBasicRooms(){
        return basic;
    }

    /**
     * Gets the total number of basic rooms that have been initialized.
     *
     * @return The number of initialized basic rooms.
     */
    public int getNumberOfCountedBasicRooms(){
        return countedBasic;
    }

    /**
     * Adds a deluxe room to the hotel.
     */
    public void addDeluxeRoom(){
        if (getTotalRooms() < 50) {
            this.deluxe++;
        }
    }

    /**
     * Gets the total number of deluxe rooms in the hotel.
     *
     * @return The total number of deluxe rooms.
     */
    public int getNumberOfDeluxeRooms(){
        return deluxe;
    }

    /**
     * Gets the total number of deluxe rooms that have been initialized.
     *
     * @return The number of initialized deluxe rooms.
     */
    public int getNumberOfCountedDeluxeRooms(){
        return countedDeluxe;
    }

    /**
     * Adds an executive room to the hotel.
     */
    public void addExecutiveRoom(){
        if (getTotalRooms() < 50) {
            this.executive++;
        }
    }

    /**
     * Gets the total number of executive rooms in the hotel.
     *
     * @return The total number of executive rooms.
     */
    public int getNumberOfExecutiveRooms(){
        return executive;
    }

    /**
     * Gets the total number of executive rooms that have been initialized.
     *
     * @return The number of initialized executive rooms.
     */
    public int getNumberOfCountedExecutiveRooms(){
        return countedExecutive;
    }

    /**
     * Deducts a basic room from the hotel.
     */
    public void deductBasicRoom(){
        if (basic > countedBasic) {
            this.basic--;
        }
    }

    /**
     * Deducts a deluxe room from the hotel.
     */
    public void deductDeluxeRoom(){
        if (deluxe > countedDeluxe) {
            this.deluxe--;
        }
    }

    /**
     * Deducts an executive room from the hotel.
     */
    public void deductExecutiveRoom(){
        if (executive > countedExecutive) {
            this.executive--;
        }
    }

    /**
     * Removes a basic room from the hotel.
     */
    public void removeBasic(){
        this.basic--;
        this.countedBasic--;
    }

    /**
     * Removes a deluxe room from the hotel.
     */
    public void removeDeluxe(){
        this.deluxe--;
        this.countedDeluxe--;
    }

    /**
     * Removes an executive room from the hotel.
     */
    public void removeExecutive(){
        this.executive--;
        this.countedExecutive--;
    }

    /**
     * Gets the total number of rooms in the hotel.
     *
     * @return The total number of rooms.
     */
    public int getTotalRooms(){
        return this.basic + this.deluxe + this.executive;
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

    /**
     * Removes a reservation from the hotel.
     *
     * @param reservation The Reservation object to be removed.
     */
    public void removeReservation(Reservation reservation) { // removes room
        listOfReservations.remove(reservation);
        reservation = null;
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

    /**
     * Retrieves a list of rooms that can be removed from the hotel, meaning they have no reservations.
     *
     * @return ArrayList of removable Room objects.
     */
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

    /**
     * Checks if the hotel is fully booked for the entire month.
     *
     * @return true if the hotel is fully booked for the month, false otherwise.
     */
    public boolean isFullyBookedForMonth() {
        int daysInMonth = 31;

        for (Room room : viewRooms()) {
            boolean isRoomFullyBooked = true;

            for (int day = 1; day <= daysInMonth; day++) {
                Date date = new Date(day);
                boolean isDayBooked = false;

                for (Reservation reservation : viewReservations()) {
                    if (reservation.room().equals(room)) {
                        int checkInDay = reservation.checkInDate().getDay();
                        int checkOutDay = reservation.checkOutDate().getDay();

                        if (date.getDay() >= checkInDay && date.getDay() < checkOutDay) {
                            isDayBooked = true;
                            break;
                        }
                    }
                }

                if (!isDayBooked) {
                    isRoomFullyBooked = false;
                    break;
                }
            }

            if (!isRoomFullyBooked) {
                return false;
            }
        }

        return true;
    }


}
