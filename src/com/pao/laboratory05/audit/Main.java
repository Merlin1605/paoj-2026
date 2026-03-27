package com.pao.laboratory05.audit;
import java.util.Scanner;
import com.pao.laboratory05.angajati.AngajatService;
import com.pao.laboratory05.angajati.Angajat;
import com.pao.laboratory05.angajati.Departament;

/**
 * Exercise 4 (Bonus) — Audit Log
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 4 (Bonus) — Audit"
 *
 * Extinde soluția de la Exercise 3 cu un sistem de audit bazat pe record.
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService service = AngajatService.getInstance();
        while (true) {
            System.out.println("\n===== Gestionare Angajati cu Audit =====");
            System.out.println("1. Adauga angajat");
            System.out.println("2. Listare dupa salariu");
            System.out.println("3. Cauta dupa departament");
            System.out.println("4. Afiseaza audit log");
            System.out.println("0. Iesire");
            System.out.print("Optiune: ");

            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.print("Nume: ");
                String nume = scanner.nextLine();
                System.out.print("Dept Nume: ");
                String dNume = scanner.nextLine();
                System.out.print("Dept Locatie: ");
                String dLoc = scanner.nextLine();
                System.out.print("Salariu: ");
                double sal = Double.parseDouble(scanner.nextLine());
                service.addAngajat(new Angajat(nume, new Departament(dNume, dLoc), sal));
            } else if (option.equals("2")) {
                service.listBySalary();
            } else if (option.equals("3")) {
                System.out.print("Nume Departament: ");
                service.findByDepartament(scanner.nextLine());
            } else if (option.equals("4")) {
                service.printAuditLog();
            } else if (option.equals("0")) {
                break;
            }
        }
        scanner.close();
    }
}
