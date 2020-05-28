package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

import model.Data.ActiviteEntreprise;
import model.Data.FormeJuridique;

import model.Data.Pays;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/PaysWS")
public class PaysWS {
    public static final String paysAM = "model.AM.PaysAM";
    public static final String paysAM_CONFIG = "PaysAMLocal";
    
    @GET
    @Path("/LOVPays/")
    @Produces("application/json")
    @Consumes("application/json")
    public List<Pays> LOVPays () {
            String p_nb = null ;
            int p_kb = 0;
            Pays list = new Pays();
            List<Pays> ListPaysWS = new ArrayList<Pays>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.paysAM, this.paysAM_CONFIG);
            String req = " select  p.kpays , p.nationnalite \n" + 
            "from pays p \n" + 
            "order by p.nationnalite ";
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                resultSet = createPreparedStatement.executeQuery();
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int nbCols = rsmd.getColumnCount();
                System.out.println("nb col " + nbCols);
                while (resultSet.next()) {
                    ListPaysWS.add(map1(resultSet));
                }
                /*resultSet = createPreparedStatement.executeQuery();
                while (resultSet.next()) {
                    p_kb = resultSet.getInt(1);
                    p_nb = resultSet.getString(2); 
                    System.out.println( p_nb + p_kb );
                }
                list.setPays(p_nb);
                list.setKpays(p_kb);*/
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return ListPaysWS;
        }
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/createPays")
    public Pays createPays (Pays paysWS){
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.paysAM, this.paysAM_CONFIG);
        String req = " insert into Pays (kpays , pays , nationnalite) values (PaysSeq.NEXTVAL , ? , ?)" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, paysWS.getPays()); 
            createPreparedStatement.setString(2, paysWS.getNationnalite());
            createPreparedStatement.executeUpdate(); 
        }catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return paysWS;
    }
    
    @GET
    @Produces("application/json")
    @Path("/PaysAll/")
    public List<Pays> PaysAll () {
            Pays PaysWS = new Pays();
            List<Pays> ListPaysWS = new ArrayList<Pays>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.paysAM, this.paysAM_CONFIG);
            String req = "select kpays , pays , nationnalite from Pays" ;
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                resultSet = createPreparedStatement.executeQuery();
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int nbCols = rsmd.getColumnCount();
                System.out.println("nb col " + nbCols);
                while (resultSet.next()) {
                    ListPaysWS.add(map(resultSet));
                }
                /*resultSet = createPreparedStatement.executeQuery();
                while (resultSet.next()) {
                            PaysWS.setKpays(resultSet.getInt(1));
                            PaysWS.setPays(resultSet.getString(2));
                            PaysWS.setNationnalite(resultSet.getString(2));
                            ListPaysWS.add(PaysWS);
                }*/
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return ListPaysWS;
        }
    
    @PUT
    @Path("/updatePays/")
    @Produces("application/json")
    @Consumes("application/json")
    public Pays updatePays(Pays PaysWS ,
                            @QueryParam("kpays") int kpays){
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.paysAM, this.paysAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update Pays set pays = ? , nationnalite = ? where kpays = ? ",0);
        try {
            createPreparedStatement.setInt(1, kpays); 
            createPreparedStatement.setString(2, PaysWS.getPays()); 
            createPreparedStatement.setString(3, PaysWS.getNationnalite());            
            createPreparedStatement.executeUpdate();
            System.out.println(String.format("Row affected %d", createPreparedStatement.executeUpdate()));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return PaysWS;
    }
    
    private static Pays map(ResultSet resultSet) throws SQLException {
           Pays user = new Pays();
           user.setKpays(resultSet.getInt("kpays"));
           user.setPays(resultSet.getString("pays"));
           user.setNationnalite(resultSet.getString("nationnalite"));
           return user;
       }
    
    private static Pays map1(ResultSet resultSet) throws SQLException {
           Pays user = new Pays();
           user.setKpays(resultSet.getInt("kpays"));
           user.setNationnalite(resultSet.getString("nationnalite"));
           return user;
       }
}
