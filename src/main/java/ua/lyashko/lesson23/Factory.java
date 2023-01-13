package ua.lyashko.lesson23;

public class Factory {
    int factoryID;
    String name;
    String country;

    public int getFactoryID () {
        return factoryID;
    }

    public void setFactoryID ( int factoryID ) {
        this.factoryID = factoryID;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getCountry () {
        return country;
    }

    public void setCountry ( String country ) {
        this.country = country;
    }

    @Override
    public String toString () {
        return "Factory{" +
                "factoryID=" + factoryID +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
