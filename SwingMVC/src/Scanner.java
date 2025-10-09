import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scanner {
    private final Random rng = new Random();
    private String lastUpc = null;

    public String pickRandomUpc(Model model) {
        List<String> upcs = new ArrayList<>();
        for (Product p : model.getCatalog()) upcs.add(p.getUpc());
        if (upcs.isEmpty()) throw new IllegalStateException("Catalog is empty");
        String choice = upcs.get(rng.nextInt(upcs.size()));
        // simple avoid immediate repeat
        if (lastUpc != null && upcs.size() > 1 && choice.equals(lastUpc)) {
            choice = upcs.get(rng.nextInt(upcs.size()));
        }
        lastUpc = choice;
        return choice;
    }
}
