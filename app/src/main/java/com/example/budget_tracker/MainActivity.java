package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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