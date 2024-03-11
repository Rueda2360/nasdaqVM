package com.politecnicomalaga.NasdaqOilPrices.Control;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.politecnicomalaga.NasdaqOilPrices.Model.Price;

import java.util.List;

public class JornadaListViewModel extends ViewModel {
    private MutableLiveData<List<Price>> listaPrecios;
    public LiveData<List<Price>> getPrices() {
        if (listaPrecios == null) {
            listaPrecios = new MutableLiveData<List<Price>>();
            loadPrecios();
        }
        return listaPrecios;
    }

    private void loadPrecios() {
        // Do an asynchronous operation to fetch precios.
        MainController.getSingleton().requestDataFromNasdaq(this);
        listaPrecios.postValue(MainController.getSingleton().getList());

    }

    public void setData(List<Price> list) {

        listaPrecios.postValue(list);
    }

}
