package cz.czechitas.lekce10.controller;

import com.jgoodies.binding.PresentationModel;
import cz.czechitas.lekce10.formbuilder.ActionBuilder;
import cz.czechitas.lekce10.model.OsobaBean;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class KontaktyController {
  public static final List<String> KRAJE = Arrays.asList(
          "Hlavní město",
          "Středočeský kraj",
          "Jihočeský kraj",
          "Plzeňský kraj",
          "Karlovarský kraj",
          "Ústecký kraj",
          "Liberecký kraj",
          "Královéhradecký kraj",
          "Pardubický kraj",
          "Kraj Vysočina",
          "Jihomoravský kraj",
          "Olomoucký kraj",
          "Moravskoslezský kraj",
          "Zlínský kraj"
  );

  public static final List<String> POHLAVI = Arrays.asList("Žena", "Muž");
  //TODO 4 Přidat konstantu „POHLAVI“, která bude obsahovat „List“ typů „String“ se seznamem pohlaví. Do seznamu zadejte alespoň hodnoty „muž“ a „žena“.
  private final PresentationModel<OsobaBean> model;
  private final Action novyAction;
  private final Action ulozitAction;

  public PresentationModel<OsobaBean> getModel() {
    return model;
  }

  public Action getNovyAction() {
    return novyAction;
  }

  public Action getUlozitAction() {
    return ulozitAction;
  }

  public void handleNovy() {
    this.model.setBean(new OsobaBean());
  }

  private void handlePropertyChange(PropertyChangeEvent propertyChangeEvent) {
    vypoctiStavAkci();
  }

  public KontaktyController() {
    model = new PresentationModel<>(new OsobaBean());
    novyAction = ActionBuilder.create("&Nový", this::handleNovy);
    ulozitAction = ActionBuilder.create("&Uložit", this::handleUlozit);
    model.addBeanPropertyChangeListener(this::handlePropertyChange);
    vypoctiStavAkci();
  }

  private void vypoctiStavAkci() {
    //TODO 1 Tlačítko „Uložit“ zpřístupnit jenom tehdy, když je zadané jmené, příjmení, a datum narození.
    OsobaBean osoba = model.getBean();
    boolean enabled = osoba.getJmeno() != null && osoba.getPrijmeni() != null && osoba.getDatumNarozeni() != null;
    ulozitAction.setEnabled(enabled);
  }

  public void handleUlozit() {
    OsobaBean bean = this.model.getBean();
    System.out.println("-- Ukládám kontakty osoby --");
    System.out.printf("Celé jméno: %s", bean.getCeleJmeno()).println();
    System.out.printf("Adresa: %s", bean.getAdresa()).println();
    System.out.printf("Kraj: %s", bean.getKraj()).println();
    System.out.printf("Datum narození: %1$td. %1$tm. %1$tY (%2$d let)", bean.getDatumNarozeni(), bean.getVek()).println();
  }
}
