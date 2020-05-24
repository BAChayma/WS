package model.Data;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Resultat {
    public Resultat() {
        super();
    }
    
    private String Etat;
    private String  msg;


    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getEtat() {
        return Etat;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
