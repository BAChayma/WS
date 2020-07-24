package model.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConsulterDossierContribuable {
    
    private String nif;
    
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

    private List<Adresse> Ladr;
    private List<CompteBancaire> Lcb;
    private List<Personne> Lact;
    
    /*private List<List<Adresse>> Ladr ;
    private List<List<CompteBancaire>> Lcb ;
    private List<List<Personne>> Lact ;*/


    public void setLadr(List<Adresse> Ladr) {
        this.Ladr = Ladr;
    }

    public List<Adresse> getLadr() {
        return Ladr;
    }

    public void setLcb(List<CompteBancaire> Lcb) {
        this.Lcb = Lcb;
    }

    public List<CompteBancaire> getLcb() {
        return Lcb;
    }

    public void setLact(List<Personne> Lact) {
        this.Lact = Lact;
    }

    public List<Personne> getLact() {
        return Lact;
    }


    //private List<ConsulterDossierContribuable> Lcontribuables;

    /*private int kcompte;
    private String rib;
    private String nombanque;
    private String libelleagence;
    private int kagence;
    private int kbanque;*/
    
    /*private int kadresse;
    private int numRue;
    private String rue;
    private String cp;
    
    private int kStructureAdr;
    private String libellesadr;
    
    private int kTStructureAdr ;
    private String libelletsadr ;*/


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

    


    /*private List<ConsulterDossierContribuable> CDcontribuables;

    public void setEmployees(List<ConsulterDossierContribuable> CDcontribuables) {
     this.CDcontribuables = CDcontribuables;
    }

    public List<ConsulterDossierContribuable> getCDcontribuable() {
     return CDcontribuables;
    }

    public void addContribuable(ConsulterDossierContribuable CDcontribuable) {
         if (CDcontribuables == null) {
             CDcontribuables = new ArrayList<ConsulterDossierContribuable>();
         }
         CDcontribuables.add(CDcontribuable);
     }*/

}
