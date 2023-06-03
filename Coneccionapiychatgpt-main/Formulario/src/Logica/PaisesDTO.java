package Logica;

import Persistencia.PaisesDAO;

public class PaisesDTO {

    private String paiss;
    private String capitalfinal;
    private String monedaFinal;
    private String idiomafinal;
    private double ginis;

    public boolean guardar (String paiss, String capitalfinal, String monedaFinal, String idiomafinal, double ginis) {

        this.paiss = paiss;
        this.capitalfinal = capitalfinal;
        this.monedaFinal=monedaFinal;
        this.idiomafinal=idiomafinal;
        this.ginis=ginis;

        PaisesDAO paisesDAO = new PaisesDAO();
        boolean respuesta= paisesDAO.crear(this.paiss, this.capitalfinal, this.monedaFinal, this.idiomafinal, this.ginis);
        return respuesta;
    }

}
