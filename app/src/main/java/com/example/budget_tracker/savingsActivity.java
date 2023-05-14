package com.example.budget_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class savingsActivity extends AppCompatActivity {
    private EditText etNewCategory, etValue2, etDetail;
    private Button btnSave, btnDone;

    private TextView tvSavings;

    List<String> filter;
    private ArrayAdapter<String> adapter2;

    String selectedOption;
    Spinner mySpinner2;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);
        TextView tv_filter1 = findViewById(R.id.tv_filter111);
        TextView tv_filter2 = findViewById(R.id.tv_filter222);
        TextView tv_filter3 = findViewById(R.id.tv_filter333);
        tvSavings = findViewById(R.id.tv_savings);
        etNewCategory = findViewById(R.id.et_new_category);
        btnDone = findViewById(R.id.btn_done);
        btnSave = findViewById(R.id.btn_save);
        etDetail = findViewById(R.id.et_detail);
        etValue2 = findViewById(R.id.et_value2);
        mySpinner2 = findViewById(R.id.my_spinner_savings);
        String[] filterArray = getResources().getStringArray(R.array.filter_array);
        filter = new ArrayList<>(Arrays.asList(filterArray));
        adapter2 = new ArrayAdapter<String>(this, R.layout.custom_spinner_item2, filter);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mySpinner2.setAdapter(adapter2);

        if (filter.size() >= 3) {
            tv_filter1.setText(filter.get(1));
            tv_filter2.setText(filter.get(2));
            tv_filter3.setText(filter.get(3));}

        mySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOption = filter.get(position);

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
    public void ClickSave (View view){
        String cat = etNewCategory.getText().toString();
        filter.add(cat);
        ArrayAdapter<String> adapter2 = (ArrayAdapter<String>) mySpinner2.getAdapter();
        adapter2.notifyDataSetChanged();
        Integer val = Integer.parseInt(etValue2.getText().toString());
        Category category = new Category(cat, val);
        category.setMonto(val);
        category.setNombre(cat);
        firestore.collection("categorias").add(category);
        etNewCategory.setText("");
    }

    public void ClickDone (View view){
        String tipo = tvSavings.getText().toString();
        String categoria = selectedOption;
        Integer valor = Integer.parseInt(etValue2.getText().toString());
        String descripcion = etDetail.getText().toString();
        Saving saving = new Saving(tipo,categoria,valor,descripcion);
        saving.setCategory(categoria);
        saving.setType(tipo);
        saving.setDetail(descripcion);
        saving.setValue(valor);
        firestore.collection("savings").add(saving);
        Toast.makeText(this, "Se creo el saving", Toast.LENGTH_SHORT).show();
    }
}