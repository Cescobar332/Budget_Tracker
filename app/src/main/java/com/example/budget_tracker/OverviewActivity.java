package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class OverviewActivity extends AppCompatActivity {
    private TextView tvBalance, tvIncomes, tvExpenses, tvSavings, tvCatIncomes, tvCatExpenses, tvCatSavings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        tvBalance = findViewById(R.id.tv_num_balance_overview);
        tvIncomes = findViewById(R.id.tv_incomes_num_overview);
        tvExpenses = findViewById(R.id.tv_expenses_num_overview);
        tvSavings = findViewById(R.id.tv_savings_num_overview);
        tvCatIncomes = findViewById(R.id.tv_categoria_incomes_overview);
        tvCatExpenses = findViewById(R.id.tv_categoria_expenses_overview);
        tvCatSavings = findViewById(R.id.tv_categoria_savings_overview);

        Intent intent = getIntent();
        Double incomesTotal = intent.getDoubleExtra("incomesTotal", 0.0);
        Double expensesTotal = intent.getDoubleExtra("expensesTotal", 0.0);
        Double savingsTotal = intent.getDoubleExtra("savingsTotal", 0.0);

        Double balance = incomesTotal - (expensesTotal + savingsTotal);

        tvBalance.setText(String.valueOf(balance));
        tvIncomes.setText(String.valueOf(incomesTotal));
        tvExpenses.setText(String.valueOf(expensesTotal));
        tvSavings.setText(String.valueOf(savingsTotal));

        //Actualiza las barras de progreso
        /*
        List<String> categoriasIngresos = new ArrayList<>();
        List<String> categoriasGastos = new ArrayList<>();
        List<String> categoriasAhorros = new ArrayList<>();
        // Obtener las categorías de ingresos, gastos y ahorros desde donde los tengas guardados
        // Por ejemplo, si los tienes en SharedPreferences, puedes recuperarlos de la siguiente manera
        SharedPreferences preferences = getSharedPreferences("budget_tracker", MODE_PRIVATE);
        categoriasIngresos = getCategorias(preferences, "categoriasIngresos"); // Reemplaza "categoriasIngresos" con la clave correcta en SharedPreferences
        categoriasGastos = getCategorias(preferences, "categoriasGastos"); // Reemplaza "categoriasGastos" con la clave correcta en SharedPreferences
        categoriasAhorros = getCategorias(preferences, "categoriasAhorros"); // Reemplaza "categoriasAhorros" con la clave correcta en SharedPreferences

        // Mostrar las categorías en los TextViews correspondientes
        tvCatIncomes.setText(TextUtils.join(", ", categoriasIngresos));
        tvCatExpenses.setText(TextUtils.join(", ", categoriasGastos));
        tvCatSavings.setText(TextUtils.join(", ", categoriasAhorros));
        double porcentajeCategoria = (montoCategoria / totalSeccion) * 100;

        ProgressBar progressBarIncomes = findViewById(R.id.pb_income_overview);
        progressBarIncomes.setProgress((int) porcentajeIngresosCategoria);

        ProgressBar progressBarExpenses = findViewById(R.id.pb_expenses_overview);
        progressBarExpenses.setProgress((int) porcentajeGastosCategoria);

        ProgressBar progressBarSavings = findViewById(R.id.pb_savings_overview);
        progressBarSavings.setProgress((int) porcentajeAhorrosCategoria);

*/
    }
    public void Return(View view){
        Intent intent = new Intent(OverviewActivity.this, detailActivity.class);
        startActivity(intent);
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