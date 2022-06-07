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

  private final ExtendedPropertyChangeSupport pcs = new ExtendedPropertyChangeSupport(this);
  private String jmeno;
  private String prijmeni;
  private String titulPred;
  private String titulZa;
  private String celeJmeno;
  private String adresa;
  private String kraj;
  private LocalDate datumNarozeni;
  private int vek;

  public String getJmeno() {
    return jmeno;
  }

  public void setJmeno(String jmeno) {
    if (jmeno.isBlank()) {
      jmeno = null;
    }
    pcs.firePropertyChange("jmeno", this.jmeno, jmeno);
    this.jmeno = jmeno;
    vypoctiCeleJmeno();
  }

  public String getPrijmeni() {
    return prijmeni;
  }

  public void setPrijmeni(String prijmeni) {
    if (prijmeni.isBlank()) {
      prijmeni = null;
    }
    pcs.firePropertyChange("prijmeni", this.prijmeni, prijmeni);
    this.prijmeni = prijmeni;
    vypoctiCeleJmeno();
  }

  public String getTitulPred() {
    return titulPred;
  }

  public void setTitulPred(String titulPred) {
    if (titulPred.isBlank()) {
      titulPred = null;
    }
    pcs.firePropertyChange("titulPred", this.titulPred, titulPred);
    this.titulPred = titulPred;
    vypoctiCeleJmeno();
  }

  public String getTitulZa() {
    return titulZa;
  }

  public void setTitulZa(String titulZa) {
    if (titulZa.isBlank()) {
      titulZa = null;
    }
    pcs.firePropertyChange("titulZa", this.titulZa, titulZa);
    this.titulZa = titulZa;
    vypoctiCeleJmeno();
  }

  public String getCeleJmeno() {
    return celeJmeno;
  }

  protected void setCeleJmeno(String celeJmeno) {
    pcs.firePropertyChange("celeJmeno", this.celeJmeno, celeJmeno);
    this.celeJmeno = celeJmeno;
  }

  public String getAdresa() {
    return adresa;
  }

  public void setAdresa(String adresa) {
    pcs.firePropertyChange("adresa", this.adresa, adresa);
    this.adresa = adresa;
  }

  public String getKraj() {
    return kraj;
  }

  public void setKraj(String kraj) {
    pcs.firePropertyChange("kraj", this.kraj, kraj);
    this.kraj = kraj;
  }

  public LocalDate getDatumNarozeni() {
    return datumNarozeni;
  }

  public void setDatumNarozeni(LocalDate datumNarozeni) {
    pcs.firePropertyChange("datumNarozeni", this.datumNarozeni, datumNarozeni);
    this.datumNarozeni = datumNarozeni;
    vypoctiVek();
  }

  public Date getDatumNarozeniDate() {
    if (datumNarozeni == null) {
      return null;
    }
    return Date.from(datumNarozeni.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public void setDatumNarozeniDate(Date datumNarozeni) {
    pcs.firePropertyChange("datumNarozeniDate", this.datumNarozeni, datumNarozeni);
    if (datumNarozeni == null) {
      this.setDatumNarozeni(null);
    } else {
      this.setDatumNarozeni(LocalDate.ofInstant(datumNarozeni.toInstant(), ZoneId.systemDefault()));
    }
    vypoctiVek();
  }

  public int getVek() {
    return vek;
  }

  protected void setVek(int vek) {
    pcs.firePropertyChange("vek", this.vek, vek);
    this.vek = vek;
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
      setVek(0);
    }
    int vek = datumNarozeni.until(LocalDate.now()).getYears();
    setVek(vek);
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
