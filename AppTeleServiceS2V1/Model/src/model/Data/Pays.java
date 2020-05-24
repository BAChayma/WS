package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pays {
    private int kpays;
    private String pays;
    private String nationnalite;


    public void setKpays(int kpays) {
        this.kpays = kpays;
    }

    public int getKpays() {
        return kpays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPays() {
        return pays;
    }

    public void setNationnalite(String nationnalite) {
        this.nationnalite = nationnalite;
    }

    public String getNationnalite() {
        return nationnalite;
    }
}
