package com.politecnicomalaga.NasdaqOilPrices.Control;

import com.politecnicomalaga.NasdaqOilPrices.Model.Coordenada;
import com.politecnicomalaga.NasdaqOilPrices.Model.Price;
import com.politecnicomalaga.NasdaqOilPrices.View.MainActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainController {

    //SINGLETON Controller
    private static final String DATA_URL = "https://data.nasdaq.com/api/v3/datatables/QDL/OPEC.json?";
    private static MainController mySingleController;
    private List<Coordenada> cartesiano;
    private List<Price> dataRequested;
    private JornadaListViewModel myjlViewModel;

    private static MainActivity activeActivity;
    //Comportamiento
    //Constructor
    private MainController() {

        dataRequested = new ArrayList<Price>();
        myjlViewModel = null;
        cartesiano = new ArrayList<Coordenada>();
        //dataShowed = new ArrayList<Price>();
    }

    //Get instance
    public static MainController getSingleton() {
        if (MainController.mySingleController == null) {
            mySingleController = new MainController();
        }
        return mySingleController;
    }

    //To send data to view
    //public List<Price> getDataFromNasdaq() {
    //    return this.dataRequested;
    //}

    //Called from the view
    public void requestDataFromNasdaq(JornadaListViewModel myjlViewModel) {
        Peticion p = new Peticion();
        p.requestData(DATA_URL);

        //Necesitamos el ViewModel instanciado para poder actualizar sus datos de manera asíncrona
        //cuando llegue la info
        this.myjlViewModel = myjlViewModel;
    }

    //Called when onResponse is OK
    public void setDataFromNasdaq(String json) {

        Respuesta answer = new Respuesta(json);
        dataRequested = answer.getData();

        //Relleno los datos de cartesiano
        rellenarCoordenadas(dataRequested);



        //Ahora cada vez que nos traemos info, lo que hacemos es informar al ViewModel, no al activity
        if (myjlViewModel!=null) myjlViewModel.setData(dataRequested);
    }

    private void rellenarCoordenadas(List<Price> dataRequested) {
        //Dar datos a list<Coordenadas> en for:each
        for(int i=1;i<=100;i++){
            Coordenada coordenada = new Coordenada(i,dataRequested.get(i).getPrice());
            cartesiano.add(coordenada);
        }
    }

    public void setErrorFromNasdaq(String error) {

        //Para los errores, seguimos funcionando como habitualmente, con la activity. Habría que cambiar
        //este método para hacerlo "igual" que con la info de la recyclerview

        //Set error data to the activity
        MainController.activeActivity.errorData(error);
    }

    public List<Coordenada> getCartesiano() {
        return cartesiano;
    }

    //este método lo usamos sólo de cara a los errores.
    public static void setActivity(MainActivity myAct) {
        activeActivity = myAct;
    }

    //Getter para coger la lista última que tengamos. Puede estar vacía o no. Ahora sólo lo debe de usar
    //el ViewModel, nadie más.
    public List<Price> getList() {
        return this.dataRequested;
    }

    //Listado de coordenadas


}
