package models;

public class ThreeBedRoom extends RoomBase {

    public ThreeBedRoom(int id, int dormId, String roomNumber, int floor) {
        super(id, dormId, roomNumber, floor);
    }

    @Override
    public int getCapacity() {
        return 3;
    }

    @Override
    public double getMonthlyFee() {
        return 20000;
    }

    @Override
    public String getType() {
        return "THREE_BED";
    }
}
