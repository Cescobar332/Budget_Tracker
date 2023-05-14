package com.example.budget_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        Incomes miIncomesAtrapado = (Incomes) getIntent().getSerializableExtra("income");

        tvIncomes.setText("$" + miIncomesAtrapado.getMonto().toString());
        tvCatIncomes.setText(miIncomesAtrapado.getCat());

        Expenses miExpensesAtrapado = (Expenses) getIntent().getSerializableExtra("expense");

        tvExpenses.setText("$" + miExpensesAtrapado.getMonto().toString);
        tvCatExpenses.setText(miExpensesAtrapado.getCat());

        Savings miSavingsAtrapado = (Savings) getIntent().getSerializableExtra("saving");

        tvSavings.setText("$" + miSavingsAtrapado.getMonto().toString());
        tvCatSavings.setText(miSavingsAtrapado.getCat());

        Double resultado = miIncomesAtrapado.getMonto() - (miExpensesAtrapado.getMonto() + miSavingsAtrapado.getMonto());
        tvBalance.setText(resultado.toString());
//        Obtén los datos de ingresos
//        double ingresosCategoria1 = 75.0;
//        double ingresosCategoria2 = 25.0;
//
//        Calcula los porcentajes
//        double totalIngresos = ingresosCategoria1 + ingresosCategoria2;
//        int porcentajeIngresosCategoria1 = (int) (ingresosCategoria1 / totalIngresos * 100);
//        int porcentajeIngresosCategoria2 = (int) (ingresosCategoria2 / totalIngresos * 100);
//
//        Actualiza las barras de progreso
//        ProgressBar progressBarIncomes = findViewById(R.id.pb_income_overview);
//        progressBarIncomes.setProgress(porcentajeIngresosCategoria1);
//        progressBarIncomes.setProgress(porcentajeIngresosCategoria2);

    }
}