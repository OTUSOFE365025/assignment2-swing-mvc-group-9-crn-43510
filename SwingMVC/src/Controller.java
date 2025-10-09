import javax.swing.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Controller {
    private final Model model;
    private final View view;
    private final Scanner scanner;
    private final NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CANADA);

    public Controller(Model model, View view, Scanner scanner) {
        this.model = model;
        this.view = view;
        this.scanner = scanner;
        attachHandlers();
        refreshTotals();
    }

    private void attachHandlers() {
        view.getScanButton().addActionListener(e -> onScan());
        view.getClearButton().addActionListener(e -> onClear());

        // Keyboard shortcuts: Enter = Scan, Ctrl+L = Clear
        JRootPane root = view.getRootPane();
        root.setDefaultButton(view.getScanButton());
        InputMap im = root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = root.getActionMap();
        im.put(KeyStroke.getKeyStroke("control L"), "clear");
        am.put("clear", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onClear();
            }
        });
    }

    private void onScan() {
        try {
            String upc = scanner.pickRandomUpc(model);
            model.addItemByUpc(upc);
            Product p = model.findByUpc(upc).get();
            view.addItemLine(formatItem(p));
            refreshTotals();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Scan failed: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClear() {
		if (model.getScannedItems().size() >= 5) {
			int res = JOptionPane.showConfirmDialog(view,
					"Clear all scanned items?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
			if (res != JOptionPane.OK_OPTION) return;
		}
		model.clear();
		view.resetList();
		refreshTotals();
	}
	

    private void refreshTotals() {
        BigDecimal subtotal = model.getSubtotal();
        view.setSubtotalText("Subtotal: " + currency.format(subtotal));
    }

    private String formatItem(Product p) {
        return p.getName() + " — " + currency.format(p.getPrice()) + "  [" + p.getUpc() + "]";
    }

    public void show() {
        SwingUtilities.invokeLater(() -> view.setVisible(true));
    }
}
