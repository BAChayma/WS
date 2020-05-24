package WS;

import Services.FormeJuridiqueService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import model.Data.CompteBancaire;
import model.Data.FormeJuridique;

import model.Data.Impot;
import model.Data.Response;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/FormejuridiqueWS")
    /*@Produces("application/json")
    @Consumes("application/json")

public class FormeJuridiqueWS implements FormeJuridiqueService {
    
    private static Map<Integer,FormeJuridique> fjs = new HashMap<Integer,FormeJuridique>();
    
    @Override
    @GET
    @Path("/getAllFJ")
    public FormeJuridique[] getAllFJ() {
        Set<Integer> ids = fjs.keySet();
        FormeJuridique[] fj = new FormeJuridique[ids.size()];
        int i=0;
        for(Integer kFormJuri : ids){
                fj[i] = fjs.get(kFormJuri);
                i++;
        }
        return fj;
    }

    @Override
    @POST
    @Path("/addFJ")
    public Response addFJ(FormeJuridique FJ) {
        Response response = new Response();
        if(fjs.get(FJ.getKFormJuri()) != null){
                response.setStatus(false);
                response.setMessage("FJ Already Exists");
                return response;
        }
        fjs.put(FJ.getKFormJuri(), FJ);
        response.setStatus(true);
        response.setMessage("FJ created successfully");
        return response;
    }

    @Override
    @DELETE
    @Path("/{id}/deleteFJ")
    public Response deleteFJ(@PathParam("kFormJuri") int kFormJuri) {
        Response response = new Response();
        if(fjs.get(kFormJuri) == null){
                response.setStatus(false);
                response.setMessage("FJ Doesn't Exists");
                return response;
        }
        fjs.remove(kFormJuri);
        response.setStatus(true);
        response.setMessage("FJ deleted successfully");
        return response;
    }

    @Override
    @GET
    @Path("/{kFormJuri}/getFJ")
    public FormeJuridique getFJ(@PathParam("kFormJuri") int kFormJuri) {
        return fjs.get(kFormJuri);
    }
    
    
}*/

public class FormeJuridiqueWS {
    
    public static final String FJAM = "model.AM.FormejuridiqueAM";
    public static final String FJAM_CONFIG = "FormejuridiqueAMLocal";
    
    @GET
    @Path("/getFJbyID/")
    @Produces("application/json")
    @Consumes("application/json")
    public FormeJuridique getFJbyID(@QueryParam("kFormJuri") int kFormJuri){
        FormeJuridique fjWS = new FormeJuridique();
        int p_kfj = 0 ; 
        String p_lfj = null;
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.FJAM, this.FJAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"select kFormJuri,libelleFJ from FormeJuridique where kFormJuri = ? ",0);
        ResultSet resultSet = null;
        try {
        createPreparedStatement.setInt(1, kFormJuri);  
        resultSet = createPreparedStatement.executeQuery();
        if (resultSet.next()) {
            p_kfj = resultSet.getInt(1);
            p_lfj = resultSet.getString(2);
        } 
        fjWS.setKFormJuri(p_kfj);
        fjWS.setLibelleFJ(p_lfj);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return fjWS;
    }
    
    @GET
    @Produces("application/json")
    @Path("/FJAll/")
    public List<FormeJuridique> FJAll () {
            FormeJuridique FJWS = new FormeJuridique();
            List<FormeJuridique> ListFJWS = new ArrayList<FormeJuridique>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.FJAM, this.FJAM_CONFIG);
            String req = "select kFormJuri,libelleFJ from FormeJuridique" ;
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                resultSet = createPreparedStatement.executeQuery();
                while (resultSet.next()) {
                    ListFJWS.add(map(resultSet));
                            //FJWS.setKFormJuri(resultSet.getInt(1));
                            //FJWS.setLibelleFJ(resultSet.getString(2));
                            //ListFJWS.add(FJWS);
                }
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return ListFJWS;
        }
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/createFJ")
    public FormeJuridique createFJ (FormeJuridique FJWS){
        //ActiviteEntreprise ActEseWS = new ActiviteEntreprise();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.FJAM, this.FJAM_CONFIG);
        String req = " insert into FormeJuridique (kFormJuri , libelleFJ) values (FormeJuridiqueSeq.NEXTVAL , ?)" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, FJWS.getLibelleFJ()); 
            createPreparedStatement.executeUpdate(); //executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return FJWS;
    }
    
    @PUT
    @Path("/updateFJ/")
    @Produces("application/json")
    @Consumes("application/json")
    public FormeJuridique updateFJ(FormeJuridique FJWS ,
                                           @QueryParam("kFormJuri") int kFormJuri){
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.FJAM, this.FJAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update FormeJuridique set libelleFJ = ? where kFormJuri = ? ",0);
        try {
            createPreparedStatement.setInt(1, kFormJuri); 
            createPreparedStatement.setString(2, FJWS.getLibelleFJ());            
            createPreparedStatement.executeUpdate();
            System.out.println(String.format("Row affected %d", createPreparedStatement.executeUpdate()));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return FJWS;
    }
    
    private static FormeJuridique map(ResultSet resultSet) throws SQLException {
           FormeJuridique user = new FormeJuridique();
           user.setKFormJuri(resultSet.getInt("kFormJuri"));
           user.setLibelleFJ(resultSet.getString("libelleFJ"));
           return user;
       }
    
}