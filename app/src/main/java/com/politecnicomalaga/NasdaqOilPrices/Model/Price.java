package com.politecnicomalaga.NasdaqOilPrices.Model;

public class Price {

    //Aunque sabemos que precios es un double, lo guardamos en String porque nos llega en String
    //y se muestra como texto, al fin y al cabo

    //POJO CLASS
    private String day;
    private float price;

    private String trend;

    //Comportamiento
    //Construtor

    public Price(String day, float price, String trend) {
        if(!(day.isEmpty()||price<=0||trend.isEmpty())){
            this.day = day;
            this.price = price;
            this.trend = trend;
        }

    }


    //Getters


    public String getDay() {
        return day;
    }

    public float getPrice() {
        return price;
    }

    public String getTrend() { return trend;}
}
