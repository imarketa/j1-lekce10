package cz.czechitas.lekce10.view;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;
import cz.czechitas.lekce10.Aplikace;
import cz.czechitas.lekce10.controller.KontaktyController;
import cz.czechitas.lekce10.model.OsobaBean;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * Formulář – hlavní okno aplikace.
 */
public class HlavniOkno extends JFrame {
  private final KontaktyController controller;

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

  public HlavniOkno(KontaktyController controller) throws HeadlessException {
    super("Kontakty");
    this.controller = controller;
    this.init();
  }

  public void start() {
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void init() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setIconImage(new ImageIcon(Aplikace.class.getResource("czechitas-icon.png")).getImage());
    setLayout(new MigLayout("wrap 4", "[right]rel[50:75:250,grow,fill]unrel[right]rel[50:75:250,grow,fill]"));
    setMinimumSize(new Dimension(400, 200));

    addTextField("&Jméno", "jmeno");
    addTextField("&Příjmení", "prijmeni");

    addTextField("Titul pře&d", "titulPred");
    addTextField("Titul &za", "titulZa");

    addTextField("Celé jméno", "celeJmeno", "span").setEditable(false);

    addTextField("&Adresa", "adresa", "span");

    addComboBox("&Kraj", "kraj", KontaktyController.KRAJE, "span");

    addDateField("&Datum narození", "datumNarozeniDate");
    addNumberField("Věk", "vek").setEditable(false);

    add(generateButtonBar(), "right, span");
    pack();
  }

  private JPanel generateButtonBar() {
    JPanel panel = new JPanel();

    addButton(panel, "&Nový", controller::handleNew);
    JButton saveButton = addButton(panel, "&Uložit", controller::handleSave);
    getRootPane().setDefaultButton(saveButton);
    return panel;
  }

  //region Pomocné metody pro vytváří komponent GUI
  private JTextField addTextField(String labelText, String property) {
    return addTextField(labelText, property, null);
  }

  private JTextField addTextField(String labelText, String property, String constraints) {
    JTextField textField = new JTextField();
    Bindings.bind(textField, getBeanModel().getModel(property));

    addComponent(labelText, textField, constraints);
    return textField;
  }

  private JFormattedTextField addFormattedField(String labelText, String property) {
    return addDateField(labelText, property, null);
  }

  private JFormattedTextField addFormattedField(String labelText, String property, JFormattedTextField.AbstractFormatter formatter, String constraints) {
    JFormattedTextField textField = new JFormattedTextField(formatter);
    Bindings.bind(textField, getBeanModel().getModel(property));

    addComponent(labelText, textField, constraints);
    return textField;
  }

  private JFormattedTextField addDateField(String labelText, String property) {
    return addDateField(labelText, property, null);
  }

  private JFormattedTextField addDateField(String labelText, String property, String constraints) {
    return addFormattedField(labelText, property, new DateFormatter(dateFormat), constraints);
  }

  private JFormattedTextField addNumberField(String labelText, String property) {
    return addNumberField(labelText, property, null);
  }

  private JFormattedTextField addNumberField(String labelText, String property, String constraints) {
    return addFormattedField(labelText, property, new NumberFormatter(), constraints);
  }

  private <E> JComboBox<E> addComboBox(String labelText, String property, List<E> values) {
    return addComboBox(labelText, property, values, null);
  }

  private <E> JComboBox<E> addComboBox(String labelText, String property, List<E> values, String constraints) {
    JComboBox<E> comboBox = new JComboBox<>();
    Bindings.bind(comboBox, new SelectionInList<>(values, getBeanModel().getModel(property)));

    addComponent(labelText, comboBox, constraints);
    return comboBox;
  }

  private <T extends JComponent> T addComponent(String labelText, T component) {
    return addComponent(labelText, component, null);
  }

  private <T extends JComponent> T addComponent(String labelText, T component, String constraints) {
    JLabel label = new JLabel();
    setTextAndMnemonic(label, labelText);
    label.setLabelFor(component);

    add(label);
    add(component, constraints);
    return component;
  }

  private JButton addButton(Container container, String text, Runnable action) {
    JButton button = new JButton();
    setTextAndMnemonic(button, text);
    button.addActionListener((event) -> action.run());
    container.add(button);
    return button;
  }

  private void setTextAndMnemonic(JLabel component, String text) {
    int mnemonicIndex = text.indexOf("&");
    if (mnemonicIndex < 0) {
      component.setText(text);
    } else {
      Objects.checkIndex(mnemonicIndex, text.length() - 1);
      component.setText(text.substring(0, mnemonicIndex) + text.substring(mnemonicIndex + 1));
      component.setDisplayedMnemonicIndex(mnemonicIndex);
    }
  }

  private void setTextAndMnemonic(JButton component, String text) {
    int mnemonicIndex = text.indexOf("&");
    if (mnemonicIndex < 0) {
      component.setText(text);
    } else {
      Objects.checkIndex(mnemonicIndex, text.length() - 1);
      component.setText(text.substring(0, mnemonicIndex) + text.substring(mnemonicIndex + 1));
      component.setDisplayedMnemonicIndex(mnemonicIndex);
    }
  }
  //endregion

  private PresentationModel<OsobaBean> getBeanModel() {
    return controller.getModel();
  }
}
