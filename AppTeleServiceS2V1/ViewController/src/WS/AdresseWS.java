package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.Data.ActiviteEntreprise;
import model.Data.Adresse;
import model.Data.CompteBancaire;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/adresseWS")
public class AdresseWS {
    
    public static final String tabAM = "model.AM.AdresseAM";
    public static final String tabAM_CONFIG = "AdresseAMLocal";
    
    @PUT
    @Path("/updateAdr/")
    @Produces("application/json")
    @Consumes("application/json")
    public Adresse updateAdr(Adresse adrWS ){
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.tabAM, this.tabAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update Adresse cb set numRue = ?, rue = ?, cp = ?, kStructureAdr = ? where kadresse = ? ",0);
        try {
            createPreparedStatement.setInt(1, adrWS.getNumRue());
            createPreparedStatement.setString(2, adrWS.getRue());
            createPreparedStatement.setString(3, adrWS.getCp());
            createPreparedStatement.setInt(4, adrWS.getKStructureAdr());
            createPreparedStatement.setInt(5, adrWS.getKadresse());
            System.out.println(adrWS.getKadresse() +"\n"+ adrWS.getNumRue() +"\n"+ adrWS.getRue() +"\n"+ adrWS.getCp() +"\n"+ adrWS.getKStructureAdr() );
            createPreparedStatement.executeUpdate();
            System.out.println(String.format("Row affected %d", createPreparedStatement.executeUpdate()));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return adrWS;
    }
    
    @GET
    @Path("/getAdrbyID/")
    @Produces("application/json")
    @Consumes("application/json")
    public Adresse getAdrbyID(@QueryParam("kadresse") int kadresse){
        Adresse adrWS = new Adresse();
        int p_id = 0 , p_numrue =0 , p_ksadr =0   ; 
        String p_rue = null , p_cp = null;
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.tabAM, this.tabAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"select kadresse,numRue,rue,cp,kStructureAdr from Adresse where kadresse = ? ",0);
        ResultSet resultSet = null;
        try {
        createPreparedStatement.setInt(1, kadresse);  
        resultSet = createPreparedStatement.executeQuery();
        if (resultSet.next()) {
            p_id = resultSet.getInt(1);
            p_numrue = resultSet.getInt(2);
            p_rue = resultSet.getString(3);
            p_cp = resultSet.getString(4);
            p_ksadr = resultSet.getInt(5);
        } 
        adrWS.setKadresse(p_id);
        adrWS.setNumRue(p_numrue);
        adrWS.setRue(p_rue);
        adrWS.setCp(p_cp);
        adrWS.setKStructureAdr(p_ksadr);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return adrWS;
    }
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/createAdr")
    public Adresse createAdr (Adresse AdrWS){
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.tabAM, this.tabAM_CONFIG);
        String req = " insert into Adresse (kadresse,numRue,rue,cp,kStructureAdr) values (AdresseSeq.NEXTVAL ,?,?,?,?)" ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setInt(1, AdrWS.getNumRue()); 
            createPreparedStatement.setString(2, AdrWS.getRue());
            createPreparedStatement.setString(3, AdrWS.getCp());
            createPreparedStatement.setInt(4, AdrWS.getKStructureAdr());
            createPreparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return AdrWS;
    }
    
    @DELETE
    @Path("/deleteAdrbyID/")
    @Produces("application/json")
    @Consumes("application/json")
    public Adresse deleteAdrbyID(@QueryParam("kadresse") int kadresse){
        Adresse adrWS = new Adresse();
        PreparedStatement createPreparedStatement = null;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.tabAM, this.tabAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"delete from Adresse where kadresse = ?;",0);
        ResultSet resultSet = null;
        try {
        createPreparedStatement.setInt(1, kadresse);  
        int result = createPreparedStatement.executeUpdate();
        System.out.println("Number of records affected :: " + result);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return adrWS;
    }
    
    @GET
    @Path("/AdresseInfoContribuableById/")
    @Produces("application/json")
    @Consumes("application/json")
    public List<Adresse> AdresseInfoContribuableById (@QueryParam("nif") String nif) {
        List<Adresse> ListAdrWS = new ArrayList<Adresse>();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.tabAM, this.tabAM_CONFIG);
        String req = " select adr.kadresse, adr.numrue , adr.rue , adr.cp , sadr.libellesadr , tsadr.libelletsadr\n" + 
        "from adresse adr , structureadr sadr , typestructureadr tsadr , Contribuable c\n" + 
        "where sadr.ktstructureadr = tsadr.ktstructureadr and adr.kstructureadr = sadr.kstructureadr and c.kcnc = adr.kcnc and c.nif = ? " ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, nif);  
            resultSet = createPreparedStatement.executeQuery();
            while (resultSet.next()) {  
                ListAdrWS.add(mapAdr(resultSet));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return ListAdrWS;
    }
    
    private static Adresse mapAdr(ResultSet resultSet) throws SQLException {
           Adresse user = new Adresse();
           user.setKadresse(resultSet.getInt("kadresse"));
           user.setNumRue(resultSet.getInt("numRue"));
           user.setRue(resultSet.getString("rue"));
           user.setCp(resultSet.getString("cp"));
           user.setLibellesadr(resultSet.getString("libellesadr"));
           user.setLibelletsadr(resultSet.getString("libelletsadr"));
           return user;
       }
    
}
