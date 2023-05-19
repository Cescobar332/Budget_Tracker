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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class detailActivity extends AppCompatActivity {
    private String month;

    Button btnIncome, btnSaving, btnExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btnIncome = findViewById(R.id.btn_add1);
        btnExpense = findViewById(R.id.btn_add);
        btnSaving = findViewById(R.id.btn_add2);
        TextView tv_filter1 = findViewById(R.id.tv_filter1);
        TextView tv_filter2 = findViewById(R.id.tv_filter2);
        TextView tv_filter3 = findViewById(R.id.tv_filter3);
//        month = getIntent().getStringExtra("month");

        Spinner mySpinner = findViewById(R.id.my_spinner_savings);
        String[] optionsArray = getResources().getStringArray(R.array.options_array);
        List<String> options = new ArrayList<>(Arrays.asList(optionsArray));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item, options);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        // Obtener el mes seleccionado de SharedPreferences
        SharedPreferences preferences = getSharedPreferences("budget_tracker", MODE_PRIVATE);
        String selectedMonth = preferences.getString("selectedMonth", "");


        int selectedOptionIndex =  options.indexOf(selectedMonth);
        /*for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(month)) {
                selectedOptionIndex = i; // Encontramos el mes, guardamos su posición
                break; // Salimos del bucle
            }
        }*/

        if (selectedOptionIndex >= 0) {
            mySpinner.setSelection(selectedOptionIndex, true);
            Toast.makeText(detailActivity.this, "Selected Month: " + selectedMonth, Toast.LENGTH_SHORT).show();
        }




        Spinner mySpinner2 = findViewById(R.id.my_spinner2);
        String[] filterArray = getResources().getStringArray(R.array.wallet_array);
        List<String> filter = new ArrayList<>(Arrays.asList(filterArray));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.custom_spinner_item2, filter);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mySpinner2.setAdapter(adapter2);

        if (filter.size() >= 3) {
            tv_filter1.setText(filter.get(0));
            tv_filter2.setText(filter.get(1));
            tv_filter3.setText(filter.get(2));
        }

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = options.get(position);
                // Guardar el mes seleccionado en SharedPreferences
                SharedPreferences.Editor editor = getSharedPreferences("budget_tracker", MODE_PRIVATE).edit();
                editor.putString("selectedMonth", selectedOption);
                editor.apply();
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
                // L贸gica para cuando no se ha seleccionado ninguna opci贸n del spinner
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