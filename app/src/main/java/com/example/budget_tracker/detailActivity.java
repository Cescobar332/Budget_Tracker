package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv_filter1 = findViewById(R.id.tv_filter1);
        TextView tv_filter2 = findViewById(R.id.tv_filter2);
        TextView tv_filter3 = findViewById(R.id.tv_filter3);

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

        if (filter.size() >= 3) {
            tv_filter1.setText(filter.get(1));
            tv_filter2.setText(filter.get(2));
            tv_filter3.setText(filter.get(3));}

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