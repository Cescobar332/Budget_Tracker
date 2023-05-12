package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUser, etPassword, etEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser = findViewById(R.id.et_username_register);
        etPassword = findViewById(R.id.et_password_register);
        etEmail = findViewById(R.id.et_email_register);
    }

    public void clickLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void clickGuardar(View view) {
        String user = etUser.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();

        Usuario newUser = new Usuario();
        newUser.setUsuario(user);
        newUser.setContrasena(password);
        newUser.setCorreo(email);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("usuarios").add(newUser);
        Toast.makeText(this, "Se creó el nuevo usuario", Toast.LENGTH_SHORT).show();
        finish();
    }
}
