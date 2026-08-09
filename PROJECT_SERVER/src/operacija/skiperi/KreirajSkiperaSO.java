/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skiperi;

import domen.Skiper;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class KreirajSkiperaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Skiper)) {
            throw new Exception("Sistem ne moze da obrise skipera: neispravan objekat.");
        }

        Skiper s = (Skiper) objekat;

        if (s.getIme() == null || s.getIme().trim().isEmpty() || !s.getIme().matches("[a-zA-Z ]+")) {
            throw new Exception("Sistem ne moze da obrise skipera: ime nije validno.");
        }

        if (s.getSertifikat() == null || s.getSertifikat().trim().isEmpty()) {
            throw new Exception("Sistem ne moze da obrise skipera: sertifikat nije validan.");
        }

        if (s.getBrojTerminaUkupno() <= 0) {
            throw new Exception("Sistem ne moze da obrise skipera: broj termina mora biti veći od 0.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Skiper)param);
    }
    
}
