package com.cuongpq.basemvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Car implements Serializable, Parcelable {
    private String id;
    private String nameCar;
    private String racer;
    private int level;

    private Car(){}

    public Car(String id, String nameCar, String racer, int level) {
        this.id = id;
        this.nameCar = nameCar;
        this.racer = racer;
        this.level = level;
    }

    protected Car(Parcel in) {
        id = in.readString();
        nameCar = in.readString();
        racer = in.readString();
        level = in.readInt();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getRacer() {
        return racer;
    }

    public void setRacer(String racer) {
        this.racer = racer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nameCar);
        dest.writeString(racer);
        dest.writeInt(level);
    }
}
