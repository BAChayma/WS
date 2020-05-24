package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormeJuridique {
    private int kFormJuri;
    private String libelleFJ;


    public void setKFormJuri(int kFormJuri) {
        this.kFormJuri = kFormJuri;
    }

    public int getKFormJuri() {
        return kFormJuri;
    }

    public void setLibelleFJ(String libelleFJ) {
        this.libelleFJ = libelleFJ;
    }

    public String getLibelleFJ() {
        return libelleFJ;
    }

}
