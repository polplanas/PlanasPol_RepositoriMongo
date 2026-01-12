package connexio.view;

import java.time.LocalDate;
import java.util.Scanner;

import connexio.model.Jugador;

public class MenuView {

    private Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n--- MENU JUGADORS ---");
        System.out.println("1. Afegir jugador");
        System.out.println("2. Llistar jugadors");
        System.out.println("3. Actualitzar equip");
        System.out.println("4. Eliminar jugador");
        System.out.println("0. Sortir");
        System.out.print("Opció: ");
        return sc.nextInt();
    }

    public Jugador llegirJugador() {

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

        LocalDate dataFitxatge = LocalDate.now();

        return new Jugador(nom, cognom, equip, posicio, dorsal, gols, nacionalitat, dataFitxatge);
    }



    public String demanarNom() {
        sc.nextLine();
        System.out.print("Nom del jugador: ");
        return sc.nextLine();
    }

    public String demanarEquipNou() {
        System.out.print("Nou equip: ");
        return sc.nextLine();
    }
}