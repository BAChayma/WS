package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.Data.Adresse;
import model.Data.Banque;
import model.Data.CompteBancaire;
import model.Data.ConsulterDossierContribuable;

import model.Data.Contribuable;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/compteBancaieWS")
public class CompteBancaieWS {
    
    public static final String CompteBancaieAM = "model.AM.ComptebancaireAM";
    public static final String CompteBancaieAM_CONFIG = "ComptebancaireAMLocal";
    
    @PUT
    @Path("/updateCB/")
    @Produces("application/json")
    @Consumes("application/json")
    public CompteBancaire updateCB(CompteBancaire cbWS ){
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.CompteBancaieAM, this.CompteBancaieAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update comptebancaire cb set rib = ?, kagence = ?, kbanque = ? where kcompte = ? ",0);
        try { 
            createPreparedStatement.setString(1, cbWS.getRib());
            createPreparedStatement.setInt(2, cbWS.getKagence());
            createPreparedStatement.setInt(3, cbWS.getKbanque());
            createPreparedStatement.setInt(4, cbWS.getKcompte());
            System.out.println(cbWS.getRib() + cbWS.getKagence() + cbWS.getKbanque() );
           
            createPreparedStatement.executeUpdate();
            System.out.println(String.format("Row affected %d", createPreparedStatement.executeUpdate()));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return cbWS;
    }
    
    @GET
    @Path("/getCBbyID/")
    @Produces("application/json")
    @Consumes("application/json")
    public CompteBancaire getCBbyID(@QueryParam("kcompte") int kcompte){
        CompteBancaire cbWS = new CompteBancaire();
        int p_kcb = 0 , p_ka = 0 , p_kb = 0; 
        String p_rib = null;
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.CompteBancaieAM, this.CompteBancaieAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"select kcompte,rib,kagence,kbanque from comptebancaire where kcompte = ? ",0);
        ResultSet resultSet = null;
        try {
        createPreparedStatement.setInt(1, kcompte);  
        resultSet = createPreparedStatement.executeQuery();
        if (resultSet.next()) {
            p_kcb = resultSet.getInt(1);
            p_rib = resultSet.getString(2);
            p_ka = resultSet.getInt(3);
            p_kb = resultSet.getInt(4);
        } 
        cbWS.setKcompte(p_kcb);
        cbWS.setRib(p_rib);
        cbWS.setKagence(p_ka);
        cbWS.setKbanque(p_kb);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return cbWS;
    }
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/createCB")
    public CompteBancaire createCB (CompteBancaire cbWS){
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.CompteBancaieAM, this.CompteBancaieAM_CONFIG);
        String req = " insert into CompteBancaire (kcompte,rib,kagence,kbanque) values (CompteBancaireSeq.NEXTVAL ,?,?,?)" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, cbWS.getRib()); 
            createPreparedStatement.setInt(2, cbWS.getKagence());
            createPreparedStatement.setInt(3, cbWS.getKbanque());
            createPreparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return cbWS;
    }
    
    @DELETE
    @Path("/deleteCBbyID/")
    @Produces("application/json")
    @Consumes("application/json")
    public CompteBancaire deleteCBbyID(@QueryParam("kcompte") int kcompte){
        CompteBancaire cbWS = new CompteBancaire();
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.CompteBancaieAM, this.CompteBancaieAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"delete from comptebancaire where kcompte = ?;",0);
        ResultSet resultSet = null;
        try {
        createPreparedStatement.setInt(1, kcompte);  
        int result = createPreparedStatement.executeUpdate();
        System.out.println("Number of records affected :: " + result);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return cbWS;
    }
    
    @GET
    @Path("/CBInfoContribuableById/")
    @Produces("application/json")
    @Consumes("application/json")
    public List<CompteBancaire> CBInfoContribuableById (@QueryParam("nif") String nif) {
        List<CompteBancaire> ListCbWS = new ArrayList<CompteBancaire>();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.CompteBancaieAM, this.CompteBancaieAM_CONFIG);
        String req = " select cb.kcompte, cb.rib , b.nombanque , a.libelleagence \n" + 
        "from Contribuable c ,comptebancaire cb , banque b , agence a \n" + 
        "where c.kcnc = cb.kcnc and cb.kbanque = b.kbanque and cb.kagence = a.kagence and c.nif = ? " ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, nif);  
            resultSet = createPreparedStatement.executeQuery();
            while (resultSet.next()) {  
                ListCbWS.add(mapCB(resultSet));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return ListCbWS;
    }
    
    private static CompteBancaire mapCB(ResultSet resultSet) throws SQLException {
           CompteBancaire user = new CompteBancaire();
           user.setKcompte(resultSet.getInt("kcompte"));
           user.setRib(resultSet.getString("rib"));
           user.setNomBanque(resultSet.getString("nomBanque"));
           user.setLibelleAgence(resultSet.getString("libelleAgence"));
           return user;
       }
    
}
