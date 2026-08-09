/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.korisnici;

import domen.Korisnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class ObrisiKorisnikaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Korisnik)){
            throw new Exception("Sistem ne može da obriše korisnika: neispravan objekat.");
        }
        Korisnik k = (Korisnik) objekat;
        if (k.getIdKorisnik()<= 0) {
            throw new Exception("Sistem ne može da obriše korisnika: neispravan ID.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Korisnik)param);
    }
    
}
