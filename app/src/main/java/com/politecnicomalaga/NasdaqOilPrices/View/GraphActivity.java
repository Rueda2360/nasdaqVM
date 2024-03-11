package com.politecnicomalaga.NasdaqOilPrices.View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.politecnicomalaga.NasdaqOilPrices.Control.MainController;
import com.politecnicomalaga.NasdaqOilPrices.Model.Coordenada;
import com.politecnicomalaga.NasdaqOilPrices.R;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        float valorMasAlto=0;
        float valorMasBajo=10000;

        //Cargamos los datos de los primeros 100 valores
        List<Coordenada> listaCoordenadas= MainController.getSingleton().getCartesiano();
        GraphView gvSensor = findViewById(R.id.gvGrafico);
        //El DataPoint es lo que usa el GraphView para poner las cosas en pantalla
        ArrayList<DataPoint> puntos = new ArrayList<>();

        for(Coordenada coordenada: listaCoordenadas){
            //DataPoint es el punto en concreto
            DataPoint dataPoint = new DataPoint(coordenada.getX(),coordenada.getY());
            puntos.add(dataPoint);

            if( coordenada.getY()>valorMasAlto){
                valorMasAlto= coordenada.getY();
            }
            if(coordenada.getY()<valorMasBajo){
                valorMasBajo= coordenada.getY();

            }
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(puntos.toArray(new DataPoint[0]));

        series.setThickness(5);

        series.setDrawBackground(true);
        series.setColor(Color.argb(255, 255, 60, 60));
        series.setBackgroundColor(Color.argb(100, 204, 119, 119));
        series.setDrawDataPoints(true);

        gvSensor.setTitle("Nasdaq");
        gvSensor.getLegendRenderer().setVisible(true);
        gvSensor.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        gvSensor.getLegendRenderer().setBackgroundColor(Color.rgb(220,220,220));


        gvSensor.getViewport().setYAxisBoundsManual(true);
        gvSensor.getViewport().setMinY(valorMasBajo-10);
        gvSensor.getViewport().setMaxY(valorMasAlto+10);

        gvSensor.getViewport().setXAxisBoundsManual(true);
        gvSensor.getViewport().setMinX(0);
        gvSensor.getViewport().setMaxX(100);

        //DECORATIVO

        gvSensor.setTitleTextSize(40);
        gvSensor.setTitleColor(Color.BLACK);
        gvSensor.getViewport().setBorderColor(Color.BLUE);

        gvSensor.removeAllSeries();
        gvSensor.addSeries(series);



    }
}