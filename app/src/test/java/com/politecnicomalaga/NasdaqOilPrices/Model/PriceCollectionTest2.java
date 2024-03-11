package com.politecnicomalaga.NasdaqOilPrices.Model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PriceCollectionTest2 {


    private PricesCollection priceCollection2;
    private static Price precioEjemplo;
    @BeforeClass
    public static void constructor(){
        precioEjemplo=new Price("2024-10-15",30f,"up");
    }

    @Test
    public void addMasivo(){
        priceCollection2=new PricesCollection();
        for(int f=0;f<1000;f++){
            priceCollection2.add(new Price(String.valueOf(f),f,String.valueOf(f)));
        }
        assertEquals(priceCollection2.getCopy().length,999);
    }
    @Test
    public void addNulo() {
        priceCollection2=new PricesCollection();
        //Prueba nulo
        Price nulo=null;
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);
        //Precio con un valor nulo
        nulo=new Price(null,5f,"up");
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        nulo=new Price("2025-12-10",0,"up");
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        nulo=new Price("2025-12-10",5f,null);
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        //Prueba con 2 valores nulos
        nulo=new Price(null,0,"up");
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        nulo=new Price("2025-12-10",0,null);
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        nulo=new Price(null,5f,null);
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        //Prueba con los 3 valores nulos
        nulo=new Price(null,0,null);
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

    }

    @Test
    public void add(){
        priceCollection2= new PricesCollection();
        priceCollection2.add(precioEjemplo);
        assertEquals(priceCollection2.getCopy().length,1);
    }
    @Test
    public void testAddMasivo(){
        priceCollection2= new PricesCollection();
        for(int f=0;f<1000;f++){
            priceCollection2.add(String.valueOf(f),f,String.valueOf(f));
        }
        assertEquals(priceCollection2.getCopy().length,999);
    }

    @Test
    public void testAddNulo() {
        priceCollection2= new PricesCollection();
        Price nulo;
        //Precio con un valor nulo
        nulo=new Price(null,5f,"up");
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        nulo=new Price("2025-12-10",0,"up");
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        nulo=new Price("2025-12-10",5f,null);
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        //Prueba con 2 valores nulos
        nulo=new Price(null,0,"up");
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        nulo=new Price("2025-12-10",0,null);
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        nulo=new Price(null,5f,null);
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

        //Prueba con los 3 valores nulos
        nulo=new Price(null,0,null);
        priceCollection2.add(nulo);
        assertEquals(priceCollection2.getCopy().length,0);

    }


    @Test
    public void testAdd() {
        priceCollection2= new PricesCollection();
        priceCollection2.add(precioEjemplo.getDay(),precioEjemplo.getPrice(),precioEjemplo.getTrend());
        assertEquals(priceCollection2.getCopy().length,1);
    }

    @Test
    public void search() {
        priceCollection2= new PricesCollection();
        priceCollection2.add(precioEjemplo);
        assertEquals(priceCollection2.search(precioEjemplo.getDay()),precioEjemplo);
        assertNull(priceCollection2.search("20204-40-20"));
    }

    @Test
    public void getCopy() {
        priceCollection2= new PricesCollection();
        assertArrayEquals(priceCollection2.getCopy(), priceCollection2.getCopy());
    }
}