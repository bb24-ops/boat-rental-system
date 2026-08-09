/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.administratori;

import domen.Administrator;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class KreirajAdministratoraSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
       if (objekat == null || !(objekat instanceof Administrator)) {
            throw new Exception("Sistem ne moze da doda admina: neispravan objekat.");
        }

        Administrator a = (Administrator) objekat;

        // provera imena
        if (a.getIme() == null || a.getIme().trim().isEmpty() || !a.getIme().matches("[a-zA-Z]+")) {
            throw new Exception("Sistem ne moze da doda admina: ime nije ispravno.");
        }

        // provera prezimena
        if (a.getPrezime() == null || a.getPrezime().trim().isEmpty() || !a.getPrezime().matches("[a-zA-Z]+")) {
            throw new Exception("Sistem ne moze da doda admina: prezime nije ispravno.");
        }

        // minimalna dužina imena/prezimena
        if (a.getIme().length() < 2 || a.getPrezime().length() < 2) {
            throw new Exception("Sistem ne moze da doda admina: ime i prezime moraju imati bar 2 karaktera.");
        }

        // provera telefona (da sadrži samo cifre, opcionalno + na početku)
        if (a.getBrojTelefona()== null || !a.getBrojTelefona().matches("^[0-9]{6,15}$")) {
            throw new Exception("Sistem ne moze da doda admina: broj telefona nije ispravan.");
        }

        // provera username-a
        if (a.getUsername() == null || a.getUsername().trim().isEmpty()) {
            throw new Exception("Sistem ne moze da doda admina: username nije ispravan.");
        }

        // provera password-a: minimalna dužina + mora da sadrži slovo i broj
        if (a.getPassword() == null || a.getPassword().length() < 6 
                || !a.getPassword().matches(".*[A-Za-z].*") 
                || !a.getPassword().matches(".*[0-9].*")) {
            throw new Exception("Sistem ne moze da doda admina: password mora da ima bar 6 karaktera, slovo i broj.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Administrator)param);
    }
    
}
