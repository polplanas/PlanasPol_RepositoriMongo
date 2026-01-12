package connexio;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import connexio.controller.Controller;
import connexio.dao.JugadorDAO;
import connexio.view.MenuView;

public class App {

    public static void main(String[] args) {

        String uri = "mongodb+srv://polplanas2006_db_user:Polp0192@cluster0.dbohrzr.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient client = MongoClients.create(uri)) {

            client.getDatabase("admin").runCommand(new Document("ping", 1));
            System.out.println("Connexió correcta a MongoDB Atlas");

            MongoDatabase database = client.getDatabase("futbol");

            JugadorDAO dao = new JugadorDAO(database);
            MenuView view = new MenuView();
            Controller controller = new Controller(dao, view);

            controller.iniciar();
        }
    }
}