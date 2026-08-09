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
public class Luka implements ApstraktniDomenskiObjekat {

    private int idLuka;
    private String naziv;
    private int brMesta;

    public Luka() {
    }

    public Luka(int idLuka, String naziv, int brMesta) {
        this.idLuka = idLuka;
        this.naziv = naziv;
        this.brMesta = brMesta;
    }

    public int getIdLuka() {
        return idLuka;
    }

    public void setIdLuka(int idLuka) {
        this.idLuka = idLuka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrMesta() {
        return brMesta;
    }

    public void setBrMesta(int brMesta) {
        this.brMesta = brMesta;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Luka other = (Luka) obj;
        return this.idLuka == other.idLuka;
    }

    

    @Override
    public String vratiNazivTabele() {
        return "luka";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            //OBAVENZNO ID!
            int idLuka = rs.getInt("luka.idLuka");
            String naziv = rs.getString("luka.naziv");
            int brMesta = rs.getInt("luka.brMesta");
            Luka l = new Luka(idLuka, naziv, brMesta);

            lista.add(l);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,brMesta";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "'," + brMesta;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "luka.idLuka=" + idLuka;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', brMesta=" + brMesta;
    }

}
