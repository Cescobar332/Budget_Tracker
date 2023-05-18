package com.example.budget_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BolsillosActivity extends AppCompatActivity {
    private ArrayList<Bolsillo> listaPrincipalBolsillos = new ArrayList<>();
    private RecyclerView rvListadoBolsillos;
    private TextView tvBalance, tvSaved, tvSpent;
    private EditText etNombreBolsillo, etMontoBolsillo;
    private String idBolsillo;
    AdaptadorPersonalizado miAdaptador = new AdaptadorPersonalizado(listaPrincipalBolsillos);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolsillos);
        tvBalance = findViewById(R.id.tv_total_bolsillos);
        tvSaved = findViewById(R.id.tv_savenum_bolsillos);
        tvSpent = findViewById(R.id.tv_spentnum_bolsillos);
        etNombreBolsillo = findViewById(R.id.et_new_wallet);
        etMontoBolsillo = findViewById(R.id.et_new_wallet_mount);
        idBolsillo = getIntent().getStringExtra("bolsillo_id");
        rvListadoBolsillos =findViewById(R.id.rv_listado_bolsillos);

        miAdaptador.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void OnItemClick(Bolsillo miBolsillo, int posicion) {
                Intent intent = new Intent(BolsillosActivity.this, MainActivity.class);
                intent.putExtra("bolsillo", miBolsillo);
                intent.putExtra("bolsillo_id", miBolsillo.getId());
                startActivity(intent);
                ArrayList<String> listaPrincipal = new ArrayList<>();
                listaPrincipal.add(String.valueOf(listaPrincipalBolsillos));
                Intent intent2 = new Intent(BolsillosActivity.this, detailActivity.class);
                intent2.putStringArrayListExtra("listado", listaPrincipal);
                startActivity(intent2);
            }

            @Override
            public void OnItemBtnEliminarClick(Bolsillo miBolsillo, int posicion) {
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("bolsillos").document(miBolsillo.getId()).delete();
                listaPrincipalBolsillos.remove(posicion);
                miAdaptador.setListadoInformacion(listaPrincipalBolsillos);
            }
        });
        rvListadoBolsillos.setAdapter(miAdaptador);
        rvListadoBolsillos.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onResume() {
        super.onResume();
        listaPrincipalBolsillos.clear();
        cargarDatos();
    }
    public void cargarDatos(){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("bolsillos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot document : task.getResult()){
                        Bolsillo bolsilloAtrapado = document.toObject(Bolsillo.class);
                        assert bolsilloAtrapado != null;
                        bolsilloAtrapado.setId(document.getId());
                        listaPrincipalBolsillos.add(bolsilloAtrapado);
                    }
                    miAdaptador.setListadoInformacion(listaPrincipalBolsillos);
                }else{
                    Toast.makeText(BolsillosActivity.this, "No se pudo conectar al servidor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private String obtenerSiglaNombre(String nombreBolsillo) {
        StringBuilder sigla = new StringBuilder();
        String[] palabras = nombreBolsillo.split(" ");

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                sigla.append(Character.toUpperCase(palabra.charAt(0)));
            }
        }

        return sigla.toString();
    }

    public void AgregarBolsillo(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        String nombre = etNombreBolsillo.getText().toString();
        Double monto =  Double.parseDouble(etMontoBolsillo.getText().toString());
        String siglaNombre = obtenerSiglaNombre(nombre);
        Bolsillo nuevoBolsillo = new Bolsillo();
        nuevoBolsillo.setNombre(nombre);
        nuevoBolsillo.setSigla(siglaNombre);
        nuevoBolsillo.setMonto(monto);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("bolsillos").add(nuevoBolsillo)
                .addOnSuccessListener(documentReference -> {
                    String bolsilloId = documentReference.getId();
                    nuevoBolsillo.setId(bolsilloId);
                    listaPrincipalBolsillos.add(nuevoBolsillo);
                    miAdaptador.setListadoInformacion(listaPrincipalBolsillos);

                    Toast.makeText(this, "Bolsillo agregado con éxito", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al agregar el bolsillo", Toast.LENGTH_SHORT).show();
                });
        etNombreBolsillo.setText("");
        etMontoBolsillo.setText("");
        etMontoBolsillo.clearFocus();
    }

    public void CerrarSesion(View view){
        SharedPreferences misPreferencias = getSharedPreferences("budget_tracker", MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.clear();
        miEditor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    public void NewWallet(View view){
        etNombreBolsillo.requestFocus();
    }
}