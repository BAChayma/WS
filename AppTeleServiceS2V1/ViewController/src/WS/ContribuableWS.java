package WS;

import com.tangosol.dev.assembler.Lvar;

import java.awt.event.ActionEvent;

import java.math.BigDecimal;

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

import java.util.Arrays;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Map;

import java.util.Set;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import model.AM.ContribuableAMImpl;

import model.Data.ActiviteEntreprise;
import model.Data.Adresse;
import model.Data.CompteBancaire;
import model.Data.ConsulterDossierContribuable;
import model.Data.InfoContribuable;

import model.VO.AdresseVOImpl;
import model.VO.ContribuableVOImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.OperationBinding;

import oracle.jbo.RowSet;


@Path("/contribuableWs")

public class ContribuableWS {
    
    public static final String contribuableAM = "model.AM.ContribuableAM";
    public static final String contribuableAM_CONFIG = "ContribuableAMLocal";
    
    public static final String tabAM = "model.AM.AdresseAM";
    public static final String tabAM_CONFIG = "AdresseAMLocal";
    
    public static final String CompteBancaieAM = "model.AM.ComptebancaireAM";
    public static final String CompteBancaieAM_CONFIG = "ComptebancaireAMLocal";
    
    ContribuableAMImpl appModule1 = (ContribuableAMImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
    
    public void setBindVarAndExceuteLov(Integer deptId){
       ContribuableVOImpl     contribuablevo =(ContribuableVOImpl) appModule1.getContribuableVO1();
           contribuablevo.setNamedWhereClauseParam("bvnif", deptId);
           contribuablevo.executeQuery();
       }
    
    public OperationBinding getBindings(String binding){
           return (OperationBinding) BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding(binding);
       }
       
    @GET
    @Produces("application/json")
    @Path("/RechercheContribuable/")
    public List<ConsulterDossierContribuable>  RechercheContribuable (@QueryParam("nif") String nif) {
        System.out.println("RechercheContribuable ");
        ConsulterDossierContribuable user = new ConsulterDossierContribuable();
        Adresse user1 = new Adresse();
        
        List<ConsulterDossierContribuable> ListContriWS = new ArrayList<ConsulterDossierContribuable>();
        List<Adresse> ListAdrWS = new ArrayList<Adresse>();
        List<CompteBancaire> ListCbWS = new ArrayList<CompteBancaire>();
        
        ContribuableVOImpl     contribuablevo =(ContribuableVOImpl) appModule1.getContribuableVO1();
        AdresseVOImpl     adrvo =(AdresseVOImpl) appModule1.getAdresseVO1();
         
        contribuablevo.setNamedWhereClauseParam("bvnif", nif);
        contribuablevo.executeQuery();
        System.out.println("contribuablevo "+contribuablevo.getQuery());
         
        Long rowcount = contribuablevo.getEstimatedRowCount();
       // while (rowcount > 0) {
            for (Row rContri : contribuablevo.getAllRowsInRange() ) {
                user.setNomCommerciale((String) rContri.getAttribute("Nomcommerciale"));
                user.setRaisonSociale((String) rContri.getAttribute("Raisonsociale"));
                user.setRegistreCommerce((String) rContri.getAttribute("Registrecommerce"));
                user.setDateDebExp((Date) rContri.getAttribute("Datedebexp"));
                //user.setCapitalSociale( rContri.getAttribute("Capitalsociale"));
                ListContriWS.add(user);
                
                for (Row rAdr : adrvo.getAllRowsInRange() ) {
                user1.setKadresse((Integer) rAdr.getAttribute("Kadresse"));
                user1.setNumRue((Integer) rAdr.getAttribute("Numrue"));
                user1.setRue((String) rAdr.getAttribute("Rue"));
                user1.setCp((String) rAdr.getAttribute("Cp"));
                ListAdrWS.add(user1);
                    
                    
             System.out.println("ListAdrWS "+ ListAdrWS); 
              
                    
                }
                
               
                
                user.setLadr(ListAdrWS);
                ListContriWS.add(user);
            } 
         //   }
               
    Configuration.releaseRootApplicationModule(appModule1, true);
 return ListContriWS;
    
    }

    
        @GET
        @Produces("application/json")
        @Path("/RechercheContribuable/")
        public List<ConsulterDossierContribuable> RechercheContribuable1 (@QueryParam("nif") String nif) {
            
            //ArrayList<ConsulterDossierContribuable> al1 = new ArrayList<>();
            ConsulterDossierContribuable user = new ConsulterDossierContribuable();
            List<ConsulterDossierContribuable> ListContriWS = new ArrayList<ConsulterDossierContribuable>();
            List<Adresse> ListAdrWS = new ArrayList<Adresse>();
            List<CompteBancaire> ListCbWS = new ArrayList<CompteBancaire>();
            
            
            //requete info gerenrale sur contribuable
            ApplicationModuleImpl appModule1 = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
            String req1 = " select c.nif, c.kcnc, c.nomCommerciale,c.raisonSociale,c.registreCommerce,c.dateDebExp,c.capitalSociale,fj.libellefj , ae.libelleae , p.nationnalite \n" + 
            "from Contribuable c , FormeJuridique fj , ActiviteEntreprise ae , Pays p  \n" + 
            "where c.kformjuri = fj.kformjuri and c.kcnc = ae.kcnc and c.kcnc = p.kcnc and c.nif = ? " ;
            PreparedStatement createPreparedStatement1 = appModule1.getDBTransaction().createPreparedStatement (""+req1,0);
            
            //requete info sur adressse contribuable
            ApplicationModuleImpl appModule2 = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.tabAM, this.tabAM_CONFIG);
            String req2 = " select adr.kadresse, adr.numrue , adr.rue , adr.cp , sadr.libellesadr , tsadr.libelletsadr \n" + 
            "from adresse adr , structureadr sadr , typestructureadr tsadr , Contribuable c \n" + 
            "where sadr.ktstructureadr = tsadr.ktstructureadr and adr.kstructureadr = sadr.kstructureadr and c.kcnc = adr.kcnc and c.nif = ? " ;
            PreparedStatement createPreparedStatement2 = appModule2.getDBTransaction().createPreparedStatement (""+req2,0);
            
            //requete info sur cb contribuable
            ApplicationModuleImpl appModule3 = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.CompteBancaieAM, this.CompteBancaieAM_CONFIG);
            String req3 = " select cb.kcompte, cb.rib , b.nombanque , a.libelleagence \n" + 
            "from Contribuable c ,comptebancaire cb , banque b , agence a \n" + 
            "where c.kcnc = cb.kcnc and cb.kbanque = b.kbanque and cb.kagence = a.kagence and c.nif = ? " ;
            PreparedStatement createPreparedStatement3 = appModule3.getDBTransaction().createPreparedStatement (""+req3,0);
            
