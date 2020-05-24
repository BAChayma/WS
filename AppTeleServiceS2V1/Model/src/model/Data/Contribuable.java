package model.Data;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Contribuable {  

       private String nomCommerciale;
       private String raisonSociale;
       private String registreCommerce;
       private double capitalSociale;
       private String nif;


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

    /*public void setDateDebExp(Date dateDebExp) {
        this.dateDebExp = dateDebExp;
    }

    public Date getDateDebExp() {
        return dateDebExp;
    }*/

    public void setCapitalSociale(double capitalSociale) {
        this.capitalSociale = capitalSociale;
    }

    public double getCapitalSociale() {
        return capitalSociale;
    }
}
