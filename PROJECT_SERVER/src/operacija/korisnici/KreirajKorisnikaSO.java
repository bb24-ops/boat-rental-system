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
public class KreirajKorisnikaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Korisnik)) {
            throw new Exception("Sistem ne može da kreira korisnika: neispravan objekat.");
        }

        Korisnik k = (Korisnik) objekat;

        // Validacija imena
        if (k.getIme() == null || k.getIme().trim().length() < 2) {
            throw new Exception("Ime korisnika mora imati barem 2 karaktera.");
        }

        // Validacija prezimena
        if (k.getPrezime() == null || k.getPrezime().trim().length() < 2) {
            throw new Exception("Prezime korisnika mora imati barem 2 karaktera.");
        }

        // Validacija broja telefona
        if (k.getBrojTelefona() == null || !k.getBrojTelefona().matches("^[0-9]+$")) {
            throw new Exception("Broj telefona mora sadržati samo cifre i ne sme biti prazan.");
        }

        // Validacija luke
        if (k.getLuka().getIdLuka() <= 0) {
            throw new Exception("Id luke mora biti veći od 0.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Korisnik)param);
    }
    
}
