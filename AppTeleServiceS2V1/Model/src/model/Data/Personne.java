package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Personne {
    private int kper ;
    private String identifiant ;
    private String nom  ;
    private String prenom  ;
    private int part ;
    private String tel ;
    private String email  ;
    private String qualite ;


    public void setKper(int kper) {
        this.kper = kper;
    }

    public int getKper() {
        return kper;
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

    public void setPart(int part) {
        this.part = part;
    }

    public int getPart() {
        return part;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

    public String getQualite() {
        return qualite;
    }
}
