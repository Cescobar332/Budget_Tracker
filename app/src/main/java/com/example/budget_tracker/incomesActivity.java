package com.example.budget_tracker;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class incomesActivity extends AppCompatActivity {

    private EditText etNewCategory, etDetail, etValueIncomes;
    private Button btnSave, btnDone;

    List<String> categoriesNames;
    private ArrayAdapter<String> adapter2;


    Spinner mySpinner2;

    String selectedOption;

    TextView tvIncomes, tvfilter1, tvfilter2, tvfilter3;
    Double valorActual = 0.0;
    Double valor = 0.0;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    CollectionReference categoriasRef = firestore.collection("categorias");
    String month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes);
        Intent intent = getIntent();
        month = intent.getStringExtra("month");
        tvfilter1 = findViewById(R.id.tv_filter1111);
        tvfilter2 = findViewById(R.id.tv_filter2222);
        tvfilter3 = findViewById(R.id.tv_filter3333);
        etNewCategory = findViewById(R.id.et_new_category);
        tvIncomes = findViewById(R.id.tv_incomes);
        etDetail = findViewById(R.id.et_detail);
        etValueIncomes = findViewById(R.id.et_value_incomes);
        btnSave = findViewById(R.id.btn_save);
        mySpinner2 = findViewById(R.id.my_spinner_incomes);
        ArrayList<Category> categoriesList = new ArrayList<>();

        firestore.collection("categorias")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Category category = document.toObject(Category.class);
                            categoriesList.add(category);
                        }
                        categoriesNames = new ArrayList<>();
                        for (Category category : categoriesList) {
                            categoriesNames.add(category.getNombre());
                        }
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.custom_spinner_item2,categoriesNames);
                        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
                        mySpinner2.setAdapter(adapter2);
                        tvfilter1.setText(categoriesNames.get(0));
                        tvfilter2.setText(categoriesNames.get(1));
                        tvfilter3.setText(categoriesNames.get(2));
                        // Aquí es donde debes inicializar el Spinner con los nombres de las categorías
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });

        tvfilter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoriesNames.size() > 0) {
                    int index = 0; // Índice de la opción que deseas seleccionar en mySpinner2
                    mySpinner2.setSelection(index);
                }
            }
        });

        tvfilter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoriesNames.size() > 0) {
                    int index = 1; // Índice de la opción que deseas seleccionar en mySpinner2
                    mySpinner2.setSelection(index);
                }
            }
        });

        tvfilter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoriesNames.size() > 0) {
                    int index = 2; // Índice de la opción que deseas seleccionar en mySpinner2
                    mySpinner2.setSelection(index);
                }
            }
        });


        mySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOption = categoriesNames.get(position);

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
        if (TextUtils.isEmpty(cat)) {
            Toast.makeText(this, "Debes ingresar un nombre para la categoría", Toast.LENGTH_SHORT).show();
            return; // Salir del método si el campo está vacío
        }
        categoriesNames.add(cat);
        adapter2 = (ArrayAdapter<String>) mySpinner2.getAdapter();
        adapter2.notifyDataSetChanged();
        Double valsav = 0.0;
        Double valexp = 0.0;
        Double valinc = 0.0;
        Category category = new Category();
        category.setNombre(cat);
        category.setMontosav(valsav);
        category.setMontoexp(valexp);
        category.setMontoinc(valinc);

        firestore.collection("categorias").add(category)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Se creó la categoría.", Toast.LENGTH_SHORT).show();
                    categoriesNames.add(cat);
                    adapter2.notifyDataSetChanged();
                    etNewCategory.setText("");
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error al crear la categoría.", e);
                    Toast.makeText(this, "Error al crear la categoría.", Toast.LENGTH_SHORT).show();
                });
        etNewCategory.setText("");
    }
    public void ClickDone (View view){
        String valorIngresos = etValueIncomes.getText().toString();
        String descIngresos = etDetail.getText().toString();
        if (TextUtils.isEmpty(valorIngresos)) {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            return; // Salir del método si el campo está vacío
        }
        if (!TextUtils.isDigitsOnly(valorIngresos)) {
            Toast.makeText(this, "El valor ingresado no es válido", Toast.LENGTH_SHORT).show();
            return; // Salir del método si el contenido no es numérico
        }
        if (TextUtils.isEmpty(descIngresos)) {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            return; // Salir del método si el campo está vacío
        }
        String tipo = tvIncomes.getText().toString();
        String categoria = selectedOption;
        valor = Double.parseDouble(etValueIncomes.getText().toString());
        String descripcion = etDetail.getText().toString();
        Income income = new Income();
        income.setCategory(categoria);
        income.setType(tipo);
        income.setDetail(descripcion);
        income.setValue(valor);
        income.setMonth(month);
        firestore.collection("incomes").add(income);
        Toast.makeText(this, "Se creo el income", Toast.LENGTH_SHORT).show();
        Intent myintent = new Intent(this, detailActivity.class);
        myintent.putExtra("catinc", categoria);
        myintent.putExtra("valinc", valor);
        myintent.putExtra("tipinc", tipo);
        myintent.putExtra("descinc", descripcion);
        myintent.putExtra("month", month);
        Query query = categoriasRef.whereEqualTo("nombre", categoria);
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot snapshot = task.getResult();
                if (!snapshot.isEmpty()) {
                    // El objeto existe en la colección
                    DocumentSnapshot document = snapshot.getDocuments().get(0);
                    if (document.getDouble("montoinc") == null) {

                        valorActual = 0.0;
                    } else {
                        valorActual = document.getDouble("montoinc");
                    }
                    Double valorNuevo = valorActual + Double.parseDouble(income.getValue().toString());
                    document.getReference().update("montoinc", valorNuevo);
                } else {
                    // El objeto no existe en la colección
                    Log.d(TAG, "El objeto no existe en la colección");
                }
            } else {
                // Ocurrió un error al ejecutar la consulta
                Log.e(TAG, "Error al ejecutar la consulta", task.getException());
            }
        });
        startActivity(myintent);



    }
}