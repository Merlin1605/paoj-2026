package com.pao.proiect.elearning.service;

import com.pao.proiect.elearning.model.Curs;
import com.pao.proiect.elearning.exception.CursNotFoundException;
import com.pao.proiect.elearning.model.Instructor;

import java.util.*;

public class CursService {
    // Indexam cursurile dupa ID pentru performanta (Cerinta 2.2)
    private Map<Integer, Curs> cursuriRepo = new HashMap<>();

    // Pattern-ul Singleton (Cerinta 2.3)
    private static CursService instance;
    private CursService() {}

    public static CursService getInstance() {
        if (instance == null) {
            instance = new CursService();
        }
        return instance;
    }

    public void adaugaCurs(Curs curs) {
        if (curs == null) throw new IllegalArgumentException("Cursul nu poate fi null");
        cursuriRepo.put(curs.getId(), curs);
        System.out.println("Curs adaugat cu succes: " + curs.getTitlu());
    }

    public Curs gasesteDupaId(int id) throws CursNotFoundException {
        Curs curs = cursuriRepo.get(id);
        if (curs == null) {
            throw new CursNotFoundException("Cursul cu ID-ul " + id + " nu a fost gasit!");
        }
        return curs;
    }

    public void stergeCurs(int id) throws CursNotFoundException {
        if (!cursuriRepo.containsKey(id)) {
            throw new CursNotFoundException("Nu pot sterge. Cursul nu exista.");
        }
        cursuriRepo.remove(id);
        System.out.println("Cursul cu ID " + id + " a fost eliminat.");
    }

    public List<Curs> listeazaToate() {
        return new ArrayList<>(cursuriRepo.values());
    }

    public List<Curs> filtreazaDupaCategorie(String numeCategorie) {
        List<Curs> rezultate = new ArrayList<>();
        for (Curs c : cursuriRepo.values()) {
            if (c.getCategorie().getNume().equalsIgnoreCase(numeCategorie)) {
                rezultate.add(c);
            }
        }
        return rezultate;
    }

    public List<Curs> filtreazaDupaInstructor(Instructor instructor) {
        List<Curs> rezultate = new ArrayList<>();

        for (Curs c : cursuriRepo.values()) {
            if (c.getInstructor().getEmail().equalsIgnoreCase(instructor.getEmail())) {
                rezultate.add(c);
            }
        }
        return rezultate;
    }

    public void actualizeazaDetaliiCurs(int id, String titluNou, String descriereNoua, double pretNou) throws CursNotFoundException {
        Curs cursDeActualizat = gasesteDupaId(id);

        cursDeActualizat.setTitlu(titluNou);
        cursDeActualizat.setDescriere(descriereNoua);
        cursDeActualizat.setPret(pretNou);

        System.out.println("Update reusit pentru ID " + id);
    }

    public List<Curs> getTopCursuri() {
        List<Curs> listaSortata = new ArrayList<>(cursuriRepo.values());
        listaSortata.sort((c1, c2) -> Integer.compare(c2.getNumarStudenti(), c1.getNumarStudenti()));

        return listaSortata;
    }


}