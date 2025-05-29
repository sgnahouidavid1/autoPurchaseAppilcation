package com.example.autopurchase;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class PurchaseActivity extends AppCompatActivity {
    //The auto object contains the information
    //About The vehicle being purchased
    Auto mAuto;

    //The data to be passed to the loan activity
    String loanReport;
    String monthlyPayment;

    //Layout input references
    private EditText carPriceET;
    private  EditText downPayET;
    private RadioGroup loanTermRG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);
        //Establish references to editable text fields and radio button
        carPriceET = findViewById(R.id.editText1);
        downPayET = findViewById(R.id.editText2);
        loanTermRG = findViewById(R.id.radioGroup1);

        //create an automobile object to store auto data
        mAuto = new Auto();
    }
    private  void collectAutoInputData() {
        // Task 1: Set the Car price
        mAuto.setPrice(Integer.parseInt(carPriceET.getText().toString()));
        //Task 2: set the down payment
        mAuto.setDownPayment(Integer.parseInt(downPayET.getText().toString()));

        //Task 3: set the loan term
        int radioId = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = findViewById(radioId);
        mAuto.setLoanTerm(term.getText().toString());
    }
    private void buildLoanReport() {
        //Task 1: Construct the monthly payment
        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1) + String.format("%10.02f", Double.valueOf(mAuto.monthlyPayment()));

        //Task 2: construct the loan report
        loanReport = res.getString(R.string.report_line6) + String.format("%10.02f", Double.valueOf(mAuto.getPrice()));
        loanReport += res.getString(R.string.report_line7) + String.format("%10.02f", Double.valueOf(mAuto.getDownPayment()));
        loanReport += res.getString(R.string.report_line9) + String.format("%18.02f", Double.valueOf(mAuto.taxAmount()));
        loanReport += res.getString(R.string.report_line10) + String.format("%18.02f", Double.valueOf(mAuto.totalCost()));
        loanReport += res.getString(R.string.report_line11) + String.format("%12.02f", Double.valueOf(mAuto.borrowedAmount()));
        loanReport += res.getString(R.string.report_line12) + String.format("%12.02f", Double.valueOf(mAuto.interestAmount()));
        loanReport += "\n\n" + res.getString(R.string.report_line8) +  " " + mAuto.getLoanTerm() + " years.";
        loanReport += "\n\n" + res.getString(R.string.report_line2);
        loanReport += res.getString(R.string.report_line3);
        loanReport += res.getString(R.string.report_line4);
        loanReport += res.getString(R.string.report_line5);
    }
    public void  activateLoanSummary(View view) {
        //Task 1: Build a loan Report Form the input data
        collectAutoInputData();
        buildLoanReport();

        //Task 2: Create an intent to display the loan summary activity
        Intent launchReport = new Intent(this, LoanSummaryActivity.class);

        //Task 3: Pass the loan summary activity two pieces of data:
        // The Loan Report Containing loan details and the monthly payment.
        launchReport.putExtra("LoanReport", loanReport);
        launchReport.putExtra("MonthlyPayment", monthlyPayment);

        //Task 4: start the Loan activity
        startActivity(launchReport);
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu) {
        // Inflate/populate the menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/up button, so long
        //as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();
        if(id == R.id.action_settings) {
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }
}