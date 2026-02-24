package connexio.controller;

import java.time.LocalDate;
import java.util.Scanner;

import connexio.model.Model;
import connexio.view.MenuView;

public class Controller {

    public static void main(String[] args) {

        Model model = new Model();
        MenuView view = new MenuView();
        Scanner sc = new Scanner(System.in);

        int opcio;

        do {
            opcio = view.mostrarMenu();

            switch (opcio) {

                // CREAR
                case 1:
                    model.inserirJugador(view.llegirJugador());
                    System.out.println("Jugador afegit correctament.");
                    break;


                // LLISTAR TOT
                case 2:
                    view.mostrarJugadors(
                        model.getAllJugadors()
                    );
                    break;


                // MODIFICAR
                case 3:
                    String nom = view.demanarNom();
                    System.out.print("Nou equip: ");
                    String nouEquip = sc.nextLine();
                    model.updateEquip(nom, nouEquip);
                    System.out.println("Equip actualitzat.");
                    break;


                // ELIMINAR
                case 4:
                    model.deleteJugador(view.demanarNom());
                    System.out.println("Jugador eliminat.");
                    break;


                // LLISTAR PER DATES
                case 5:
                    LocalDate inici = view.demanarData("Data inici");
                    LocalDate fi = view.demanarData("Data fi");
                    view.mostrarJugadors(
                        model.getJugadorsByDate(inici, fi)
                    );
                    break;


                // FILTRAR TOTS ELS JUGADOR PER EQUIP
                case 6:
                    System.out.print("Equip a cercar: ");
                    String equip = sc.nextLine();
                    view.mostrarJugadors(
                        model.getJugadorsByEquip(equip)
                    );
                    break;

                case 0:
                    System.out.println("Sortint...");
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }

        } while (opcio != 0);
    }
}