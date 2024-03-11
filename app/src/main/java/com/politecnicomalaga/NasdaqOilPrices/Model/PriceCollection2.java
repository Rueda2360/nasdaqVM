package com.politecnicomalaga.NasdaqOilPrices.Model;

import java.util.ArrayList;
import java.util.List;

public class PriceCollection2 {

    private List<Price> myListia;

    public PriceCollection2() {
        myListia=new ArrayList<>();
    }

    //Añadir mediante precio
    public void add(Price p) {
        if (p != null) {
            if(this.search(p.getDay())==null) {
                if (p.getDay() != null && p.getPrice() != 0 && p.getTrend() != null) {
                    myListia.add(p);
                }
            }
        }
    }
    public void add(String fecha,float precio,String tendencia){
        if (fecha != null && precio != 0 && tendencia != null) {
            if (this.search(fecha) == null) {
                myListia.add(new Price(fecha, precio, tendencia));
            }
        }
    }

    public Price search(String fecha){
        if (fecha!=null){
            for(int f=0;f<myListia.size();f++){
                if(myListia.get(f).getDay().equals(fecha)){
                    return myListia.get(f);
                }
            }
        }
        return null;
    }

    public Price[] getCopy(){
        return myListia.toArray(new Price[0]);
    }
    public boolean comprobarFormatoFecha(String fecha){

        return false;
    }
}
