import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scanner {
    private final List<String> upcs = new ArrayList<>();
    private final Random rng = new Random();
    private String lastUpc = null;

    /**
     * Call this once at startup. For example: scanner.loadProducts("products.txt");
     * The file should contain lines like:
     * 12345 Coffee $8.32
     * 67890 Muffin 2.50
     */
    public void loadProducts(String path) {
        File f = new File(path);
        if (!f.exists()) {
            throw new IllegalStateException("Products file not found: " + path);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("\\s+");
                if (parts.length >= 1) {
                    // First token is UPC
                    String upc = parts[0];
                    // skip header lines that don't start with a digit
                    if (!upc.matches("\\d+")) continue;
                    upcs.add(upc);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load products: " + e.getMessage(), e);
        }
        if (upcs.isEmpty()) {
            throw new IllegalStateException("No UPCs loaded from " + path);
        }
    }

    /** Returns a random UPC each time; avoids immediate repeat when possible. */
    public String pickRandomUpc() {
        if (upcs.isEmpty()) throw new IllegalStateException("UPC list not loaded");
        String choice = upcs.get(rng.nextInt(upcs.size()));
        if (lastUpc != null && upcs.size() > 1 && choice.equals(lastUpc)) {
            choice = upcs.get(rng.nextInt(upcs.size()));
        }
        lastUpc = choice;
        return choice;
    }
}
