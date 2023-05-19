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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    private EditText etUser, etPassword;
    private SharedPreferences misPreferencias;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        referenciar();

        misPreferencias = getSharedPreferences("budget_tracker", MODE_PRIVATE);

        if (misPreferencias.getBoolean("logueado", false)){
            Intent miIntent = new Intent(this,BolsillosActivity.class);
            startActivity(miIntent);
            finish();
        }
        cargarData();
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
        String email = etUser.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userID = user.getUid(); // Obtener el ID único del usuario
                            SharedPreferences.Editor editor = misPreferencias.edit();
                            editor.putBoolean("logueado", true);
                            editor.putString("userID", userID); // Guardar el ID único del usuario
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, BolsillosActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void clickRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


}