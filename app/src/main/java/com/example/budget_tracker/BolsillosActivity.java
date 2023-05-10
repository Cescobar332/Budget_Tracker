package com.example.budget_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BolsillosActivity extends AppCompatActivity {
    private ArrayList<Bolsillo> listaPrincipalBolsillos = new ArrayList<>();
    private RecyclerView rvListadoBolsillos;
    AdaptadorPersonalizado miAdaptador = new AdaptadorPersonalizado(listaPrincipalBolsillos);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolsillos);

        rvListadoBolsillos =findViewById(R.id.rv_listado_bolsillos);

        miAdaptador.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void OnItemClick(Bolsillo miBolsillo, int posicion) {
                Intent intent = new Intent(BolsillosActivity.this, MainActivity.class);
                intent.putExtra("bolsillo", miBolsillo);
                intent.putExtra("bolsillo_id", miBolsillo.getId());
                startActivity(intent);
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
    public void AgregarBolsillo(View view) {
        return;
    }
    public void CerrarSesion(View view){
        SharedPreferences misPreferencias = getSharedPreferences("budget_tracker", MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.clear();
        miEditor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}