package com.example.autopurchase;

//Data model for an auto
public class Auto {
    static final double STATE_TAX = .07;
    static final double INTEREST_RATE = .09;

    private  double mPrice;

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    private double mDownPayment;

    public double getDownPayment() {
        return mDownPayment;
    }

    public void setDownPayment(double mDownPayment) {
        this.mDownPayment = mDownPayment;
    }

    private  int mLoanTerm;

    public int getLoanTerm() {
        return mLoanTerm;
    }

    public void setLoanTerm(String mLoanTerm) {
        if (mLoanTerm.contains("2"))
            this.mLoanTerm = 2;
        else if (mLoanTerm.contains("3"))
            this.mLoanTerm = 3;
        else
            this.mLoanTerm = 4;
    }

    public double taxAmount() {
            return  mPrice * STATE_TAX;
    }
    public double totalCost() {
            return  mPrice + taxAmount();
    }

    public double borrowedAmount() {
            return totalCost() - mDownPayment;
    }
    public double interestAmount() {
            return  borrowedAmount() * INTEREST_RATE;
    }

    public double monthlyPayment() {
        return  borrowedAmount() / (mLoanTerm * 12);
    }
}
