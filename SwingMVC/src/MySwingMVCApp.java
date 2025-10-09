import javax.swing.*;

public class MySwingMVCApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Model model = new Model();
                // In Eclipse/VS Code, this reads from project root; if you move file, keep the same name.
                model.loadCatalog("products.txt");

                View view = new View();
                Scanner scanner = new Scanner();
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
