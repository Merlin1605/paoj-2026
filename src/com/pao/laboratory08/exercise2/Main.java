package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputPath = "src/com/pao/laboratory08/tests/studenti.txt";
        String outputPath = "rezultate.txt";
        List<Student> totiStudentii = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] date = linie.split(",");
                if (date.length == 4) {
                    String nume = date[0].trim();
                    int varsta = Integer.parseInt(date[1].trim());
                    String oras = date[2].trim();
                    String strada = date[3].trim();

                    Adresa adr = new Adresa(oras, strada);
                    totiStudentii.add(new Student(nume, varsta, adr));
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la citire: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int prag = scanner.nextInt();


        List<Student> filtrati = new ArrayList<>();
        for (Student s : totiStudentii) {
            if (s.getVarsta() >= prag) {
                filtrati.add(s);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            System.out.println("Filtru: varsta >= " + prag);
            System.out.println("Rezultate: " + filtrati.size() + " studenti\n");

            for (Student s : filtrati) {
                String line = s.toString();
                // Scriem in consola
                System.out.println(line);
                // Scriem in fisier
                bw.write(line);
                bw.newLine();
            }

            System.out.println("\nScris in: " + outputPath);
        } catch (IOException e) {
            System.err.println("Eroare la scriere: " + e.getMessage());
        }
    }
}