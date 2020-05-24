package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Banque {
    private int kbanque;
    private String nomBanque;


    public void setKbanque(int kbanque) {
        this.kbanque = kbanque;
    }

    public int getKbanque() {
        return kbanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    public String getNomBanque() {
        return nomBanque;
    }

}
