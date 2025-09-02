package org.example.model;

public class Cargo {
    private int cargoId;      // cargo_id
    private int userId;       // user_id (foreign key)
    private String cargoType;
    private String address;

    // Constructor
    public Cargo() {}

    public Cargo(int cargoId, int userId, String cargoType, String address) {
        this.cargoId = cargoId;
        this.userId = userId;
        this.cargoType = cargoType;
        this.address = address;
    }

    // Getter and Setter methods
    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
