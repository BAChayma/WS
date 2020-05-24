package model.Data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Impot {
    private int kimpot;
    private String limpot;
    private String abriviation;
    private String periodicite;
    private double taux;
    private Date dateDebEffet;

    public void setKimpot(int kimpot) {
        this.kimpot = kimpot;
    }

    public int getKimpot() {
        return kimpot;
    }

    public void setLimpot(String limpot) {
        this.limpot = limpot;
    }

    public String getLimpot() {
        return limpot;
    }

    public void setAbriviation(String abriviation) {
        this.abriviation = abriviation;
    }

    public String getAbriviation() {
        return abriviation;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public double getTaux() {
        return taux;
    }

    public void setDateDebEffet(Date dateDebEffet) {
        this.dateDebEffet = dateDebEffet;
    }

    public Date getDateDebEffet() {
        return dateDebEffet;
    }
}
