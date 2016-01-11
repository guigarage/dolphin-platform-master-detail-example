package com.guigarage.dolphin.service;

/**
 * Created by hendrikebbers on 11.01.16.
 */
public class StockData {

    private String ident;

    private String name;

    private double value;

    private String type;

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
