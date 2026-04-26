package com.pao.proiect.elearning.model;

public class Lectie {
    private String titlu;
    private int durataMinute;
    private Quiz quiz; // Legatura: Lectia ARE un Quiz

    public Lectie(String titlu, int durataMinute) {
        this.titlu = titlu;
        this.durataMinute = durataMinute;
    }

    // Setter pentru Quiz (nu toate lectiile au test)
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        String infoQuiz = (quiz != null) ? " [Are Quiz]" : " [Fara Quiz]";
        return "Lecția: " + titlu + infoQuiz;
    }
}