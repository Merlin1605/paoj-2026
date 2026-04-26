package com.pao.proiect.elearning.service;

import com.pao.proiect.elearning.exception.ValidareUtilizatorException;
import com.pao.proiect.elearning.model.Utilizator;
import java.util.*;

public class UtilizatorService {
    private Map<String, Utilizator> utilizatoriMap = new HashMap<>();

    private static UtilizatorService instance;
    private UtilizatorService() {}

    public static UtilizatorService getInstance() {
        if (instance == null) instance = new UtilizatorService();
        return instance;
    }

    public void adaugaUtilizator(Utilizator u) {
        if (u == null || u.getEmail() == null) {
            throw new ValidareUtilizatorException("Date utilizator invalide la adaugare.");
        }
        utilizatoriMap.put(u.getEmail().toLowerCase(), u);
    }

    public List<Utilizator> listeazaUtilizatoriSortati() {
        // Extragem valorile din Map intr-o lista noua
        List<Utilizator> copie = new ArrayList<>(utilizatoriMap.values());

        copie.sort(Comparator.comparing(Utilizator::getEmail));

        return copie;
    }

    public Utilizator cautaDupaEmail(String email) {
        if (email == null) return null;
        return utilizatoriMap.get(email.toLowerCase());
    }

    // Filtrare polimorfica
    public List<Utilizator> filtreazaDupaRol(String rol) {
        List<Utilizator> rezultate = new ArrayList<>();
        for (Utilizator u : utilizatoriMap.values()) {
            if (u.getRol().equalsIgnoreCase(rol)) {
                rezultate.add(u);
            }
        }
        return rezultate;
    }
}