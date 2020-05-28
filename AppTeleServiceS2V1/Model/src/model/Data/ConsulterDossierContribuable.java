package model.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    private int kcompte;
    private String rib;
    private String nombanque;
    private String libelleagence;
    
    private String identifiant; 
    private String nom;
    private String prenom;
    
    private int kadresse;
    private int numRue;
    private String rue;
    private String cp;
    
    private int kStructureAdr;
    private String libellesadr;
    
    private int kTStructureAdr ;
    private String libelletsadr ;


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

    public void setKcompte(int kcompte) {
        this.kcompte = kcompte;
    }

    public int getKcompte() {
        return kcompte;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getRib() {
        return rib;
    }

    public void setNombanque(String nombanque) {
        this.nombanque = nombanque;
    }

    public String getNombanque() {
        return nombanque;
    }

    public void setLibelleagence(String libelleagence) {
        this.libelleagence = libelleagence;
    }

    public String getLibelleagence() {
        return libelleagence;
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
