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

public class incomesActivity extends AppCompatActivity {

    EditText etNewCategory;
    Button btnSave;

    List<String> filter;
    private ArrayAdapter<String> adapter2;

    Spinner mySpinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes);
        TextView tv_filter1 = findViewById(R.id.tv_filter1111);
        TextView tv_filter2 = findViewById(R.id.tv_filter2222);
        TextView tv_filter3 = findViewById(R.id.tv_filter3333);
        etNewCategory = findViewById(R.id.et_new_category);
        btnSave = findViewById(R.id.btn_save);
        mySpinner2 = findViewById(R.id.my_spinner_incomes);
        String[] filterArray = getResources().getStringArray(R.array.filter_array);
        filter = new ArrayList<>(Arrays.asList(filterArray));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.custom_spinner_item2, filter);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mySpinner2.setAdapter(adapter2);

        if (filter.size() >= 3) {
            tv_filter1.setText(filter.get(1));
            tv_filter2.setText(filter.get(2));
            tv_filter3.setText(filter.get(3));
        }

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

    public void clickSave (View view){
        String cat = etNewCategory.getText().toString();
        filter.add(cat);
        ArrayAdapter<String> adapter2 = (ArrayAdapter<String>) mySpinner2.getAdapter();
        adapter2.notifyDataSetChanged();
        etNewCategory.setText("");
    }
}