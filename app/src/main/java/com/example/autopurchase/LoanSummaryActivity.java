package com.example.autopurchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoanSummaryActivity extends AppCompatActivity {

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loansummary_layout);
        TextView monthlyPayET = findViewById(R.id.textView2);
        TextView loanReportET = findViewById(R.id.textView3);

        //Passed data
        Intent intent = getIntent();
        String report;
        report = intent.getStringExtra("LoanReport");

        String monthlyPay;
        monthlyPay = intent.getStringExtra("MonthlyPayment");
        monthlyPayET.setText(monthlyPay);
        loanReportET.setText(report);
    }
    public void  goDataEntry(View view){
        finish();
    }
}
