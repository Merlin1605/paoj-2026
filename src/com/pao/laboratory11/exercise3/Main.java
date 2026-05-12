package com.pao.laboratory11.exercise3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Transaction> data = List.of(
                new Transaction(1, new BigDecimal("1500.00"), LocalDate.now(), "RO", "WEB"),
                new Transaction(2, new BigDecimal("5000.00"), LocalDate.now(), "RU", "CRYPTO"),
                new Transaction(3, new BigDecimal("1200.00"), LocalDate.now(), "RO", "APP"),
                new Transaction(4, new BigDecimal("5000.00"), LocalDate.now(), "NG", "WEB"), // Tie-breaker suma cu ID 2
                new Transaction(5, new BigDecimal("300.00"),  LocalDate.now(), "RO", "WEB")
        );

        // Generăm Snapshot-ul (Top 3 tranzacții)
        Snapshot snap = data.stream().collect(CustomCollectors.toSnapshot(3));

        System.out.println("=== ANALYTIC SNAPSHOT GENERATED ===");

        // Interogare 1: Top tranzacții (ordonate după sumă desc, apoi ID asc)
        System.out.println("\n1. Top 3 Tranzacții:");
        snap.getTopTransactions().forEach(System.out::println);

        // Interogare 2: Distribuția pe țări (sortată după volum)
        System.out.println("\n2. Tranzacții pe țări (Descrescător):");
        snap.getCountByCountry().entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        // Interogare 3: Statistici globale
        System.out.println("\n3. Statistici Globale:");
        System.out.println("Total rulat: " + snap.getTotalAmount() + " RON");
        System.out.println("Canal preferat: " + snap.getCountByChannel().entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey());
    }
}
