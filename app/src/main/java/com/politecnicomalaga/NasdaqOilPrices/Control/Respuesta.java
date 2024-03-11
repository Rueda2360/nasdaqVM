package com.politecnicomalaga.NasdaqOilPrices.Control;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.politecnicomalaga.NasdaqOilPrices.Model.Price;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase respuesta. Encapsulará los datos que nos devuelve la API REST
 * opendata de Nasdaq.
 *
 * El controlador le dará el texto a "analizar" en JSON y proporcionará
 * una serialización de los datos "amigable" para la vista. Es en
 * realidad un procesador de textos (parser)
 */

public class Respuesta {
    //ESTADO
    protected String datos;


    //COMPORTAMIENTO
    public Respuesta(String entrada) {
        datos = entrada;
    }


    public List<Price> getData() {
        String newTrend;
        int iPriceLast, iPriceNow = 0;
        float fPriceNow;

        LinkedList<Price> dataList = new LinkedList<>();
        //Parsing the JSON

        //TODO: parse JSON and add data to the list.
        JsonElement jsonElement = JsonParser.parseString(this.datos);

        JsonObject jso = jsonElement.getAsJsonObject().get("datatable").getAsJsonObject();
        JsonArray jsonLista = jso.get("data").getAsJsonArray();

        for(int i = 0;i<jsonLista.size();i++) {
            fPriceNow = Float.parseFloat(jsonLista.get(i).getAsJsonArray().get(1).getAsJsonPrimitive().getAsString());
            if (i==0) {
                newTrend = "equal";
                iPriceNow = (int)fPriceNow;

            } else {
                iPriceLast = iPriceNow;
                iPriceNow = (int)fPriceNow;
                if (iPriceNow > iPriceLast) newTrend = "up";
                else if (iPriceNow < iPriceLast) newTrend = "down";
                else newTrend = "equal";
            }
            dataList.add(new Price(jsonLista.get(i).getAsJsonArray().get(0).getAsJsonPrimitive().getAsString(),fPriceNow,newTrend));
        }

        return dataList;
    }
}
