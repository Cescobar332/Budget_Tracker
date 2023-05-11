package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.budget_tracker.retrofit.MiAPI;
import com.example.budget_tracker.retrofit.UserService;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUser, etPassword, etEmail;
    private ImageButton btnSave;
    private static final long MAX_ID_VALUE = 9999999999L;
    private static final String SHARED_PREFS_KEY = "MyPrefs";
    private static final String ID_KEY = "currentId";
    private AtomicLong currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnSave = findViewById(R.id.ibtn_next_register);
        etUser = findViewById(R.id.et_username_register);
        etPassword = findViewById(R.id.et_password_register);
        etEmail = findViewById(R.id.et_email_register);
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        currentId = new AtomicLong(sharedPrefs.getLong(ID_KEY, 1L));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickGuardar(view);
            }
        });


    }

    public void clickLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void clickGuardar(View view) {
        String user = etUser.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        long id = generateUniqueId();

        Usuario newUser = new Usuario();
        newUser.setId_usuario(id);
        newUser.setUsuario(user);
        newUser.setContrasena(password);
        newUser.setCorreo(email);

        Retrofit myRetrofit = MiAPI.getInstancia();
        UserService myUserService = myRetrofit.create(UserService.class);
        String formattedId = String.format("%010d", id);

        myUserService.agregar(newUser).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(RegisterActivity.this, "Se creó el usuario" + formattedId, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

            }
        });

    }

    protected void onDestroy() {
        super.onDestroy();
        // Guardar el valor actual de currentId en SharedPreferences
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putLong(ID_KEY, currentId.get());
        editor.apply();
    }

    private long generateUniqueId() {
        long id = currentId.getAndIncrement();
        if (id > MAX_ID_VALUE) {
            currentId.set(1L);
            id = 1L;
        }
        return id;
    }

}