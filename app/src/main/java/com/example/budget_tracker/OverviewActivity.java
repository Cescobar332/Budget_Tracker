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

        Income miIncomesAtrapado = (Income) getIntent().getSerializableExtra("income");

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