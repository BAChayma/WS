package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import model.Data.ActiviteEntreprise;

import model.Data.Declaration;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/DeclarationWS")
public class DeclarationWS {
    public static final String dclAM = "model.AM.DeclarationAM";
    public static final String dclAM_CONFIG = "DeclarationAMLocal";
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/createDcl")
    public Declaration createDcl (Declaration DclWS){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.dclAM, this.dclAM_CONFIG);
        String req = " insert into Declaration (kdcl,kcnc,kimpot,mantantDeclarer,mantantDeclaration,datedcl) values (DeclarationSeq.NEXTVAL , ?)" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setInt(1, DclWS.getKcnc()); 
            createPreparedStatement.setInt(2, DclWS.getKimpot());
            createPreparedStatement.setDouble(3, DclWS.getMantantDeclarer());
            createPreparedStatement.setDouble(4, DclWS.getMantantDeclaration());
            //createPreparedStatement.setDate(1, DclWS.getDatedcl());
            createPreparedStatement.setTimestamp(5, new java.sql.Timestamp(dt.getTime()) );
            createPreparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return DclWS;
    }
    
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/DclAll/")
    public List<Declaration> DclAll (@QueryParam("nif") String nif) {                            
            Declaration dclWS = new Declaration();
            List<Declaration> ListWS = new ArrayList<Declaration>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.dclAM, this.dclAM_CONFIG);
            String req = "select dcl.kdcl , dcl.kcnc , dcl.kimpot , dcl.mantantdeclarer , dcl.mantantdeclaration , dcl.datedcl \n" + 
            "from declaration dcl , contribuable c \n" + 
            "where c.kcnc = dcl.kcnc and  c.nif = ?" ;
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                createPreparedStatement.setString(1, nif);
                resultSet = createPreparedStatement.executeQuery();
                //ResultSetMetaData rsmd = resultSet.getMetaData();
                //int nbCols = rsmd.getColumnCount();
                //System.out.println("nb col " + nbCols);
                while (resultSet.next()) {  
                    ListWS.add(map(resultSet));
                }
            /*} catch (SQLException e) {
                        System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            appModule.getTransaction().commit();
            Configuration.releaseRootApplicationModule(appModule, true);
            return  ListWS;
        }
    
    private static Declaration map(ResultSet resultSet) throws SQLException {
    Declaration user = new Declaration();
    user.setKdcl(resultSet.getInt("kdcl"));
    user.setKcnc(resultSet.getInt("kcnc"));
    user.setKimpot(resultSet.getInt("kimpot"));
    user.setMantantDeclarer(resultSet.getDouble("mantantDeclarer"));
    user.setMantantDeclaration(resultSet.getDouble("mantantDeclaration"));
    user.setDatedcl(resultSet.getDate("datedcl"));
    return user;
    }
}
