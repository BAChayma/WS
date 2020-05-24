package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import model.Data.FormeJuridique;

import model.Data.ModePaiement;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/ModePaiementWS")
@Produces("application/json")
@Consumes("application/json")
public class ModePaiementWS {
    public static final String MPAM = "model.AM.ModepaiementAM";
    public static final String MPAM_CONFIG = "ModepaiementLocal";
    
    @POST
    @Path("/createMP")
    public ModePaiement createMP (ModePaiement MPWS){
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.MPAM, this.MPAM_CONFIG);
        String req = " insert into ModePaiement (kModePaiement , libelle) values (ModePaiementSeq.NEXTVAL , ?)" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, MPWS.getLibelle()); 
            createPreparedStatement.executeUpdate(); 
        }catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return MPWS;
    }
    
    @GET
    @Path("/MPAll/")
    public List<ModePaiement> MPAll () {
            ModePaiement MPWS = new ModePaiement();
            List<ModePaiement> ListMPWS = new ArrayList<ModePaiement>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.MPAM, this.MPAM_CONFIG); 
            String req = "select kModePaiement , libelle from ModePaiement" ;
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                resultSet = createPreparedStatement.executeQuery();
                while (resultSet.next()) {
                            MPWS.setKModePaiement(resultSet.getInt(1));
                            MPWS.setLibelle(resultSet.getString(2));
                            ListMPWS.add(MPWS);
                }
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return ListMPWS;
        }
    
    @PUT
    @Path("/updateMP/")
    public ModePaiement updateMP(ModePaiement MPWS ,
                                @QueryParam("kModePaiement") int kModePaiement){
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.MPAM, this.MPAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update ModePaiement set libelle = ? where kModePaiement = ? ",0);
        try {
            createPreparedStatement.setInt(1, kModePaiement); 
            createPreparedStatement.setString(2, MPWS.getLibelle());            
            createPreparedStatement.executeUpdate();
            System.out.println(String.format("Row affected %d", createPreparedStatement.executeUpdate()));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return MPWS;
    }
    
}
