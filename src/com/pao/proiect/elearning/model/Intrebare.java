package com.pao.proiect.elearning.model;

import java.util.List;

public class Intrebare {
    private String textIntrebare;
    private List<String> optiuni; // Lista de variante (A, B, C...)
    private int indexRaspunsCorect;

    public Intrebare(String textIntrebare, List<String> optiuni, int indexRaspunsCorect) {
        this.textIntrebare = textIntrebare;
        this.optiuni = optiuni;
        this.indexRaspunsCorect = indexRaspunsCorect;
    }

    public String getTextIntrebare() { return textIntrebare; }
    public int  getIndexRaspunsCorect() { return indexRaspunsCorect; }
}