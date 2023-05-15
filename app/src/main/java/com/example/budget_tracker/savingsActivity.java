package com.example.budget_tracker;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class savingsActivity extends AppCompatActivity {
    private EditText etNewCategory, etValue2, etDetail;
    private Button btnSave, btnDone;

    private TextView tvSavings;

    List<String> categoriesNames;
    private ArrayAdapter<String> adapter2;

    String selectedOption;
    Spinner mySpinner2;

    Double valorActual = 0.0;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    CollectionReference categoriasRef = firestore.collection("categorias");
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
                        tv_filter1.setText(categoriesNames.get(0));
                        tv_filter2.setText(categoriesNames.get(1));
                        tv_filter3.setText(categoriesNames.get(2));
                        // Aquí es donde debes inicializar el Spinner con los nombres de las categorías
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
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
    public void ClickSave (View view){
        String cat = etNewCategory.getText().toString();
        categoriesNames.add(cat);
        ArrayAdapter<String> adapter2 = (ArrayAdapter<String>) mySpinner2.getAdapter();
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
        String tipo = tvSavings.getText().toString();
        String categoria = selectedOption;
        Double valor = Double.parseDouble(etValue2.getText().toString());
        String descripcion = etDetail.getText().toString();
        Saving saving = new Saving(tipo,categoria,valor,descripcion);
        saving.setCategory(categoria);
        saving.setType(tipo);
        saving.setDetail(descripcion);
        saving.setValue(valor);
        firestore.collection("savings").add(saving);
        Toast.makeText(this, "Se creo el saving", Toast.LENGTH_SHORT).show();
        Intent myintent = new Intent(this, detailActivity.class);
        myintent.putExtra("catsav", categoria);
        myintent.putExtra("valsav", valor);
        myintent.putExtra("tipsav", tipo);
        myintent.putExtra("descsav", descripcion);
        Query query = categoriasRef.whereEqualTo("nombre", categoria);
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot snapshot = task.getResult();
                if (!snapshot.isEmpty()) {
                    // El objeto existe en la colección
                    DocumentSnapshot document = snapshot.getDocuments().get(0);
                    if(document.getDouble("valsav") == null){

                        valorActual = 0.0;
                    }else{
                        valorActual = document.getDouble("valsav");
                    }
                    Double valorNuevo =   valor + Double.parseDouble(saving.getValue().toString());
                    document.getReference().update("valsav", valorNuevo);
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