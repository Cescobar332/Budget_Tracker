package com.example.budget_tracker;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class detailActivity extends AppCompatActivity {
    private String month;
    private TextView tvTotal;

    Button btnIncome, btnSaving, btnExpense;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    List<String> BolsillosNames;
    String mes;
    private static final String TAG = "MainActivity";
    Double incomesTotal = 0.0;
    Double expensesTotal = 0.0;
    Double savingsTotal = 0.0;

    // Calcular la suma total según la fórmula
    Double total = incomesTotal - (expensesTotal + savingsTotal);
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

        sumarValores("incomes");
        sumarValores("savings");
        sumarValores("expenses");

        Intent intent = getIntent();
        mes = intent.getStringExtra("month");

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
        ArrayList<Bolsillo> bolsillosList = new ArrayList<>();

        firestore.collection("bolsillos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Bolsillo bolsillo = document.toObject(Bolsillo.class);
                            bolsillosList.add(bolsillo);
                        }
                        BolsillosNames = new ArrayList<>();
                        for (Bolsillo bolsillo   : bolsillosList) {
                            BolsillosNames.add(bolsillo.getNombre());
                        }
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.custom_spinner_item2,BolsillosNames);
                        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
                        mySpinner2.setAdapter(adapter2);

                        // Aquí es donde debes inicializar el Spinner con los nombres de las categorías
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
        //String[] filterArray = getResources().getStringArray(R.array.wallet_array);
        //List<String> filter = new ArrayList<>(Arrays.asList(filterArray));
        //ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.custom_spinner_item2, filter);
        //adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
       // mySpinner2.setAdapter(adapter2);

        //if (filter.size() >= 3) {
        //    tv_filter1.setText(filter.get(0));
        //    tv_filter2.setText(filter.get(1));
        //    tv_filter3.setText(filter.get(2));
        //}

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
                String selectedOption = BolsillosNames.get(position);

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
        startActivity(intent);
    }

    public void AddIncome(View view){

        Intent intent =  new Intent(this, incomesActivity.class);
        intent.putExtra("month",mes);
        startActivity(intent);
    }

    public void AddSaving(View view){
        Intent intent =  new Intent(this, savingsActivity.class);
        intent.putExtra("month",mes);
        startActivity(intent);
    }

    public void AddExpense(View view){
        Intent intent =  new Intent(this, expensesActivity.class);
        intent.putExtra("month",mes);
        startActivity(intent);
    }
    private void sumarValores(String nombreColeccion) {
        TextView tvTotal = findViewById(R.id.tv_total);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference coleccionRef = db.collection(nombreColeccion);

        coleccionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Double sum = 0.0;
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Long valorLong = documentSnapshot.getLong("value");
                    if (valorLong != null) {
                        Double valor = valorLong.doubleValue();
                        sum += valor;
                    }
                }


                Toast.makeText(detailActivity.this, "Suma total para la colección " + nombreColeccion + ": " + sum, Toast.LENGTH_SHORT).show();

                // Actualizar las variables según la colección
                if (nombreColeccion.equals("incomes")) {
                    incomesTotal = sum;
                } else if (nombreColeccion.equals("expenses")) {
                    expensesTotal = sum;
                } else if (nombreColeccion.equals("savings")) {
                    savingsTotal = sum;
                }

                // Calcular la suma total final
                Double total = incomesTotal - (expensesTotal + savingsTotal);

                Toast.makeText(detailActivity.this, "Suma total final: " + total, Toast.LENGTH_SHORT).show();
                // Mostrar el resultado en el TextView
                tvTotal.setText((total).toString());
            }

        });
    }
}

