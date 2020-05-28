package WS;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Data.Contribuable;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.client.Configuration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.DBTransaction;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.sql.Timestamp;

import java.text.ParseException;

import java.util.Map;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import model.Data.ActiviteEntreprise;
import model.Data.Adresse;
import model.Data.CompteBancaire;
import model.Data.ConsulterDossierContribuable;
import model.Data.InfoContribuable;


@Path("/contribuableWs")

public class ContribuableWS {
    
    public static final String contribuableAM = "model.AM.ContribuableAM";
    public static final String contribuableAM_CONFIG = "ContribuableAMLocal";
    
    
    @GET
    @Path("/InfoContribuableById/")
    @Produces("application/json")
    @Consumes("application/json")
    public InfoContribuable InfoContribuableById (@QueryParam("nif") String nif) {
        Date p_dbexp = new Date();
        String p_rs = null, p_nc = null, p_rc = null, p_lfj = null, p_lae = null, p_nat = null, p_rue = null, p_cp = null, p_libsadr = null, libtsadr = null;
        String p_rib = null, p_nb = null, p_na = null, p_idPer = null, p_nomPer = null, p_prenomPer = null;
        double p_cs = 0;
        int p_kc = 0, p_kadr = 0, p_numrue = 0;
        InfoContribuable contriWS = new InfoContribuable();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
        String req = " select c.nomCommerciale,c.raisonSociale,c.registreCommerce,c.dateDebExp,c.capitalSociale,fj.libellefj , ae.libelleae , p.nationnalite , per.identifiant , per.nom , per.prenom \n" + 
        "from Contribuable c , FormeJuridique fj , ActiviteEntreprise ae , Pays p  ,Personne per\n" + 
        "where c.kformjuri = fj.kformjuri and c.kcnc = ae.kcnc and c.kcnc = p.kcnc and per.kcnc = c.kcnc and per.qualite = 'responsable' and c.nif = ? " ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, nif);  
            resultSet = createPreparedStatement.executeQuery();
            if (resultSet.next()) {
                p_nc = resultSet.getString(1);
                p_rs = resultSet.getString(2);
                p_rc = resultSet.getString(3);
                p_dbexp = resultSet.getDate(4);
                p_cs = resultSet.getDouble(5);
                p_lfj = resultSet.getString(6);
                p_lae = resultSet.getString(7);
                p_nat = resultSet.getString(8);
                p_idPer = resultSet.getString(9);
                p_nomPer = resultSet.getString(10);
                p_prenomPer = resultSet.getString(11);  
            }
            contriWS.setNomCommerciale(p_nc);
            contriWS.setRaisonSociale(p_rs);
            contriWS.setRegistreCommerce(p_rc);
            contriWS.setDateDebExp(p_dbexp);
            contriWS.setCapitalSociale(p_cs);
            contriWS.setLibellefj(p_lfj);
            contriWS.setLibelleae(p_lae);
            contriWS.setNationnalite(p_nat);
            contriWS.setIdentifiant(p_idPer);
            contriWS.setNom(p_nomPer);
            contriWS.setPrenom(p_prenomPer);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return contriWS;
    }
    
    
    
    @GET
    @Produces("application/json")
    @Path("/ContribuableById/")
    public ConsulterDossierContribuable ContribuableById (@QueryParam("nif") String nif) {
            Date p_dbexp = new Date();  
            ConsulterDossierContribuable contriWS = new ConsulterDossierContribuable();
            Adresse adrWS = new Adresse();
            String p_rs = null, p_nc = null, p_rc = null, p_lfj = null, p_lae = null, p_nat = null, p_rue = null, p_cp = null, p_libsadr = null, libtsadr = null;
            String p_rib = null, p_nb = null, p_na = null, p_idPer = null, p_nomPer = null, p_prenomPer = null;
            double p_cs = 0;
            int p_kc = 0, p_kadr = 0, p_numrue = 0;
            //, p_ksadr = 0, p_ktsadr = 0
            SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
            String req = " select c.nomCommerciale,c.raisonSociale,c.registreCommerce,c.dateDebExp,c.capitalSociale,\n" + 
            "       fj.libellefj , ae.libelleae , p.nationnalite , cb.kcompte, cb.rib , b.nombanque , a.libelleagence , \n" + 
            "       per.identifiant , per.nom , per.prenom ,\n" + 
            "       adr.kadresse, adr.numrue , adr.rue , adr.cp , sadr.libellesadr , tsadr.libelletsadr\n" + 
            "from Contribuable c , FormeJuridique fj , ActiviteEntreprise ae , Pays p , comptebancaire cb , banque b , agence a ,Personne per,\n" + 
            "     adresse adr , structureadr sadr , typestructureadr tsadr\n" + 
            "where c.kformjuri = fj.kformjuri and c.kcnc = ae.kcnc and c.kcnc = p.kcnc\n" + 
            "      and c.kcnc = cb.kcnc and cb.kbanque = b.kbanque and cb.kagence = a.kagence\n" + 
            "      and sadr.ktstructureadr = tsadr.ktstructureadr and adr.kstructureadr = sadr.kstructureadr and c.kcnc = adr.kcnc\n" + 
            "      and per.kcnc = c.kcnc and per.qualite = 'responsable' and c.nif = ? " ;
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                createPreparedStatement.setString(1, nif);  
                resultSet = createPreparedStatement.executeQuery();
                if (resultSet.next()) {
                    p_nc = resultSet.getString(1);
                    p_rs = resultSet.getString(2);
                    p_rc = resultSet.getString(3);
                    p_dbexp = resultSet.getDate(4);
                    p_cs = resultSet.getDouble(5);
                    p_lfj = resultSet.getString(6);
                    p_lae = resultSet.getString(7);
                    p_nat = resultSet.getString(8);
                    p_kc = resultSet.getInt(9);
                    p_rib = resultSet.getString(10);
                    p_nb = resultSet.getString(11);
                    p_na = resultSet.getString(12);
                    p_idPer = resultSet.getString(13);
                    p_nomPer = resultSet.getString(14);
                    p_prenomPer = resultSet.getString(15);  
                    p_kadr = resultSet.getInt(16);
                    p_numrue = resultSet.getInt(17);
                    p_rue = resultSet.getString(18);
                    p_cp = resultSet.getString(19);
                    //p_ksadr = resultSet.getInt(20);
                    p_libsadr = resultSet.getString(20);
                    //p_ktsadr = resultSet.getInt(22);
                    libtsadr = resultSet.getString(21);
                    
                    System.out.println( p_nc +  p_rs  + p_rc + p_dbexp  + p_cs + p_lfj + p_lae + p_nat + p_kc + p_rib + p_nb + p_na + p_idPer + p_nomPer + p_prenomPer + p_kadr + p_numrue + p_rue + p_cp + p_libsadr +  libtsadr);
                }
                contriWS.setNomCommerciale(p_nc);
                contriWS.setRaisonSociale(p_rs);
                contriWS.setRegistreCommerce(p_rc);
                contriWS.setDateDebExp(p_dbexp);
                contriWS.setCapitalSociale(p_cs);
                contriWS.setLibellefj(p_lfj);
                contriWS.setLibelleae(p_lae);
                contriWS.setNationnalite(p_nat);
                contriWS.setKcompte(p_kc);
                contriWS.setRib(p_rib);
                contriWS.setNombanque(p_nb);
                contriWS.setLibelleagence(p_na);
                contriWS.setIdentifiant(p_idPer);
                contriWS.setNom(p_nomPer);
                contriWS.setPrenom(p_prenomPer);
                
                contriWS.setKadresse(p_kadr);
                contriWS.setNumRue(p_numrue);
                contriWS.setRue(p_rue);
                contriWS.setCp(p_cp);
                //contriWS.setKStructureAdr(p_ksadr);
                contriWS.setLibellesadr(p_libsadr);
                //contriWS.setKTStructureAdr(p_ktsadr);
                contriWS.setLibelletsadr(libtsadr);
                //contriWS.setLadr();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return contriWS;
        }
   
    @PUT
    @Path("/updateContribuable/")
    @Produces("application/json")
    @Consumes("application/json")
    public Contribuable updateContribuable(Contribuable contriWS){
        //Contribuable contriWS = new Contribuable();
        PreparedStatement createPreparedStatement = null;
        String req = " update contribuable c set nomCommerciale = '?', raisonSociale = '?', dateDebExp = ?, capitalSociale = '?', registreCommerce = '?'  where c.nif = ?  " ;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update contribuable c set nomCommerciale = ?, raisonSociale = ?, registreCommerce = ?, capitalSociale = ?  where c.nif = ?  ",0);
        try {
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            
            createPreparedStatement.setString(1, contriWS.getNomCommerciale());
            createPreparedStatement.setString(2, contriWS.getRaisonSociale());
            createPreparedStatement.setString(3, contriWS.getRegistreCommerce());
            createPreparedStatement.setDouble(4, contriWS.getCapitalSociale());
            createPreparedStatement.setString(5, contriWS.getNif());
           
            //createPreparedStatement.setTimestamp(4, new java.sql.Timestamp(dt.getTime()) );
            //createPreparedStatement.setTimestamp(4,  new java.sql.Timestamp(dt.getTime())); 
            //createPreparedStatement.setString(4, currentTime) ; 
            
            createPreparedStatement.executeUpdate();
            System.out.println(String.format("Row affected %d", createPreparedStatement.executeUpdate()));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return contriWS;
    }
    
    /*private static ConsulterDossierContribuable map(ResultSet resultSet) throws SQLException {
           ConsulterDossierContribuable user = new ConsulterDossierContribuable();
           user.setNomCommerciale(resultSet.getString("nomCommerciale"));
           user.setRaisonSociale(resultSet.getString("raisonSociale"));
           user.setRegistreCommerce(resultSet.getString("registreCommerce"));
           user.setDateDebExp(resultSet.getDate("dateDebExp"));
           user.setCapitalSociale(resultSet.getDouble("capitalSociale"));
           user.setLibellefj(resultSet.getString("libelleFJ"));
           user.setLibelleae(resultSet.getString("libelleAE"));
           user.setNationnalite(resultSet.getString("nationnalite"));
           user.setKcompte(resultSet.getInt("kcompte"));
           user.setRib(resultSet.getString("rib"));
           user.setNombanque(resultSet.getString("nomBanque"));
           user.setLibelleagence(resultSet.getString("libelleAgence"));
           user.setIdentifiant(resultSet.getString("identifiant"));
           user.setNom(resultSet.getString("nom"));
           user.setPrenom(resultSet.getString("prenom"));
           user.setKadresse(resultSet.getInt("kadresse"));
           user.setNumRue(resultSet.getInt("numRue"));
           user.setRue(resultSet.getString("rue"));
           user.setCp(resultSet.getString("cp"));
           //user.setKStructureAdr(resultSet.getInt("kStructureAdr"));
           user.setLibellesadr(resultSet.getString("libellesadr"));
           //user.setKTStructureAdr(resultSet.getInt("kTStructureAdr"));
           user.setLibelletsadr(resultSet.getString("libelletsadr"));
           return user;
       }*/
   
    
    
   
}
    