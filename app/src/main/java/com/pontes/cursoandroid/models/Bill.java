package com.pontes.cursoandroid.models;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Bill extends RealmObject {

    @PrimaryKey
    @Required
    private String codigo = UUID.randomUUID().toString();
    private String description;
    private int day, month, year;
    private double value;
    private boolean payed;

    public void save(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(this );

        realm.commitTransaction();
        realm.close();
    }

    public Bill() {    }

    public Bill(String codigo, String description, int day, int month, int year, double value, boolean payed) {
        this.codigo = codigo;
        this.description = description;
        this.day = day;
        this.month = month;
        this.year = year;
        this.value = value;
        this.payed = payed;
    }

    public Bill(String description, int day, int month, int year, double value, boolean payed) {
        this.description = description;
        this.day = day;
        this.month = month;
        this.year = year;
        this.value = value;
        this.payed = payed;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "codigo='" + codigo + '\'' +
                ", description='" + description + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", value=" + value +
                ", payed=" + payed +
                '}';
    }
}
