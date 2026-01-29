package models;

public class OneBedRoom extends RoomBase {

    public OneBedRoom(int id, int dormId, String roomNumber, int floor) {
        super(id, dormId, roomNumber, floor);
    }

    @Override
    public int getCapacity() {
        return 1;
    }

    @Override
    public double getMonthlyFee() {
        return 45000;
    }

    @Override
    public String getType() {
        return "ONE_BED";
    }
}
