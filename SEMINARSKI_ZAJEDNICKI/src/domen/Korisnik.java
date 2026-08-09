/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author boris
 */
public class Korisnik implements ApstraktniDomenskiObjekat {

    private int idKorisnik;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private Luka luka;

    public Korisnik() {
    }

    public Korisnik(int idKorisnik, String ime, String prezime, String brojTelefona, Luka luka) {
        this.idKorisnik = idKorisnik;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.luka = luka;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Luka getLuka() {
        return luka;
    }

    public void setLuka(Luka luka) {
        this.luka = luka;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKorisnik);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Korisnik other = (Korisnik) obj;
        return this.idKorisnik == other.idKorisnik;
    }

    @Override
    public String vratiNazivTabele() {
        return "korisnik";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            //OBAVENZNO ID!
            int idKorisnik = rs.getInt("k.idKorisnik");

            String ime = rs.getString("k.ime");
            String prezime = rs.getString("k.prezime");
            String brojTelefona = rs.getString("k.brojTelefona");
            Luka luka = new Luka();
            luka.setIdLuka(rs.getInt("l.idLuka"));
            luka.setNaziv(rs.getString("l.naziv"));
            luka.setBrMesta(rs.getInt("l.brMesta"));

            Korisnik k = new Korisnik(idKorisnik, ime, prezime, brojTelefona, luka);

            lista.add(k);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,brojTelefona,luka";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + brojTelefona + "'," + luka.getIdLuka();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "korisnik.idKorisnik=" + idKorisnik;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', brojTelefona='" + brojTelefona + "', luka=" + luka.getIdLuka();
    }

}
