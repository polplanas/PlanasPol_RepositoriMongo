package connexio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.regex;

import connexio.ConnectionManager;

public class Model {

    private MongoCollection<Document> collection;

    // Constructor buit (OBLIGATORI)
    public Model() {
        MongoDatabase db = ConnectionManager.getConnection();
        collection = db.getCollection("jugadors");
    }

    public Document toDocument(Jugador j) {
        return new Document("nom", j.getNom())
                .append("cognom", j.getCognom())
                .append("equip", j.getEquip())
                .append("posicio", j.getPosicio())
                .append("dorsal", j.getDorsal())
                .append("gols", j.getGols())
                .append("nacionalitat", j.getNacionalitat())
                .append("dataFitxatge", j.getDataFitxatge().toString());
    }


    // CREAR
    public void inserirJugador(Jugador j) {
        collection.insertOne(toDocument(j));
    }


    // LLISTAR TOT
    public List<Document> getAllJugadors() {
        return collection.find().into(new ArrayList<>());
    }


    // ACTUALITZAR
    public void updateEquip(String nom, String nouEquip) {
        collection.updateOne(
                eq("nom", nom),
                new Document("$set", new Document("equip", nouEquip))
        );
    }


    // ELIMINAR
    public void deleteJugador(String nom) {
        collection.deleteOne(eq("nom", nom));
    }


    // LLISTAR FILTRANT PER DATA DE FITXATGE
    public List<Document> getJugadorsByDate(LocalDate dataInici, LocalDate dataFi) {
        return collection.find(and(
                gte("dataFitxatge", dataInici.toString()),
                lte("dataFitxatge", dataFi.toString())
        )).into(new ArrayList<>());
    }


    // LLISTAR TOTS ELS JUGADOR FILTRATS PER EQUIP
    public List<Document> getJugadorsByEquip(String equip) {
        return collection.find(regex("equip", equip, "i"))
                .into(new ArrayList<>());
    }
}