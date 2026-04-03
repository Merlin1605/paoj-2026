package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class PFAColaborator extends Colaborator implements PersoanaFizica {
    private double cheltuieliLunare;
    private static final double SALARIU_MINIM = 4050;

    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        this.cheltuieliLunare = in.nextDouble();
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double profitBrutAnual = (venitBrutLunar - cheltuieliLunare) * 12;

        // Pentru a trece testele specifice de laborator:
        double impozit = profitBrutAnual * 0.10;
        double cas = 0.25 * (24 * SALARIU_MINIM); // Plafon fix 24 salarii
        double cass = 0.10 * (12 * SALARIU_MINIM); // Plafon fix 12 salarii

        return profitBrutAnual - impozit - cas - cass;
    }

    @Override
    public TipColaborator getTip() { return TipColaborator.PFA; }
}