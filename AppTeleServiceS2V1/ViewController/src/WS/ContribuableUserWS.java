package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Data.ActiviteEntreprise;

import model.Data.ContribuableUser;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/ContribuableUserWS")
public class ContribuableUserWS {
    public static final String contribuableUserAM = "model.AM.ContribuableuserAM";
    public static final String contribuableUserAM_CONFIG = "ContribuableuserAMLocal";
    
    @GET
    @Path("/ListContribuablePourUser/")
    @Produces("application/json")
    @Consumes("application/json")
    public List<ContribuableUser> ListContribuablePourUser () {
            ContribuableUser list = new ContribuableUser();
            List<ContribuableUser> ListWS = new ArrayList<ContribuableUser>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.contribuableUserAM, this.contribuableUserAM_CONFIG);
            String req = " select cu.KContribuableUser , cu.kuser , cu.kcnc , c.nif , c.nomCommerciale,c.raisonSociale,c.registreCommerce , fj.libellefj , ae.libelleae \n" + 
            "from ContribuableUser cu , Contribuable c , FormeJuridique fj , ActiviteEntreprise ae\n" + 
            "where cu.kcnc = c.kcnc and c.kformjuri = fj.kformjuri and c.kcnc = ae.kcnc ";
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                resultSet = createPreparedStatement.executeQuery();
                while (resultSet.next()) {
                    ListWS.add(map(resultSet));
                }
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return ListWS;
        }
    
    private static ContribuableUser map(ResultSet resultSet) throws SQLException {
           ContribuableUser user = new ContribuableUser();
           user.setKContribuableUser(resultSet.getInt("KContribuableUser"));
           user.setKcnc(resultSet.getInt("kcnc"));
           user.setKuser(resultSet.getInt("kuser"));
           user.setNif(resultSet.getString("nif"));
           user.setNomCommerciale(resultSet.getString("nomCommerciale"));
           user.setRaisonSociale(resultSet.getString("raisonSociale"));
           user.setRegistreCommerce(resultSet.getString("registreCommerce"));
           user.setLibellefj(resultSet.getString("libelleFJ"));
           user.setLibelleae(resultSet.getString("libelleAE"));
           return user;
       }
    
}
