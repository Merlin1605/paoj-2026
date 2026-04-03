package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 1. Sortare Ingineri
        Inginer[] ingineri = {
                new Inginer("Popescu", "Ion", "0722", 5000),
                new Inginer("Ababei", "Ana", "0733", 8000),
                new Inginer("Ionescu", "Dan", "0744", 6500)
        };

        System.out.println("--- Sortare Naturala (Nume) ---");
        Arrays.sort(ingineri);
        for (Inginer i : ingineri) System.out.println(i);

        System.out.println("\n--- Sortare Comparator (Salariu DESC) ---");
        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        for (Inginer i : ingineri) System.out.println(i);

        // 2. Demonstratie referinta tip Interfata
        System.out.println("\n--- Polimorfism prin Interfata ---");
        PlataOnline plata = ingineri[0];
        plata.autentificare("user1", "pass1");
        // plata.salariu; -> Eroare de compilare, referinta tip PlataOnline nu vede campurile clasei Inginer
        System.out.println("Sold consultat prin interfata: " + plata.consultareSold());

        // 3. Persoana Juridica si SMS
        System.out.println("\n--- Persoana Juridica si SMS ---");
        PersoanaJuridica firma = new PersoanaJuridica("TechSRL", "Admin", "0799123456", 10000);
        PlataOnlineSMS plataSms = firma;

        System.out.println("Trimitere SMS valid: " + plataSms.trimiteSMS("Confirmare plata 100 RON"));

        PersoanaJuridica firmaFaraTel = new PersoanaJuridica("NoPhoneSRL", "Admin", "", 5000);
        System.out.println("Trimitere SMS fara telefon: " + firmaFaraTel.trimiteSMS("Test"));
        System.out.println("Mesaje inregistrate firma1: " + firma.getSmsTrimise());

        // 4. Constante Financiare
        System.out.println("\n--- Constante Financiare ---");
        System.out.println("TVA curent: " + ConstanteFinanciare.TVA.getValoare());

        // 5. Tratare erori si cazuri speciale
        System.out.println("\n--- Tratare erori ---");
        try {
            plata.autentificare(null, "");
        } catch (IllegalArgumentException e) {
            System.out.println("Eroare autentificare prinsa: " + e.getMessage());
        }

        // Simulare UnsupportedOperationException
        try {
            trimiteDacaPoti(ingineri[0], "Mesaj spam");
        } catch (UnsupportedOperationException e) {
            System.out.println("Eroare capabilitate prinsa: " + e.getMessage());
        }
    }

    public static void trimiteDacaPoti(PlataOnline entitate, String msg) {
        if (entitate instanceof PlataOnlineSMS) {
            ((PlataOnlineSMS) entitate).trimiteSMS(msg);
        } else {
            throw new UnsupportedOperationException("Aceasta entitate nu are capabilitate SMS!");
        }
    }
}