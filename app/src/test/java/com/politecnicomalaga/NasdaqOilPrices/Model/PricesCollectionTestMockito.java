package com.politecnicomalaga.NasdaqOilPrices.Model;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PricesCollectionTestMockito {

    List<Price> listaPrecios;

    @InjectMocks
    PricesCollection pricesMockCollection;
    @Mock
    Price priceMock;

    @Before
    public void init(){
        listaPrecios= new ArrayList<Price>();
    }

    @Test
    public void testingMocks(){
        Mockito.when(priceMock.getPrice()).thenReturn(56.7f);
        Mockito.when(priceMock.getDay()).thenReturn("2024-01-24");
        Mockito.when(priceMock.getTrend()).thenReturn("up");

    }
    @Test
    public void add(){
        Mockito.when(priceMock.getPrice()).thenReturn(56.7f);
        Mockito.when(priceMock.getDay()).thenReturn("2024-01-24");
        Mockito.when(priceMock.getTrend()).thenReturn("up");
        pricesMockCollection.add(priceMock);
        pricesMockCollection.add(priceMock);
        Assert.assertEquals(1,pricesMockCollection.getSize());

    }

    @Test
    public void search(){
        Mockito.when(priceMock.getDay()).thenReturn("2024-02-22");

        pricesMockCollection.add(priceMock);
        Price preciotemp = pricesMockCollection.search("2024-02-22");

        assertEquals(priceMock, preciotemp);
    }

}