package com.pao.proiect.elearning.model;

public class CoordonatorCurs extends Instructor {
    private int vechimeAni;

    public CoordonatorCurs(int id, String nume, String email, String specializare, int vechimeAni) {
        super(id, nume, email, specializare);
        this.vechimeAni = vechimeAni;
    }

    @Override
    public String getRol() { return "COORDONATOR"; }
}