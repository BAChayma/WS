package model.Data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Declaration {
    
    private int kdcl;
    private int kcnc ;   
    private int kimpot ;    
    private Double mantantDeclarer ;
    private Double mantantDeclaration ;  
    private Date datedcl ;


    public void setKdcl(int kdcl) {
        this.kdcl = kdcl;
    }

    public int getKdcl() {
        return kdcl;
    }

    public void setKcnc(int kcnc) {
        this.kcnc = kcnc;
    }

    public int getKcnc() {
        return kcnc;
    }

    public void setKimpot(int kimpot) {
        this.kimpot = kimpot;
    }

    public int getKimpot() {
        return kimpot;
    }

    public void setMantantDeclarer(Double mantantDeclarer) {
        this.mantantDeclarer = mantantDeclarer;
    }

    public Double getMantantDeclarer() {
        return mantantDeclarer;
    }

    public void setMantantDeclaration(Double mantantDeclaration) {
        this.mantantDeclaration = mantantDeclaration;
    }

    public Double getMantantDeclaration() {
        return mantantDeclaration;
    }

    public void setDatedcl(Date datedcl) {
        this.datedcl = datedcl;
    }

    public Date getDatedcl() {
        return datedcl;
    }
}
