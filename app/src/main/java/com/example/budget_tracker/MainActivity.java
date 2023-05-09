package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnJan, btn_feb, btn_mar, btn_apr, btn_may, btn_jun, btn_jul, btn_ago, btn_sep, btn_oct, btn_nov, btn_dec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJan = findViewById(R.id.btn_jan);
        btn_feb = findViewById(R.id.btn_feb);
        btn_mar = findViewById(R.id.btn_mar);
        btn_apr = findViewById(R.id.btn_apr);
        btn_may = findViewById(R.id.btn_may);
        btn_jun = findViewById(R.id.btn_jun);
        btn_jul = findViewById(R.id.btn_jul);
        btn_ago = findViewById(R.id.btn_ago);
        btn_sep = findViewById(R.id.btn_sep);
        btn_oct = findViewById(R.id.btn_oct);
        btn_nov = findViewById(R.id.btn_nov);
        btn_dec = findViewById(R.id.btn_dic);

        btnJan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "January");
                startActivity(intent);
            }
        });
        btn_feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "February");
                startActivity(intent);
            }
        });
        btn_mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "March");
                startActivity(intent);
            }
        });
        btn_apr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "April");
                startActivity(intent);
            }
        });
        btn_may.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "May");
                startActivity(intent);
            }
        });
        btn_jun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "June");
                startActivity(intent);
            }
        });
        btn_jul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "July");
                startActivity(intent);
            }
        });
        btn_ago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "August");
                startActivity(intent);
            }
        });
        btn_sep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "September");
                startActivity(intent);
            }
        });
        btn_oct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "October");
                startActivity(intent);
            }
        });
        btn_nov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "November");
                startActivity(intent);
            }
        });
        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                intent.putExtra("selected_month", "December");
                startActivity(intent);
            }
        });

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