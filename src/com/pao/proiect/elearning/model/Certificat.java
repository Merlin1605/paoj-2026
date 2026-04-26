package com.pao.proiect.elearning.model;

import java.util.UUID;

public class Certificat {
    private String codUnic;
    private String numeCurs;
    private String numeStudent;

    public Certificat(String numeCurs, String numeStudent) {
        this.codUnic = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.numeCurs = numeCurs;
        this.numeStudent = numeStudent;
    }

    @Override
    public String toString() {
        return "CERTIFICAT [" + codUnic + "] - " + numeStudent + " a absolvit " + numeCurs;
    }
}