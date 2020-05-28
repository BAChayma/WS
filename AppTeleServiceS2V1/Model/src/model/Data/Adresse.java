package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Adresse {
    private int kadresse;
    private int numRue;
    private String rue;
    private String cp;
    
    private int kStructureAdr;
    private String libellesadr;
    
    private int kTStructureAdr ;
    private String libelletsadr ;


    public void setKadresse(int kadresse) {
        this.kadresse = kadresse;
    }

    public int getKadresse() {
        return kadresse;
    }

    public void setNumRue(int numRue) {
        this.numRue = numRue;
    }

    public int getNumRue() {
        return numRue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getRue() {
        return rue;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCp() {
        return cp;
    }

    public void setKStructureAdr(int kStructureAdr) {
        this.kStructureAdr = kStructureAdr;
    }

    public int getKStructureAdr() {
        return kStructureAdr;
    }

    public void setLibellesadr(String libellesadr) {
        this.libellesadr = libellesadr;
    }

    public String getLibellesadr() {
        return libellesadr;
    }

    public void setKTStructureAdr(int kTStructureAdr) {
        this.kTStructureAdr = kTStructureAdr;
    }

    public int getKTStructureAdr() {
        return kTStructureAdr;
    }

    public void setLibelletsadr(String libelletsadr) {
        this.libelletsadr = libelletsadr;
    }

    public String getLibelletsadr() {
        return libelletsadr;
    }
}
