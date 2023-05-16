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

        /*Income miIncomesAtrapado = (Income) getIntent().getSerializableExtra("income");

        tvIncomes.setText("$" + miIncomesAtrapado.getValue().toString());
        tvCatIncomes.setText(miIncomesAtrapado.getCategory());

        Expense miExpensesAtrapado = (Expense) getIntent().getSerializableExtra("expense");

        tvExpenses.setText("$" + miExpensesAtrapado.getValue().toString());
        tvCatExpenses.setText(miExpensesAtrapado.getCategory());

        Saving miSavingsAtrapado = (Saving) getIntent().getSerializableExtra("saving");

        tvSavings.setText("$" + miSavingsAtrapado.getValue().toString());
        tvCatSavings.setText(miSavingsAtrapado.getCategory());

        Double resultado = miIncomesAtrapado.getValue() - (miExpensesAtrapado.getValue() + miSavingsAtrapado.getValue());
        tvBalance.setText(resultado.toString());
        Obtén los datos de ingresos
        double ingresosCategoria1 = 75.0;
        double ingresosCategoria2 = 25.0;

        Calcula los porcentajes
        double totalIngresos = ingresosCategoria1 + ingresosCategoria2;
        int porcentajeIngresosCategoria1 = (int) (ingresosCategoria1 / totalIngresos * 100);*/
        int porcentajeIngresosCategoria1 = (int) (15000 / 15000 * 100);

        int porcentajeGastosCategoria1 = (int) (2000 / 2000 * 100);

        int porcentajeAhorrosCategoria1 = (int) (3000 / 3000 * 100);

        //Actualiza las barras de progreso
        ProgressBar progressBarIncomes = findViewById(R.id.pb_income_overview);
        progressBarIncomes.setProgress(porcentajeIngresosCategoria1);

        ProgressBar progressBarExpenses = findViewById(R.id.pb_expenses_overview);
        progressBarExpenses.setProgress(porcentajeGastosCategoria1);

        ProgressBar progressBarSavings = findViewById(R.id.pb_savings_overview);
        progressBarSavings.setProgress(porcentajeAhorrosCategoria1);

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