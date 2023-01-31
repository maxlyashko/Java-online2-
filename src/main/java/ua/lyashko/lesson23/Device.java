package ua.lyashko.lesson23;

import java.sql.Date;

public class Device {
    int deviceID;
    String type;
    String name;
    int price;
    Date manufactureDate;
    String description;
    boolean isAvailable;
    int factoryID;

    public int getDeviceID () {
        return deviceID;
    }

    public void setDeviceID ( int deviceID ) {
        this.deviceID = deviceID;
    }

    public String getType () {
        return type;
    }

    public void setType ( String type ) {
        this.type = type;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public int getPrice () {
        return price;
    }

    public void setPrice ( int price ) {
        this.price = price;
    }

    public Date getManufactureDate () {
        return manufactureDate;
    }

    public void setManufactureDate ( Date manufactureDate ) {
        this.manufactureDate = manufactureDate;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public boolean getAvailable () {
        return isAvailable;
    }

    public void setAvailable ( boolean available ) {
        isAvailable = available;
    }

    public int getFactoryID () {
        return factoryID;
    }

    public void setFactoryID ( int factoryID ) {
        this.factoryID = factoryID;
    }

    @Override
    public String toString () {
        return "Device{" +
                "deviceID=" + deviceID +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", manufactureDate=" + manufactureDate +
                ", description='" + description + '\'' +
                ", isAvailable=" + isAvailable +
                ", factoryID=" + factoryID +
                '}';
    }
}
