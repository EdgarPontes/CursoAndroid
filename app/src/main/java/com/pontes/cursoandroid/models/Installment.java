package com.pontes.cursoandroid.models;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Installment extends RealmObject {

    @PrimaryKey
    @Required
    private String codigo = UUID.randomUUID().toString();
    private String description;
    private double value;
    private int totalPart;
    private int currentPart;
    private boolean finished;

    public void save(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(this);

        realm.commitTransaction();
        realm.close();
    }

    public Installment() {
    }

    public Installment(String description, double value, int totalPart, int currentPart, boolean finished) {
        this.description = description;
        this.value = value;
        this.totalPart = totalPart;
        this.currentPart = currentPart;
        this.finished = finished;
    }

    public Installment(String codigo, String description, double value, int totalPart, int currentPart, boolean finished) {
        this.codigo = codigo;
        this.description = description;
        this.value = value;
        this.totalPart = totalPart;
        this.currentPart = currentPart;
        this.finished = finished;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTotalPart() {
        return totalPart;
    }

    public void setTotalPart(int totalPart) {
        this.totalPart = totalPart;
    }

    public int getCurrentPart() {
        return currentPart;
    }

    public void setCurrentPart(int currentPart) {
        this.currentPart = currentPart;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
