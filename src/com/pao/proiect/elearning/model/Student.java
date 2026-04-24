package com.pao.proiect.elearning.model;

public class Student extends Utilizator {
    private String facultate;

    public Student(int id, String nume, String email, String facultate) {
        super(id, nume, email);
        this.facultate = facultate;
    }

    @Override
    public String getRol() { return "STUDENT"; }
}