package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnJan, btnfeb, btnmar, btnapr, btnmay, btnjun, btnjul, btnago, btnsep, btnoct, btnnov, btndec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJan = findViewById(R.id.btn_jan);
        btnfeb = findViewById(R.id.btn_feb);
        btnmar = findViewById(R.id.btn_mar);
        btnapr = findViewById(R.id.btn_apr);
        btnmay = findViewById(R.id.btn_may);
        btnjun = findViewById(R.id.btn_jun);
        btnjul = findViewById(R.id.btn_jul);
        btnago = findViewById(R.id.btn_ago);
        btnsep = findViewById(R.id.btn_sep);
        btnoct = findViewById(R.id.btn_oct);
        btnnov = findViewById(R.id.btn_nov);
        btndec = findViewById(R.id.btn_dic);

        btnJan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Enero");
            }
        });

        btnfeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Febrero");
            }
        });

        btnmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Marzo");
            }
        });

        btnapr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Abril");
            }
        });

        btnmay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Mayo");
            }
        });

        btnjun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Junio");
            }
        });

        btnjul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Julio");
            }
        });

        btnago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Agosto");
            }
        });

        btnsep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Septiembre");
            }
        });

        btnoct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Octubre");
            }
        });

        btnnov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Noviembre");
            }
        });

        btndec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpinnerActivity("Diciembre");
            }
        });
    }

    private void startSpinnerActivity(String monthName) {
        Intent intent = new Intent(MainActivity.this, detailActivity.class);
        intent.putExtra("MONTH_NAME", monthName);
        startActivity(intent);
    }

    public void CerrarSesion(View view) {
        SharedPreferences misPreferencias = getSharedPreferences("budget_tracker", MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.clear();
        miEditor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


}