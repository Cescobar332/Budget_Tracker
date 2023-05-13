package com.example.budget_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText etUser, etPassword, etEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser = findViewById(R.id.et_username_register);
        etPassword = findViewById(R.id.et_password_register);
        etEmail = findViewById(R.id.et_email_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void clickLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void clickGuardar(View view) {
        String user = etUser.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Se ha creado el usuario", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "No se ha podido crear el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
