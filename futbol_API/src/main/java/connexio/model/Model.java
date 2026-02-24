package connexio.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Model {

    private HttpClient client;
    private final String baseURL = "https://pol-planas-api-futbol.vercel.app";

    public Model() {
        client = HttpClient.newHttpClient();
    }

    // CREAR
    public void inserirJugador(Jugador j) {

        try {
            String json = "{"
                    + "\"nom\":\"" + j.getNom() + "\","
                    + "\"cognom\":\"" + j.getCognom() + "\","
                    + "\"equip\":\"" + j.getEquip() + "\","
                    + "\"posicio\":\"" + j.getPosicio() + "\","
                    + "\"dorsal\":" + j.getDorsal() + ","
                    + "\"gols\":" + j.getGols() + ","
                    + "\"nacionalitat\":\"" + j.getNacionalitat() + "\","
                    + "\"dataFitxatge\":\"" + j.getDataFitxatge() + "\""
                    + "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL + "/add"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            System.out.println("Error inserint jugador: " + e.getMessage());
        }
    }

    // LLISTAR TOT
    public List<Document> getAllJugadors() {
        return getDocuments("/list");
    }

    // ACTUALITZAR
    public void updateEquip(String nom, String nouEquip) {

        try {
            String json = "{"
                    + "\"nom\":\"" + nom + "\","
                    + "\"equip\":\"" + nouEquip + "\""
                    + "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL + "/update"))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            System.out.println("Error actualitzant: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void deleteJugador(String nom) {

        try {
            String json = "{"
                    + "\"nom\":\"" + nom + "\""
                    + "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL + "/delete"))
                    .header("Content-Type", "application/json")
                    .method("DELETE", HttpRequest.BodyPublishers.ofString(json))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            System.out.println("Error eliminant: " + e.getMessage());
        }
    }

    // FILTRAR PER DATES
    public List<Document> getJugadorsByDate(LocalDate inici, LocalDate fi) {
        return getDocuments("/list/" + inici + "/" + fi);
    }

    // MÈTODE AUXILIAR PER LLISTAR
    private List<Document> getDocuments(String endpoint) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL + endpoint))
                    .GET()
                    .build();

            String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            return Document.parse("{\"array\":" + response + "}")
                    .getList("array", Document.class);

        } catch (Exception e) {
            System.out.println("Error obtenint dades: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}