package models;

public abstract class RoomBase {
    private int id;
    private int dormId;
    private String roomNumber;
    private int floor;

    protected RoomBase(int id, int dormId, String roomNumber, int floor) {
        this.id = id;
        this.dormId = dormId;
        this.roomNumber = roomNumber;
        this.floor = floor;
    }

    public abstract int getCapacity();

    public abstract double getMonthlyFee();

    public abstract String getType();

    public void printInfo() {
        System.out.println(
                getType() + " | room " + roomNumber +
                        " | floor " + floor +
                        " | capacity " + getCapacity() +
                        " | fee " + getMonthlyFee());
    }

    public int getId() {
        return id;
    }

    public int getDormId() {
        return dormId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDormId(int dormId) {
        this.dormId = dormId;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}