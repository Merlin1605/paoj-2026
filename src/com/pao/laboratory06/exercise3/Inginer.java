package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer> {

    public Inginer(String nume, String prenume, String telefon, double salariu) {
        super(nume, prenume, telefon, salariu);
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty() || parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("Credentiale invalide!");
        }
        System.out.println("Inginer " + nume + " s-a autentificat.");
    }

    @Override
    public double consultareSold() {
        return salariu;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) return false;
        if (suma <= salariu) {
            salariu -= suma;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Inginer o) {
        return this.nume.compareTo(o.nume);
    }

    @Override
    public String toString() {
        return "Inginer{nume='" + nume + "', salariu=" + salariu + "}";
    }
}