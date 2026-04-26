package com.pao.proiect.elearning;

import com.pao.proiect.elearning.model.*;
import com.pao.proiect.elearning.service.*;
import com.pao.proiect.elearning.exception.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CursService cursService = CursService.getInstance();
        UtilizatorService utilizatorService = UtilizatorService.getInstance();

        System.out.println("=== Sistem E-Learning PAO 2026 - Etapa I ===\n");

        Categorie it = new Categorie("Tehnologia Informatiei", "IT-01");
        Instructor profAndrei = new Instructor(1, "Andrei Ionescu", "andrei@pao.ro", "Java Developer");
        Student studentMihai = new Student(2, "Mihai Popa", "mihai@student.ro", "2024-10-01");

        utilizatorService.adaugaUtilizator(profAndrei);
        utilizatorService.adaugaUtilizator(studentMihai);

        Curs cursJava = new Curs(101, "Java Advanced", "Invata OOP ca un profesionist", profAndrei, it, 10);
        cursService.adaugaCurs(cursJava);

        cursJava.adaugaLectie(new Lectie("Introducere in Interfete", 15));
        cursJava.adaugaLectie(new Lectie("Design Patterns", 45));
        cursJava.adaugaTag("Java");
        cursJava.adaugaTag("OOP");

        System.out.println("\nActiune: Inscriere student...");
        System.out.println("Studentul " + studentMihai.getNume() + " s-a inscris la " + cursJava.getTitlu());

        System.out.println("\nActiune: Cautare dupa categorie (Tehnologia Informatiei):");
        List<Curs> cursuriIT = cursService.filtreazaDupaCategorie("Tehnologia Informatiei");
        cursuriIT.forEach(System.out::println);

        System.out.println("\nActiune: Utilizatori sortati dupa email:");
        utilizatorService.listeazaUtilizatoriSortati().forEach(System.out::println);

        System.out.println("\nActiune: Cautare curs inexistent (Tratare Exceptie):");
        try {
            cursService.gasesteDupaId(999); // ID care nu exista
        } catch (CursNotFoundException e) {
            System.err.println("Eroare prinsa: " + e.getMessage());
        }

        System.out.println("\nActiune: Verificare roluri (Polimorfism):");
        Utilizator cineva = studentMihai;
        System.out.println("Utilizatorul " + cineva.getNume() + " are rolul de: " + cineva.getRol());

        System.out.println("\nActiune: Simulare finalizare Quiz:");
        int scor = 85;
        if (scor >= 50) {
            System.out.println("Studentul a trecut testul cu scorul: " + scor + "/100");
        }

        System.out.println("\nActiune: Stergere curs:");
        try {
            cursService.stergeCurs(101);
            System.out.println("Cursuri ramase in sistem: " + cursService.listeazaToate().size());
        } catch (CursNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Inscriere inscriereMihai = new Inscriere(studentMihai, cursJava);
        System.out.println("\nActiune: Generare inscriere...");
        System.out.println(inscriereMihai);

        Intrebare i1 = new Intrebare("Ce este Java?", List.of("Limbaj", "Animal", "Masina"), 0);

        Quiz q1 = new Quiz("Test Intro", 50);
        q1.adaugaIntrebare(i1);
        q1.adaugaIntrebare(new Intrebare("Care cuvant cheie indica mostenirea?", List.of("this", "super", "extends"), 2));
        q1.adaugaIntrebare(new Intrebare("Pot clasele abstracte sa aiba constructori?", List.of("Da", "Nu"), 0));
        q1.adaugaIntrebare(new Intrebare("Interfetele pot avea variabile private?", List.of("Da", "Nu"), 1));

        Lectie l1 = new Lectie("Introducere", 20);
        l1.setQuiz(q1);

        System.out.println("\nActiune: Quiz adaugat cursului...");
        System.out.println(q1);

        List<Integer> raspunsuriMihai = List.of(1, 2, 0, 1);
        double scorObtinut = q1.calculeazaScor(raspunsuriMihai);
        double pragMinim = 74.0;
        System.out.println("Scor obtinut: " + scorObtinut + "%");
        if (inscriereMihai.esteEligibilPentruCertificat(scorObtinut, pragMinim)) {
            Certificat cert = new Certificat(cursJava.getTitlu(), studentMihai.getNume());
            System.out.println("Felicitari! Ai aboslvit!");
            System.out.println("Certificat generat: " + cert);
        } else if (scorObtinut < pragMinim) {
            System.out.println("Din pacate, nu ai atins punctajul minim.");
        }
        else
        {
            System.out.println("Nu ai completat toate lectiile");
        }

        List<Lectie> lectiiDisponibile = cursJava.getLectii();

        if (!lectiiDisponibile.isEmpty()) {
            inscriereMihai.finalizeazaLectie(lectiiDisponibile.get(0));
            System.out.println("Dupa finalizarea primei lectii:");
            System.out.println(inscriereMihai);

            if (lectiiDisponibile.size() > 1) {
                inscriereMihai.finalizeazaLectie(lectiiDisponibile.get(1));
                System.out.println("Dupa finalizarea tuturor lectiilor:");
                System.out.println(inscriereMihai);
            }
        }
        if (inscriereMihai.esteEligibilPentruCertificat(scorObtinut, pragMinim)) {
            Certificat cert = new Certificat(cursJava.getTitlu(), studentMihai.getNume());
            System.out.println("Felicitari! Ai aboslvit!");
            System.out.println("Certificat generat: " + cert);
        } else if (scorObtinut < pragMinim) {
            System.out.println("Din pacate, nu ai atins punctajul minim.");
        }
        else
        {
            System.out.println("Nu ai completat toate lectiile");
        }

        Curs curs1 = new Curs(101, "Java Fundamente", "Bazele limbajului", profAndrei, it);
        cursService.adaugaCurs(curs1);
        curs1.adaugaLectie(l1);
        profAndrei.adaugaCursInPortofoliu(curs1);
        Curs curs2 = new Curs(102, "Design Patterns in Java", "Factory, Singleton, etc.", profAndrei, it);
        cursService.adaugaCurs(curs2);
        profAndrei.adaugaCursInPortofoliu(curs2);
        Curs curs3 = new Curs(103, "Java Spring Boot", "Microservicii si baze de date", profAndrei, it);
        cursService.adaugaCurs(curs3);
        profAndrei.adaugaCursInPortofoliu(curs3);

        System.out.println("Cautam cursurile predate de: " + profAndrei.getNume());
        List<Curs> cursuriProfesor = cursService.filtreazaDupaInstructor(profAndrei);
        if (cursuriProfesor.isEmpty()) {
            System.out.println("Acest instructor nu are cursuri listate.");
        } else {
            System.out.println("Cursuri gasite (" + cursuriProfesor.size() + "):");
            for (Curs c : cursuriProfesor) {
                System.out.println("- " + c.getTitlu() + " [Categoria: " + c.getCategorie().getNume() + "]");
            }
        }

        try {
            int idCursDeModificat = 101;
            System.out.println("Inainte de update: " + cursService.gasesteDupaId(idCursDeModificat));
            cursService.actualizeazaDetaliiCurs(
                    idCursDeModificat,
                    "Java Advanced 2026 Edition",
                    "Continut actualizat cu noile functii Java",
                    49.99
            );
            System.out.println("Dupa update: " + cursService.gasesteDupaId(idCursDeModificat));
        } catch (CursNotFoundException e) {
            System.err.println(e.getMessage());
        }
        // Cursul 101 primeste 2 studenti
        curs1.adaugaStudent(studentMihai);
        curs1.adaugaStudent(new Student(3, "Ana Maria", "ana@pao.ro", "FMI"));

        // Cursul 102 primeste 1 student
                curs2.adaugaStudent(studentMihai);

        // Cursul 103 ramane cu 0 studenti

        List<Curs> top = cursService.getTopCursuri();
        System.out.println("Topul cursurilor dupa popularitate:");
        for (int i = 0; i < top.size(); i++) {
            Curs c = top.get(i);
            System.out.println((i + 1) + ". " + c.getTitlu() + " - " + c.getNumarStudenti() + " studenti");
        }
        //US
        Utilizator u = utilizatorService.cautaDupaEmail("mihai@student.ro");
        System.out.println("Gasit prin email: " + (u != null ? u.getNume() : "Nu exista"));

        Student s2 = new Student(3, "Bogdan Dumitru", "bogdan.d@student.ro", "FMI");
        utilizatorService.adaugaUtilizator(s2);

        Student s3 = new Student(4, "Zoe Ionescu", "zoe@student.ro", "FMI");
        utilizatorService.adaugaUtilizator(s3);


        Instructor profElena = new Instructor(5, "Elena Vasilescu", "elena.v@pao.ro", "Database Expert");
        utilizatorService.adaugaUtilizator(profElena);

        Student s4 = new Student(6, "Alex Radu", "alex.radu@student.ro", "FMI");
        utilizatorService.adaugaUtilizator(s4);

        System.out.println("\nLista tuturor utilizatorilor sortati alfabetic:");
        List<Utilizator> sortati = utilizatorService.listeazaUtilizatoriSortati();
        for (Utilizator user : sortati) {
            System.out.println("- " + user.getEmail() + " (" + user.getNume() + ")");
        }
    }
}