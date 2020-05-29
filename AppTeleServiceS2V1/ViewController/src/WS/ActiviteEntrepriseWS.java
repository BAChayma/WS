package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import model.Data.ActiviteEntreprise;
import model.Data.CompteBancaire;

import model.Data.ConsulterDossierContribuable;

import model.Data.Contribuable;

import model.Data.FormeJuridique;

import model.Data.Pays;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/ActiviteEntrepriseWS")
public class ActiviteEntrepriseWS {
    public static final String ActiviteEntrepriseAM = "model.AM.ActiviteentrepriseAM";
    public static final String ActiviteEntrepriseAM_CONFIG = "ActiviteentrepriseAMLocal";
    
    @GET
    @Path("/LOVAE/")
    @Produces("application/json")
    @Consumes("application/json")
    public List<ActiviteEntreprise> LOVAE () {
            String p_nb = null ;
            int p_kb = 0;
            ActiviteEntreprise list = new ActiviteEntreprise();
            List<ActiviteEntreprise> ListWS = new ArrayList<ActiviteEntreprise>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.ActiviteEntrepriseAM, this.ActiviteEntrepriseAM_CONFIG);
            String req = " select  ae.kactent , ae.libelleae \n" + 
            "from activiteentreprise ae \n" + 
            "order by ae.libelleae ";
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                resultSet = createPreparedStatement.executeQuery();
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int nbCols = rsmd.getColumnCount();
                System.out.println("nb col " + nbCols);
                while (resultSet.next()) {
                    ListWS.add(map(resultSet));
                }
                /*resultSet = createPreparedStatement.executeQuery();
                if (resultSet.next()) {
                    p_kb = resultSet.getInt(1);
                    p_nb = resultSet.getString(2); 
                    System.out.println( p_nb + p_kb );
                }
                list.setLibelleAE(p_nb);
                list.setKActEnt(p_kb);*/
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return ListWS;
        }
    
    @DELETE
    @Path("/deleteAEbyID/")
    @Produces("application/json")
    @Consumes("application/json")
    public ActiviteEntreprise deleteAEbyID(@QueryParam("kactent") int kactent){
        ActiviteEntreprise aeWS = new ActiviteEntreprise();
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.ActiviteEntrepriseAM, this.ActiviteEntrepriseAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"delete from comptebancaire where kactent = ?;",0);
        ResultSet resultSet = null;
        try {
        createPreparedStatement.setInt(1, kactent);  
        int result = createPreparedStatement.executeUpdate();
        System.out.println("Number of records affected :: " + result);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return aeWS;
    }
    
    @PUT
    @Path("/updateActEse/")
    @Produces("application/json")
    @Consumes("application/json")
    public ActiviteEntreprise updateActEse( ActiviteEntreprise ActEseWS ){
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.ActiviteEntrepriseAM, this.ActiviteEntrepriseAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update ActiviteEntreprise set libelleAE = ? where kActEnt = ? ",0);
        try {
            createPreparedStatement.setString(1, ActEseWS.getLibelleAE());
            createPreparedStatement.setInt(2, ActEseWS.getKActEnt());
            
            createPreparedStatement.executeUpdate();
            System.out.println(String.format("Row affected %d", createPreparedStatement.executeUpdate()));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return ActEseWS;
    }
    
    @GET
    @Path("/getAEbyID/")
    @Produces("application/json")
    @Consumes("application/json")
    public ActiviteEntreprise getAEbyID(@QueryParam("kActEnt") int kActEnt){
        ActiviteEntreprise aeWS = new ActiviteEntreprise();
        int p_kae = 0 ; 
        String p_lae = null;
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.ActiviteEntrepriseAM, this.ActiviteEntrepriseAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"select kActEnt,libelleAE from ActiviteEntreprise where kActEnt = ? ",0);
        ResultSet resultSet = null;
        try {
        createPreparedStatement.setInt(1, kActEnt);  
        resultSet = createPreparedStatement.executeQuery();
        if (resultSet.next()) {
            p_kae = resultSet.getInt(1);
            p_lae = resultSet.getString(2);
        } 
        aeWS.setKActEnt(p_kae);
        aeWS.setLibelleAE(p_lae);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return aeWS;
    }
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/createActEse")
    public ActiviteEntreprise createActEse (ActiviteEntreprise ActEseWS){
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.ActiviteEntrepriseAM, this.ActiviteEntrepriseAM_CONFIG);
        String req = " insert into ActiviteEntreprise (kActEnt , libelleAE) values (ActiviteEntrepriseSeq.NEXTVAL , ?)" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, ActEseWS.getLibelleAE()); 
            createPreparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return ActEseWS;
    }
    
                    @GET
                    @Produces("application/json")
                    @Consumes("application/json")
                    @Path("/ActEseAll/")
                    public List<ActiviteEntreprise> ActEseAll () {                            
                            ActiviteEntreprise ActEseWS = new ActiviteEntreprise();
                            List<ActiviteEntreprise> ListActEseWS = new ArrayList<ActiviteEntreprise>();
                            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.ActiviteEntrepriseAM, this.ActiviteEntrepriseAM_CONFIG);
                            String req = "select kActEnt,libelleAE from ActiviteEntreprise" ;
                            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
                            ResultSet resultSet = null;
                            try {
                                resultSet = createPreparedStatement.executeQuery();
                                ResultSetMetaData rsmd = resultSet.getMetaData();
                                int nbCols = rsmd.getColumnCount();
                                System.out.println("nb col " + nbCols);
                                while (resultSet.next()) {  
                                    ListActEseWS.add(map(resultSet));
                                }
                            } catch (SQLException e) {
                                        System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                            appModule.getTransaction().commit();
                            Configuration.releaseRootApplicationModule(appModule, true);
                            return  ListActEseWS;
                        }
    
    private static ActiviteEntreprise map(ResultSet resultSet) throws SQLException {
           ActiviteEntreprise user = new ActiviteEntreprise();
           user.setKActEnt(resultSet.getInt("kActEnt"));
           user.setLibelleAE(resultSet.getString("libelleAE"));
           return user;
       }
    
}
