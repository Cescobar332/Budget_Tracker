package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class detailActivity extends AppCompatActivity implements Serializable {
    private Spinner spinner2;
    private AdaptadorPersonalizado miAdaptador;
    private Button btnadd, btnadd1, btnadd2;
    private TextView tvTotal;
    private int mTotalIncomes = 0;

    public static void receiveArrayList(ArrayList<Bolsillo> listaPrincipalBolsillos) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnadd = findViewById(R.id.btn_add1);
        btnadd1 = findViewById(R.id.btn_add);
        btnadd2 = findViewById(R.id.btn_add2);

        TextView tv_filter1 = findViewById(R.id.tv_filter1);
        TextView tv_filter2 = findViewById(R.id.tv_filter2);
        TextView tv_filter3 = findViewById(R.id.tv_filter3);
        TextView tvTotal = findViewById(R.id.tv_total);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailActivity.this, incomesActivity.class);
                startActivity(intent);
            }
        });
        btnadd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailActivity.this, expensesActivity.class);
                startActivity(intent);
            }
        });
        btnadd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailActivity.this, savingsActivity.class);
                startActivity(intent);
            }
        });

        Spinner mySpinner = findViewById(R.id.my_spinner_savings);
        String[] optionsArray = getResources().getStringArray(R.array.options_array);
        List<String> options = new ArrayList<>(Arrays.asList(optionsArray));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item, options);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);

        Spinner mySpinner2 = findViewById(R.id.my_spinner2);
        String[] filterArray = getResources().getStringArray(R.array.filter_array);
        List<String> filter = new ArrayList<>(Arrays.asList(filterArray));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.custom_spinner_item2, filter);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mySpinner2.setAdapter(adapter2);
        mySpinner.setAdapter(adapter);

        ArrayList<String> listadoPrincipalProductos = getIntent().getStringArrayListExtra("listado");

        // Crea un ArrayAdapter y asigna los datos al Spinner
        ArrayAdapter<String> myadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listadoPrincipalProductos);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myadapter);

        String texto = getIntent().getStringExtra("valinc");

        // Muestra el valor en el TextView
        tvTotal.setText(texto);

        mySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object elementoSeleccionado = parent.getItemAtPosition(position);
                // Hacer algo con el elemento seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se ha seleccionado nada en el Spinner
            }
        });


        String monthName = getIntent().getStringExtra("MONTH_NAME");

        if (filter.size() >= 3) {
            tv_filter1.setText(filter.get(1));
            tv_filter2.setText(filter.get(2));
            tv_filter3.setText(filter.get(3));
        }

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = options.get(position);
                // Lógica para cuando se selecciona una opción del spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Lógica para cuando no se ha seleccionado ninguna opción del spinner
            }
        });

        mySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = filter.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Lógica para cuando no se ha seleccionado ninguna opción del spinner
            }
        });
    }



}