            ResultSet resultSet = null;

            try {
                createPreparedStatement1.setString(1, nif);  
                resultSet = createPreparedStatement1.executeQuery();
                
                createPreparedStatement2.setString(1, nif);  
                resultSet = createPreparedStatement2.executeQuery();
                
                createPreparedStatement3.setString(1, nif);  
                resultSet = createPreparedStatement3.executeQuery();
                
                int taille1 = ListAdrWS.size();
                int taille2 = ListCbWS.size();
                
                while (resultSet.next()) {
                    ListContriWS.add(mapContri(resultSet));
                    for (int i=0;i<=taille1;i++){
                        ListAdrWS.add(mapAdr(resultSet));
                    }
                    for (int i=0;i<=taille2;i++){
                        ListCbWS.add(mapCB(resultSet));
                    }
                }
                
                user.setLadr(ListAdrWS);
                user.setLcb( ListCbWS);
                ListContriWS.add(user);
                
                
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            
        Configuration.releaseRootApplicationModule(appModule1, true);
        Configuration.releaseRootApplicationModule(appModule2, true);
        Configuration.releaseRootApplicationModule(appModule3, true);
        return ListContriWS;
        }
        
   
    
    /*@GET
    @Produces("application/json")
    @Path("/RechercheContribuable/")
    public List<ConsulterDossierContribuable> ContribuableById (@QueryParam("nif") String nif) {
            ArrayList<ConsulterDossierContribuable> al1 = new ArrayList<>();
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
            "      and per.kcnc = c.kcnc and per.qualite = 'responsable'  and c.nif = ? " ;
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                createPreparedStatement.setString(1, nif);  
                resultSet = createPreparedStatement.executeQuery();
                while (resultSet.next()) {
                    ConsulterDossierContribuable user = new ConsulterDossierContribuable();
                    user.setNomCommerciale(resultSet.getString("nomCommerciale"));
                    user.setRaisonSociale(resultSet.getString("raisonSociale"));
                    user.setRegistreCommerce(resultSet.getString("registreCommerce"));
                    user.setDateDebExp(resultSet.getDate("dateDebExp"));
                    user.setCapitalSociale(resultSet.getDouble("capitalSociale"));
                    user.setLibellefj(resultSet.getString("libelleFJ"));
                    user.setLibelleae(resultSet.getString("libelleAE"));
                    user.setNationnalite(resultSet.getString("nationnalite"));
                    al1.add(user);
                    ArrayList<ConsulterDossierContribuable> contriList = new ArrayList<>(Arrays.asList(user));
                    
                    Adresse user1 = new Adresse(); 
                    ArrayList<Adresse> user11 = new ArrayList<Adresse>();
                    List<List<Adresse>> al2 = new ArrayList<List<Adresse>>();
                    user1.setKadresse(resultSet.getInt("kadresse"));
                    user1.setKadresse(resultSet.getInt("kadresse"));
                    user1.setNumRue(resultSet.getInt("numRue"));
                    user1.setRue(resultSet.getString("rue"));
                    user1.setCp(resultSet.getString("cp"));
                    user1.setLibellesadr(resultSet.getString("libellesadr"));
                    user1.setLibelletsadr(resultSet.getString("libelletsadr"));
                    user11.add(user1);
                    
                    CompteBancaire user2 = new CompteBancaire();
                    ArrayList<CompteBancaire> user22 = new ArrayList<CompteBancaire>();
                    List<List<CompteBancaire>> al3 = new ArrayList<List<CompteBancaire>>();
                    user2.setKcompte(resultSet.getInt("kcompte"));
                    user2.setRib(resultSet.getString("rib"));
                    user2.setNomBanque(resultSet.getString("nomBanque"));
                    user2.setLibelleAgence(resultSet.getString("libelleAgence"));
                    user22.add(user2);
                   
                   
                    /*user.setLadr(adrList);
                    user.setLcb( cbList);
                    user.setLadr(al2);

                    user.setLadr((List) user11);
                    user.setLcb((List) user22);
                    al1.add(user);
                    
                    //ListWS.add(map(resultSet));
                    
                    /*ListWS.add(mapContri(resultSet));
                    ListADR.add(mapAdr(resultSet));
                    ListWS.addAll(ListADR); 
                    System.out.println(ListWS);
                
                }
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return al1;
        }*/
    
