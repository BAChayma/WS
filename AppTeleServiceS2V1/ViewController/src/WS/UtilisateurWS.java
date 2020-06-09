package WS;

import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;

import java.security.Principal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import model.Data.ConsulterDossierContribuable;
import model.Data.Utilisateur;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/UtilisateurWS")

public class UtilisateurWS {
    
    public static final String cnxAM = "model.AM.UtilisateurAM";
    public static final String cnxAM_CONFIG = "UtilisateurAMLocal";
    
    /*@GET
    @Produces("text/plain")
    public String hello(@Context SecurityContext sc) {
           String user = "";
           if (sc != null) {
               Principal p = sc.getUserPrincipal();
               if (p != null) {
                   user = p.getName();
               }
           }
           return "Hello " + user + "!";
       }*/
    
    
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/connexion/")
    public Utilisateur connexion (@QueryParam("username") String username , @QueryParam("mdp") String mdp) {
        String p_username = null , p_mdp = null ;
        Utilisateur userWS = new Utilisateur();
        List<Utilisateur> ListWS = new ArrayList<Utilisateur>();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.cnxAM, this.cnxAM_CONFIG);
        String req = " select u.username , u.mdp from Utilisateur u where u.username = ? and u.mdp=? " ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, username);  
            createPreparedStatement.setString(2, mdp); 
            resultSet = createPreparedStatement.executeQuery();
            if (resultSet.next()) {
                p_username = resultSet.getString(1);
                p_mdp = resultSet.getString(2);
            }
            userWS.setUsername(p_username);
            userWS.setMdp(p_mdp);           
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("cnx reussit");
        Configuration.releaseRootApplicationModule(appModule, true);
        return userWS;
    }
    
}
