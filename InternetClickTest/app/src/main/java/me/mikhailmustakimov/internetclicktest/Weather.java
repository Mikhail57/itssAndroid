package me.mikhailmustakimov.internetclicktest;

/**
 * Created by student on 07.01.2016.
 */
public class Weather {

    String date, wind, cloud;
    int tod, pressure, temp, humidity;

    public Weather(String date, String wind, String cloud,
                   int tod, int pressure, int temp, int humidity) {
        this.date=date;
        this.wind=wind;
        this.cloud=cloud;
        this.tod=tod;
        this.pressure=pressure;
        this.temp=temp;
        this.humidity=humidity;
    }
}
