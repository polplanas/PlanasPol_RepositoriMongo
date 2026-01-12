package connexio.dao;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import connexio.model.Jugador;

public class JugadorDAO {

    private MongoCollection<Document> collection;

    public JugadorDAO(MongoDatabase database) {
        this.collection = database.getCollection("jugadors");
    }

    // CREATE
    public void inserir(Jugador j) {
        Document doc = new Document("nom", j.getNom())
                .append("cognom", j.getCognom())
                .append("equip", j.getEquip())
                .append("posicio", j.getPosicio())
                .append("dorsal", j.getDorsal())
                .append("nacionalitat", j.getNacionalitat())
                .append("dataFitxatge", j.getDataFitxatge());

        collection.insertOne(doc);
    }

    // READ
    public FindIterable<Document> llistar() {
        return collection.find();
    }

    // UPDATE
    public void actualitzarEquip(String nom, String equipNou) {
        collection.updateOne(
                new Document("nom", nom),
                new Document("$set", new Document("equip", equipNou))
        );
    }

    // DELETE
    public void eliminar(String nom) {
        collection.deleteOne(new Document("nom", nom));
    }
}
