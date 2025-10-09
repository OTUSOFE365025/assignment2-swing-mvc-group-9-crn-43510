import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String upc;
    private final String name;
    private final BigDecimal price;

    public Product(String upc, String name, BigDecimal price) {
        this.upc = upc;
        this.name = name;
        this.price = price;
    }
    public String getUpc() { return upc; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }

    @Override public String toString() {
        return name + " (" + upc + ") - " + price;
    }
    @Override public boolean equals(Object o) {
        if (!(o instanceof Product)) return false;
        return Objects.equals(upc, ((Product)o).upc);
    }
    @Override public int hashCode() { return Objects.hash(upc); }
}
