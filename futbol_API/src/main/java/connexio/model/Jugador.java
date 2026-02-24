package connexio.model;

import java.time.LocalDate;

public class Jugador {

    private String nom;
    private String cognom;
    private String equip;
    private String posicio;
    private int dorsal;
    private int gols;
    private String nacionalitat;
    private LocalDate dataFitxatge;

    public Jugador(String nom, String cognom, String equip, String posicio, int dorsal, int gols, String nacionalitat, LocalDate dataFitxatge) {

        this.nom = nom;
        this.cognom = cognom;
        this.equip = equip;
        this.posicio = posicio;
        this.dorsal = dorsal;
        this.gols = gols;
        this.nacionalitat = nacionalitat;
        this.dataFitxatge = dataFitxatge;
    }

    public String getNom() { return nom; }
    public String getCognom() { return cognom; }
    public String getEquip() { return equip; }
    public String getPosicio() { return posicio; }
    public int getDorsal() { return dorsal; }
    public int getGols() { return gols; }
    public String getNacionalitat() { return nacionalitat; }
    public LocalDate getDataFitxatge() { return dataFitxatge; }
}