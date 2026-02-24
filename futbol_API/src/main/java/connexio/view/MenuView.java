package connexio.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import connexio.model.Jugador;

public class MenuView {

    private Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n--- MENU JUGADORS ---");
        System.out.println("1. Afegir jugador");
        System.out.println("2. Llistar jugadors");
        System.out.println("3. Actualitzar equip");
        System.out.println("4. Eliminar jugador");
        System.out.println("5. Llistar jugadors per dates de fitxatge");
        System.out.println("6. Cerca jugadors per equip");
        System.out.println("0. Sortir");
        System.out.print("Opció: ");
        return sc.nextInt();
    }

    public Jugador llegirJugador() {
        sc.nextLine();
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Cognom: ");
        String cognom = sc.nextLine();
        System.out.print("Equip: ");
        String equip = sc.nextLine();
        System.out.print("Posició: ");
        String posicio = sc.nextLine();
        System.out.print("Dorsal: ");
        int dorsal = sc.nextInt();
        System.out.print("Gols: ");
        int gols = sc.nextInt();
        sc.nextLine();
        System.out.print("Nacionalitat: ");
        String nacionalitat = sc.nextLine();
        System.out.print("Data fitxatge (YYYY-MM-DD): ");
        LocalDate data = LocalDate.parse(sc.nextLine());

        return new Jugador(nom, cognom, equip, posicio, dorsal, gols, nacionalitat, data);
    }

    public String demanarNom() {
        sc.nextLine();
        System.out.print("Nom del jugador: ");
        return sc.nextLine();
    }

    public String demanarEquip() {
        System.out.print("Nou equip: ");
        return sc.nextLine();
    }

    public LocalDate demanarData(String text) {
        System.out.print(text + " (YYYY-MM-DD): ");
        return LocalDate.parse(sc.next());
    }

    public void mostrarJugadors(List<Document> jugadors) {

        System.out.println("\n--- LLISTAT DE JUGADORS ---");

        for (Document d : jugadors) {
            System.out.println( "- " +
                d.getString("nom") + " " +
                d.getString("cognom") + " | " +
                d.getString("equip") + " | " +
                d.getString("posicio") + " | Dorsal: " +
                d.getInteger("dorsal") + " | Gols: " +
                d.getInteger("gols") + " | Fitxatge: " +
                d.get("dataFitxatge") + "\n"
            );
        }
    }
}