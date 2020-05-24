package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Adresse {
    private int numRue;
    private String rue;
    private String cp;

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
}
