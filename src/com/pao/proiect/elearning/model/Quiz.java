package com.pao.proiect.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String titlu;
    private List<Intrebare> intrebari;
    private int punctajMinim;

    public Quiz(String titlu, int punctajMinim) {
        this.titlu = titlu;
        this.intrebari = new ArrayList<>();
        this.punctajMinim = punctajMinim;
    }

    public void adaugaIntrebare(Intrebare i) {
        this.intrebari.add(i);
    }

    @Override
    public String toString() {
        return "Quiz: " + titlu + " (" + intrebari.size() + " intrebari)";
    }

    public double calculeazaScor(List<Integer> raspunsuriUser) {
        if (intrebari.isEmpty()) return 0;

        int puncteCorecte = 0;
        for (int i = 0; i < intrebari.size(); i++) {
            // Comparam indexul trimis de student cu cel salvat in Intrebare
            if (i < raspunsuriUser.size() &&
                    raspunsuriUser.get(i) == intrebari.get(i).getIndexRaspunsCorect()) {
                puncteCorecte++;
            }
        }
        return (double) puncteCorecte / intrebari.size() * 100;
    }
}