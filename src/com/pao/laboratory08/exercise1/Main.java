package com.pao.laboratory08.exercise1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Student> studenti = citesteStudentiDinFisier();
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextLine()) return;
        String input = scanner.nextLine();
        String[] parts = input.split(" ", 2);
        String comanda = parts[0];

        try {
            if (comanda.equals("PRINT")) {
                for (Student s : studenti) System.out.println(s);
            }
            else if (comanda.equals("SHALLOW")) {
                String numeCautat = parts[1];
                Student original = gasesteStudent(studenti, numeCautat);
                if (original != null) {
                    Student clona = (Student) original.clone();
                    clona.getAdresa().setOras("MODIFICAT");
                    System.out.println("Original: " + original);
                    System.out.println("Clona: " + clona);
                }
            }
            else if (comanda.equals("DEEP")) {
                String numeCautat = parts[1];
                Student original = gasesteStudent(studenti, numeCautat);
                if (original != null) {
                    Student clona = original.deepClone();
                    clona.getAdresa().setOras("MODIFICAT");
                    System.out.println("Original: " + original);
                    System.out.println("Clona: " + clona);
                }
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static List<Student> citesteStudentiDinFisier() {
        List<Student> lista = new ArrayList<>();
        String path = "src/com/pao/laboratory08/tests/studenti.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] date = linie.split(",");
                if (date.length == 4) {
                    String nume = date[0].trim();
                    int varsta = Integer.parseInt(date[1].trim());
                    String oras = date[2].trim();
                    String strada = date[3].trim();
                    lista.add(new Student(nume, varsta, new Adresa(oras, strada)));
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului: " + e.getMessage());
        }
        return lista;
    }

    private static Student gasesteStudent(List<Student> studenti, String nume) {
        for (Student s : studenti) {
            if (s.getNume().equalsIgnoreCase(nume)) return s;
        }
        return null;
    }
}