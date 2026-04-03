package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private List<String> smsTrimise;
    private double soldCont;

    public PersoanaJuridica(String nume, String prenume, String telefon, double soldInitial) {
        super(nume, prenume, telefon);
        this.smsTrimise = new ArrayList<>();
        this.soldCont = soldInitial;
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty() || parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("User sau parola null!");
        }
        System.out.println("Compania " + nume + " s-a autentificat.");
    }

    @Override
    public double consultareSold() {
        return soldCont;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma > 0 && suma <= soldCont) {
            soldCont -= suma;
            return true;
        }
        return false;
    }

    @Override
    public boolean trimiteSMS(String mesaj) {
        if (mesaj == null || mesaj.isEmpty() || telefon == null || telefon.isEmpty()) {
            return false;
        }
        smsTrimise.add(mesaj);
        return true;
    }

    public List<String> getSmsTrimise() {
        return new ArrayList<>(smsTrimise);
    }
}