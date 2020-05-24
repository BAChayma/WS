package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Agence {
    
    private int kagence;
    private String libelleAgence;


    public void setKagence(int kagence) {
        this.kagence = kagence;
    }

    public int getKagence() {
        return kagence;
    }

    public void setLibelleAgence(String libelleAgence) {
        this.libelleAgence = libelleAgence;
    }

    public String getLibelleAgence() {
        return libelleAgence;
    }

}
