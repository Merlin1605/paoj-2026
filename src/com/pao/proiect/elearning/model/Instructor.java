package com.pao.proiect.elearning.model;

public class Instructor extends Utilizator {
    protected String specializare;

    public Instructor(int id, String nume, String email, String specializare) {
        super(id, nume, email);
        this.specializare = specializare;
    }

    @Override
    public String getRol() { return "INSTRUCTOR"; }
}