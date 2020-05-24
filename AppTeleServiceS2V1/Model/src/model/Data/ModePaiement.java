package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ModePaiement {
    private int kModePaiement;
    private String libelle;

    public void setKModePaiement(int kModePaiement) {
        this.kModePaiement = kModePaiement;
    }

    public int getKModePaiement() {
        return kModePaiement;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
