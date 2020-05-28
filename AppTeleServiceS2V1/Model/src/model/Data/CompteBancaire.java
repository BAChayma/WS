package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompteBancaire {
    
    private int kcompte ;
    private String rib;
    private int kagence;
    private int kbanque;
    
    private String nomBanque;
    private String libelleAgence;

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setLibelleAgence(String libelleAgence) {
        this.libelleAgence = libelleAgence;
    }

    public String getLibelleAgence() {
        return libelleAgence;
    }


    public void setKcompte(int kcompte) {
        this.kcompte = kcompte;
    }

    public int getKcompte() {
        return kcompte;
    }


    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getRib() {
        return rib;
    }

    public void setKagence(int kagence) {
        this.kagence = kagence;
    }

    public int getKagence() {
        return kagence;
    }

    public void setKbanque(int kbanque) {
        this.kbanque = kbanque;
    }

    public int getKbanque() {
        return kbanque;
    }

}
