package WS;

import java.text.SimpleDateFormat;
import java.util.Date;

import Services.ImpotService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import model.Data.ActiviteEntreprise;
import model.Data.Impot;
import model.Data.Pays;
import model.Data.Response;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/ImpotWS")
    @Produces("application/json")
    @Consumes("application/json")

public class ImpotWS{
    public static final String impotAM = "model.AM.ImpotAM";
    public static final String impotAM_CONFIG = "ImpotAMLocal";
    
    @GET
    @Path("/ListImpotContri/")
    @Produces("application/json")
    @Consumes("application/json")
    public List<Impot> ListImpotContri () {
            Impot list = new Impot();
            List<Impot> ListWS = new ArrayList<Impot>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.impotAM, this.impotAM_CONFIG);
            String req = " select  i.kimpot , i.abriviation , i.taux \n" + 
            "from Impot i \n" + 
            "order by i.abriviation ";
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
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return ListWS;
        }

    
    @GET
    @Path("/LOVImpot/")
    @Produces("application/json")
    @Consumes("application/json")
    public List<Impot> LOVImpot () {
            Impot list = new Impot();
            List<Impot> ListWS = new ArrayList<Impot>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.impotAM, this.impotAM_CONFIG);
            String req = " select  i.kimpot , i.abriviation , i.taux \n" + 
            "from Impot i \n" + 
            "order by i.abriviation ";
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
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return ListWS;
        }
    
    @POST
    @Path("/createImpot")
    public Impot createImpot (Impot impotWS){
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.impotAM, this.impotAM_CONFIG);
        String req = " insert into Impot (kimpot,limpot,abriviation,periodicite,taux,dateDebEffet) values (ImpotSeq.NEXTVAL,?,?,?,?,?)" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = new Date();
            //Date myDate = formatter.parse(dt);
            java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
            
            createPreparedStatement.setString(1, impotWS.getLimpot()); 
            createPreparedStatement.setString(2, impotWS.getAbriviation());
            createPreparedStatement.setString(3, impotWS.getPeriodicite());
            createPreparedStatement.setDouble(4, impotWS.getTaux());
            createPreparedStatement.setDate(5, sqlDate);
            createPreparedStatement.executeUpdate(); 
        }catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return impotWS;
    }
    
    @GET
    @Path("/ImpotAll")
    public List<Impot> ImpotAll (){
        Impot ImpotWS = new Impot();
        List<Impot> ListImpotWS = new ArrayList<Impot>();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.impotAM, this.impotAM_CONFIG);
        String req = "select kimpot,limpot,abriviation,periodicite,taux,dateDebEffet from Impot" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            resultSet = createPreparedStatement.executeQuery();
            while (resultSet.next()) {
                        ImpotWS.setKimpot(resultSet.getInt(1));
                        ImpotWS.setLimpot(resultSet.getString(2));
                        ImpotWS.setAbriviation(resultSet.getString(3));
                        ImpotWS.setPeriodicite(resultSet.getString(4));
                        ImpotWS.setTaux(resultSet.getDouble(5));
                        ImpotWS.setDateDebEffet(resultSet.getDate(6));
                        ListImpotWS.add(ImpotWS);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return ListImpotWS;
    }
    
    @PUT
    @Path("/updateImpot/")
    public Impot updateImpot(Impot impotWS ,
                            @QueryParam("kimpot") int kimpot){
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.impotAM, this.impotAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update Impot set limpot = ?,abriviation = ?,periodicite = ?,taux = ?,dateDebEffet = ? where kimpot = ? ",0);
        try {
            //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = new Date();
            //Date myDate = formatter.parse(dt);
            java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
            
            createPreparedStatement.setInt(1, kimpot); 
            createPreparedStatement.setString(1, impotWS.getLimpot()); 
            createPreparedStatement.setString(2, impotWS.getAbriviation());
            createPreparedStatement.setString(3, impotWS.getPeriodicite());
            createPreparedStatement.setDouble(4, impotWS.getTaux());
            createPreparedStatement.setDate(5, sqlDate);
            createPreparedStatement.executeUpdate();
            System.out.println(String.format("Row affected %d", createPreparedStatement.executeUpdate()));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return impotWS;
    }
    
    private static Impot map(ResultSet resultSet) throws SQLException {
           Impot user = new Impot();
           user.setKimpot(resultSet.getInt("kimpot"));
           user.setAbriviation(resultSet.getString("abriviation"));
           user.setTaux(resultSet.getDouble("taux"));
           return user;
       }
    
}

/*public class ImpotWS implements ImpotService {
    private static Map<Integer,Impot> impots = new HashMap<Integer,Impot>();
        
    @Override
    @POST
    @Path("/addImpot")
    public Response addImpot(Impot impot) {
                        Response response = new Response();
                        if(impots.get(impot.getKimpot()) != null){
                                response.setStatus(false);
                                response.setMessage("Impot Already Exists");
                                return response;
                        }
                        impots.put(impot.getKimpot(), impot);
                        response.setStatus(true);
                        response.setMessage("Impot created successfully");
                        return response;
    }

    @Override
    @DELETE
    @Path("/{id}/deleteImpot")
    public Response deleteImpot(@PathParam("kimpot") int kimpot) {
                        Response response = new Response();
                        if(impots.get(kimpot) == null){
                                response.setStatus(false);
                                response.setMessage("Impot Doesn't Exists");
                                return response;
                        }
                        impots.remove(kimpot);
                        response.setStatus(true);
                        response.setMessage("Impot deleted successfully");
                        return response;
    }

    @Override
    @GET
    @Path("/{kimpot}/getImpot")
    public Impot getImpot(@PathParam("kimpot") int kimpot) {
        return impots.get(kimpot);
    }

    @Override
    @GET
    @Path("/getAllImpots")
    public Impot[] getAllImpots() {
                        Set<Integer> ids = impots.keySet();
                        Impot[] impot = new Impot[ids.size()];
                        int i=0;
                        for(Integer kimpot : ids){
                                impot[i] = impots.get(kimpot);
                                i++;
                        }
                        return impot;
    }
}*/
