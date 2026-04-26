package com.pao.proiect.elearning.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

public class Inscriere {
    private Student student;
    private Curs curs;
    private LocalDate dataInscriere;
    private boolean finalizat;
    private Set<Lectie> lectiiCompletate = new HashSet<>();
    private Certificat certificat;

    public Inscriere(Student student, Curs curs) {
        this.student = student;
        this.curs = curs;
        this.dataInscriere = LocalDate.now();
        this.finalizat = false;
    }

    public void setFinalizat(boolean finalizat) { this.finalizat = finalizat; }
    public boolean isFinalizat() { return finalizat; }

    public void finalizeazaLectie(Lectie lectie) {
        // Verificam daca lectia chiar apartine acestui curs
        if (curs.getLectii().contains(lectie)) {
            this.lectiiCompletate.add(lectie);
        }
    }

    // Calculul progresului (Cerinta 1.1 - Actiunea 8)
    public double getProgresProcentual() {
        int totalLectii = curs.getLectii().size();
        if (totalLectii == 0) return 0;

        return (double) lectiiCompletate.size() / totalLectii * 100;
    }

    @Override
    public String toString() {
        return "Progres " + student.getNume() + " la " + curs.getTitlu() +
                ": " + String.format("%.2f", getProgresProcentual()) + "%";
    }

    // Cerinta 2.1: equals si hashCode pentru a identifica inscrieri unice
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscriere that = (Inscriere) o;
        return Objects.equals(student, that.student) && Objects.equals(curs, that.curs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, curs);
    }

    public boolean esteEligibilPentruCertificat(double scorUltimulQuiz, double pragTrecere) {
        return getProgresProcentual() == 100 && scorUltimulQuiz >= pragTrecere;
    }
    public void setCertificat(Certificat certificat) {
        this.certificat = certificat;
    }
    public Certificat getCertificat() {
        return certificat;
    }
}