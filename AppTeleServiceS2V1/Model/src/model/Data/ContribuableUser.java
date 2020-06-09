package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContribuableUser {
    private int KContribuableUser ; 
    private int  kcnc  ;
    private int  kuser ;
    
    private String nif;


    public void setKContribuableUser(int KContribuableUser) {
        this.KContribuableUser = KContribuableUser;
    }

    public int getKContribuableUser() {
        return KContribuableUser;
    }

    public void setKcnc(int kcnc) {
        this.kcnc = kcnc;
    }

    public int getKcnc() {
        return kcnc;
    }

    public void setKuser(int kuser) {
        this.kuser = kuser;
    }

    public int getKuser() {
        return kuser;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }
}
