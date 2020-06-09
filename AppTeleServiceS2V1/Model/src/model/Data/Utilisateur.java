package model.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Utilisateur {
    private int kuser ;
    private String username ;
    private String mdp ;
    private String email ;
    private int kprofile ;


    public void setKuser(int kuser) {
        this.kuser = kuser;
    }

    public int getKuser() {
        return kuser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMdp() {
        return mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setKprofile(int kprofile) {
        this.kprofile = kprofile;
    }

    public int getKprofile() {
        return kprofile;
    }
}
