package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TypeStructureAdr {
    private int kTStructureAdr ;
    private String libelletsadr ;

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
