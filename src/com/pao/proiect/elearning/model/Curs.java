package com.pao.proiect.elearning.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Curs {
    private final int id;
    private String titlu;
    private String descriere;
    private Instructor instructor;
    private final Categorie categorie;
    private double pret;
    private List<Student> studentiInscrisi = new ArrayList<>();

    // Cerinta 2.2: Folosirea colectiilor
    private List<Lectie> lectii;
    private Set<String> taguri;

    public Curs(int id, String titlu, String descriere, Instructor instructor, Categorie categorie, double pret) {
        this.id = id;
        this.titlu = titlu;
        this.descriere = descriere;
        this.instructor = instructor;
        this.categorie = categorie;
        this.lectii = new ArrayList<>();
        this.taguri = new HashSet<>();
        this.pret = pret;
    }

    public Curs(int id, String titlu, String descriere, Instructor instructor, Categorie categorie) {
        this(id, titlu, descriere, instructor, categorie, 50.0);
    }

    public void adaugaLectie(Lectie lectie) {
        this.lectii.add(lectie);
    }

    public void adaugaTag(String tag) {
        this.taguri.add(tag.toLowerCase());
    }

    public void adaugaStudent(Student s) {
        this.studentiInscrisi.add(s);
    }

    public int getNumarStudenti() {
        return studentiInscrisi.size();
    }

    public void setTitlu(String titlu) { this.titlu = titlu; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public void setPret(double pret) { this.pret = pret; }

    // Getteri
    public int getId() { return id; }
    public String getTitlu() { return titlu; }
    public List<Lectie> getLectii() { return new ArrayList<>(lectii); } // Defensive copy
    public Categorie getCategorie() { return categorie; }
    public Instructor getInstructor() { return instructor; }
    public double getPret() { return pret; }


    @Override
    public String toString() {
        return "Curs: " + titlu + " [" + categorie.getNume() + "]\n" +
                "Predat de: " + instructor.getNume() + "\n" +
                "Nr. lecții: " + lectii.size() + " | Tag-uri: " + taguri + " | Pret: " + pret + " EUR";
    }
}