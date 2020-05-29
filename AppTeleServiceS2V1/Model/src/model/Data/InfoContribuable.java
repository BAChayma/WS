package model.Data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InfoContribuable {
    private String nif;
    private int kcnc;
    private String nomCommerciale;
    private String raisonSociale;
    private String registreCommerce;
    private Date dateDebExp;
    private double capitalSociale;
    
    private String libellefj;
    private String libelleae;
    private String nationnalite;
    
    private String identifiant; 
    private String nom;
    private String prenom;


    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }
    
    public void setKcnc(int kcnc) {
        this.kcnc = kcnc;
    }

    public int getKcnc() {
        return kcnc;
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

    public void setDateDebExp(Date dateDebExp) {
        this.dateDebExp = dateDebExp;
    }

    public Date getDateDebExp() {
        return dateDebExp;
    }

    public void setCapitalSociale(double capitalSociale) {
        this.capitalSociale = capitalSociale;
    }

    public double getCapitalSociale() {
        return capitalSociale;
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

    public void setNationnalite(String nationnalite) {
        this.nationnalite = nationnalite;
    }

    public String getNationnalite() {
        return nationnalite;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }
}
