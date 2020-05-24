package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import model.Data.Agence;
import model.Data.Banque;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/agenceWS")
public class AgenceWS {
    
    public static final String AgenceAM = "model.AM.AgenceAM";
    public static final String AgenceAM_CONFIG = "AgenceAMLocal";
    
    @GET
    @Path("/LOVAgence/")
    @Produces("application/json")
    @Consumes("application/json")
    public Agence LOVAgence (@QueryParam("kbanque") int kbanque) {
            String p_na = null ;
            int p_ka = 0;
            Agence agence = new Agence();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.AgenceAM, this.AgenceAM_CONFIG);
            String req = " select a.kagence , a.libelleagence , b.kbanque \n" + 
            "from agence a , banque b where a.kbanque = b.kbanque and a.kbanque = ?  \n" + 
            "order by a.libelleagence ";
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                createPreparedStatement.setInt(1, kbanque); 
                resultSet = createPreparedStatement.executeQuery();
                if (resultSet.next()) {
                    p_ka = resultSet.getInt(1); 
                    p_na = resultSet.getString(2);
                    System.out.println( p_na +  p_ka );
                }
                agence.setLibelleAgence(p_na);
                agence.setKagence(p_ka);
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return agence;
        }
    
    
}
