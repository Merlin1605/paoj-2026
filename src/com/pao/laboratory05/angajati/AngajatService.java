package com.pao.laboratory05.angajati;

import com.pao.laboratory05.audit.AuditEntry;
import java.util.Arrays;
import java.time.LocalDateTime;

public class AngajatService {
    private Angajat[] angajati = new Angajat[0];
    private AuditEntry[] auditLog = new AuditEntry[0];

    private AngajatService() {
    }

    private static class Holder {
        private static final AngajatService INSTANCE = new AngajatService();
    }

    public static AngajatService getInstance() {
        return Holder.INSTANCE;
    }

    private void logAction(String action, String target) {
        AuditEntry[] tmp = new AuditEntry[auditLog.length + 1];
        System.arraycopy(auditLog, 0, tmp, 0, auditLog.length);
        tmp[tmp.length - 1] = new AuditEntry(action, target, LocalDateTime.now().toString());
        auditLog = tmp;
    }

    public void addAngajat(Angajat a) {
        Angajat[] tmp = new Angajat[angajati.length + 1];
        System.arraycopy(angajati, 0, tmp, 0, angajati.length);
        tmp[tmp.length - 1] = a;
        angajati = tmp;
        logAction("ADD", a.getNume());
        System.out.println("Angajat adaugat: " + a.getNume());
    }

    public void printAll() {
        for (Angajat a : angajati) {
            System.out.println(a);
        }
    }

    public void listBySalary() {
        Angajat[] copy = angajati.clone();
        Arrays.sort(copy);
        System.out.println("--- Angajati dupa salariu (descrescator) ---");
        for (int i = 0; i < copy.length; i++) {
            System.out.println((i + 1) + ". " + copy[i]);
        }
    }

    public void findByDepartament(String numeDept) {
        logAction("FIND_BY_DEPT", numeDept);
        for (Angajat a : angajati) {
            if (a.getDepartament().nume().equalsIgnoreCase(numeDept)) {
                System.out.println(a);
            }
        }
    }

    public void printAuditLog() {
        System.out.println("\n--- Audit Log ---");
        for (AuditEntry e : auditLog) {
            System.out.println(e);
        }
    }
}