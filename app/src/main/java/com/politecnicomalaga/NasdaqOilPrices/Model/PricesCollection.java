package com.politecnicomalaga.NasdaqOilPrices.Model;

import java.util.ArrayList;
import java.util.List;

public class PricesCollection {

    //Agregación de un montón de precios
    private List<Price> myPricesList;

    public PricesCollection() {
        myPricesList = new ArrayList<Price>();
    }

    //Métodos CRUD o de gestión de la colección
    //que hemos encapsulado

    //Añadir un price a la lista
    public void add(Price p) {
        boolean esta=false;
        for(Price temp:myPricesList){
            if(p.getDay().equals(temp.getDay())){
                esta=true;
            }
        }
        if(!esta){
            myPricesList.add(p);
        }
    }

    public int getSize(){
        return this.myPricesList.size();
    }


    //Añadir un precio dado los valores
    public void add(String fecha, float precio, String tendencia) {
        if(!(fecha.isEmpty() || precio<=0 || tendencia.isEmpty())) {
            Price p = new Price(fecha, precio, tendencia);
            myPricesList.add(p);
        }
    }


    //Buscar un precio por fecha
    public Price search(String fecha) {
        for(Price p: myPricesList){
            if(p.getDay().equals(fecha)){
                return p;
            }
        }
        return null;
    }


    //Devolver una copia en forma de array de los precios
    public Price[] getCopy() {
        return myPricesList.toArray(new Price[0]);
    }

    public void clear(){
        this.myPricesList.clear();
    }



}
