package com.pao.proiect.elearning.model;

import java.util.Objects;

public final class Categorie { // final = nu poate fi mostenita
    private final String nume;
    private final String cod;

    public Categorie(String nume, String cod) {
        this.nume = nume;
        this.cod = cod;
    }

    public String getNume() { return nume; }
    public String getCod() { return cod; }

    // Suprascrierea equals si hashCode (cerinta 2.1)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return Objects.equals(cod, categorie.cod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod);
    }
}