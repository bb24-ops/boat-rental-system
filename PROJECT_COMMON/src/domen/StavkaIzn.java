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
public class StavkaIzn implements ApstraktniDomenskiObjekat {

    private int rbStavke;
    private int idiznajmljivanje;
    private Date datumIzdavanja;
    private Date datumPovratka;
    private int brojDana;
    private Brod brod;
    private double iznosJedneStavke;

    public StavkaIzn() {
    }

    public StavkaIzn(int rbStavke, Iznajmljivanje iznaiznajmljivanje, Date datumIzdavanja, Date datumPovratka, int brojDana, Brod brod, double iznosJedneStavke) {
        this.rbStavke = rbStavke;
        this.datumIzdavanja = datumIzdavanja;
        this.datumPovratka = datumPovratka;
        this.brojDana = brojDana;
        this.brod = brod;
        this.iznosJedneStavke = iznosJedneStavke;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

//    public Iznajmljivanje getIznajmljivanje() {
//        return iznajmljivanje;
//    }
//
//    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
//        this.iznajmljivanje = iznajmljivanje;
//    }
    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumPovratka() {
        return datumPovratka;
    }

    public void setDatumPovratka(Date datumPovratka) {
        this.datumPovratka = datumPovratka;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public Brod getBrod() {
        return brod;
    }

    public void setBrod(Brod brod) {
        this.brod = brod;
    }

    public double getIznosJedneStavke() {
        return iznosJedneStavke;
    }

    public void setIznosJedneStavke(double iznosJedneStavke) {
        this.iznosJedneStavke = iznosJedneStavke;
    }

    public int getIdiznajmljivanje() {
        return idiznajmljivanje;
    }

    public void setIdiznajmljivanje(int idiznajmljivanje) {
        this.idiznajmljivanje = idiznajmljivanje;
    }

    @Override
    public String toString() {
        return rbStavke + ". broj dana:" + brojDana + ", iznos stavke:" + iznosJedneStavke;
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
        final StavkaIzn other = (StavkaIzn) obj;
        if (this.brojDana != other.brojDana) {
            return false;
        }
        if (Double.doubleToLongBits(this.iznosJedneStavke) != Double.doubleToLongBits(other.iznosJedneStavke)) {
            return false;
        }
        return Objects.equals(this.brod, other.brod);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaizn";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            StavkaIzn stavka = new StavkaIzn();
            stavka.setRbStavke(rs.getInt("rbStavke"));
            //stavka.setIznajmljivanje(rs.getInt("iznajmljivanje"));
            stavka.setDatumIzdavanja(rs.getDate("datumIzdavanja"));
            stavka.setDatumPovratka(rs.getDate("datumPovratka"));
            stavka.setBrojDana(rs.getInt("brojDana"));
            stavka.setIznosJedneStavke(rs.getDouble("iznosJedneStavke"));
            stavka.setIdiznajmljivanje(rs.getInt("stavkaizn.iznajmljivanje"));

            Brod brod = new Brod();
            brod.setIdBrod(rs.getInt("IdBrod"));
            brod.setNaziv(rs.getString("naziv"));
            TipBroda t = TipBroda.valueOf(rs.getString("brod.tip"));
            brod.setTip(t);
            KategorijaBroda kb = KategorijaBroda.valueOf(rs.getString("brod.kategorija"));
            brod.setKategorija(kb);
            brod.setCenaPoDanu(rs.getDouble("cenaPoDanu"));

            stavka.setBrod(brod);
            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rbStavke,iznajmljivanje,datumIzdavanja,datumPovratka,brojDana,brod,iznosJedneStavke";
    }

    //OVDE MENJAJ STA TREBA DA IDE BEZ OVOG IZNAJMLJIVANJA!
    @Override
    public String vratiVrednostiZaUbacivanje() {
        java.sql.Date sqlDatumIzdavanja = new java.sql.Date(datumIzdavanja.getTime());
        java.sql.Date sqlDatumPovratka = new java.sql.Date(datumPovratka.getTime());
        return rbStavke + ", " + idiznajmljivanje + ",'" + sqlDatumIzdavanja + "','" + sqlDatumPovratka + "'," + brojDana + "," + brod.getIdBrod() + "," + iznosJedneStavke;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rbStavke=" + rbStavke + " AND iznajmljivanje=" + idiznajmljivanje;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        java.sql.Date sqlDatumIzdavanja = new java.sql.Date(datumIzdavanja.getTime());
        java.sql.Date sqlDatumPovratka = new java.sql.Date(datumPovratka.getTime());
        return "rbStavke=" + rbStavke
            + ", iznajmljivanje=" + idiznajmljivanje
            + ", datumIzdavanja='" + sqlDatumIzdavanja + "'"
            + ", datumPovratka='" + sqlDatumPovratka + "'"
            + ", brojDana=" + brojDana
            + ", brod=" + brod.getIdBrod()
            + ", iznosJedneStavke=" + iznosJedneStavke;
    }

}
