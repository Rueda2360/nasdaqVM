package com.politecnicomalaga.NasdaqOilPrices.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.politecnicomalaga.NasdaqOilPrices.Control.JornadaAdapter;
import com.politecnicomalaga.NasdaqOilPrices.Control.JornadaListViewModel;
import com.politecnicomalaga.NasdaqOilPrices.Control.MainController;
import com.politecnicomalaga.NasdaqOilPrices.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //private List<Price> mList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private JornadaAdapter mAdapter;

    private Map<String, Drawable> imageBank = new HashMap<String,Drawable>();

    private static MainActivity myActiveActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageBank.put("up",this.getResources().getDrawable(R.drawable.up,this.getTheme()));
        imageBank.put("down",this.getResources().getDrawable(R.drawable.down,this.getTheme()));
        imageBank.put("equal",this.getResources().getDrawable(R.drawable.eq,this.getTheme()));



        JornadaListViewModel vmodel = new ViewModelProvider(this).get(JornadaListViewModel.class);
        vmodel.getPrices().observe(this, prices -> {
            // update UI
            // Get a handle to the RecyclerView.
            mRecyclerView = findViewById(R.id.rv_prices);
            // Create an adapter and supply the data to be displayed.
            mAdapter = new JornadaAdapter(this, prices, imageBank);
            // Connect the adapter with the RecyclerView.
            mRecyclerView.setAdapter(mAdapter);
            // Give the RecyclerView a default layout manager.
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        });

        //Button generar = (Button) findViewById(R.id.b_getData);
        /*generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Getting data from Nasdaq Servers...", Toast.LENGTH_LONG).show();
                MainController.getSingleton().requestDataFromNasdaq();
            }
        });*/

        /*generar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                MainController.getSingleton().requestDataFromNasdaq();
            }
        });*/

        MainActivity.myActiveActivity = this;
        MainController.setActivity(this);
    }

    public void accessData() {


        mAdapter.notifyDataSetChanged();
        TextView tv = (TextView) findViewById(R.id.tv_oilDesc);
        tv.setText("Nasdaq Oil Prices yesterday");
    }

    public void errorData(String error) {
        TextView tv = (TextView) findViewById(R.id.tv_oilDesc);
        tv.setText(error);

    }


}
