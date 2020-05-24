package model.AM;

import java.sql.CallableStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jbo.JboException;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.DBTransaction;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Apr 28 14:50:55 CEST 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContribuableAMImpl extends ApplicationModuleImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ContribuableAMImpl() {
    }

    /**
     * Container's getter for ContribuableVO1.
     * @return ContribuableVO1
     */
    public ViewObjectImpl getContribuableVO1() {
        return (ViewObjectImpl) findViewObject("ContribuableVO1");
    }

    /**
     * Container's getter for AdresseVO1.
     * @return AdresseVO1
     */
    public ViewObjectImpl getAdresseVO1() {
        return (ViewObjectImpl) findViewObject("AdresseVO1");
    }

    /**
     * Container's getter for ContribuableVO2.
     * @return ContribuableVO2
     */
    public ViewObjectImpl getContribuableVO2() {
        return (ViewObjectImpl) findViewObject("ContribuableVO2");
    }

    /**
     * Container's getter for ComptebancaireVO1.
     * @return ComptebancaireVO1
     */
    public ViewObjectImpl getComptebancaireVO1() {
        return (ViewObjectImpl) findViewObject("ComptebancaireVO1");
    }

    /**
     * Container's getter for ContribuableVO3.
     * @return ContribuableVO3
     */
    public ViewObjectImpl getContribuableVO3() {
        return (ViewObjectImpl) findViewObject("ContribuableVO3");
    }

    /**
     * Container's getter for ActiviteentrepriseVO1.
     * @return ActiviteentrepriseVO1
     */
    public ViewObjectImpl getActiviteentrepriseVO1() {
        return (ViewObjectImpl) findViewObject("ActiviteentrepriseVO1");
    }

    /**
     * Container's getter for SysC0014181Link.
     * @return SysC0014181Link
     */
    public ViewLinkImpl getSysC0014181Link() {
        return (ViewLinkImpl) findViewLink("SysC0014181Link");
    }

    /**
     * Container's getter for SysC0014194Link.
     * @return SysC0014194Link
     */
    public ViewLinkImpl getSysC0014194Link() {
        return (ViewLinkImpl) findViewLink("SysC0014194Link");
    }

    /**
     * Container's getter for SysC0014177Link.
     * @return SysC0014177Link
     */
    public ViewLinkImpl getSysC0014177Link() {
        return (ViewLinkImpl) findViewLink("SysC0014177Link");
    }
    
    public boolean getAllContribuable (){
        PreparedStatement createPreparedStatement = getDBTransaction().createPreparedStatement(""+"select nomCommerciale,raisonSociale,registreCommerce from Contribuable", 0);
        Boolean result = Boolean.FALSE;
        ResultSet resultSet = null;
        try{
            resultSet = createPreparedStatement.executeQuery();
            if (resultSet.next()) {
             result = Boolean.TRUE;   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
}

