import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnvLoader {
    private static final Map<String, String> env = new HashMap<>();

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.trim().startsWith("#")) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        env.put(parts[0].trim(), parts[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading env file: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return env.get(key);
    }
}
