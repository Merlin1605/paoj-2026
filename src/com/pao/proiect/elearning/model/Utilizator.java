package com.pao.proiect.elearning.model;

public abstract class Utilizator {
    protected int id;
    protected String nume;
    protected String email;

    public Utilizator(int id, String nume, String email) {
        this.id = id;
        this.nume = nume;
        this.email = email;
    }

    public abstract String getRol(); // Metoda abstracta ceruta

    // Getteri si Setteri
    public int getId() { return id; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nume: " + nume + " | Email: " + email;
    }
}