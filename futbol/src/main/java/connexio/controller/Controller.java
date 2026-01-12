package connexio.controller;

import org.bson.Document;

import connexio.dao.JugadorDAO;
import connexio.view.MenuView;

public class Controller {

    private JugadorDAO dao;
    private MenuView view;

    public Controller(JugadorDAO dao, MenuView view) {
        this.dao = dao;
        this.view = view;
    }

    public void iniciar() {
        int opcio;

        do {
            opcio = view.mostrarMenu();

            switch (opcio) {
                case 1: dao.inserir(view.llegirJugador());
                break;

                case 2: {
                    int i = 1;
                    for (Document d : dao.llistar()) {
                        System.out.println("Jugador " + i + ": "
                                + d.getString("nom") + " "
                                + d.getString("cognom") + " - "
                                + d.getString("equip"));
                        i++;
                    }
                }
                break;

                case 3: {
                    String nom = view.demanarNom();
                    String equip = view.demanarEquipNou();
                    dao.actualitzarEquip(nom, equip);
                }
                break;

                case 4: dao.eliminar(view.demanarNom());
                break;

                case 0: System.out.println("Sortint...");
                break;
                default: System.out.println("Opció no vàlida");
                break;
            }

        } while (opcio != 0);
    }
}