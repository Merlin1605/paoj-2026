package com.pao.laboratory05.angajati;
import java.util.Scanner;
/**
 * Exercise 3 — Angajați
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 3 — Angajați"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService service = AngajatService.getInstance();

        while (true) {
            System.out.println("\n===== Gestionare Angajati =====");
            System.out.println("1. Adauga angajat");
            System.out.println("2. Listare dupa salariu");
            System.out.println("3. Cauta dupa departament");
            System.out.println("0. Iesire");
            System.out.print("Optiune: ");

            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.print("Nume: ");
                String nume = scanner.nextLine();
                System.out.print("Departament (nume): ");
                String dNume = scanner.nextLine();
                System.out.print("Departament (locatie): ");
                String dLoc = scanner.nextLine();
                System.out.print("Salariu: ");
                double sal = Double.parseDouble(scanner.nextLine());

                service.addAngajat(new Angajat(nume, new Departament(dNume, dLoc), sal));
            } else if (option.equals("2")) {
                service.listBySalary();
            } else if (option.equals("3")) {
                System.out.print("Departament: ");
                String cauta = scanner.nextLine();
                service.findByDepartament(cauta);
            } else if (option.equals("0")) {
                System.out.println("La revedere!");
                break;
            }
        }
        scanner.close();
    }
}
