package model.Data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContribuableUser {
    private int KContribuableUser ; 
    private int  kcnc  ;
    private int  kuser ;
    
    private String nif;
    private String nomCommerciale;
    private String raisonSociale;
    private String registreCommerce;
    
    private String libellefj;
    private String libelleae;


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


    public void setNomCommerciale(String nomCommerciale) {
        this.nomCommerciale = nomCommerciale;
    }

    public String getNomCommerciale() {
        return nomCommerciale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public void setLibellefj(String libellefj) {
        this.libellefj = libellefj;
    }

    public String getLibellefj() {
        return libellefj;
    }

    public void setLibelleae(String libelleae) {
        this.libelleae = libelleae;
    }

    public String getLibelleae() {
        return libelleae;
    }
}
