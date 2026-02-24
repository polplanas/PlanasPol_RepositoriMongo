package connexio;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionManager {

    private static final String URI =
        "mongodb+srv://polplanas2006_db_user:Polp0192@cluster0.dbohrzr.mongodb.net/?retryWrites=true&w=majority";

    private static final String DB_NAME = "futbol";

    public static MongoDatabase getConnection() {
        MongoClient client = MongoClients.create(URI);
        client.getDatabase("admin").runCommand(new Document("ping", 1));
        System.out.println("Connexió correcta a MongoDB");
        return client.getDatabase(DB_NAME);
    }
}