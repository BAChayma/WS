package model.VO;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jun 11 14:06:09 CEST 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContribuableVORowImpl extends ViewRowImpl {
    public static final int ENTITY_CONTRIBUABLEEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Kcnc,
        Kformjuri,
        Nif,
        Nomcommerciale,
        Raisonsociale,
        Datedebexp,
        Capitalsociale,
        Registrecommerce,
        AdresseVO,
        ComptebancaireVO,
        ActiviteentrepriseVO,
        DeclarationVO,
        PaysVO,
        PersonneVO,
        ReclamationVO,
        ContribuableuserVO,
        ImpotcontribuableVO,
        AdresseVO1,
        ComptebancaireVO1,
        DeclarationVO1,
        ReclamationVO1,
        ImpotcontribuableVO1;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int KCNC = AttributesEnum.Kcnc.index();
    public static final int KFORMJURI = AttributesEnum.Kformjuri.index();
    public static final int NIF = AttributesEnum.Nif.index();
    public static final int NOMCOMMERCIALE = AttributesEnum.Nomcommerciale.index();
    public static final int RAISONSOCIALE = AttributesEnum.Raisonsociale.index();
    public static final int DATEDEBEXP = AttributesEnum.Datedebexp.index();
    public static final int CAPITALSOCIALE = AttributesEnum.Capitalsociale.index();
    public static final int REGISTRECOMMERCE = AttributesEnum.Registrecommerce.index();
    public static final int ADRESSEVO = AttributesEnum.AdresseVO.index();
    public static final int COMPTEBANCAIREVO = AttributesEnum.ComptebancaireVO.index();
    public static final int ACTIVITEENTREPRISEVO = AttributesEnum.ActiviteentrepriseVO.index();
    public static final int DECLARATIONVO = AttributesEnum.DeclarationVO.index();
    public static final int PAYSVO = AttributesEnum.PaysVO.index();
    public static final int PERSONNEVO = AttributesEnum.PersonneVO.index();
    public static final int RECLAMATIONVO = AttributesEnum.ReclamationVO.index();
    public static final int CONTRIBUABLEUSERVO = AttributesEnum.ContribuableuserVO.index();
    public static final int IMPOTCONTRIBUABLEVO = AttributesEnum.ImpotcontribuableVO.index();
    public static final int ADRESSEVO1 = AttributesEnum.AdresseVO1.index();
    public static final int COMPTEBANCAIREVO1 = AttributesEnum.ComptebancaireVO1.index();
    public static final int DECLARATIONVO1 = AttributesEnum.DeclarationVO1.index();
    public static final int RECLAMATIONVO1 = AttributesEnum.ReclamationVO1.index();
    public static final int IMPOTCONTRIBUABLEVO1 = AttributesEnum.ImpotcontribuableVO1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ContribuableVORowImpl() {
    }
}

