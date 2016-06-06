package com.example.alice.tipscalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alice on 6/5/16.
 */

public class TipRecord {
    double  bill;
    int tipPercentage;
    Date timestamp;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTip(){
        return bill * (tipPercentage/100d);
    }

    public String getDateFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
        return  simpleDateFormat.format(timestamp);
    }


}
