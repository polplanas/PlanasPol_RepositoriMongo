package connexio;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {
    public static void main(String[] args) {

        String uri = "mongodb+srv://polplanas2006_db_user:Polp0192@cluster0.dbohrzr.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("futbol");
            MongoCollection<Document> collection = database.getCollection("jugadors");

            System.out.println("Connexió correcta a MongoDB!");

            FindIterable<Document> jugadors = collection.find();

            int contador = 1;

            for (Document jugador : jugadors) {
                String nom = jugador.getString("nom");
                String cognom = jugador.getString("cognom");

                System.out.println("Jugador " + contador + ": " + nom + " " + cognom);
                contador++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}