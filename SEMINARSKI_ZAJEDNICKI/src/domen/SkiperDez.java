/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author boris
 */
public class SkiperDez implements ApstraktniDomenskiObjekat {

    private int idSkiperDez;
    private Administrator administrator;
    private Skiper skiper;
    private Date datumDezurstva;

    public SkiperDez() {
    }

    public SkiperDez(int idSkiperDez, Administrator administrator, Skiper skiper, Date datumDezurstva) {
        this.idSkiperDez = idSkiperDez;
        this.administrator = administrator;
        this.skiper = skiper;
        this.datumDezurstva = datumDezurstva;
    }

    public int getIdSkiperDez() {
        return idSkiperDez;
    }

    public void setIdSkiperDez(int idSkiperDez) {
        this.idSkiperDez = idSkiperDez;
    }

    public Skiper getSkiper() {
        return skiper;
    }

    public void setSkiper(Skiper skiper) {
        this.skiper = skiper;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Date getDatumDezurstva() {
        return datumDezurstva;
    }

    public void setDatumDezurstva(Date datumDezurstva) {
        this.datumDezurstva = datumDezurstva;
    }

    @Override
    public String toString() {
        return "Datum: " + datumDezurstva;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SkiperDez other = (SkiperDez) obj;
        if (!Objects.equals(this.administrator, other.administrator)) {
            return false;
        }
        if (!Objects.equals(this.skiper, other.skiper)) {
            return false;
        }
        return Objects.equals(this.datumDezurstva, other.datumDezurstva);
    }

    @Override
    public String vratiNazivTabele() {
        return "skiperdez";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            //OBAVENZNO ID!
            int idSkiperDez = rs.getInt("skiperdez.idSkiperDez");

            int idSkiper = rs.getInt("skiper.idSkiper");
            String ime = rs.getString("skiper.ime");
            int brojTerminaUkupno = rs.getInt("skiper.brojTerminaUkupno");
            String sertifikat = rs.getString("skiper.sertifikat");
            Skiper s = new Skiper(idSkiper, ime, brojTerminaUkupno, sertifikat);

            int idAdministrator = rs.getInt("administrator.idAdministrator");
            String imeAdmin = rs.getString("administrator.ime");
            String prezime = rs.getString("administrator.prezime");
            String brojTelefona = rs.getString("administrator.brojTelefona");
            String username = rs.getString("administrator.username");
            String password = rs.getString("administrator.password");
            Administrator a = new Administrator(idAdministrator, imeAdmin, prezime, brojTelefona, username, password);

            SkiperDez sd = new SkiperDez();
            sd.setIdSkiperDez(idSkiperDez);
            sd.setDatumDezurstva(rs.getDate("skiperdez.datumDezurstva"));
            sd.setAdministrator(a);
            sd.setSkiper(s);

            lista.add(sd);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "administrator,skiper,datumDezurstva";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        java.sql.Date sqlDatum = new java.sql.Date(datumDezurstva.getTime());
        return administrator.getIdAdministrator() + "," + skiper.getIdSkiper() + ",'" + sqlDatum + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "skiperdez.idSkiperDez=" + idSkiperDez;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        java.sql.Date sqlDatum = new java.sql.Date(datumDezurstva.getTime());
        return "administrator=" + administrator.getIdAdministrator() + ", skiper=" + skiper.getIdSkiper() + ", datumDezurstva='" + sqlDatum + "'";
    }

}
