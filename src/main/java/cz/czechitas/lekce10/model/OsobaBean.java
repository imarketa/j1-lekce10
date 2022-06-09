package cz.czechitas.lekce10.model;

import com.jgoodies.binding.beans.ExtendedPropertyChangeSupport;
import com.jgoodies.common.bean.ObservableBean;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * JavaBean s údaji o osobě.
 */
public class OsobaBean implements ObservableBean {
  //TODO 2 Přidat property „pohlavi“ typu String. Při změně hodnoty informovat posluchače události.
  //TODO 3 Přidat readonly property „dospely“ typu boolean. Při změně hodnoty informovat posluchače události. Property se bude nastavovat z metody vypoctiVek.
  private final ExtendedPropertyChangeSupport pcs = new ExtendedPropertyChangeSupport(this);
  private String jmeno;
  private String prijmeni;
  private String titulPred;
  private String titulZa;
  private String celeJmeno;
  private String adresa;
  private String kraj;
  private LocalDate datumNarozeni;
  private Integer vek;

  public boolean isDospely() {
    return dospely;
  }

  protected void setDospely(Boolean dospely) {
    this.dospely = dospely;
    //doplnit kod z repository

  }

  private boolean dospely;

  public String getPohlavi() {
    return pohlavi;
  }

  public void setPohlavi(String pohlavi) {
    if (pohlavi.isBlank()){
      pohlavi=null;
    }
    String oldValue = this.pohlavi;
    this.pohlavi = pohlavi;
    pcs.firePropertyChange("pohlaví", oldValue, pohlavi);
  }

  private String pohlavi;

  public String getJmeno() {
    return jmeno;
  }

  public void setJmeno(String jmeno) {
    if (jmeno.isBlank()) {
      jmeno = null;
    }
    String oldValue = this.jmeno;
    this.jmeno = jmeno;
    pcs.firePropertyChange("jmeno", oldValue, jmeno);
    vypoctiCeleJmeno();
  }

  public String getPrijmeni() {
    return prijmeni;
  }

  public void setPrijmeni(String prijmeni) {
    if (prijmeni.isBlank()) {
      prijmeni = null;
    }
    String oldValue = this.prijmeni;
    this.prijmeni = prijmeni;
    pcs.firePropertyChange("prijmeni", oldValue, prijmeni);
    vypoctiCeleJmeno();
  }

  public String getTitulPred() {
    return titulPred;
  }

  public void setTitulPred(String titulPred) {
    if (titulPred.isBlank()) {
      titulPred = null;
    }
    String oldValue = this.titulPred;
    this.titulPred = titulPred;
    pcs.firePropertyChange("titulPred", oldValue, titulPred);
    vypoctiCeleJmeno();
  }

  public String getTitulZa() {
    return titulZa;
  }

  public void setTitulZa(String titulZa) {
    if (titulZa.isBlank()) {
      titulZa = null;
    }
    String oldValue = this.titulZa;
    this.titulZa = titulZa;
    pcs.firePropertyChange("titulZa", oldValue, titulZa);
    vypoctiCeleJmeno();
  }

  public String getCeleJmeno() {
    return celeJmeno;
  }

  protected void setCeleJmeno(String celeJmeno) {
    String oldValue = this.celeJmeno;
    this.celeJmeno = celeJmeno;
    pcs.firePropertyChange("celeJmeno", oldValue, celeJmeno);
  }

  public String getAdresa() {
    return adresa;
  }

  public void setAdresa(String adresa) {
    String oldValue = this.adresa;
    this.adresa = adresa;
    pcs.firePropertyChange("adresa", oldValue, adresa);
  }

  public String getKraj() {
    return kraj;
  }

  public void setKraj(String kraj) {
    String oldValue = this.kraj;
    this.kraj = kraj;
    pcs.firePropertyChange("kraj", oldValue, kraj);
  }

  public LocalDate getDatumNarozeni() {
    return datumNarozeni;
  }

  public void setDatumNarozeni(LocalDate datumNarozeni) {
    LocalDate oldValue = this.datumNarozeni;
    this.datumNarozeni = datumNarozeni;
    pcs.firePropertyChange("datumNarozeni", oldValue, datumNarozeni);
    vypoctiVek();
  }

  public Date getDatumNarozeniDate() {
    if (datumNarozeni == null) {
      return null;
    }
    return Date.from(datumNarozeni.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public void setDatumNarozeniDate(Date datumNarozeni) {
    if (datumNarozeni == null) {
      this.setDatumNarozeni(null);
    } else {
      this.setDatumNarozeni(LocalDate.ofInstant(datumNarozeni.toInstant(), ZoneId.systemDefault()));
    }
    pcs.firePropertyChange("datumNarozeniDate", null, datumNarozeni);
    vypoctiVek();
  }

  public Integer getVek() {
    return vek;
  }

  protected void setVek(Integer vek) {
    Integer oldValue = this.vek;
    this.vek = vek;
    pcs.firePropertyChange("vek", oldValue, vek);
  }

  protected void vypoctiCeleJmeno() {
    StringBuilder builer = new StringBuilder();
    if (titulPred != null) {
      builer.append(titulPred);
      builer.append(" ");
    }
    if (jmeno != null) {
      builer.append(jmeno);
      if (prijmeni != null) {
        builer.append(' ');
      }
    }
    if (prijmeni != null) {
      builer.append(prijmeni);
    }
    if (titulZa != null) {
      builer.append(", ");
      builer.append(titulZa);
    }
    setCeleJmeno(builer.toString());
  }

  protected void vypoctiVek() {
    if (datumNarozeni == null) {
      setVek(null);
      setDospely(null);
    }
    setVek(datumNarozeni.until(LocalDate.now()).getYears());
    setDospely(vek>=18);
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    pcs.removePropertyChangeListener(listener);
  }
}
