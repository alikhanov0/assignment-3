package models;

public class Room {
    private int roomId;
    private int dormId;
    private String roomNumber;
    private int floor;
    private String roomType;

    public Room(int roomId, int dormId, String roomNumber, int floor, String roomType) {
        this.roomId = roomId;
        this.dormId = dormId;
        setRoomNumber(roomNumber);
        this.floor = floor;
        this.roomType = roomType;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getDormId() {
        return dormId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public String getRoomType() {
        return roomType;
    }
}
