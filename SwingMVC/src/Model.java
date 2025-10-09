import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Model {
    private final Map<String, Product> catalog = new LinkedHashMap<>();
    private final List<Product> scanned = new ArrayList<>();
    private java.math.BigDecimal subtotal = java.math.BigDecimal.ZERO;

    public void loadCatalog(String pathOrResource) {
        List<String> lines = readAllLines(pathOrResource);
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            // Before splitting, normalize: join "$ <num>" into "$<num>"
			line = line.replaceAll("\\$\\s+(\\d)", "\\$$1");


            String[] parts = line.split("\\s+");
            if (parts.length < 3) continue;

            String upc = parts[0];
            String rawPrice = parts[parts.length - 1].replace("$", "").replace(",", "");
            BigDecimal price = new BigDecimal(rawPrice);
            String name = String.join(" ", Arrays.asList(parts).subList(1, parts.length - 1));

            Product p = new Product(upc, name, price);
            catalog.put(upc, p);
        }
        if (catalog.isEmpty()) throw new IllegalStateException("No products loaded from " + pathOrResource);
    }

    private List<String> readAllLines(String pathOrResource) {
		// already implemented: tries filesystem first, then classpath "/products.txt"
		// This commit documents that behavior; no code change if your current method matches.

        File f = new File(pathOrResource);
        try {
            if (f.exists()) {
                return java.nio.file.Files.readAllLines(f.toPath());
            } else {
                try (InputStream in = getClass().getResourceAsStream("/" + pathOrResource)) {
                    if (in == null) throw new FileNotFoundException(pathOrResource);
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                        List<String> out = new ArrayList<>();
                        String s;
                        while ((s = br.readLine()) != null) out.add(s);
                        return out;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read " + pathOrResource + ": " + e.getMessage(), e);
        }
    }

    public Collection<Product> getCatalog() { return Collections.unmodifiableCollection(catalog.values()); }
    public Optional<Product> findByUpc(String upc) { return Optional.ofNullable(catalog.get(upc)); }

    public void addItemByUpc(String upc) {
		Product p = findByUpc(upc).orElseThrow(() ->
			new IllegalArgumentException("UPC not found in catalog: " + upc));
		addItem(p);
	}
	

    public void addItem(Product p) {
        scanned.add(p);
        subtotal = subtotal.add(p.getPrice());
    }

    public List<Product> getScannedItems() { return Collections.unmodifiableList(scanned); }
    public java.math.BigDecimal getSubtotal() { return subtotal; }
    public void clear() { scanned.clear(); subtotal = java.math.BigDecimal.ZERO; }
}
