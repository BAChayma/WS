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

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/ActiviteEntrepriseWS")
public class ActiviteEntrepriseWS {
    public static final String ActiviteEntrepriseAM = "model.AM.ActiviteentrepriseAM";
    public static final String ActiviteEntrepriseAM_CONFIG = "ActiviteentrepriseAMLocal";
    
    @PUT
    @Path("/updateActEse/")
    @Produces("application/json")
    @Consumes("application/json")
    public ActiviteEntreprise updateActEse( ActiviteEntreprise ActEseWS ){
        // ActiviteEntreprise ActEseWS , @QueryParam("kActEnt") int kActEnt
        //ActiviteEntreprise ActEseWS = new ActiviteEntreprise();
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.ActiviteEntrepriseAM, this.ActiviteEntrepriseAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update ActiviteEntreprise set libelleAE = ? where kActEnt = ? ",0);
        try {
            //createPreparedStatement.setInt(1, ActEseWS.getKActEnt());
            createPreparedStatement.setString(1, ActEseWS.getLibelleAE());
            createPreparedStatement.setInt(2, ActEseWS.getKActEnt());
            //createPreparedStatement.setInt(2, kActEnt);
            
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
        //ActiviteEntreprise ActEseWS = new ActiviteEntreprise();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.ActiviteEntrepriseAM, this.ActiviteEntrepriseAM_CONFIG);
        String req = " insert into ActiviteEntreprise (kActEnt , libelleAE) values (ActiviteEntrepriseSeq.NEXTVAL , ?)" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, ActEseWS.getLibelleAE()); 
            createPreparedStatement.executeUpdate(); //executeQuery();
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
