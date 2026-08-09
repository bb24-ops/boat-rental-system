/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.luka;

import domen.Luka;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class PromeniLukuSO extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Luka)) {
            throw new Exception("Sistem ne moze da kreira luku: neispravan objekat.");
        }

        Luka l = (Luka) objekat;

        if (l.getNaziv() == null || l.getNaziv().trim().isEmpty()) {
            throw new Exception("Sistem ne moze da kreira luku: naziv mora da sadrži samo slova.");
        }

        if (l.getBrMesta() <= 0) {
            throw new Exception("Sistem ne moze da kreira luku: broj mesta mora biti veći od 0.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
       Luka l = (Luka) param;
       broker.edit(l);
    }
}
