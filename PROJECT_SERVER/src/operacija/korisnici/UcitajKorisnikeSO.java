/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.korisnici;

import domen.Korisnik;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class UcitajKorisnikeSO extends ApstraktnaGenerickaOperacija{
    List<Korisnik> korisnici;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //nema preduslova za ucitavanje
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov  = " k JOIN Luka l ON k.luka = l.idLuka";
        korisnici = broker.getAll(new Korisnik(),uslov);
        //System.out.println("KLASA UcitajKorisnikeSO:" + korisnici);
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }
    
    
    
}
