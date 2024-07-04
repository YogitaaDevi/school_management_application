package com.i2i.sma.dto;

import com.i2i.sma.models.Cabin;

public class ResponseCabinDto {
    private int id;
    private int laptopId;

    public ResponseCabinDto(int id, int laptopId) {
        this.id = id;
        this.laptopId = laptopId;
    }

    public ResponseCabinDto(Cabin cabin) {
        this.id = cabin.getId();
        this.laptopId = cabin.getLaptopId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(int laptopId) {
        this.laptopId = laptopId;
    }

    @Override
    public String toString() {
        return "ResponseCabinDto{" +
                "id=" + id +
                ", laptopId=" + laptopId +
                '}';
    }
}
