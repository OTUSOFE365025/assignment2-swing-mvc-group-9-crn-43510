import javax.swing.*;

public class MySwingMVCApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Model model = new Model();
                model.loadCatalog("products.txt");
                if (model.getCatalog().isEmpty()) {
                    throw new IllegalStateException("Catalog is empty after loading products.txt");
                }

                View view = new View();            // no-arg constructor
                Scanner scanner = new Scanner();    // our custom class
                Controller controller = new Controller(model, view, scanner);
                controller.show();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Startup failed: " + ex.getMessage() +
                        "\nEnsure products.txt exists in the project root or on classpath.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
