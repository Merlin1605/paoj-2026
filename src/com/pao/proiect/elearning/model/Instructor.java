package com.pao.proiect.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Utilizator {
    protected String specializare;
    private List<Curs> cursuriPredate = new ArrayList<>();

    public Instructor(int id, String nume, String email, String specializare) {
        super(id, nume, email);
        this.specializare = specializare;
    }

    @Override
    public String getRol() { return "INSTRUCTOR"; }

    public String getEmail() { return email; }

    public void adaugaCursInPortofoliu(Curs curs) {
        this.cursuriPredate.add(curs);
    }

    public List<Curs> getCursuriPredate() {
        return new ArrayList<>(cursuriPredate); // Defensive copy
    }
}