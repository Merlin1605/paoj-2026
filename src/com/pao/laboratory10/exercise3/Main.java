package com.pao.laboratory10.exercise3;

import com.pao.laboratory10.exercise1.TipTranzactie;
import com.pao.laboratory10.exercise1.Tranzactie;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Tranzactie> tranzactii = Arrays.asList(
                new Tranzactie(1, 1500.0, "2024-01-10", TipTranzactie.CREDIT, "RO01"),
                new Tranzactie(2, 200.0,  "2024-01-15", TipTranzactie.DEBIT,  "RO01"),
                new Tranzactie(3, 3000.0, "2024-02-05", TipTranzactie.CREDIT, "RO02"),
                new Tranzactie(4, 50.0,   "2024-02-10", TipTranzactie.DEBIT,  "RO03"),
                new Tranzactie(5, 1200.0, "2024-02-20", TipTranzactie.CREDIT, "RO01"),
                new Tranzactie(6, 450.0,  "2024-03-01", TipTranzactie.DEBIT,  "RO02"),
                new Tranzactie(7, 800.0,  "2024-03-12", TipTranzactie.CREDIT, "RO04"),
                new Tranzactie(8, 100.0,  "2024-03-15", TipTranzactie.DEBIT,  "RO04"),
                new Tranzactie(9, 2500.0, "2024-03-20", TipTranzactie.CREDIT, "RO01"),
                new Tranzactie(10, 300.0, "2024-03-25", TipTranzactie.DEBIT,  "RO02")
        );

        // 1. filter(tip == CREDIT)
        System.out.println("\n# 1. Toate tranzacțiile de tip CREDIT:");
        tranzactii.stream()
                .filter(t -> t.getTip() == TipTranzactie.CREDIT)
                .forEach(System.out::println);

        // 2. mapToDouble(suma).sum()
        double total = tranzactii.stream()
                .mapToDouble(Tranzactie::getSuma)
                .sum();
        System.out.printf("\n# 2. Total procesat: %.2f RON\n", total);

        // 3. Collectors.groupingBy(luna, summingDouble(suma))
        System.out.println("\n# 3. Suma totală per lună:");
        Map<String, Double> sumaPerLuna = tranzactii.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        TreeMap::new, // Pentru a avea lunile sortate
                        Collectors.summingDouble(Tranzactie::getSuma)
                ));
        sumaPerLuna.forEach((luna, suma) -> System.out.printf("%s: %.2f RON\n", luna, suma));

        // 4. sorted + limit(3)
        System.out.println("\n# 4. Top 3 tranzacții (cele mai mari sume):");
        tranzactii.stream()
                .sorted(Comparator.comparingDouble(Tranzactie::getSuma).reversed())
                .limit(3)
                .forEach(System.out::println);

        // 5. map + distinct + collect
        System.out.println("\n# 5. Conturi sursă unice:");
        List<String> conturiUnice = tranzactii.stream()
                .map(Tranzactie::getContSursa)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(conturiUnice);

        // 6. mapToDouble + average
        double medie = tranzactii.stream()
                .mapToDouble(Tranzactie::getSuma)
                .average()
                .orElse(0.0);
        System.out.printf("\n# 6. Suma medie a tranzacțiilor: %.2f RON\n", medie);

        // 7. Collectors.groupingBy(luna) cu format extras complex
        System.out.println("\n# 7. EXTRASE DE CONT LUNARE:");
        tranzactii.stream()
                .collect(Collectors.groupingBy(t -> t.getData().substring(0, 7)))
                .forEach((luna, lista) -> {
                    double totalLuna = lista.stream().mapToDouble(Tranzactie::getSuma).sum();
                    System.out.printf("EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON\n",
                            luna, lista.size(), totalLuna);
                });
    }
}
