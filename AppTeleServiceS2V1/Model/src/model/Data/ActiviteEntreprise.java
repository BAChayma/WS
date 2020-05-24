package model.Data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ActiviteEntreprise {
    private int kActEnt;
    private String libelleAE;
   
    public void setKActEnt(int kActEnt) {
        this.kActEnt = kActEnt;
    }

    public int getKActEnt() {
        return kActEnt;
    }

    public void setLibelleAE(String libelleAE) {
        this.libelleAE = libelleAE;
    }

    public String getLibelleAE() {
        return libelleAE;
    }
}
