import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> itemList = new JList<>(listModel);
    private final JLabel subtotalLabel = new JLabel("Subtotal: $0.00");
    private final JButton scanButton = new JButton("Scan");
    private final JButton clearButton = new JButton("Clear");

    // new fields
    private final JTextField upcField = new JTextField(10);
    private final JButton addByUpcButton = new JButton("Add by UPC");

    public View() {
        super("Cash Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        itemList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        itemList.setVisibleRowCount(12);
        JScrollPane scroll = new JScrollPane(itemList);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(subtotalLabel, BorderLayout.WEST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(new JLabel("UPC:"));
        actions.add(upcField);
        actions.add(addByUpcButton);
        actions.add(clearButton);
        actions.add(scanButton);
        bottom.add(actions, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setSize(520, 420);
        setLocationRelativeTo(null);
    }

    public JButton getScanButton() { return scanButton; }
    public JButton getClearButton() { return clearButton; }
    public JButton getAddByUpcButton() { return addByUpcButton; }
    public String getEnteredUpc() { return upcField.getText().trim(); }
    public void clearUpcField() { upcField.setText(""); }

    public void addItemLine(String line) {
        listModel.addElement(line);
        itemList.ensureIndexIsVisible(listModel.size() - 1);
    }

    public void resetList() { listModel.clear(); }
    public void setSubtotalText(String text) { subtotalLabel.setText(text); }
}
