package com.politecnicomalaga.NasdaqOilPrices.Model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class PricesCollectionTest {


    private static PriceCollection2 myPricesList;
    private static Price p;

    @BeforeClass
    public static void inicializar() {
        if (myPricesList == null) {
            myPricesList = new PriceCollection2();

            p = new Price("2024-01-24",56.7f,"up");

            myPricesList.add(new Price("2024-01-24",56.7f,"up"));
        }
    }

    @Test
    public void constructor() {

        for(Price p: PricesCollectionTest.myPricesList.getCopy()){
            assertTrue(p.getDay().equals("2024-01-24"));
            assertTrue(p.getPrice() == 56.7f);
            assertTrue(p.getTrend().equals("up"));
        }

    }
    @Test
    public void add() {
        Price p = new Price ("2024-01-24",56.7f,"up");
        myPricesList.add(p);
        Price[] listaPrecio = myPricesList.getCopy();
        assertTrue(listaPrecio[0].getDay().equals(p.getDay()));
    }

    @Test
    public void search() {
        Price p = new Price("2024-01-24",56.7f,"up");
        boolean siono = false;
        myPricesList.add(p);
        for(Price p2 : myPricesList.getCopy()){
            if(p2.getDay().equals("2024-01-24")){
                siono=true;
            }
        }
        assertTrue(siono);

    }

    @Test
    public void getCopy() {
        Price unPrecio = new Price("2024-01-24",56.7f,"up");
        myPricesList.add(unPrecio);

        assertArrayEquals(myPricesList.getCopy(), myPricesList.getCopy());

    }
}