package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Data.Banque;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/banqueWS")
public class BanqueWS {
    
    public static final String BanqueAM = "model.AM.BanqueAM";
    public static final String BanqueAM_CONFIG = "BanqueAMLocal";
    
    @GET
    @Path("/LOVBanque/")
    @Produces("application/json")
    @Consumes("application/json")
    public Banque LOVBanque () {
            String p_nb = null ;
            int p_kb = 0;
            Banque banque = new Banque();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.BanqueAM, this.BanqueAM_CONFIG);
            String req = " select b.kbanque , b.nombanque  \n" + 
            "from banque b  \n" + 
            "order by b.nombanque ";
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                resultSet = createPreparedStatement.executeQuery();
                if (resultSet.next()) {
                    p_kb = resultSet.getInt(1);
                    p_nb = resultSet.getString(2); 
                    System.out.println( p_nb + p_kb );
                }
                banque.setNomBanque(p_nb);
                banque.setKbanque(p_kb);
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return banque;
        }
    
    
}
