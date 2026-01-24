package models;

public class Dorm {
    private int dormId;
    private String dormName;
    private String address;
    private int capacity;

    public Dorm(int dormId, String dormName, String address, int capacity) {
        this.dormId = dormId;
        setDormName(dormName);
        this.address = address;
        setCapacity(capacity);
    }

    public int getDormId() {
        return dormId;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
