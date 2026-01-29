package models;

public class TwoBedRoom extends RoomBase {

    public TwoBedRoom(int id, int dormId, String roomNumber, int floor) {
        super(id, dormId, roomNumber, floor);
    }

    @Override
    public int getCapacity() {
        return 2;
    }

    @Override
    public double getMonthlyFee() {
        return 30000;
    }

    @Override
    public String getType() {
        return "TWO_BED";
    }
}
