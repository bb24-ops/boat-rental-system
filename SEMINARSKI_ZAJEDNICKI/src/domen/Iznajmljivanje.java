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
public class Iznajmljivanje implements ApstraktniDomenskiObjekat {

    private int idIznajmljivanje;
    private Date datum;
    private double ukupanIznos;
    private Administrator administrator;
    private Korisnik korisnik;
    private List<StavkaIzn> stavke;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, Date datum, double ukupanIznos, Administrator administrator, Korisnik korisnik, List<StavkaIzn> stavke) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.administrator = administrator;
        this.korisnik = korisnik;
        this.stavke = stavke;
    }

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<StavkaIzn> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIzn> stavke) {
        this.stavke = stavke;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanje=" + idIznajmljivanje + ", datum=" + datum + ", ukupanIznos=" + ukupanIznos + ", administrator=" + administrator + ", korisnik=" + korisnik + ", stavke=" + stavke + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        if (Double.doubleToLongBits(this.ukupanIznos) != Double.doubleToLongBits(other.ukupanIznos)) {
            return false;
        }
        if (!Objects.equals(this.administrator, other.administrator)) {
            return false;
        }
        return Objects.equals(this.korisnik, other.korisnik);
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
            iznajmljivanje.setIdIznajmljivanje(rs.getInt("i.idIznajmljivanje"));
            iznajmljivanje.setDatum(rs.getDate("i.datum"));
            iznajmljivanje.setUkupanIznos(rs.getDouble("i.ukupanIznos"));

            Administrator administrator = new Administrator();
            administrator.setIdAdministrator(rs.getInt("a.idAdministrator"));
            administrator.setIme(rs.getString("a.ime"));
            administrator.setPrezime(rs.getString("a.prezime"));

            Korisnik korisnik = new Korisnik();
            korisnik.setIdKorisnik(rs.getInt("k.idKorisnik"));
            korisnik.setIme(rs.getString("k.ime"));

            iznajmljivanje.setAdministrator(administrator);
            iznajmljivanje.setKorisnik(korisnik);

            iznajmljivanje.setStavke(new ArrayList<>());
            lista.add(iznajmljivanje);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ukupanIznos,datum,administrator,korisnik";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        return ukupanIznos + ",'" + sqlDatum + "'," + administrator.getIdAdministrator() + "," + korisnik.getIdKorisnik();//obrati paznju!
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje.idIznajmljivanje=" + idIznajmljivanje;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        return "ukupanIznos=" + ukupanIznos + ", datum='" + sqlDatum + "', administrator=" + administrator.getIdAdministrator() + ", korisnik=" + korisnik.getIdKorisnik();
    }

}
