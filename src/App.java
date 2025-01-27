import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1";
        URI uri = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();

        String apiToken= EnvLoader.get("API_TOKEN");

        HttpRequest request = HttpRequest.newBuilder(uri)
                .GET()
                .header("Authorization", "Bearer " + apiToken)
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, Object>> movieList = parser.parse(body);

        for (Map<String, Object> movie : movieList) {
            String title = (String) movie.getOrDefault("title", "Title not available");
            String posterPath = (String) movie.get("poster_path");
            String posterUrl = posterPath != null ? "https://image.tmdb.org/t/p/w500" + posterPath : "Poster not available";
            String rating = String.valueOf(movie.getOrDefault("vote_average", "Rating not available"));
            String starEmoji = "⭐";
            int stars = (int) Math.round(Double.parseDouble(rating));

            System.out.println("\u001b[34mTitle: " + title);
            System.out.println("Poster Image URL: " + posterUrl);
            System.out.println("Rating: " + starEmoji.repeat(stars));
            System.out.println();
        }
    }
}
