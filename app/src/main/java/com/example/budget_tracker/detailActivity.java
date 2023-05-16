package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class detailActivity extends AppCompatActivity {


    Button btnIncome, btnSaving, btnExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btnIncome = findViewById(R.id.btn_add1);
        btnExpense = findViewById(R.id.btn_add);
        btnSaving = findViewById(R.id.btn_add2);
        TextView tvfilter1 = findViewById(R.id.tv_filter1);
        TextView tvfilter2 = findViewById(R.id.tv_filter2);
        TextView tvfilter3 = findViewById(R.id.tv_filter3);

        Spinner mySpinner = findViewById(R.id.my_spinner_savings);
        String[] optionsArray = getResources().getStringArray(R.array.options_array);
        List<String> options = new ArrayList<>(Arrays.asList(optionsArray));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item, options);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);

        Spinner mySpinner2 = findViewById(R.id.my_spinner2);
        String[] filterArray = getResources().getStringArray(R.array.wallet_array);
        List<String> filter = new ArrayList<>(Arrays.asList(filterArray));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.custom_spinner_item2, filter);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mySpinner2.setAdapter(adapter2);
        mySpinner.setAdapter(adapter);

        if (filter.size() >= 3) {
            tvfilter1.setText(filter.get(0));
            tvfilter2.setText(filter.get(1));
            tvfilter3.setText(filter.get(2));
        }

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = options.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Lógica para cuando no se ha seleccionado ninguna opción del spinner
            }
        });

        tvfilter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filter.size() > 0) {
                    int index = 0; // Índice de la opción que deseas seleccionar en mySpinner2
                    mySpinner2.setSelection(index);
                }
            }
        });

        tvfilter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filter.size() > 0) {
                    int index = 1; // Índice de la opción que deseas seleccionar en mySpinner2
                    mySpinner2.setSelection(index);
                }
            }
        });

        tvfilter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filter.size() > 0) {
                    int index = 2; // Índice de la opción que deseas seleccionar en mySpinner2
                    mySpinner2.setSelection(index);
                }
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

    public void CerrarSesion(View view) {
        SharedPreferences misPreferencias = getSharedPreferences("budget_tracker", MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.clear();
        miEditor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    public void clickOverview (View view){
        Intent intent = new Intent(detailActivity.this, OverviewActivity.class);
        //intent.putExtra("myIncome");
        startActivity(intent);
    }

    public void AddIncome(View view){
        Intent intent =  new Intent(this, incomesActivity.class);
        startActivity(intent);
    }

    public void AddSaving(View view){
        Intent intent =  new Intent(this, savingsActivity.class);
        startActivity(intent);
    }

    public void AddExpense(View view){
        Intent intent =  new Intent(this, expensesActivity.class);
        startActivity(intent);
    }

}