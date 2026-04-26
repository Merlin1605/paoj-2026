1.1 — Lista de actiuni / interogari (E-learning)
Inregistrarea unui utilizator nou (Student sau Instructor).

Crearea unui curs nou de catre un instructor.

Inscrierea unui student la un curs (Enrollment).

Adaugarea unei lectii (video sau text) intr-un modul de curs.

Crearea unui Quiz cu mai multe intrebari pentru o anumita lectie.

Simularea sustinerii unui test si calcularea scorului final.

Cautarea cursurilor dupa categorie (ex: Programare, Design, Business).

Vizualizarea progresului unui student la un anumit curs (procentaj lectii parcurse).

Generarea unui certificat de absolvire la finalizarea tuturor lectiilor si testelor.

Listarea tuturor cursurilor predate de un anumit instructor.

Actualizarea detaliilor unui curs (titlu, descriere, pret).

Afisarea topului cursurilor in functie de numarul de studenti inscrisi.

1.2 — Lista de obiecte din domeniu (Clase)
Utilizator (Clasă abstractă pentru date comune: nume, email, id).

Student (Extinde Utilizator; are detalii despre facultate și cursuri).

Instructor (Extinde Utilizator; are specializare și listă de cursuri predate).

CoordonatorCurs (Extinde Instructor; adaugă nivel de vechime și management - nivelul 2 de moștenire).

Curs (Conține titlu, descriere, instructor, categorie și colecții de lecții/tag-uri).

Lectie (Obiect care reține titlul, durata și conținutul video/text).

Categorie (Clasă imutabilă pentru indexarea cursurilor: IT, Business, etc.).

Quiz (Sistem de întrebări și răspunsuri pentru evaluare).

Inscriere (Enrollment - legătura dintre un Student și un Curs).

Certificat (Document generat la finalizarea cursului).