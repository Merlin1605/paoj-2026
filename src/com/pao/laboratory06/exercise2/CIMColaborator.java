package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class CIMColaborator extends Colaborator implements PersoanaFizica {
    private boolean areBonus;

    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        String bonusStr = in.next();
        this.areBonus = bonusStr.equalsIgnoreCase("DA");
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double net = venitBrutLunar * 12 * 0.55;
        if (areBonus) {
            net *= 1.10;
        }
        return net;
    }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.CIM;
    }
}