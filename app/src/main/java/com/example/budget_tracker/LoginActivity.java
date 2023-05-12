package com.example.budget_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    private EditText etUser, etPassword;
    private SharedPreferences misPreferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        referenciar();

        misPreferencias = getSharedPreferences("budget_tracker", MODE_PRIVATE);
        cargarData();
        //verificar sí está logueado
        if (misPreferencias.getBoolean("logueado", false)) {
            Intent myIntent = new Intent(this, BolsillosActivity.class);
            startActivity(myIntent);
            finish();
        }
    }

    public void cargarData() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("usuario").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Usuario user = document.toObject(Usuario.class);
                        user.setId(document.getId());
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "No se pudo conectar al servidor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void referenciar() {
        etUser = findViewById(R.id.et_username_login);
        etPassword = findViewById(R.id.et_password_login);
    }

    public void clickIniciarSesion(View view) {
        String PASS = "123456";
        String USER = "Camila";
        String passUser = etPassword.getText().toString();
        String userUSer = etUser.getText().toString();
        if (PASS.equals(passUser) && USER.equals(userUSer)) {
            String bolsillo_id = getIntent().getStringExtra("bolsillo_id");
            SharedPreferences.Editor myEditor = misPreferencias.edit();
            Intent myIntent = new Intent(this, BolsillosActivity.class);
            myIntent.putExtra("bolsillo_id", bolsillo_id);
            startActivity(myIntent);
            finish();
            myEditor.putBoolean("logueado", true);
            myEditor.apply();
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickRegister (View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }

}