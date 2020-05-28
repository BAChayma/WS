package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StructureAdr {
    private int kStructureAdr;
    private String libellesadr;

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
}
