package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Data.StructureAdr;

import model.Data.TypeStructureAdr;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/TypeStructureAdrWS")
public class TypeStructureAdrWS {
    public static final String TabAM = "model.AM.TypestructureadrAM";
    public static final String TabAM_CONFIG = "TypestructureadrAMLocal";
    
    @GET
    @Path("/LOVTypeStructureAdr/")
    @Produces("application/json")
    @Consumes("application/json")
    public TypeStructureAdr LOVTypeStructureAdr () {
            String p_nb = null ;
            int p_kb = 0;
            TypeStructureAdr list = new TypeStructureAdr();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.TabAM, this.TabAM_CONFIG);
            String req = " select sadr.kStructureAdr , sadr.libellesadr   \n" + 
            "from StructureAdr sadr \n" + 
            "order by sadr.libellesadr; ";
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                resultSet = createPreparedStatement.executeQuery();
                if (resultSet.next()) {
                    p_kb = resultSet.getInt(1);
                    p_nb = resultSet.getString(2); 
                    System.out.println( p_nb + p_kb );
                }
                list.setLibelletsadr(p_nb);
                list.setKTStructureAdr(p_kb);
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return list;
        }
    
}
