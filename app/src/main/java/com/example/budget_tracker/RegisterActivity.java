package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUser, etPassword, etEmail;
    private ImageButton btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        btnSave = findViewById(R.id.ibtn_next_register);
        etUser = findViewById(R.id.et_username_register);
        etPassword = findViewById(R.id.et_password_register);
        etEmail = findViewById(R.id.et_email_register);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickGuardar(view);
            }
        });


    }

    public void clickLogin (View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void clickGuardar(View view) {
        String user = etUser.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        Usuario newUser = new Usuario();
        newUser.setUser(user);
        newUser.setPassword(password);
        newUser.setEmail(email);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("usuarios").add(newUser);
        Toast.makeText(this, "Se creó el usuario", Toast.LENGTH_SHORT).show();

    }
}