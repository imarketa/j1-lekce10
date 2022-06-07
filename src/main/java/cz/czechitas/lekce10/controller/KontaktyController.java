package cz.czechitas.lekce10.controller;

import com.jgoodies.binding.PresentationModel;
import cz.czechitas.lekce10.model.OsobaBean;

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

  private final PresentationModel<OsobaBean> model = new PresentationModel<>(new OsobaBean());

  public PresentationModel<OsobaBean> getModel() {
    return model;
  }

  public void handleNew() {
    this.model.setBean(new OsobaBean());
  }

  public void handleSave() {
    OsobaBean bean = this.model.getBean();
    System.out.println("Ukládám kontakty osoby:");
    System.out.println(bean.getCeleJmeno());
    System.out.println(bean.getAdresa());
    System.out.println(bean.getKraj());
  }
}