    @GET
    @Path("/InfoContriDclByNif/")
    @Produces("application/json")
    @Consumes("application/json")
    public InfoContribuable InfoContriDclByNif (@QueryParam("nif") String nif) {
        Date p_dbexp = new Date();
        String p_nif = null, p_rs = null, p_nc = null, p_rc = null, p_lfj = null, p_lae = null, p_nat = null, p_rue = null, p_cp = null, p_libsadr = null, libtsadr = null;
        String p_rib = null, p_nb = null, p_na = null, p_idPer = null, p_nomPer = null, p_prenomPer = null;
        double p_cs = 0;
        int p_kcnc = 0, p_kc = 0, p_kadr = 0, p_numrue = 0;
        InfoContribuable contriWS = new InfoContribuable();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
        String req = " select c.nif, c.kcnc, c.nomCommerciale,c.raisonSociale,c.registreCommerce,c.dateDebExp,c.capitalSociale,fj.libellefj , ae.libelleae , p.nationnalite \n" + 
        "from Contribuable c , FormeJuridique fj , ActiviteEntreprise ae , Pays p  \n" + 
        "where c.kformjuri = fj.kformjuri and c.kcnc = ae.kcnc and c.kcnc = p.kcnc and c.nif = ? " ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, nif);  
            resultSet = createPreparedStatement.executeQuery();
            if (resultSet.next()) {
                p_nif = resultSet.getString(1);
                p_kcnc = resultSet.getInt(2);
                p_nc = resultSet.getString(3);
                p_rs = resultSet.getString(4);
                p_rc = resultSet.getString(5);
                p_dbexp = resultSet.getDate(6);
                p_cs = resultSet.getDouble(7);
                p_lfj = resultSet.getString(8);
                p_lae = resultSet.getString(9);
                p_nat = resultSet.getString(10);    
            }
            contriWS.setKcnc(p_kcnc);
            contriWS.setNif(p_nif);
            contriWS.setNomCommerciale(p_nc);
            contriWS.setRaisonSociale(p_rs);
            contriWS.setRegistreCommerce(p_rc);
            contriWS.setDateDebExp(p_dbexp);
            contriWS.setCapitalSociale(p_cs);
            contriWS.setLibellefj(p_lfj);
            contriWS.setLibelleae(p_lae);
            contriWS.setNationnalite(p_nat);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return contriWS;
    }
    
    
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
    
     private static Adresse mapAdr(ResultSet resultSet) throws SQLException {
           Adresse user = new Adresse();
           user.setKadresse(resultSet.getInt("kadresse"));
           user.setNumRue(resultSet.getInt("numRue"));
           user.setRue(resultSet.getString("rue"));
           user.setCp(resultSet.getString("cp"));
           user.setLibellesadr(resultSet.getString("libellesadr"));
           user.setLibelletsadr(resultSet.getString("libelletsadr"));
           return user;
       }
    
    private static CompteBancaire mapCB(ResultSet resultSet) throws SQLException {
           CompteBancaire user = new CompteBancaire();
           user.setKcompte(resultSet.getInt("kcompte"));
           user.setRib(resultSet.getString("rib"));
           user.setNomBanque(resultSet.getString("nomBanque"));
           user.setLibelleAgence(resultSet.getString("libelleAgence"));
           return user;
       }
    
    private static ConsulterDossierContribuable mapContri(ResultSet resultSet) throws SQLException {
           ConsulterDossierContribuable user = new ConsulterDossierContribuable();
           user.setNomCommerciale(resultSet.getString("nomCommerciale"));
           user.setRaisonSociale(resultSet.getString("raisonSociale"));
           user.setRegistreCommerce(resultSet.getString("registreCommerce"));
           user.setDateDebExp(resultSet.getDate("dateDebExp"));
           user.setCapitalSociale(resultSet.getDouble("capitalSociale"));
           user.setLibellefj(resultSet.getString("libelleFJ"));
           user.setLibelleae(resultSet.getString("libelleAE"));
           user.setNationnalite(resultSet.getString("nationnalite"));
           return user;
    }
    
    
   
}
    